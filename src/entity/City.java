package entity;


/**
 * This class represents a city object.
 *
 */

public class City {

	
	/* -------------------- Attributes -------------------- */

	private String cityID;
	private String cityName;
	private long election1;
	private long election2;
	private long election3;
	
	
	/* -------------------- Constructor -------------------- */
	
	public City(String cityID, String cityName, long election1, long election2, long election3) {
		super();
		this.cityID = cityID;
		this.cityName = cityName;
		this.election1 = election1;
		this.election2 = election2;
		this.election3 = election3;
	}

	
	/* -------------------- Getters & Setters -------------------- */
	
	public String getCityID() {
		return cityID;
	}


	public void setCityID(String cityID) {
		this.cityID = cityID;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public long getElection1() {
		return election1;
	}


	public void setElection1(long election1) {
		this.election1 = election1;
	}


	public long getElection2() {
		return election2;
	}


	public void setElection2(long election2) {
		this.election2 = election2;
	}


	public long getElection3() {
		return election3;
	}


	public void setElection3(long election3) {
		this.election3 = election3;
	}


	/* -------------------- To String -------------------- */
		
		@Override
	public String toString() {
		return cityName;
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
		City other = (City) obj;
		if (cityID != other.cityID)
			return false;
		return true;
	}
	
	
	/* -------------------- Hashing -------------------- */
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityID == null) ? 0 : cityID.hashCode());
		return result;
	}
	
	
}
