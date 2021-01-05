package boundary;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import com.jfoenix.controls.JFXButton;

import control.ImportControl;
import entity.AssignedTo;
import entity.BallotBox;
import entity.City;
import entity.Party;
import entity.Voter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import util.Consts.Manipulation;


/**
 * This class represent the screen used to import information
 *
 */

public class ImportScreen implements Initializable{


	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	@FXML private JFXButton btn_menu;
	@FXML private JFXButton btn_voters;
	@FXML private JFXButton btn_members;
	@FXML private JFXButton btn_ballots;
	@FXML private JFXButton btn_elections;
	@FXML private JFXButton btn_parties;
	
	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
	
	// TODO import methods
	
	/**
	 * Imports the voters info
	 */
	
	@FXML
	public void importVoters(ActionEvent event) {

    	try (FileReader reader = new FileReader(new File("json/voters.json"))) {
    		JsonObject doc = (JsonObject) Jsoner.deserialize(reader);
    		JsonArray voters = (JsonArray) doc.get("Voters_info");
    		Iterator<Object> iterator = voters.iterator();
    		int errors = 0;
    		while (iterator.hasNext()) {
    			JsonObject obj = (JsonObject) iterator.next();
    			Voter v = new Voter((String) obj.get("VoterID"),
    					(String) obj.get("FirstName"), 
    					(String) obj.get("LastName"),
    					 (Date) obj.get("BirthDate"), 
    					(String) obj.get("Gender"),
    					(String) obj.get("Status"), 
    					(String) obj.get("PhoneNum"),
    					(String) obj.get("Address"));
    			if (!ImportControl.manipulateVoter(v, Manipulation.INSERT) && 
						!ImportControl.manipulateVoter(v, Manipulation.UPDATE))
					errors++;
    		}
    		
			System.out.println((errors == 0) ? "Voters data imported successfully!" : 
				String.format("Voters data imported with %d errors!", errors));
    	} catch (IOException | DeserializationException e) {
    		e.printStackTrace();
    	}
    }

	
	/**
	 * Imports the members info
	 */
	
	public void importMembers() {
		

    	try (FileReader reader = new FileReader(new File("json/Position.json"))) {
    		JsonObject doc = (JsonObject) Jsoner.deserialize(reader);
    		JsonArray positions = (JsonArray) doc.get("Position_info");
    		Iterator<Object> iterator = positions.iterator();
    		int errors = 0;
    		while (iterator.hasNext()) {
    			JsonObject obj = (JsonObject) iterator.next();
    			AssignedTo p = new AssignedTo((String) obj.get("Member ID"),
    					(String) obj.get("Role"), 
    					(String) obj.get("Start Time"),
    					 (String) obj.get("Finish Time"));
    			if (!ImportControl.manipulatePosition(p, Manipulation.INSERT) && 
						!ImportControl.manipulatePosition(p, Manipulation.UPDATE))
					errors++;
    		}
    		
			System.out.println((errors == 0) ? "Members data imported successfully!" : 
				String.format("Members data imported with %d errors!", errors));
    	} catch (IOException | DeserializationException e) {
    		e.printStackTrace();
    	}
		

	}
	
	
	/**
	 * Imports the ballots info
	 */

	@FXML
	public void importBallots(ActionEvent event) {

    	try (FileReader reader = new FileReader(new File("json/ballots.json"))) {
    		JsonObject doc = (JsonObject) Jsoner.deserialize(reader);
    		JsonArray ballots = (JsonArray) doc.get("Voters_info");
    		Iterator<Object> iterator = ballots.iterator();
    		int errors = 0;
    		while (iterator.hasNext()) {
    			JsonObject obj = (JsonObject) iterator.next();
    			BallotBox b = new BallotBox((String) obj.get("BallotID"),
    					(String) obj.get("Branch ID")); 
    			if (!ImportControl.manipulateBallot(b, Manipulation.INSERT) && 
						!ImportControl.manipulateBallot(b, Manipulation.UPDATE))
					errors++;
    		}
    		
			System.out.println((errors == 0) ? "Ballots data imported successfully!" : 
				String.format("Ballots data imported with %d errors!", errors));
    	} catch (IOException | DeserializationException e) {
    		e.printStackTrace();
    	}
		
	}
	
	
	/**
	 * Imports the previous elections info
	 */
	
	@FXML
	public void importElections(ActionEvent event) {
		
		try (FileReader reader = new FileReader(new File("json/elections.json"))) {
    		JsonObject doc = (JsonObject) Jsoner.deserialize(reader);
    		JsonArray cities  = (JsonArray) doc.get("Election_info");
    		Iterator<Object> iterator = cities.iterator();
    		int errors = 0;
    		while (iterator.hasNext()) {
    			JsonObject obj = (JsonObject) iterator.next();
    			City c = new City((String) obj.get("CityID"),
    					(String) obj.get("CityName"), 
    					(long) obj.get("Election1%"),
    					(long) obj.get("Election2%"),
    					(long) obj.get("Election3%"));
    			if (!ImportControl.manipulateCity(c, Manipulation.INSERT) && 
						!ImportControl.manipulateCity(c, Manipulation.UPDATE))
					errors++;
    		}
    		
			System.out.println((errors == 0) ? "Elections data imported successfully!" : 
				String.format("Elections data imported with %d errors!", errors));
    	} catch (IOException | DeserializationException e) {
    		e.printStackTrace();
    	}
	}
	
	
	/**
	 * Imports the parties info
	 */
	
	@FXML
	public void importParties(ActionEvent event) {
		
		try (FileReader reader = new FileReader(new File("json/parties.json"))) {
    		JsonObject doc = (JsonObject) Jsoner.deserialize(reader);
    		JsonArray parties = (JsonArray) doc.get("Parties_info");
    		Iterator<Object> iterator = parties.iterator();
    		int errors = 0;
    		while (iterator.hasNext()) {
    			JsonObject obj = (JsonObject) iterator.next();
    			Party p = new Party((String) obj.get("PartyID"),
    					(String) obj.get("PartyName"), 
    					(String) obj.get("Image"));
    			if (!ImportControl.manipulateParty(p, Manipulation.INSERT) && 
						!ImportControl.manipulateParty(p, Manipulation.UPDATE))
					errors++;
    		}
    		
			System.out.println((errors == 0) ? "Parties data imported successfully!" : 
				String.format("Parties data imported with %d errors!", errors));
    	} catch (IOException | DeserializationException e) {
    		e.printStackTrace();
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
		
	}
	
		
}
