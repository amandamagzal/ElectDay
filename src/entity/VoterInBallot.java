package entity;

import java.sql.Time;


/**
 * This class represents an assignment of a voter to a ballot box.
 *
 */

public class VoterInBallot {

	
	/* -------------------- Attributes -------------------- */
	
	private String voterID;
	private String ballotID;
	private int serialNum;
	private Time votingTime;
	
	
	/* -------------------- Constructor -------------------- */
	
	public VoterInBallot(String voterID, String ballotID, int serialNum, Time votingTime) {
		this.voterID = voterID;
		this.ballotID = ballotID;
		this.serialNum = serialNum;
		this.votingTime = votingTime;
	}

	
	/* -------------------- Getters & Setters -------------------- */

	public String getVoterID() {
		return voterID;
	}


	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}


	public String getBallotID() {
		return ballotID;
	}


	public void setBallotID(String ballotID) {
		this.ballotID = ballotID;
	}


	public int getSerialNum() {
		return serialNum;
	}


	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}


	public Time getVotingTime() {
		return votingTime;
	}


	public void setVotingTime(Time votingTime) {
		this.votingTime = votingTime;
	}

	
	/* -------------------- To String -------------------- */

	@Override
	public String toString() {
		return "VoterInBallot [voterID=" + voterID + ", ballotID=" + ballotID + ", serialNum=" + serialNum
				+ ", votingTime=" + votingTime + "]";
	}

	
}
