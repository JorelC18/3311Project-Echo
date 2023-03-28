package Forecasting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SGD;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.experiment.DatabaseUtils;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.RemoveWithValues;

/**
 * Class for forecasting methods. Contains implementation of Use Case 5.
 * Used in collaboration with WEKA.
 * @author Jorel Louie Chim
 *
 */

public class Forecasting {
	
	/**
	 * Method to predict the next X months of a given time-series.
	 * @param selection1 selected location by user
	 * @param endDate selected end date by user
	 * @param startDate selected start date by user
	 */

	public void Forcasting(final String selection1, final String endDate, final String startDate){
		
		//testing
		System.out.println(selection1);
		System.out.println(endDate);
		System.out.println(startDate);
		
		//setup JFrame
		final JFrame frame = new JFrame();
		frame.setTitle("Forecasting");
		frame.setSize(700,500);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		//initial JFrame variables
		JLabel location = new JLabel(selection1 + " House Value Forecasting");
		JLabel forecasting = new JLabel("Forecasting(in months): ");
		final JTextField inputMonths = new JTextField("2");
		JLabel DecimalPlaces = new JLabel("DecimalPlaces: ");
		final JTextField inputDecimalPlaces = new JTextField("2");
		JLabel Ridge = new JLabel("Number of Ridge: ");
		final JTextField inputRidge = new JTextField("0.1");
		JTextArea reminder = new JTextArea("Reminder: Month 2 to 12, \nDecimalPlaces 1 to 10,\nRidge 0 to 1 exclusive ");
		//JLabel convergenceThreshold = new JLabel("Convergence Threshold: ");
		//final JTextField inputThreshold = new JTextField("0.01");
		//JLabel chartTypeLabel = new JLabel("Show Result in Which: ");
		//String[] chartTypes = {"Line Chart", "Bar Chart"};
		//JComboBox<String> chartTypesComboBox = new JComboBox<String>(chartTypes);
		JButton showData = new JButton("Show Data");
		
		//setup panels
		final JPanel northPanel = new JPanel();
		final JPanel westPanel = new JPanel();
		final JPanel eastPanel = new JPanel();
		final JPanel southPanel = new JPanel();
		
		//north panel
		location.setFont(new Font("", Font.PLAIN, 20));
		northPanel.add(location);
		northPanel.setPreferredSize(new Dimension(100, 30));

		//west panel
	    forecasting.setBounds(20, 50, 150, 30);
	    inputMonths.setBounds(170, 50, 130, 30);
	    DecimalPlaces.setBounds(20, 90, 150, 30);
	    inputDecimalPlaces.setBounds(170, 90, 130, 30);
	    Ridge.setBounds(20, 130, 150, 30);
	    inputRidge.setBounds(170, 130, 130, 30);
	    reminder.setBounds(20, 170, 350, 60);
	    //convergenceThreshold.setBounds(20, 170, 350, 30);
	    //inputThreshold.setBounds(170, 170, 130, 30);
	    // chartTypeLabel.setBounds(20, 210, 150, 30);
	    // chartTypesComboBox.setBounds(170, 210, 130, 30);
	    showData.setBounds(85, 270, 130, 30);
		westPanel.add(forecasting);	
		westPanel.add(inputMonths);	
		westPanel.add(DecimalPlaces);	
		westPanel.add(inputDecimalPlaces);	
		westPanel.add(Ridge);	
		westPanel.add(inputRidge);	
		westPanel.add(reminder);
		//westPanel.add(convergenceThreshold);	
		//westPanel.add(inputThreshold);
		//westPanel.add(chartTypeLabel);
		//westPanel.add(chartTypesComboBox);
		westPanel.add(showData);
		westPanel.setLayout(null);
		westPanel.setPreferredSize(new Dimension(300, 100));
		
		
		//south panel
		southPanel.setLayout(null);
		southPanel.setPreferredSize(new Dimension(100, 70));
		
		//layout
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(westPanel, BorderLayout.WEST);
		frame.add(eastPanel, BorderLayout.EAST);
		frame.add(southPanel, BorderLayout.SOUTH);
		
		/**
		 * Adds an action listener to start the forecasting.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		//action listener to start forecasting
		
		showData.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				int month = Integer.parseInt(inputMonths.getText());
				int DecimalPlaces = Integer.parseInt(inputDecimalPlaces.getText());
				double Ridge = Double.parseDouble(inputRidge.getText());
				//Double threshold = Double.parseDouble(inputThreshold.getText());
				
				
				if (month <= 1 || month > 12 || DecimalPlaces < 1 || DecimalPlaces > 10 || Ridge <= 0 || Ridge > 1 ) {
					JOptionPane.showMessageDialog(null, "Please input appropriate values months 2 to 12, DecimalPlaces 1 to 10, "
							+ "Ridge 0 to 1 exclusive", "Error", JOptionPane.ERROR_MESSAGE);
					}else 
				{
				  try {
					// load data from CSV file
					CSVLoader loader = new CSVLoader();
					loader.setSource(new File("price.csv"));//this is a sample file in the src/main/resources
					Instances data = loader.getDataSet();
					
					//set the class index, which is the VALUE.
					data.setClassIndex(data.numAttributes() - 1);
					
					//do the linear regression to predict
					LinearRegression model = new LinearRegression();
					model.setNumDecimalPlaces(DecimalPlaces);
					model.setRidge(Ridge);
					Evaluation eval = new Evaluation(data);
					eval.crossValidateModel(model, data, 10, new Random(1));
					model.buildClassifier(data);
					System.out.println(model.toString());
					
					//show the result in southPanel. 
					DecimalFormat df = new DecimalFormat("#0.0000");
					JLabel regressionResult = new JLabel(model.toString() + "  ");
					regressionResult.setBounds(150,0, 700, 30);
					JLabel MeanError = new JLabel("Mean absolute error: " + df.format(eval.meanAbsoluteError()));
					MeanError.setBounds(130, 30, 250, 30);
					JLabel R_squared = new JLabel("R squared value: " + df.format(eval.correlationCoefficient()));
					R_squared.setBounds(400, 30, 300, 30);
					southPanel.add(regressionResult);
					southPanel.add(MeanError);
					southPanel.add(R_squared);
					
					//save the predicted result to a instance so the chart can load data.
					Instances newData = new Instances(data, month); 
					for(int i = 0; i < month; i++){
						Instance instance = new DenseInstance(2);
						instance.setValue(0, data.numInstances() + i + 1);
						newData.add(instance);
					}
					
					//show the result as a chart in eastPanel
					XYSeries series = new XYSeries("Predicted VALUE");
					for (int i = 0; i < newData.numInstances(); i++) {
					    double predictedValue = model.classifyInstance(newData.instance(i));
					    series.add(newData.instance(i).value(0), predictedValue);
					}
					XYSeriesCollection dataset = new XYSeriesCollection(series);
					JFreeChart chart = ChartFactory.createXYLineChart("Predicted VALUE", "DATE", "VALUE", dataset);
					ChartPanel chartPanel = new ChartPanel(chart);
					eastPanel.add(chartPanel);
					chartPanel.setPreferredSize(new Dimension(380, 350));
					frame.validate();
					frame.repaint();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
				
				/*
				 * 
				 * THIS PART IS TESTING THE ACTUAL CONNECTION TO THE DATABASE.
					try {
					 	//query for the selected location
					 	//1. by this way, we get the desire data from the database and saved to the Instances data.
					 	//the problem is there is not class index. if it can not set up, can not do linear regression.
						InstanceQuery query = new InstanceQuery();
						String sql = "SELECT REF_DATE, VALUE FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\"" + ")" + " AND (REF_DATE <= " + "\"" + 
								endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");";
						System.out.println(sql);
						query.setDatabaseURL("jdbc:mysql://localhost:3306/echodata");
						query.setUsername("root");
						query.setPassword("password");
						query.setQuery(sql);
						Instances data = query.retrieveInstances();
						System.out.println(data);
						
						//remove the lines with null value in the data.
				        RemoveWithValues filter = new RemoveWithValues();
				        filter.setAttributeIndex("1"); // The index of the attribute containing missing values.
				        filter.setMatchMissingValues(true);
				        filter.setInputFormat(data);
				        Instances filteredData = Filter.useFilter(data, filter);
				        System.out.println(filteredData);
				        
				        //stuck here, can not do the linear regression.
				        filteredData.setClassIndex(1);
				        LinearRegression model = new LinearRegression();
				        model.buildClassifier(filteredData);
				        
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					*/
								
				}
		});
	}
}
