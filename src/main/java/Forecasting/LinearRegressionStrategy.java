package Forecasting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.RemoveWithValues;

/**
 * A class that implements the ForecastingStrategy interface and provides a linear regression strategy for performing forecasting.
 * It involves the use of WEKA.
 * @author Jorel Louie Chim
 */

public class LinearRegressionStrategy implements ForecastingStrategy {

	/**
	 * Executes a linear regression algorithm for forecasting. 
	 * @param selection1 the first selection location made by the user
	 * @param inputQuery the query needed to be made to the database
	 */
	
	public void LinearRegressionForecasting(final String selection1, final String inputQuery){
		
		// Setup JFrame
		final JFrame frame = new JFrame();
		frame.setTitle("Forecasting");
		frame.setSize(700,500);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		// Initialize JFrame Variables
		JLabel location = new JLabel(selection1 + " House Value Forecasting");
		JLabel forecasting = new JLabel("Forecasting(in months): ");
		final JTextField inputMonths = new JTextField("2");
		JLabel DecimalPlaces = new JLabel("DecimalPlaces: ");
		final JTextField inputDecimalPlaces = new JTextField("2");
		JLabel Ridge = new JLabel("Number of Ridge: ");
		final JTextField inputRidge = new JTextField("0.1");
		JTextArea reminder = new JTextArea("Reminder: Month 2 to 60, DecimalPlaces 1 to 10, \nRidge 0 to 1 exclusive, "
				+ "X-axis in the chart starts from \nthe month count of the last selected date."
				+ "\n1981-01 is the base month \"1\"");
		JButton showData = new JButton("Show Data");
		final JLabel regressionResult = new JLabel();
		
		// Setup Panels
		final JPanel northPanel = new JPanel();
		final JPanel westPanel = new JPanel();
		final JPanel eastPanel = new JPanel();
		final JPanel southPanel = new JPanel();
		
		// North Panel
		location.setFont(new Font("", Font.PLAIN, 20));
		northPanel.add(location);
		northPanel.setPreferredSize(new Dimension(100, 30));

		// West Panel
	    forecasting.setBounds(10, 50, 150, 30);
	    inputMonths.setBounds(160, 50, 130, 30);
	    DecimalPlaces.setBounds(10, 90, 150, 30);
	    inputDecimalPlaces.setBounds(160, 90, 130, 30);
	    Ridge.setBounds(10, 130, 150, 30);
	    inputRidge.setBounds(160, 130, 130, 30);
	    reminder.setBounds(10, 170, 350, 70);
	    showData.setBounds(85, 270, 130, 30);
		westPanel.add(forecasting);	
		westPanel.add(inputMonths);	
		westPanel.add(DecimalPlaces);	
		westPanel.add(inputDecimalPlaces);	
		westPanel.add(Ridge);	
		westPanel.add(inputRidge);	
		westPanel.add(reminder);
		westPanel.add(showData);
		westPanel.setLayout(null);
		westPanel.setPreferredSize(new Dimension(300, 100));
		
		
		// South Panel
		southPanel.setLayout(null);
		southPanel.setPreferredSize(new Dimension(100, 70));
		
		// Layout
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(westPanel, BorderLayout.WEST);
		frame.add(eastPanel, BorderLayout.EAST);
		frame.add(southPanel, BorderLayout.SOUTH);
		
		/**
		 * Adds an action listener to the show data button to start a linear regression forecasting algorithm.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		// Action Listener to start forecasting
		showData.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				// Set Parameters
				int month = Integer.parseInt(inputMonths.getText());
				int DecimalPlaces = Integer.parseInt(inputDecimalPlaces.getText());
				double Ridge = Double.parseDouble(inputRidge.getText());
				
				if (month <= 1 || month > 60 || DecimalPlaces < 1 || DecimalPlaces > 10 || Ridge <= 0 || Ridge > 1 ) {
					JOptionPane.showMessageDialog(null, "Please input appropriate values months 2 to 60, DecimalPlaces 1 to 10, "
							+ "Ridge 0 to 1 exclusive", "Error", JOptionPane.ERROR_MESSAGE);
					}else 
				{
				  try {
					  // Query for the selected location
					  InstanceQuery query = new InstanceQuery();
					  String sql = inputQuery;
					  query.setDatabaseURL("jdbc:mysql://localhost:3306/echodata");
					  query.setUsername("root");
					  query.setPassword("password");
					  query.setQuery(sql);
					  Instances data = query.retrieveInstances();

					  // Remove instances with missing values
					  RemoveWithValues removeWithValues = new RemoveWithValues();
					  removeWithValues.setAttributeIndex("2");
					  removeWithValues.setMatchMissingValues(true);
					  removeWithValues.setInvertSelection(false);
					  removeWithValues.setInputFormat(data);
					  data = Filter.useFilter(data, removeWithValues);

					  // Create a new dataSet with the new attribute 
					  Attribute newAttr = new Attribute("DATE");
					  ArrayList<Attribute> attributes = new ArrayList<Attribute>();
					  attributes.add(newAttr);
					  for (int i = 1; i < data.numAttributes(); i++) {
					      attributes.add(data.attribute(i));
					  }
					  Instances newData = new Instances("newData", attributes, data.numInstances());

					  // Convert the REF_DATE attribute to numeric and add it to the new instances
					  for (int i = 0; i < data.numInstances(); i++) {
					      Instance inst = data.instance(i);
					      String dateStr = inst.stringValue(0);
					      String[] dateParts = dateStr.split("-");
					      int year = Integer.parseInt(dateParts[0]);
					      int month1 = Integer.parseInt(dateParts[1]);
					      double dateNum = (year - 1981) * 12 + month1;
					      Instance newInst = new DenseInstance(newData.numAttributes());
					      for (int j = 1; j < inst.numAttributes(); j++) {
					          if (j == 1) {
					              newInst.setValue(0, dateNum);
					          }
					          newInst.setValue(j, inst.value(j));
					      }
					      newData.add(newInst);
					  }
					  System.out.println(newData.lastInstance());
					  
				      if (newData.numInstances() <= 50 ) {
				    	  JOptionPane.showMessageDialog(null, "Not enough sample size(<=50), please select longer time period", "Error", JOptionPane.ERROR_MESSAGE);
						} else 
					  {
					  // Set the class attribute
					  newData.setClassIndex(1);
					    
				      // Linear Regression
					  LinearRegression model = new LinearRegression();
					  model.setNumDecimalPlaces(DecimalPlaces);
					  model.setRidge(Ridge);
					  Evaluation eval = new Evaluation(newData);
					  eval.crossValidateModel(model, newData, 10, new Random(1));
					  model.buildClassifier(newData);
				      System.out.println(model.toString());
				      
					  // Show the result in southPanel. 
					  DecimalFormat df = new DecimalFormat("#0.0000");
					  regressionResult.setText(model.toString() + "  ");
					  regressionResult.setBounds(150, 0, 700, 30);
					  JLabel MeanError = new JLabel("Mean absolute error: " + df.format(eval.meanAbsoluteError()));
					  MeanError.setBounds(130, 30, 250, 30);
					  JLabel R_squared = new JLabel("R squared value: " + df.format(eval.correlationCoefficient()));
					  R_squared.setBounds(400, 30, 300, 30);
					  southPanel.add(regressionResult);
					  southPanel.add(MeanError);
					  southPanel.add(R_squared);
						
					  // Get the last date info in the instances
					  double lastDate = newData.instance(newData.numInstances() - 1).value(0);
					  double lastValue = newData.instance(newData.numInstances() - 1).value(1);

					  // Create a new dataSet with instances for the next few months
					  Instances newData1 = new Instances(data, month + 1);
					  for (int i = 1; i <= month + 1; i++) {
						  Instance instance = new DenseInstance(2);
						  double dateNum = lastDate + i;
						  instance.setValue(0, dateNum);
						  newData1.add(instance);
						  }

					  // Predict the values for the next few months using the LinearRegression model
					  XYSeries series = new XYSeries("Predicted VALUE");
					  for (int i = 0; i < newData1.numInstances(); i++) {
						  double predictedValue = model.classifyInstance(newData1.instance(i));
						  double dateNum = newData1.instance(i).value(0);
						  series.add(dateNum, predictedValue);
						  }

					  // Create a chart and a chart panel to display the predicted values
					  XYSeriesCollection dataset = new XYSeriesCollection(series);
					  JFreeChart chart = ChartFactory.createXYLineChart("Predicted VALUES", "DATE", "VALUE", dataset);
					  XYPlot plot = (XYPlot) chart.getPlot();
					  NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
					  xAxis.setRange(lastDate + 1, lastDate + month + 1);
					  NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
					  yAxis.setRange(lastValue - 20, lastValue + 20);
						
					  // Add the chart to the frame
					  ChartPanel chartPanel = new ChartPanel(chart);
					  eastPanel.add(chartPanel);
					  chartPanel.setPreferredSize(new Dimension(380, 350));
					  frame.validate();
					  frame.repaint();
						}
				      
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				  }
			}
		});
	}
}
