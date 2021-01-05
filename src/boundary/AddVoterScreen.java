package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;

import control.VotersControl;
import entity.ActiveMember;
import entity.Voter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.E_Gender;


/**
 * This class represents the screen used to add a voter's voting time
 * An active member (observer) adds the voting time of a voter
 *
 */

public class AddVoterScreen implements Initializable{

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	@FXML private JFXButton btn_menu;

	@FXML private TableView<Voter> tbl_Voters;
    @FXML private TableColumn<Voter, String> col_voterID;
    @FXML private TableColumn<Voter, String> col_firstName;
    @FXML private TableColumn<Voter, String> col_lastName;
    @FXML private TableColumn<Voter, Date> col_birthDate;
    @FXML private TableColumn<Voter, E_Gender> col_gender;
    @FXML private TableColumn<Voter, String> col_phoneNum;

    @FXML private JFXButton btn_add;
    @FXML private JFXButton btn_edit;
    @FXML private JFXButton btn_delete;
    
    @FXML private Pane pane_newVoter;
    @FXML private JFXButton btn_save;
    @FXML private JFXButton btn_cancel;
    @FXML private JFXTimePicker picker_votingTime;
    
    static Voter selectedVoter;
	
    ActiveMember user;
    
    
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
    
    /**
     * Sets the voters table
     */
    
    protected void setVotersTable() {
		ObservableList<Voter> voters = FXCollections.observableArrayList();
		voters.setAll(ViewLogic.votersControl.getVoters());
		tbl_Voters.setItems(voters);
		tbl_Voters.refresh();
	}
    
    
    /**
     * Saves the selected voter's information
     * @param event
     */
    
	public void voterSelected(MouseEvent event) {
		if(tbl_Voters.getSelectionModel().getSelectedItem() instanceof Voter) {
			selectedVoter = tbl_Voters.getSelectionModel().getSelectedItem();
		}				
	}
	
	
	/**
     * Makes the voting information pane visible when the "add" or "edit" buttons are pressed
     * @param event
     */
	
	@FXML
	void showAddVoter(ActionEvent event) {
		if (event.getSource() == btn_add || event.getSource() == btn_edit)
			if (selectedVoter != null) {
				pane_newVoter.setVisible(true);
			}
			else{ 
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.initOwner(tbl_Voters.getScene().getWindow());
				alert.setTitle("New Voting Time Error");
				alert.setHeaderText(null);
				alert.setContentText("You did not select a voter!");
				alert.showAndWait();
			}
			
	}
	
	
	/**
	 * Saves a voter's voting time when the "save" button is pressed
	 * @param event
	 */
	
	@FXML
	void saveClick(ActionEvent event) {
		
		String voter = selectedVoter.getVoterID();
		Time votingTime = Time.valueOf(picker_votingTime.getValue());

		if(VotersControl.getInstance().addVotingTime(votingTime, voter)) {
			// show the success message
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Add Voting Time");
			alert.setHeaderText(null);
			alert.setContentText("The voting time was successfully added!");
			alert.showAndWait();
			pane_newVoter.setVisible(false);
		}
		else { // show the error message
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Failed to add voting time!");
			alert.showAndWait();
		}
		
	}
	
	
	/**
	 * Hides the voting information pane when the "cancel" button is pressed
	 * @param event
	 */
	
	@FXML
	void cancelClick(ActionEvent event) {
		pane_newVoter.setVisible(false);	
	}
	
	
	/**
	 * Deletes the voting time when the "delete" button is pressed
	 * @param event
	 */
	
	@FXML
	void delete(ActionEvent event) {
		
		if (selectedVoter != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.initOwner(tbl_Voters.getScene().getWindow());
			alert.setTitle("Delete Voting Time");
			String id = selectedVoter.getVoterID();
			alert.setHeaderText(id);
			alert.setContentText("Are you sure you want to delete this voter's voting time?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if (VotersControl.getInstance().removeVotingTime(selectedVoter.getVoterID())){
					// show the success message
					Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
					alert2.setTitle("Delete Voting Time");
					alert2.setHeaderText(null);
					alert2.setContentText("The voter's voting time was successfully deleted!");
					alert2.showAndWait();
				}
				else { // show the error message
					alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Failed to delete voting time!");
					alert.showAndWait();
				}	
			}
			else {
				alert.close();
			}
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(tbl_Voters.getScene().getWindow());
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a voter!");
			alert.showAndWait();
		}
	}
	
	
	/* ------------------------- Back To Main Menu ------------------------- */
	
	@FXML
	void close() {
		Stage stage = (Stage) btn_menu.getScene().getWindow();
		stage.close();
	}
	
	
	@FXML
	void back(ActionEvent event) throws IOException {
		close();
		
		if (user.getPassword().equals("BranchM") || user.getPassword().equals("Member"))
			ViewLogic.MenuWindow2();
		else
			ViewLogic.MenuWindow();
	}	
	
	
	
	/* ------------------------------------------------------------------ */
	/* ------------------------- Initialization ------------------------- */
	/* ------------------------------------------------------------------ */
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		user = ViewLogic.user;
		
		/* ------------------------- Voters Table ------------------------- */

		col_voterID.setCellValueFactory(new PropertyValueFactory<>("voterID")); 
		col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		col_birthDate.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));	
		tbl_Voters.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		setVotersTable();
		
		
		// hide pane
		pane_newVoter.setVisible(false);
		
	}
	
	
}
