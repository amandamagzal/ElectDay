package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import com.jfoenix.controls.JFXButton;

import entity.ActiveMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


/**
 * This class represents the screen used to produce the voting percentage report
 *
 */

public class VotingPercReportScreen implements Initializable{

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	ToggleGroup params = new ToggleGroup();
	@FXML private RadioButton btn_age;
	@FXML private RadioButton btn_gender;
	@FXML private RadioButton btn_status;
	@FXML private JFXButton btn_produceReport;
	@FXML private JFXButton btn_menu;
	
	ActiveMember user;

	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
	
	/**
	 * Produces the voting percentage report
	 */
	
	@FXML
	private void produceReport() {

		if (btn_age.isSelected()) {
			JFrame reportFrame = ViewLogic.votingControl.produceAgePercReport();
			reportFrame.setVisible(true);
		}
		else if (btn_gender.isSelected()) {
			JFrame reportFrame = ViewLogic.votingControl.produceGenderPercReport();
			reportFrame.setVisible(true);
		}
		else if (btn_status.isSelected()) {
			JFrame reportFrame = ViewLogic.votingControl.produceStatusPercReport();
			reportFrame.setVisible(true);
		}
		else {
			JFrame reportFrame = ViewLogic.votingControl.produceVotingPercReport();
			reportFrame.setVisible(true);
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
		ViewLogic.MenuWindow();	
	}
	
	
	
	/* ------------------------------------------------------------------ */
	/* ------------------------- Initialization ------------------------- */
	/* ------------------------------------------------------------------ */
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		user = ViewLogic.user;
		
		// assigns radio buttons to a toggle group
		btn_age.setToggleGroup(params);
		btn_gender.setToggleGroup(params);
		btn_status.setToggleGroup(params);
		
	}
	
	
}
