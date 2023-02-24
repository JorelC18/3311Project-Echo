package NHPIVisualizer;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.inference.TTest;

public class t_TestStrategy implements TestStrategy {
	
	public void t_Test(ResultSet result, String selection1, String selection2, String endDate, String startDate) {
		
		JFrame frame = new JFrame();
		JLabel degreesOfFreedomResult;
		JLabel tResult;
		JLabel pResult;
		JLabel statement;
		
		System.out.println(selection1);
		System.out.println(selection2);
		System.out.println(endDate);
		System.out.println(startDate);
		
		try {
			
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/echodata", "root", "password");
		
		String query1 = "SELECT VALUE FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\"" + ")" + " AND (REF_DATE <= " + "\"" + 
				endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");";
		ResultSet rs1;
		
		String query2 = "SELECT VALUE FROM echodata.echodata WHERE (GEO LIKE \"%" + selection2 + "%\"" + ")" + " AND (REF_DATE <= " + "\"" + 
				endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");";
		ResultSet rs2;
		
		ArrayList<Double> resultSample1 = new ArrayList<Double>();
		Statement statement1 = connection.prepareStatement(query1);
		rs1 = statement1.executeQuery(query1);
		
		while (rs1.next()) {
			String input1Str = rs1.getString(1);
			Double input1 = null;
			if (input1Str != null) {
				input1 = Double.parseDouble(rs1.getString(1));
			}
			resultSample1.add(input1);
		}
		
		List<Double> resultSample2 = new ArrayList<Double>();
		Statement statement2 = connection.prepareStatement(query2);
		rs2 = statement2.executeQuery(query2);
		
		while (rs2.next()) {
			String input2Str = rs2.getString(1);
			Double input2 = null;
			if (input2Str != null) {
				input2 = Double.parseDouble(rs2.getString(1));
			}
			resultSample2.add(input2);
		}
		
		double[] result1 = new double[resultSample1.size()];
		for (int i = 0; i < resultSample1.size(); i++) {
			if (resultSample1.get(i) != null) {
				result1[i] = resultSample1.get(i);
			}
		}
		DescriptiveStatistics stats1 = new DescriptiveStatistics(result1);
		
		double[] result2 = new double[resultSample2.size()];
		for (int i = 0; i < resultSample2.size(); i++) {
			if (resultSample2.get(i) != null) {
				result2[i] = resultSample2.get(i);
			}
		}
		DescriptiveStatistics stats2 = new DescriptiveStatistics(result2);
		
		System.out.println(stats1.getN());
		System.out.println(stats2.getN());
		
		//t_Test formula performing
		double degreesOfFreedom = stats1.getN() + stats2.getN() - 2;
		if (degreesOfFreedom <= 0) {
			JOptionPane.showMessageDialog(null, "Not enough data, please choose a longer time interval or choose a different location.", "Error", JOptionPane.ERROR_MESSAGE);

		} else {
			TTest tTest = new TTest();
			double t = tTest.t(stats1, stats2);
			double p = tTest.tTest(stats1, stats2);
			double alpha = 0.05;
			
			if(p < alpha) {
				statement = new JLabel("We can reject the null hypothesis.");
			}else {
				statement = new JLabel("We can not reject the null hypothesis.");
			}
			
			//create label for result
			degreesOfFreedomResult = new JLabel(Double.toString(degreesOfFreedom));
			tResult = new JLabel(Double.toString(t));
			pResult = new JLabel(Double.toString(p));
			
			JLabel DOF = new JLabel("Degrees of Freedom:");
			JLabel t_value = new JLabel("t-value:");
			JLabel p_value = new JLabel("p-value:");
			
			frame.setTitle("Testing");
		    frame.setLayout(null);
		    frame.setSize(400,400);
		    frame.setVisible(true);
		    
		    frame.add(DOF);
		    frame.add(degreesOfFreedomResult);
		    frame.add(t_value);
		    frame.add(tResult);
		    frame.add(p_value);
		    frame.add(pResult);
		    frame.add(statement);
 
		    DOF.setBounds(50, 80, 150, 50);
		    degreesOfFreedomResult.setBounds(200, 80, 150, 50);
		    t_value.setBounds(50, 120, 150, 50);
		    tResult.setBounds(200, 120, 150, 50);
		    p_value.setBounds(50, 160, 150, 50);
		    pResult.setBounds(200, 160, 150, 50);
		    statement.setBounds(50, 200, 300, 50);
		    DOF.setFont(new Font("", Font.PLAIN, 15));
		    t_value.setFont(new Font("", Font.PLAIN, 15));
		    degreesOfFreedomResult.setFont(new Font("", Font.PLAIN, 15));
		    tResult.setFont(new Font("", Font.PLAIN, 15));
		    p_value.setFont(new Font("", Font.PLAIN, 15));
		    pResult.setFont(new Font("", Font.PLAIN, 15));
		    statement.setFont(new Font("", Font.PLAIN, 15));
		    }
		}	catch (Exception e) {
			e.printStackTrace();
		}
		    
	}
}
