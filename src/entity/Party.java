package entity;


/**
 * This class represents a party object.
 *
 */

public class Party {

	
	/* -------------------- Attributes -------------------- */

	private String partyID;
	private String name;
	private String image;
	
	
	/* -------------------- Constructor -------------------- */
	
	public Party(String partyID, String name, String image) {
		this.partyID = partyID;
		this.name = name;
		this.image = image;
	}
	
	
	public Party(String partyID, String name) {
		this.partyID = partyID;
		this.name = name;
	}

	
	/* -------------------- Getters & Setters -------------------- */

	public String getPartyID() {
		return partyID;
	}


	public void setPartyID(String partyID) {
		this.partyID = partyID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
	
	
	/* -------------------- To String -------------------- */
	
		@Override
	public String toString() {
		return partyID;
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
		Party other = (Party) obj;
		if (partyID != other.partyID)
			return false;
		return true;
	}
	
	
	/* -------------------- Hashing -------------------- */
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partyID == null) ? 0 : partyID.hashCode());
		return result;
	}
	
	
}
