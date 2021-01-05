package entity;


/**
 * This class represents a main branch object.
 *
 */

public class MainBranch extends Branch {

	
	/* -------------------- Attributes -------------------- */
	
	private String vice1ID;
	private String vice2ID;
	
	
	/* -------------------- Constructor -------------------- */
	
	public MainBranch(String branchID, String address, String managerID, String mainBranch, String vice1id, String vice2id) {
		super(branchID, address, managerID, mainBranch);
		vice1ID = vice1id;
		vice2ID = vice2id;
	}

	
	/* -------------------- Getters & Setters -------------------- */

	public String getVice1ID() {
		return vice1ID;
	}


	public void setVice1ID(String vice1id) {
		vice1ID = vice1id;
	}


	public String getVice2ID() {
		return vice2ID;
	}


	public void setVice2ID(String vice2id) {
		vice2ID = vice2id;
	}


	/* -------------------- To String -------------------- */

	@Override
	public String toString() {
		return "MainBranch [vice1ID=" + vice1ID + ", vice2ID=" + vice2ID + "]";
	}
	
	
}
