package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import entity.AssignedTo;
import entity.BallotBox;
import entity.City;
import entity.Party;
import entity.Voter;
import util.Consts.Manipulation;

/**
 * This class controls the import methods
 *
 */

public class ImportControl {

	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	public static ImportControl instance;


	public static ImportControl getInstance() {
		if (instance == null)
			instance = new ImportControl();
		return instance;
	}
	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
	
	/**
     * Performs data manipulation in DB on given voter
     * @param v
     * @param manipulation 
     * @return 
     */
	
    public static boolean manipulateVoter(Voter v, Manipulation manipulation) {
    	try {
    		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    		try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
    				CallableStatement stmt = conn.prepareCall(
    						(manipulation.equals(Manipulation.UPDATE)) ? 
    								util.Consts.SQL_UPD_VOTER : 
    									(manipulation.equals(Manipulation.INSERT)) ? 
    											util.Consts.SQL_INS_VOTER : 
    												util.Consts.SQL_DEL_VOTER)) {
    			allocateVoterParams(stmt, v, manipulation);
    			stmt.executeUpdate();
    			return true;
    		} catch (SQLException e) {
	    			e.printStackTrace();
    		}
    	} catch (ClassNotFoundException e) {
	    		e.printStackTrace();
    	}
    	
    	return false;
    }
    
    
    /**
     * Fills statement's place holders with voter's field values
     * @param stmt 
     * @param v 
     * @param m 
     * @throws SQLException
     */
    
    private static void allocateVoterParams(CallableStatement stmt, Voter v, Manipulation m) throws SQLException {
    	int i = 1;
    	
    	if (!m.equals(Manipulation.UPDATE)) {
    		stmt.setString(i++, v.getVoterID());
    		
    		if (m.equals(Manipulation.DELETE))
    			return;
    	}
    	
    	stmt.setString(i++, v.getFirstName());
    	
    	if (v.getLastName() == null)
    		stmt.setNull(i++, java.sql.Types.VARCHAR);
    	else
    		stmt.setString(i++, v.getLastName());
    	
    	if (v.getDateOfBirth() == null)
    		stmt.setNull(i++, java.sql.Types.VARCHAR);
    	else
    		stmt.setDate(i++, v.getDateOfBirth());
    	
    	if (v.getGender() == null)
    		stmt.setNull(i++, java.sql.Types.VARCHAR);
    	else
    		stmt.setString(i++, v.getGender());
    	
    	if (v.getFamilyStatus() == null)
    		stmt.setNull(i++, java.sql.Types.VARCHAR);
    	else
    		stmt.setString(i++, v.getFamilyStatus());
    	
    	if (v.getPhoneNum() == null)
    		stmt.setNull(i++, java.sql.Types.VARCHAR);
    	else
    		stmt.setString(i++, v.getPhoneNum());
    	
    	if (v.getAddress() == null)
    		stmt.setNull(i++, java.sql.Types.VARCHAR);
    	else
    		stmt.setString(i++, v.getAddress());
    
    	
    	if (m.equals(Manipulation.UPDATE))
    		stmt.setString(i, v.getVoterID());
    }
    
    
    /**
     * Performs data manipulation in DB on given position
     * @param p
     * @param manipulation 
     * @return 
     */
    
    public static boolean manipulatePosition(AssignedTo p, Manipulation manipulation) {
    	try {
    		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    		try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
    				CallableStatement stmt = conn.prepareCall(
    						(manipulation.equals(Manipulation.UPDATE)) ? 
    								util.Consts.SQL_UPD_POSITION : 
    									(manipulation.equals(Manipulation.INSERT)) ? 
    											util.Consts.SQL_INS_POSITION : 
    												util.Consts.SQL_DEL_POSITION)) {
    			allocatePositionParams(stmt, p, manipulation);
    			stmt.executeUpdate();
    			return true;
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	return false;
    }
    
    
    /**
     * Fills statement's place holders with position's field values
     * @param stmt 
     * @param p
     * @param m 
     * @throws SQLException
     */
    
    private static void allocatePositionParams(CallableStatement stmt, AssignedTo p, Manipulation m) throws SQLException {
    	int i = 1;
    	
    	if (!m.equals(Manipulation.UPDATE)) {
    		stmt.setString(i++, p.getMemberID());
    		
    		if (m.equals(Manipulation.DELETE))
    			return;
    	}
    	
    	stmt.setString(i++, p.getRole().toString());
    	
    	
    	if (p.getFromTime() == null)
    		stmt.setNull(i++, java.sql.Types.VARCHAR);
    	else
    		stmt.setTime(i++, p.getFromTime());
    	
    	if (p.getToTime() == null)
    		stmt.setNull(i++, java.sql.Types.VARCHAR);
    	else
    		stmt.setTime(i++, p.getToTime());
    }
    
	
    /**
     * Performs data manipulation in DB on given ballot
     * @param b
     * @param manipulation 
     * @return 
     */
    
    public static boolean manipulateBallot(BallotBox b, Manipulation manipulation) {
    	try {
    		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    		try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
    				CallableStatement stmt = conn.prepareCall(
    						(manipulation.equals(Manipulation.UPDATE)) ? 
    								util.Consts.SQL_UPD_VOTER : 
    									(manipulation.equals(Manipulation.INSERT)) ? 
    											util.Consts.SQL_INS_VOTER : 
    												util.Consts.SQL_DEL_VOTER)) {
    			allocateBallotParams(stmt, b, manipulation);
    			stmt.executeUpdate();
    			return true;
    		} catch (SQLException e) {
	    			e.printStackTrace();
    		}
    	} catch (ClassNotFoundException e) {
	    		e.printStackTrace();
    	}
    	
    	return false;
    }
    
    
    /**
     * Fills statement's place holders with ballot's field values
     * @param stmt 
     * @param b 
     * @param m 
     * @throws SQLException
     */
    
    private static void allocateBallotParams(CallableStatement stmt, BallotBox b, Manipulation m) throws SQLException {
    	int i = 1;
    	
    	if (!m.equals(Manipulation.UPDATE)) {
    		stmt.setString(i++, b.getBallotNum());
    		
    		if (m.equals(Manipulation.DELETE))
    			return;
    	}
    	
    	stmt.setString(i++, b.getBranchNum());
    	
    }
    
    
    /**
     * Performs data manipulation in DB on given party
     * @param p
     * @param manipulation 
     * @return 
     */
	
    public static boolean manipulateParty(Party p, Manipulation manipulation) {
    	try {
    		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    		try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
    				CallableStatement stmt = conn.prepareCall(
    						(manipulation.equals(Manipulation.UPDATE)) ? 
    								util.Consts.SQL_UPD_PARTY : 
    									(manipulation.equals(Manipulation.INSERT)) ? 
    											util.Consts.SQL_INS_PARTY : 
    												util.Consts.SQL_DEL_PARTY)) {
    			allocatePartyParams(stmt, p, manipulation);
    			stmt.executeUpdate();
    			return true;
    		} catch (SQLException e) {
	    			e.printStackTrace();
    		}
    	} catch (ClassNotFoundException e) {
	    		e.printStackTrace();
    	}
    	
    	return false;
    }
    
    
    /**
     * Fills statement's place holders with voter's field values
     * @param stmt 
     * @param v 
     * @param m 
     * @throws SQLException
     */
    
    private static void allocatePartyParams(CallableStatement stmt, Party p, Manipulation m) throws SQLException {
    	int i = 1;
    	
    	if (!m.equals(Manipulation.UPDATE)) {
    		stmt.setString(i++, p.getPartyID());
    		
    		if (m.equals(Manipulation.DELETE))
    			return;
    	}
    	
    	stmt.setString(i++, p.getName());
    	
    	if (p.getImage() == null)
    		stmt.setNull(i++, java.sql.Types.VARCHAR);
    	else
    		stmt.setString(i++, p.getImage());
    
    	
    	if (m.equals(Manipulation.UPDATE))
    		stmt.setString(i, p.getPartyID());
    }
    
    
    /**
     * Performs data manipulation in DB on given city
     * @param c
     * @param manipulation 
     * @return 
     */
	
    public static boolean manipulateCity(City c, Manipulation manipulation) {
    	try {
    		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    		try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
    				CallableStatement stmt = conn.prepareCall(
    						(manipulation.equals(Manipulation.UPDATE)) ? 
    								util.Consts.SQL_UPD_CITY : 
    									(manipulation.equals(Manipulation.INSERT)) ? 
    											util.Consts.SQL_INS_CITY : 
    												util.Consts.SQL_DEL_CITY)) {
    			allocateCityParams(stmt, c, manipulation);
    			stmt.executeUpdate();
    			return true;
    		} catch (SQLException e) {
	    			e.printStackTrace();
    		}
    	} catch (ClassNotFoundException e) {
	    		e.printStackTrace();
    	}
    	
    	return false;
    }
    
    
    /**
     * Fills statement's place holders with city's field values
     * @param stmt 
     * @param c
     * @param m 
     * @throws SQLException
     */
    
    private static void allocateCityParams(CallableStatement stmt, City c, Manipulation m) throws SQLException {
    	int i = 1;
    	
    	if (!m.equals(Manipulation.UPDATE)) {
    		stmt.setString(i++, c.getCityID());
    		
    		if (m.equals(Manipulation.DELETE))
    			return;
    	}
    	
    	stmt.setString(i++, c.getCityName());
    	
    	if (c.getElection1() == 0)
    		stmt.setNull(i++, java.sql.Types.DOUBLE);
    	else
    		stmt.setLong(i++, c.getElection1());
    	
    	if (c.getElection2() == 0)
    		stmt.setNull(i++, java.sql.Types.DOUBLE);
    	else
    		stmt.setLong(i++, c.getElection2());
    	
    	if (c.getElection3() == 0)
    		stmt.setNull(i++, java.sql.Types.DOUBLE);
    	else
    		stmt.setLong(i++, c.getElection3());
    
    	
    	if (m.equals(Manipulation.UPDATE))
    		stmt.setString(i, c.getCityID());
    }
    
	
}
