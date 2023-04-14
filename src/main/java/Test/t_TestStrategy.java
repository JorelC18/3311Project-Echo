package Test;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.inference.TTest;

import SQLConnection.t_TestDataRetrieval;

/**
 * A class that implements the TestStrategy interface and provides a t-test strategy for conducting t-tests with 2 queries
 * using the Apache Math Commons Library.
 * @author Jorel Louie Chim
 */

public class t_TestStrategy implements TestStrategy {
			
	JFrame frame = new JFrame();
	JLabel degreesOfFreedomResult;
	JLabel tResult;
	JLabel pResult;
	JLabel statement;
	JLabel DOF = new JLabel("Degrees of Freedom:");
	JLabel t_value = new JLabel("t-value:");
	JLabel p_value = new JLabel("p-value:");
		
	/**
	 * Performs a t-test based on the given result set, and the 2 queries given.
	 * @param result the resultSet needed for the t-test
	 * @param query1 the first query needed for the t-test
	 * @param query2 the second query needed for the t-test
	 */
	
	public void t_Test(ResultSet result, String query1, String query2) {
		
		try {
			DescriptiveStatistics stats1 = new DescriptiveStatistics(retrieveData(query1));
			DescriptiveStatistics stats2 = new DescriptiveStatistics(retrieveData(query2));
			//t_Test formula performing
			double degreesOfFreedom = stats1.getN() + stats2.getN() - 2;
			if (degreesOfFreedom <= 0) {
				JOptionPane.showMessageDialog(null, "Not enough data, please choose a longer time interval or choose a different location.",
											"Error", JOptionPane.ERROR_MESSAGE);
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
				setupFrame(degreesOfFreedom, t, p);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method call to retrieve data from database for t_testing purposes
	 * @param query the query needed to retrieve the data
	 */
	
	private double[] retrieveData(String query) throws SQLException {
		t_TestDataRetrieval t_TestDataRetrieval = new t_TestDataRetrieval();
		double[] result = t_TestDataRetrieval.t_Test_DataRetrieval(query);
		return result;
	}
	
	/**
	 * set up the frame to show the t-test result
	 * @param degreesOfFreedom the degrees of freedom from the selected data
	 * @param t the t value from the t-test 
	 * @param p the p value from the t-test
	 */
	
	private void setupFrame(double degreesOfFreedom, double t, double p) {
		
		degreesOfFreedomResult = new JLabel(Double.toString(degreesOfFreedom));
		tResult = new JLabel(Double.toString(t));
		pResult = new JLabel(Double.toString(p));
		
		frame.setTitle("T_Test Results");
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
}
