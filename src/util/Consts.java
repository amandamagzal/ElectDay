package util;

import java.net.URLDecoder;


public class Consts {

	
	protected static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR =
			"jdbc:ucanaccess://" + DB_FILEPATH;
	public static final String JDBC_STR = "net.ucanaccess.jdbc.UcanaccessDriver";

	
	
	/* ------------------------------ VOTERS QUERIES ------------------------------ */
	
	public static final String SQL_SEL_VOTERS = "SELECT tblVoter.[Voter ID], tblVoter.[First Name], tblVoter.[Last Name], tblVoter.[Birth Date], tblVoter.[Gender] , tblVoter.[Phone Num] FROM tblVoter";
	public static final String SQL_UPD_VOTINGTIME = "{ call qryUpdVotingTime(?,?) }";
	public static final String SQL_UPD_RIDE = "{ call qryUpdRide(?,?,?,?) }";

	/* ------------------------------ COUNT QUERIES ------------------------------ */
	
	public static final String SQL_SEL_BALLOTS = "SELECT tblBallot.[Ballot ID] FROM tblBallot";
	public static final String SQL_SEL_PARTIES = "SELECT tblParty.[Party ID], tblParty.[Party Name] FROM tblParty";
	
	/* ------------------------------ VOTES QUERIES ------------------------------ */
	
	public static final String SQL_SEL_VOTES = "SELECT * FROM tblVote";
	public static final String SQL_INS_VOTE = "{ call qryInsVote(?,?,?,?) }";
	
	
	/* ------------------------------ BRANCHES QUERIES ------------------------------ */
	
	public static final String SQL_SEL_BRANCHES = "SELECT tblBranch.[Branch ID] FROM tblBranch";
	
	
	/* ------------------------------ IMPORT QUERIES ------------------------------ */
	
	public static final String SQL_UPD_VOTER = "{ call qryUpdVoter(?,?,?,?,?,?,?,?) }";
	public static final String SQL_INS_VOTER = "{ call qryInsVoter(?,?,?,?,?,?,?,?) }";
	public static final String SQL_DEL_VOTER = "{ call qryDelVoter(?) }";
	
	public static final String SQL_UPD_BALLOT = "{ call qryUpdBallot(?,?) }";
	public static final String SQL_INS_BALLOT = "{ call qryInsBallot(?,?) }";
	public static final String SQL_DEL_BALLOT = "{ call qryDelBallot(?) }";
	
	public static final String SQL_INS_POSITION = "{ call qryInsPosition(?,?,?,?) }";
	public static final String SQL_UPD_POSITION = "{ call qryUpdPosition(?,?,?,?) }";
	public static final String SQL_DEL_POSITION = "{ call qryDelPosition(?) }";
	
	public static final String SQL_UPD_PARTY = "{ call qryUpdParty(?,?,?) }";
	public static final String SQL_INS_PARTY = "{ call qryInsParty(?,?,?) }";
	public static final String SQL_DEL_PARTY = "{ call qryDelParty(?) }";
	
	public static final String SQL_UPD_CITY = "{ call qryUpdCity(?,?,?,?,?) }";
	public static final String SQL_INS_CITY = "{ call qryInsCity(?,?,?,?,?) }";
	public static final String SQL_DEL_CITY = "{ call qryDelCity(?) }";
	
	
	public enum Manipulation {
		UPDATE, INSERT, DELETE;	    
	}
	
	 
	 
	 
	/**
	 * Finds the correct path of the DB file
	 * @return the path of the DB file (from eclipse or with runnable file)
	 */
	
	private static String getDBPath() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar")) {
				decoded = decoded.substring(0, decoded.lastIndexOf('/'));
				return decoded + "/database/ElectDayDB.accdb";
			} else {
				decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
				return decoded + "src/entity/ElectDayDB.accdb";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
