package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import entity.ActiveMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * This class represent the menu screen used to navigate
 *
 */

public class MenuScreen implements Initializable{

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	@FXML private AnchorPane mainPane;

    @FXML private JFXButton btn_voter;
    @FXML private JFXButton btn_ride;
    @FXML private JFXButton btn_vote;    
    @FXML private JFXButton btn_import;
    @FXML private JFXButton btn_votersReport;
    @FXML private JFXButton btn_voteReport;
    @FXML private JFXButton btn_countReport;
    @FXML private JFXButton btn_resultsReport;

    @FXML private JFXButton btn_logout;
	private double x , y;
	
	ActiveMember user;

	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
		
	@FXML
	void close() {
		Stage stage = (Stage) btn_logout.getScene().getWindow();
		stage.close();
	}

	
	@FXML
	void minimize(MouseEvent event) {
		Stage stage = (Stage) btn_logout.getScene().getWindow();
		stage.setIconified(true);
	}

	
	@FXML
	void mousepressed(MouseEvent event) {
		x = event.getSceneX();
		y = event.getSceneY();
	}

	
	@FXML
	void dragged(MouseEvent event) {
		Stage stage = (Stage) btn_logout.getScene().getWindow();;
		stage.setX(event.getScreenX() - x);
		stage.setY(event.getScreenY() - y);
	}
	

	/**
	 * Opens the window according to the pressed button
	 * @param event
	 * @throws IOException
	 */
	
	@FXML
	void menu(ActionEvent event) throws IOException {

		if (event.getSource() == btn_voter) {
			close();
			ViewLogic.AddVoterWindow();
		}
		

		if (event.getSource() == btn_ride) {
			close();
			ViewLogic.AddRideWindow();
		}
		
		
		if (event.getSource() == btn_vote) {
			close();
			ViewLogic.VoteCountWindow();
		}
		
		
		if (event.getSource() == btn_import) {
			close();
			ViewLogic.ImportWindow();
		}
		
		
		if (event.getSource() == btn_votersReport) {
			close();
			ViewLogic.VotersReportWindow();
		}
		
		
		if (event.getSource() == btn_voteReport) {
			close();
			ViewLogic.VotePercReportWindow();
		}
		

		if (event.getSource() == btn_countReport) {
			close();
			ViewLogic.CountReportWindow();
		}
		
		
		if (event.getSource() == btn_resultsReport) {
			close();
			ViewLogic.ResultsReportWindow();
		}
		

		if (event.getSource() == btn_logout) {
			close();
			ViewLogic.LoginWindow();
		}
	}
	
	
	
	/* ------------------------------------------------------------------ */
	/* ------------------------- Initialization ------------------------- */
	/* ------------------------------------------------------------------ */
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		user = ViewLogic.user;
		
	}

	
}
