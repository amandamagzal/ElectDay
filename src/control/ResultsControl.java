package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import util.Consts;


/**
 * This class controls the results functionality
 * It includes all the methods used to produce the final results report
 *
 */

public class ResultsControl {

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	public static ResultsControl instance;


	public static ResultsControl getInstance() {
		if (instance == null)
			instance = new ResultsControl();
		return instance;
	}
	
	
	
	/* -------------------------------------------------------------- */
	/* --------------------------- Methods -------------------------- */
	/* -------------------------------------------------------------- */
	
	
	/**
	 * Produces the final results report
	 * @return
	 */
	
	public JFrame produceResultsReport() {

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)){
				HashMap<String, Object> params = new HashMap<>();
				JasperPrint print = JasperFillManager.fillReport(
						getClass().getResourceAsStream("/boundary/ResultsReport.jasper"),
						params, conn);
				JFrame frame = new JFrame("Final Results Report");
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
