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
import javafx.stage.Stage;


/**
 * This class represents the screen used to produce the final results report
 *
 */

public class ResultsReportScreen implements Initializable{

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
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
		JFrame reportFrame = ViewLogic.resultsControl.produceResultsReport();
		reportFrame.setVisible(true);
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
		
	}
	
	
}
