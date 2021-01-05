package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import entity.BallotBox;
import entity.Party;
import entity.Vote;
import util.Consts;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;


/**
 * This class controls the counting functionality
 * It includes all the methods used to count a vote and produce the live counting information report
 *
 */

public class CountControl {

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	public static CountControl instance;


	public static CountControl getInstance() {
		if (instance == null)
			instance = new CountControl();
		return instance;
	}
	
	
	
	/* -------------------------------------------------------------- */
	/* --------------------------- Methods -------------------------- */
	/* -------------------------------------------------------------- */
	
	
	/**
	 * Gets all the ballots from the DB
	 * @return
	 */
	
	public ArrayList<BallotBox> getBallots() {
		ArrayList<BallotBox> ballots = new ArrayList<BallotBox>();

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(util.Consts.SQL_SEL_BALLOTS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					ballots.add(new BallotBox(rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ballots;
	}
	
	
	/**
	 * Gets all the parties from the DB
	 * @return
	 */
	
	public ArrayList<Party> getParties() {
		ArrayList<Party> parties = new ArrayList<Party>();

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(util.Consts.SQL_SEL_PARTIES);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					parties.add(new Party(rs.getString(i++), rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return parties;
	}
	
	
	/**
	 * Gets the last vote number from the DB
	 * @return
	 */
	
	public int getLastVoteNum() {
		ArrayList<Vote> votes = new ArrayList<Vote>();

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_VOTES);
					ResultSet rs = stmt.executeQuery())
			{
				while (rs.next()) {
					int i = 1;
					votes.add(new Vote(rs.getString(i++), 
							rs.getString(i++), 
							rs.getBoolean(i++), 
							rs.getString(i++)));
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return votes.size();
	}
	
	
	/**
	 * Adds a vote's information to the DB
	 * @param v
	 * @return
	 */
	
	public boolean addVote(Vote v) {
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_VOTE)) {
				int i = 1;
				stmt.setString(i++, v.getVoteNum()); 
				stmt.setString(i++, v.getPartyID());
				stmt.setBoolean(i++, v.isValid());
				stmt.setString(i++, v.getBallotNum());
				
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
	 * Produces the live counting information report
	 * @param time
	 * @return
	 */
	
	public JFrame produceCountReport() {
		
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)){
				HashMap<String, Object> params = new HashMap<>();
				JasperPrint print = JasperFillManager.fillReport(
						getClass().getResourceAsStream("/boundary/CountReport.jasper"),
						params, conn);
				JFrame frame = new JFrame("Live Count Report");
				frame.getContentPane().add(new JRViewer(print));
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.pack();
				return frame;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	
}
