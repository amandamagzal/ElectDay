package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import entity.Branch;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import util.Consts;


/**
 * This class controls the voting functionality
 * It includes all the methods used to produce the missing voters and the voting percentage reports
 *
 */

public class VotingControl {

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	public static VotingControl instance;


	public static VotingControl getInstance() {
		if (instance == null)
			instance = new VotingControl();
		return instance;
	}
	
	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
	
	/**
	 * Gets the branches from the DB
	 * @return
	 */
	
	public ArrayList<Branch> getBranches() {
		ArrayList<Branch> branches = new ArrayList<Branch>();

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(util.Consts.SQL_SEL_BRANCHES);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					branches.add(new Branch(rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return branches;
	}
	
	
	/**
	 * Produces the missing voters report
	 * @param time
	 * @return
	 */
	
	public JFrame produceVotersReport(String branchID) {
		// TODO fix the report
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)){
				HashMap<String, Object> params = new HashMap<>();
				JasperPrint print = JasperFillManager.fillReport(
						getClass().getResourceAsStream("/boundary/MissingVotersReport.jasper"),
						params, conn);
				JFrame frame = new JFrame("Missing Voters Report");
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
	
	
	/**
	 * Produces the live voting percentage report
	 * @param time
	 * @return
	 */
	
	public JFrame produceVotingPercReport() {
		// TODO fix the report
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)){
				HashMap<String, Object> params = new HashMap<>();
				JasperPrint print = JasperFillManager.fillReport(
						getClass().getResourceAsStream("/boundary/VotingPercReport.jasper"),
						params, conn);
				JFrame frame = new JFrame("Voting Percentage");
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
	
	
	/**
	 * Produces the live voting percentage report divided by age
	 * @param time
	 * @return
	 */
	
	public JFrame produceAgePercReport() {
		// TODO fix the report
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)){
				HashMap<String, Object> params = new HashMap<>();
				JasperPrint print = JasperFillManager.fillReport(
						getClass().getResourceAsStream("/boundary/PercReportByAge.jasper"),
						params, conn);
				JFrame frame = new JFrame("Voting Percentage Divided by Age");
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
	
	
	/**
	 * Produces the live voting percentage report divided by gender
	 * @param time
	 * @return
	 */
	
	public JFrame produceGenderPercReport() {
		
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)){
				HashMap<String, Object> params = new HashMap<>();
				JasperPrint print = JasperFillManager.fillReport(
						getClass().getResourceAsStream("/boundary/PercReportByGender.jasper"),
						params, conn);
				JFrame frame = new JFrame("Voting Percentage Divided by Gender");
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
	
	
	/**
	 * Produces the live voting percentage report divided by status
	 * @param time
	 * @return
	 */
	
	public JFrame produceStatusPercReport() {

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)){
				HashMap<String, Object> params = new HashMap<>();
				JasperPrint print = JasperFillManager.fillReport(
						getClass().getResourceAsStream("/boundary/PercReportByStatus.jasper"),
						params, conn);
				JFrame frame = new JFrame("Voting Percentage Divided by Status");
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
