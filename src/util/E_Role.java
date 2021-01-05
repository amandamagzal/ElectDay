package util;


/**
 * This class represents an enumeration for role.
 *
 */

public enum E_Role {

	
	/* ------------------------- Values ------------------------- */
	
	Driver ("Driver"), Observer ("Observer"),
	CampaignManager ("CampaignManager"), BranchManager ("BranchManager");

	
	/* ------------------------- Variable ------------------------- */
	
	private final String role;

	
	/* ------------------------- Constructor ------------------------- */
	
	E_Role(String role) {
		this.role = role;
	}

	
	/* ------------------------- Methods ------------------------- */
	
	public String getRole() {
		return role;
	}

	
	public static E_Role returnRole(String val) {
		switch (val) {
		case "Driver":
			return E_Role.Driver;
		case "Observer":
			return E_Role.Observer;
		case "CampaignManager":
			return E_Role.CampaignManager;
		case "BranchManager":
			return E_Role.BranchManager;	
		default:
			return E_Role.Observer;
		}
	}
	
	
}
