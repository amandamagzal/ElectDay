package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import control.CountControl;
import entity.ActiveMember;
import entity.BallotBox;
import entity.Party;
import entity.Vote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * This class represent the screen used to add a vote's information
 * An active member (observer) adds the vote's information
 *
 */

public class VoteCountScreen implements Initializable{

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	@FXML private JFXButton btn_menu;
    @FXML private JFXComboBox<String> combo_ballotBox;
    
    @FXML private Pane pane_counting;
    @FXML private JFXCheckBox check_countDone;
    @FXML private JFXButton btn_new;
    
    @FXML private Pane pane_newVote;
    @FXML private JFXTextField voteNum;
    @FXML private JFXComboBox<Party> combo_party;
    @FXML private JFXCheckBox check_valid;
    @FXML private JFXButton btn_save;
    @FXML private JFXButton btn_cancel;
    
    ActiveMember user;
	
	
    
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
    
    /**
     * Sets the combo box with the ballot IDs
     */
    
	private void setBallotCombo() {
		ArrayList<String> ballots = new ArrayList<String>();
		for (BallotBox b : ViewLogic.countControl.getBallots()) {
			ballots.add(b.toString());
		}
		combo_ballotBox.getItems().setAll(ballots);
		combo_ballotBox.getSelectionModel().select(0);
	}
	
	
	/**
	 * Sets the combo box with the party IDs
	 */
	
	private void setPartyCombo() {
		combo_party.getItems().setAll(ViewLogic.countControl.getParties());
		combo_party.getSelectionModel().select(0);
	}
	
	
	/**
	 * Makes the ballot information pane visible when a ballot is selected
	 * @param event
	 */
	
	@FXML
	void showBallotPane(ActionEvent event) {
		if (combo_ballotBox.getValue() != null)
			pane_counting.setVisible(true);
	}
	
	
	/**
	 * Sets the vote's number
	 */
	
	private void setVoteNum() {
		Integer num = new Integer(ViewLogic.countControl.getLastVoteNum() + 1);
		voteNum.setText(num.toString());
	}
	
	
	/**
	 * Makes the vote information pane visible when the "new" button is pressed
	 * @param event
	 */
	
	@FXML
	void showNewVote(ActionEvent event) {
		if (event.getSource() == btn_new)
			pane_newVote.setVisible(true);
	}
	
	
	/**
	 * Saves a vote's information when the "save" button is pressed
	 * @param event
	 */
	
	@FXML
	void saveClick(ActionEvent event) {
		
		Integer voteNum = new Integer(ViewLogic.countControl.getLastVoteNum() + 1);
		String party = combo_party.getValue().toString();
		boolean valid = check_valid.isSelected();
		String ballotID = combo_ballotBox.getValue();
		
		if(party != null && ballotID != null) {
			
			Vote v = new Vote(voteNum.toString(), party, valid, ballotID);
			if(CountControl.getInstance().addVote(v)) {
				// show the success message
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Add Vote");
				alert.setHeaderText(null);
				alert.setContentText("The vote was successfully added!");
				alert.showAndWait();
			}
			else { // show the error message
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Failed to add vote!");
				alert.showAndWait();
			}
		}
		else if(ballotID == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(combo_ballotBox.getScene().getWindow());
			alert.setTitle("Add Vote Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a ballot!");
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(combo_party.getScene().getWindow());
			alert.setTitle("Add Vote Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a party!");
			alert.showAndWait();
		}
		
	}
	
	
	/**
	 * Hides the vote information pane when the "cancel" button is pressed
	 * @param event
	 */
	
	@FXML
	void cancelClick(ActionEvent event) {
	
		pane_newVote.setVisible(false);
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
		
		// hide pane
		pane_counting.setVisible(false);
		pane_newVote.setVisible(false);
		
		// set combo boxes
		setBallotCombo();
		setPartyCombo();
		
		// set vote number
		setVoteNum();
		
	}
	
	
}
