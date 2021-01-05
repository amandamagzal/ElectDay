package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import entity.Voter;
import util.Consts;


/**
 * This class controls the voters functionality
 * It includes all the methods used to add a voter's voting time and a ride's information
 *
 */

public class VotersControl {

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	public static VotersControl instance;


	public static VotersControl getInstance() {
		if (instance == null)
			instance = new VotersControl();
		return instance;
	}
	
	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
	
	/**
	 * Gets the voters from the DB
	 * @return
	 */
	
	public ArrayList<Voter> getVoters() {
		ArrayList<Voter> voters = new ArrayList<Voter>();

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(util.Consts.SQL_SEL_VOTERS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					voters.add(new Voter(rs.getString(i++), rs.getString(i++), rs.getString(i++), 
							rs.getDate(i++), rs.getString(i++), rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return voters;
	}
	
	
	/**
	 * Adds a voter's voting time
	 * @param votingTime
	 * @param voterID
	 * @return
	 */
	
	public boolean addVotingTime(Time votingTime, String voterID) {
		
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_UPD_VOTINGTIME)) {
				int i = 1;

				stmt.setTime(i++, votingTime);
				stmt.setString(i++, voterID);

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
	 * Removes a voter's voting time
	 * @param voterID
	 * @return
	 */
	
	public boolean removeVotingTime(String voterID) {
		
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_UPD_VOTINGTIME)) {
				int i = 1;

				stmt.setNull(i++, java.sql.Types.TIME);
				stmt.setString(i++, voterID);

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
	 * Adds a voter's ride information
	 * @param driverID
	 * @param pickupTime
	 * @param returnTime
	 * @param voterID
	 * @return
	 */
	
	public boolean addRide(String driverID, Time pickupTime, Time returnTime, String voterID) {
		
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_UPD_RIDE)) {
				int i = 1;

				stmt.setString(i++, driverID);
				stmt.setTime(i++, pickupTime);
				stmt.setTime(i++, returnTime);
				stmt.setString(i++, voterID);

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
	 * Removes a voter's ride info
	 * @param voterID
	 * @return
	 */
	
	public boolean removeRide(String voterID) {
		
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_UPD_RIDE)) {
				int i = 1;

				stmt.setNull(i++, java.sql.Types.CHAR);
				stmt.setNull(i++, java.sql.Types.TIME);
				stmt.setNull(i++, java.sql.Types.TIME);
				stmt.setString(i++, voterID);

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
	
	
}
