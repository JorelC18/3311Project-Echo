package Forecasting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Arrays;
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

import com.opencsv.CSVWriter;

import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SGD;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.experiment.DatabaseUtils;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.RemoveWithValues;

public class LinearRegressionStrategy implements ForecastingStrategy {

	public void LinearRegressionForecasting(final String selection1, final String inputQuery) {
		
		// Setup JFrame
		final JFrame frame = new JFrame();
		frame.setTitle("Forecasting");
		frame.setSize(700,500);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		// Initialize JFrame Variables
		JLabel location = new JLabel(selection1 + " House Value Forecasting");
		JLabel forecasting = new JLabel("Forecasting (in months): ");
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
		
		// South Panel
		southPanel.setLayout(null);
		southPanel.setPreferredSize(new Dimension(100, 70));
		
		// Set the Layout
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(westPanel, BorderLayout.WEST);
		frame.add(eastPanel, BorderLayout.EAST);
		frame.add(southPanel, BorderLayout.SOUTH);
		
		// Implement the actionListener to start forecasting
		showData.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				int month = Integer.parseInt(inputMonths.getText());
				int DecimalPlaces = Integer.parseInt(inputDecimalPlaces.getText());
				double Ridge = Double.parseDouble(inputRidge.getText());
				// Double threshold = Double.parseDouble(inputThreshold.getText());
				
				
				if (month <= 1 || month > 12 || DecimalPlaces < 1 || DecimalPlaces > 10 || Ridge <= 0 || Ridge > 1 ) {
					JOptionPane.showMessageDialog(null, "Please input appropriate values months 2 to 12, DecimalPlaces 1 to 10, "
							+ "Ridge 0 to 1 exclusive", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						File file = new File("src/forecastingResults.csv");
						FileWriter outputFile = new FileWriter(file);
						CSVWriter writer = new CSVWriter(outputFile);
						String[] header = { "DATE", "VALUE"};
						writer.writeNext(header, false);
						
						InstanceQuery query = new InstanceQuery();
						query.setDatabaseURL("jdbc:mysql://localhost:3306/echodata");
						query.setUsername("root");
						query.setPassword("password");
						query.setQuery(inputQuery);
						Instances rawData = query.retrieveInstances();
						RemoveWithValues filter = new RemoveWithValues();
				        filter.setAttributeIndex("2");
				        filter.setMatchMissingValues(true);
				        filter.setInputFormat(rawData);
				        Instances filteredData = Filter.useFilter(rawData, filter);
				        //System.out.println(filteredData);
						
						int dataIndex = filteredData.toString().indexOf("@data") + 5;
				        String trimmedData = filteredData.toString().substring(dataIndex, filteredData.toString().length()).trim();
				        String[] trimmedDataSplit = trimmedData.split("\n");
				        String[] dates = new String[trimmedDataSplit.length];
				        String[] values = new String[trimmedDataSplit.length];
				        for (int i = 0; i < trimmedDataSplit.length; i++) {
				        	dates[i] = trimmedDataSplit[i].split(",")[0].split("-")[1];
				        	values[i] = trimmedDataSplit[i].split(",")[1];
				        }

						String[] inputData = new String[2];
						for (int i = 0; i < dates.length; i++) {
							inputData[0] = dates[i];
							inputData[1] = values[i];
							writer.writeNext(inputData, false);
						}
						writer.close();
	
						CSVLoader loader = new CSVLoader();
						loader.setSource(new File("src/forecastingResults.csv"));
						Instances data = loader.getDataSet();
						
						/*// Using a dummy test CSV file
						CSVLoader loader = new CSVLoader();
						loader.setSource(new File("test.csv"));//this is a sample file in the src/main/resources
						Instances data = loader.getDataSet();*/
						
						/*InstanceQuery query = new InstanceQuery();
						query.setDatabaseURL("jdbc:mysql://localhost:3306/echodata");
						query.setUsername("root");
						query.setPassword("password");
						query.setQuery(inputQuery);
						Instances data = query.retrieveInstances();
						RemoveWithValues filter = new RemoveWithValues();
				        filter.setAttributeIndex("2");
				        filter.setMatchMissingValues(true);
				        filter.setInputFormat(data);
				        Instances filteredData = Filter.useFilter(data, filter);
				        //System.out.println(filteredData);*/
						
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
						XYSeries series = new XYSeries("Predicted Value");
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
					}
					catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
