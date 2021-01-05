package entity;


/**
 * This class represents a vote object.
 *
 */

public class Vote {

	
	/* -------------------- Attributes -------------------- */

	private String voteNum;
	private String partyID;
	private boolean valid;
	private String ballotNum;
	
	
	/* -------------------- Constructor -------------------- */
	
	public Vote(String voteNum, String partyID, boolean valid, String ballotNum) {
		this.voteNum = voteNum;
		this.partyID = partyID;
		this.valid = valid;
		this.ballotNum = ballotNum;
	}

	
	/* -------------------- Getters & Setters -------------------- */

	public String getVoteNum() {
		return voteNum;
	}


	public void setVoteNum(String voteNum) {
		this.voteNum = voteNum;
	}


	public String getPartyID() {
		return partyID;
	}


	public void setPartyID(String partyID) {
		this.partyID = partyID;
	}


	public boolean isValid() {
		return valid;
	}


	public void setValid(boolean valid) {
		this.valid = valid;
	}


	public String getBallotNum() {
		return ballotNum;
	}


	public void setBallotNum(String ballotNum) {
		this.ballotNum = ballotNum;
	}

	
	/* -------------------- To String -------------------- */

	@Override
	public String toString() {
		return "Vote [voteNum=" + voteNum + ", partyID=" + partyID + ", valid=" + valid + ", ballotNum=" + ballotNum + "]";
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
		Vote other = (Vote) obj;
		if (voteNum != other.voteNum)
			return false;
		return true;
	}
	
	
	/* -------------------- Hashing -------------------- */
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((voteNum == null) ? 0 : voteNum.hashCode());
		return result;
	}
	
	
}
