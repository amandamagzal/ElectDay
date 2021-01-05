package entity;

import java.sql.Time;


/**
 * This class represents a ride for a voter.
 *
 */

public class RideToBallot {
	
	
	/* -------------------- Attributes -------------------- */
	
	private String rideNum;
	private String driverID;
	private String voterID;
	private Time pickupTime;
	private Time returnTime;
	
	
	/* -------------------- Constructor -------------------- */
	
	public RideToBallot(String rideNum, String driverID, String voterID, Time pickupTime, Time returnTime) {
		this.rideNum = rideNum;
		this.driverID = driverID;
		this.voterID = voterID;
		this.pickupTime = pickupTime;
		this.returnTime = returnTime;
	}


	/* -------------------- Getters & Setters -------------------- */

	public String getRideNum() {
		return rideNum;
	}


	public void setRideNum(String rideNum) {
		this.rideNum = rideNum;
	}


	public String getDriverID() {
		return driverID;
	}


	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}


	public String getVoterID() {
		return voterID;
	}


	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}


	public Time getPickupTime() {
		return pickupTime;
	}


	public void setPickupTime(Time pickupTime) {
		this.pickupTime = pickupTime;
	}


	public Time getReturnTime() {
		return returnTime;
	}


	public void setReturnTime(Time returnTime) {
		this.returnTime = returnTime;
	}


	/* -------------------- To String -------------------- */

	@Override
	public String toString() {
		return "RideToBallot [rideNum=" + rideNum + ", driverID=" + driverID + ", voterID=" + voterID + ", pickupTime="
				+ pickupTime + ", returnTime=" + returnTime + "]";
	}
	
	
}
