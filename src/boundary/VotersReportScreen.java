package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import entity.ActiveMember;
import entity.Branch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * This class represents the screen used to produce the missing voters report
 *
 */

public class VotersReportScreen implements Initializable{

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	@FXML private JFXComboBox<Branch> combo_branch;
	@FXML private JFXButton btn_produceReport;
	@FXML private JFXButton btn_menu;
	
	ActiveMember user;

	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
	
	/**
	 * Sets the combo box with the branch IDs
	 */
	
	private void setBranchCombo() {
		combo_branch.getItems().setAll(ViewLogic.votingControl.getBranches());
		combo_branch.getSelectionModel().select(0);
	}
	
	
	/**
	 * Produces the missing voters report
	 */
	
	@FXML
	private void produceReport() {

		Branch branch = combo_branch.getValue();
		
		if (branch == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Branch Number Error");
			alert.setContentText("Please select branch.");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}
		else {
			JFrame reportFrame = ViewLogic.votingControl.produceVotersReport(branch.toString());
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
		
		// sets the combo box
		setBranchCombo();
		
	}
	
	
}
