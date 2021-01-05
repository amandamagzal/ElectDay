package entity;

import java.sql.Date;

import util.E_Answer;
import util.E_Gender;
import util.E_Status;


/**
 * This class represents an active member object.
 *
 */

public class ActiveMember extends Voter {

	
	/* -------------------- Attributes -------------------- */
	
	private String password;
	
	
	/* -------------------- Constructor -------------------- */

	public ActiveMember(String voterID, String firstName, String lastName, Date dateOfBirth, E_Gender gender,
			E_Status familyStatus, String phoneNum, String address, E_Answer supportsParty, String password) {
		super(voterID, firstName, lastName, dateOfBirth, gender, familyStatus, phoneNum, address, supportsParty);
		this.password = password;
	}	
	
	public ActiveMember(String voterID, String password) {
		super(voterID);
		this.password = password;
	}

	
	/* -------------------- Getters & Setters -------------------- */

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
	/* -------------------- To String -------------------- */

	@Override
	public String toString() {
		return "";
	}

	
}
