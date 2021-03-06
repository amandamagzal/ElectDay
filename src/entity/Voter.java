package entity;

import java.sql.Date;

import util.E_Answer;
import util.E_Gender;
import util.E_Status;


/**
 * This class represents a voter object.
 *
 */

public class Voter {

	
	/* -------------------- Attributes -------------------- */
	
	private String voterID;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private E_Gender gender;
	private E_Status familyStatus;
	private String phoneNum;
	private String address;
	private E_Answer supportsParty;
	
	
	/* -------------------- Constructor -------------------- */
	
	public Voter(String voterID, String firstName, String lastName, Date dateOfBirth, E_Gender gender,
			E_Status familyStatus, String phoneNum, String address, E_Answer supportsParty) {
		this.voterID = voterID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.familyStatus = familyStatus;
		this.phoneNum = phoneNum;
		this.address = address;
		this.supportsParty = supportsParty;
	}
	
	
	public Voter(String voterID, String firstName, String lastName, Date dateOfBirth,
			String gender, String phoneNum) {
		this.voterID = voterID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = E_Gender.valueOf(gender);
		this.phoneNum = phoneNum;
	}

	public Voter(String voterID, String firstName, String lastName, Date dateOfBirth, String gender,
			String familyStatus, String phoneNum, String address) {
		this.voterID = voterID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = E_Gender.valueOf(gender);;
		this.familyStatus = E_Status.valueOf(familyStatus);
		this.phoneNum = phoneNum;
		this.address = address;
	
	}
	
	public Voter(String voterID) {
		this.voterID = voterID;
	}

	/* -------------------- Getters & Setters -------------------- */

	public String getVoterID() {
		return voterID;
	}

	
	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender.toString();
	}


	public void setGender(String gender) {
		this.gender = E_Gender.valueOf(gender);
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getFamilyStatus() {
		return familyStatus.toString();
	}


	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = E_Status.valueOf(familyStatus);
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}	
	
	
	public E_Answer getSupportsParty() {
		return supportsParty;
	}


	public void setSupportsParty(E_Answer supportsParty) {
		this.supportsParty = supportsParty;
	}	
	
	
	/* -------------------- To String -------------------- */

	@Override
	public String toString() {
		return "Voter [voterID=" + voterID + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", dateOfBirth=" + dateOfBirth + ", familyStatus=" + familyStatus + ", address=" + address
				+ ", phoneNum=" + phoneNum + "]";
	}

	
	/* -------------------- Equals -------------------- */
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voter other = (Voter) obj;
		if (voterID != other.voterID)
			return false;
		return true;
	}
	
	
	/* -------------------- Hashing -------------------- */
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((voterID == null) ? 0 : voterID.hashCode());
		return result;
	}
	
	
	
}
