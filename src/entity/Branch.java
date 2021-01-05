package entity;


/**
 * This class represents a branch object.
 *
 */

public class Branch {

	
	/* -------------------- Attributes -------------------- */

	private String branchID;
	private String address;
	private String managerID;
	private String mainBranch;
	
	
	/* -------------------- Constructor -------------------- */

	public Branch(String branchID, String address, String managerID, String mainBranch) {
		this.branchID = branchID;
		this.address = address;
		this.managerID = managerID;
		this.mainBranch = mainBranch;
	}
	
	
	public Branch(String branchID) {
		this.branchID = branchID;
	}

	
	/* -------------------- Getters & Setters -------------------- */

	public String getBranchID() {
		return branchID;
	}


	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}
	
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getManagerID() {
		return managerID;
	}


	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}


	public String getMainBranch() {
		return mainBranch;
	}


	public void setMainBranch(String mainBranch) {
		this.mainBranch = mainBranch;
	}
	
	
	/* -------------------- To String -------------------- */

		@Override
	public String toString() {
		return branchID;
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
		Branch other = (Branch) obj;
		if (branchID != other.branchID)
			return false;
		return true;
	}
	
	
	/* -------------------- Hashing -------------------- */
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchID == null) ? 0 : branchID.hashCode());
		return result;
	}
	
	
}
