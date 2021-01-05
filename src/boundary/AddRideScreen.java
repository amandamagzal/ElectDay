package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import util.E_Role;


/**
 * This class represent the screen used to add a ride's information
 * An active member (driver) adds the pickup and return time of a ride
 *
 */

public class AddRideScreen implements Initializable{

	
	
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
    
    @FXML private Pane pane_newRide;
    @FXML private JFXButton btn_save;
    @FXML private JFXButton btn_cancel;
    @FXML private JFXTextField driverID;
    @FXML private JFXTimePicker picker_pickupTime;
    @FXML private JFXTimePicker picker_returnTime;
    
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
     * Makes the ride information pane visible when the "add" or "edit" buttons are pressed
     * @param event
     */
	
	@FXML
	void showAddRide(ActionEvent event) {
		if (event.getSource() == btn_add || event.getSource() == btn_edit)
			if (selectedVoter != null) {
				pane_newRide.setVisible(true);
			}
			else{ 
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.initOwner(tbl_Voters.getScene().getWindow());
				alert.setTitle("New Ride Error");
				alert.setHeaderText(null);
				alert.setContentText("You did not select a voter!");
				alert.showAndWait();
			}
			
	}
	
	
	/**
	 * Saves a ride's information when the "save" button is pressed
	 * @param event
	 */
	
	@FXML
	void saveClick(ActionEvent event) {
		
		if(selectedVoter != null) {
			
			String voter = selectedVoter.getVoterID();
			String driver = driverID.getText();
			Time pickupTime = Time.valueOf(picker_pickupTime.getValue());
			Time returnTime = Time.valueOf(picker_returnTime.getValue());

			if(isInputValid(driver, pickupTime, returnTime)) {
				
				if(VotersControl.getInstance().addRide(driver, pickupTime, returnTime, voter)) {
					// show the success message
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Add Ride");
					alert.setHeaderText(null);
					alert.setContentText("The ride was successfully added!");
					alert.showAndWait();
					pane_newRide.setVisible(false);
				}
				else { // show the error message
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Failed to add ride!");
					alert.showAndWait();
				}
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
	
	
	/**
	 * Hides the ride's information pane when the "cancel" button is pressed
	 * @param event
	 */
	
	@FXML
	void cancelClick(ActionEvent event) {
		pane_newRide.setVisible(false);
		driverID.clear();
	}
	
	
	/**
	 * Deletes the ride info when the "delete" button is pressed
	 * @param event
	 */
	
	@FXML
	void delete(ActionEvent event) {
		
		if (selectedVoter != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.initOwner(tbl_Voters.getScene().getWindow());
			alert.setTitle("Delete Ride");
			String id = selectedVoter.getVoterID();
			alert.setHeaderText(id);
			alert.setContentText("Are you sure you want to delete this voter's ride info?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if (VotersControl.getInstance().removeRide(selectedVoter.getVoterID())){
					// show the success message
					Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
					alert2.setTitle("Delete Ride");
					alert2.setHeaderText(null);
					alert2.setContentText("The voter's ride info was successfully deleted!");
					alert2.showAndWait();
				}
				else { // show the error message
					alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Failed to delete ride info!");
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
	
	
	
	
	@SuppressWarnings("unlikely-arg-type")
	private boolean isInputValid(String driverID, Time pickupTime, Time returnTime) {
		String errorMessage = "";
		
		if (driverID == null || driverID.length() != 9)
			errorMessage += "Invalid driver ID!\n";
		if (pickupTime == null)
			errorMessage += "No Pickup Time selected!\n";
		if (returnTime == null)
			errorMessage += "No Return Time selected!\n";
		
		if (errorMessage.length() == 0)
			return true;		
		else { // Show the error message
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(btn_save.getScene().getWindow());
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct the invalid fields.");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
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
		pane_newRide.setVisible(false);
		
	}
	
	
}
