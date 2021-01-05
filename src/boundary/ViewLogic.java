package boundary;

import java.io.IOException;
import java.net.URL;

import control.CountControl;
import control.ResultsControl;
import control.VotersControl;
import control.VotingControl;
import entity.ActiveMember;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 * This class controls the window properties and navigation between the different windows
 *
 */

public class ViewLogic {


	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	protected static final Rectangle2D FULL_SCREEN = Screen.getPrimary().getBounds();
	protected static final Rectangle2D VISIBLE_SCREEN = Screen.getPrimary().getVisualBounds();
	
	protected static VotersControl votersControl;
	protected static VotingControl votingControl;
	protected static CountControl countControl;
	protected static ResultsControl resultsControl;
	
	protected static ActiveMember user;

	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
	
	/* -------------------- Start Window -------------------- */
	
	public static void initUI() {
		
		votersControl = VotersControl.getInstance();
		votingControl = VotingControl.getInstance();
		countControl = CountControl.getInstance();
		resultsControl = ResultsControl.getInstance();
		
		LoginWindow();
	}
	
	
	/* -------------------- Window Properties -------------------- */
	
	protected static void newWindow(URL fxmlLocation, Stage stage, Double prefWidth,
			Double prefHeight, Double minWidth, Double minHeight, Double maxWidth,
			Double maxHeight, boolean resizable, String title, boolean waitFor) {
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(fxmlLocation);
					Parent root = loader.load();
					Scene scene;

					if (prefWidth == null || prefHeight == null)
						scene = new Scene(root);
					else
						scene = new Scene(root, prefWidth, prefHeight);

					Image icon = new Image(getClass().getResourceAsStream("/images/login.jpg"));
					stage.getIcons().setAll(icon);
					
					stage.setScene(scene);

					if (minWidth != null)
						stage.setMinWidth(minWidth);
					if (minHeight != null)
						stage.setMinHeight(minHeight);
					if (maxWidth != null)
						stage.setMaxWidth(maxWidth);
					if (maxHeight != null)
						stage.setMaxHeight(maxHeight);

					stage.setResizable(resizable);

					if (title != null && !title.isEmpty() && !title.trim().isEmpty())
						stage.setTitle(title);

					if (waitFor)
						stage.initModality(Modality.APPLICATION_MODAL);

					stage.showAndWait();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/* -------------------- Login Screen -------------------- */

	
	protected static void LoginWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("LoginScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Login Screen",
				false);
	}
	
	
	/* -------------------- Menu Screen -------------------- */
	
	
	protected static void MenuWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("MainMenuScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Menu Screen",
				false);
	}
	
	
	protected static void MenuWindow2() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("MainMenuScreen2.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Menu Screen",
				false);
	}
	
	
	/* -------------------- Other Screens -------------------- */
	
	
	protected static void AddVoterWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("AddVoterScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"New Voter Screen",
				false);
	}
	
	
	protected static void AddRideWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("AddRideScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"New Ride Screen",
				false);
	}
	
	
	protected static void VoteCountWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("VoteCountScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Vote Count Screen",
				false);
	}
	
	
	protected static void ImportWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("ImportScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Import Info Screen",
				false);
	}
	
	
	protected static void VotersReportWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("MissingVotersReport.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Missing Voters Report Screen",
				false);
	}
	
	
	protected static void VotePercReportWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("VotingPercReport.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Vote Percentage Report Screen",
				false);
	}
	
	
	protected static void CountReportWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("LiveCountReport.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Count Report Screen",
				false);
	}
	
	
	protected static void ResultsReportWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("ResultsReport.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Final Results Report Screen",
				false);
	}
	
	
}
