package entity;

import java.sql.Time;

import util.E_Role;


/**
 * This class represents an assignment object of an active member to a role.
 *
 */

public class AssignedTo {

	
	/* -------------------- Attributes -------------------- */
	
	private String memberID;
	private E_Role role;
	private Time fromTime;
	private Time toTime;
	private String ballotID;
	
		
	/* -------------------- Constructor -------------------- */
	
	public AssignedTo(String memberID, E_Role role, Time fromTime, Time toTime, String ballotID) {
		this.memberID = memberID;
		this.role = role;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.ballotID = ballotID;
	}
	
	
	public AssignedTo(String memberID, String role, String fromTime, String toTime) {
		this.memberID = memberID;
		this.role = E_Role.valueOf(role);
		this.fromTime = Time.valueOf(fromTime);
		this.toTime = Time.valueOf(toTime);
		
	}

	
	/* -------------------- Getters & Setters -------------------- */

	public String getMemberID() {
		return memberID;
	}


	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}


	public E_Role getRole() {
		return role;
	}


	public void setRole(E_Role role) {
		this.role = role;
	}


	public Time getFromTime() {
		return fromTime;
	}


	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}


	public Time getToTime() {
		return toTime;
	}


	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}


	public String getBallotID() {
		return ballotID;
	}


	public void setBallotID(String ballotID) {
		this.ballotID = ballotID;
	}

	
	/* -------------------- To String -------------------- */

	@Override
	public String toString() {
		return "AssignedTo [memberID=" + memberID + ", role=" + role + ", fromTime=" + fromTime + ", toTime=" + toTime
				+ ", ballotID=" + ballotID + "]";
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
		AssignedTo other = (AssignedTo) obj;
		if (ballotID == null) {
			if (other.ballotID != null)
				return false;
		} else if (!ballotID.equals(other.ballotID))
			return false;
		if (fromTime == null) {
			if (other.fromTime != null)
				return false;
		} else if (!fromTime.equals(other.fromTime))
			return false;
		if (memberID == null) {
			if (other.memberID != null)
				return false;
		} else if (!memberID.equals(other.memberID))
			return false;
		if (role != other.role)
			return false;
		if (toTime == null) {
			if (other.toTime != null)
				return false;
		} else if (!toTime.equals(other.toTime))
			return false;
		return true;
	}
	
	
	/* -------------------- Hashing -------------------- */
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberID == null) ? 0 : memberID.hashCode());
		return result;
	}
	
	
}
