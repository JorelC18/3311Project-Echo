package MVC_Components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Chart.BarChartStrategy;
import Chart.ChartContext;
import Chart.LineChartStrategy;
import Forecasting.ForecastingContext;
import Forecasting.ForecastingStrategy;
import Forecasting.LinearRegressionStrategy;
import Query.Query;
import Query.QueryFactory;
import Test.TestContext;
import Test.t_TestStrategy;


/**
 * Controller part of MVC architecture.
 * It processes all logic and requests. works with both view and model.
 * @author Jorel Louie Chim
 */

public class Controller {
	
	private ResultSet result;
	private ResultSet result2;
	private Query query;
	
	String geoComboBoxSelection;
	
	/**
	 * Constructor for the controller part.
	 * @param view view part of the MVC architecture
	 * @param model model part of the MVC architecture
	 */
	
	public Controller(final View view, final Model model) {
		
		// Change drop down menus based on geographical parameter selection.
		
		JComboBox<String> geographicalParametersComboBox = view.getGeographicalParametersComboBox();
		
		/**
		 * Adds an action listener to the geographical parameters combo box to show different combo boxes depending on the user's selection.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		geographicalParametersComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String geoComboBoxSelection = view.getGeographicalParametersComboBox().getSelectedItem().toString();
				if (geoComboBoxSelection.equals("2 Provinces")) {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "Province");
				} else if (geoComboBoxSelection.equals("2 Towns")) {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "Town");
				} else if (geoComboBoxSelection.equals("3 Provinces")) {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "3 Provinces");
				} else {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "3 Towns");
				}
			}
		});
		
		// Change drop down menus based on time granularity parameter selection.
		
		JComboBox<String> timeGranularityComboBox = view.getTimeGranularityComboBox();
		
		/**
		 * Adds an action listener to the time granularity combo box to show different combo boxes depending on the user's selection.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		timeGranularityComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String timeComboBoxSelection = view.getTimeGranularityComboBox().getSelectedItem().toString();
				if (timeComboBoxSelection.equals("Both Monthly and Yearly")) {
					view.getCardLayout2().show(view.getTimeParametersBMYSubPanel(), "Both");
				} else if (timeComboBoxSelection.equals("Monthly")) {
					view.getCardLayout2().show(view.getTimeParametersBMYSubPanel(), "Monthly");
				} else {
					view.getCardLayout2().show(view.getTimeParametersBMYSubPanel(), "Yearly");
				}
			}
		});
		
		// Process raw data.
		
		JButton loadRawDataButton = view.getLoadRawDataButton();
		
		/**
		 * Adds an action listener to the load raw data button to show data loaded into a table based on the user's selections.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		loadRawDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String geoComboBoxSelection = view.getGeographicalParametersComboBox().getSelectedItem().toString();
				String timeComboBoxSelection = view.getTimeGranularityComboBox().getSelectedItem().toString();
				String[] args = new String[3];
				String startDate = "";
				String endDate = "";
			
				if (timeComboBoxSelection.equals("Both Monthly and Yearly")) {
					startDate = view.getStartYearComboBox2().getSelectedItem().toString() + "-" + view.getStartMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					endDate = view.getEndYearComboBox2().getSelectedItem().toString() + "-" + view.getEndMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					if (!fullDateErrorChecking(startDate, endDate))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				} 
				else if (timeComboBoxSelection.equals("Monthly")) {
					String endMonth = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String startMonth = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String selectedYear = view.getYearComboBox().getSelectedItem().toString();
					startDate = selectedYear + "-" + startMonth;
					endDate = selectedYear + "-" + endMonth;
					if (!partialDateErrorChecking(startMonth, endMonth))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}			
				} 
				else {
					String endYear = view.getEndYearComboBox().getSelectedItem().toString();
					String startYear = view.getStartYearComboBox().getSelectedItem().toString();
					startDate = startYear + "-01";
					endDate = endYear + "-12";
					if (!partialDateErrorChecking(startYear, endYear))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				}
				
				if (geoComboBoxSelection.equals("2 Provinces"))  {
					args[0] = view.getProvinceList1().getSelectedItem().toString();
					args[1] = view.getProvinceList2().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1])) {
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
						return;
					}
					query = QueryFactory.createQuery("2 Provinces", args, startDate, endDate);
				} 
				else if (geoComboBoxSelection.equals("2 Towns")) {
					args[0] = view.getTownList1().getSelectedItem().toString();
					args[1] = view.getTownList2().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1])) {
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
						return;
					}
					query = QueryFactory.createQuery("2 Towns", args, startDate, endDate);
				} 
				else if (geoComboBoxSelection.equals("3 Provinces")) {
					args[0] = view.getThreeProvinceList1().getSelectedItem().toString();
					args[1] = view.getThreeProvinceList2().getSelectedItem().toString();
					args[2] = view.getThreeProvinceList3().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1]) || emptySelectionChecking(args[2])) {
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
						return;
					}
					query = QueryFactory.createQuery("3 Provinces", args, startDate, endDate);
				} 
				else {
					args[0] = view.getThreeTownList1().getSelectedItem().toString();
					args[1] = view.getThreeTownList2().getSelectedItem().toString();
					args[2] = view.getThreeTownList3().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1]) || emptySelectionChecking(args[2])) {
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
						return;
					}
					query = QueryFactory.createQuery("3 Towns", args, startDate, endDate);
				}
				
				System.out.println(query.getQuery());
				model.loadData(query);
				result = model.getData();
				
				DefaultTableModel tableModel = new DefaultTableModel();
				tableModel.setColumnIdentifiers(new String [] {"REF_DATE", "GEO", "NHPIs", "VALUE"});
				try {
					while (result.next()) {
						 tableModel.addRow(new Object [] {
					               result.getString("REF_DATE"), result.getString("GEO"), result.getString("NHPIs"), result.getString("VALUE")});
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				view.getRawDataTable().setModel(tableModel);
				view.getRawDataTable().getColumnModel().getColumn(1).setPreferredWidth(250);
				view.getTablePanel().revalidate();
				view.getTablePanel().repaint();
				view.getCardLayout3().show(view.getTablePanel(), "Raw Data");
				
			}
		});
		
		// Process summary data.
		
		JButton loadSummaryDataButton = view.getLoadSummaryDataButton();
		
		loadSummaryDataButton.addActionListener(new ActionListener() {
			
			/**
			 * Adds an action listener to the load summary data button to show data loaded into a table based on the user's selections.
			 * @param e The ActionEvent object that represents the user's action taken
			 */
			
			public void actionPerformed(ActionEvent e) {
				
				String geoComboBoxSelection = view.getGeographicalParametersComboBox().getSelectedItem().toString();
				String timeComboBoxSelection = view.getTimeGranularityComboBox().getSelectedItem().toString();
				String[] args = new String[3];
				String s1AvgHeader = "";
				String s2AvgHeader = "";
				String s3AvgHeader = "";
				String s1STDHeader = "";
				String s2STDHeader = "";
				String s3STDHeader = "";
				String s1MinHeader = "";
				String s1MaxHeader = "";
				String s2MinHeader = "";
				String s2MaxHeader = "";
				String s3MinHeader = "";
				String s3MaxHeader = "";
				String startDate = "";
				String endDate = "";
				Query summaryQuery;
				
				if (timeComboBoxSelection.equals("Both Monthly and Yearly")) {
					startDate = view.getStartYearComboBox2().getSelectedItem().toString() + "-" + view.getStartMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					endDate = view.getEndYearComboBox2().getSelectedItem().toString() + "-" + view.getEndMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					if (!fullDateErrorChecking(startDate, endDate))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				} 
				else if (timeComboBoxSelection.equals("Monthly")) {
					String endMonth = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String startMonth = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String selectedYear = view.getYearComboBox().getSelectedItem().toString();
					startDate = selectedYear + "-" + startMonth;
					endDate = selectedYear + "-" + endMonth;
					if (!partialDateErrorChecking(startMonth, endMonth))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}			
				} 
				else {
					String endYear = view.getEndYearComboBox().getSelectedItem().toString();
					String startYear = view.getStartYearComboBox().getSelectedItem().toString();
					startDate = startYear + "-01";
					endDate = endYear + "-12";
					if (!partialDateErrorChecking(startYear, endYear))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				}
				
				if (geoComboBoxSelection.equals("2 Provinces"))  {
					args[0] = view.getProvinceList1().getSelectedItem().toString();
					args[1] = view.getProvinceList2().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1])) 
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
					summaryQuery = QueryFactory.createQuery("2 Summary", args, startDate, endDate);
					
					s1AvgHeader = view.getProvinceList1().getSelectedItem() + " Average";
					s2AvgHeader = view.getProvinceList2().getSelectedItem() + " Average";
					s1STDHeader = view.getProvinceList1().getSelectedItem() + " Standard Deviation";
					s2STDHeader = view.getProvinceList2().getSelectedItem() + " Standard Deviation";
					s1MinHeader = view.getProvinceList1().getSelectedItem() + " Min";
					s1MaxHeader = view.getProvinceList1().getSelectedItem() + " Max";
					s2MinHeader = view.getProvinceList2().getSelectedItem() + " Min";
					s2MaxHeader = view.getProvinceList2().getSelectedItem() + " Max";
					
				} 
				else if (geoComboBoxSelection.equals("2 Towns")) {
					args[0] = view.getTownList1().getSelectedItem().toString();
					args[1] = view.getTownList2().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1])) 
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
					summaryQuery = QueryFactory.createQuery("2 Summary", args, startDate, endDate);
					
					s1AvgHeader = view.getTownList1().getSelectedItem() + " Average";
					s2AvgHeader = view.getTownList2().getSelectedItem() + " Average";
					s1STDHeader = view.getTownList1().getSelectedItem() + " Standard Deviation";
					s2STDHeader = view.getTownList2().getSelectedItem() + " Standard Deviation";
					s1MinHeader = view.getTownList1().getSelectedItem() + " Min";
					s1MaxHeader = view.getTownList1().getSelectedItem() + " Max";
					s2MinHeader = view.getTownList2().getSelectedItem() + " Min";
					s2MaxHeader = view.getTownList2().getSelectedItem() + " Max";
					
				} 
				else if (geoComboBoxSelection.equals("3 Provinces")) {
					args[0] = view.getThreeProvinceList1().getSelectedItem().toString();
					args[1] = view.getThreeProvinceList2().getSelectedItem().toString();
					args[2] = view.getThreeProvinceList3().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1]) || emptySelectionChecking(args[2])) 
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
					summaryQuery = QueryFactory.createQuery("3 Summary", args, startDate, endDate);
					
					s1AvgHeader = view.getThreeProvinceList1().getSelectedItem() + " Average";
					s2AvgHeader = view.getThreeProvinceList2().getSelectedItem() + " Average";
					s3AvgHeader = view.getThreeProvinceList3().getSelectedItem() + " Average";
					s1STDHeader = view.getThreeProvinceList1().getSelectedItem() + " Standard Deviation";
					s2STDHeader = view.getThreeProvinceList2().getSelectedItem() + " Standard Deviation";
					s3STDHeader = view.getThreeProvinceList3().getSelectedItem() + " Standard Deviation";
					s1MinHeader = view.getThreeProvinceList1().getSelectedItem() + " Min";
					s1MaxHeader = view.getThreeProvinceList1().getSelectedItem() + " Max";
					s2MinHeader = view.getThreeProvinceList2().getSelectedItem() + " Min";
					s2MaxHeader = view.getThreeProvinceList2().getSelectedItem() + " Max";
					s3MinHeader = view.getThreeProvinceList3().getSelectedItem() + " Min";
					s3MaxHeader = view.getThreeProvinceList3().getSelectedItem() + " Max";
					
				} 
				else {
					args[0] = view.getThreeTownList1().getSelectedItem().toString();
					args[1] = view.getThreeTownList2().getSelectedItem().toString();
					args[2] = view.getThreeTownList3().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1]) || emptySelectionChecking(args[2])) 
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
					summaryQuery = QueryFactory.createQuery("3 Summary", args, startDate, endDate);
					
					s1AvgHeader = view.getThreeTownList1().getSelectedItem() + " Average";
					s2AvgHeader = view.getThreeTownList2().getSelectedItem() + " Average";
					s3AvgHeader = view.getThreeTownList3().getSelectedItem() + " Average";
					s1STDHeader = view.getThreeTownList1().getSelectedItem() + " Standard Deviation";
					s2STDHeader = view.getThreeTownList2().getSelectedItem() + " Standard Deviation";
					s3STDHeader = view.getThreeTownList3().getSelectedItem() + " Standard Deviation";
					s1MinHeader = view.getThreeTownList1().getSelectedItem() + " Min";
					s1MaxHeader = view.getThreeTownList1().getSelectedItem() + " Max";
					s2MinHeader = view.getThreeTownList2().getSelectedItem() + " Min";
					s2MaxHeader = view.getThreeTownList2().getSelectedItem() + " Max";
					s3MinHeader = view.getThreeTownList3().getSelectedItem() + " Min";
					s3MaxHeader = view.getThreeTownList3().getSelectedItem() + " Max";
				}
				
				System.out.println(summaryQuery.getQuery());
				model.loadData(summaryQuery);
				result2 = model.getData();
				DefaultTableModel tableModel = new DefaultTableModel();
				try {
					if (geoComboBoxSelection.equals("2 Provinces") || geoComboBoxSelection.equals("2 Towns")) {
						tableModel.setColumnIdentifiers(new String [] {s1AvgHeader, s2AvgHeader, s1STDHeader, s2STDHeader,
								s1MinHeader, s1MaxHeader, s2MinHeader, s2MaxHeader});
						
						while (result2.next()) {
							 tableModel.addRow(new Object [] {
						               result2.getString(s1AvgHeader), result2.getString(s2AvgHeader), 
						               result2.getString(s1STDHeader), result2.getString(s2STDHeader),
						               result2.getString(s1MinHeader), result2.getString(s1MaxHeader), 
						               result2.getString(s2MinHeader), result2.getString(s2MaxHeader)});
						}
					} else {
						tableModel.setColumnIdentifiers(new String [] {s1AvgHeader, s2AvgHeader, s3AvgHeader, 
								s1STDHeader, s2STDHeader, s3STDHeader,
								s1MinHeader, s1MaxHeader,
								s2MinHeader, s2MaxHeader,
								s3MinHeader, s3MaxHeader});
						
						while (result2.next()) {
							 tableModel.addRow(new Object [] {
									   result2.getString(s1AvgHeader), result2.getString(s2AvgHeader), result2.getString(s3AvgHeader), 
						               result2.getString(s1STDHeader), result2.getString(s2STDHeader), result2.getString(s3STDHeader),
						               result2.getString(s1MinHeader), result2.getString(s1MaxHeader), 
						               result2.getString(s2MinHeader), result2.getString(s2MaxHeader),
						               result2.getString(s3MinHeader), result2.getString(s3MaxHeader)});
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				view.getSummaryDataTable().setModel(tableModel);
				for (int i = 0; i < view.getSummaryDataTable().getColumnModel().getColumnCount(); i++) {
					view.getSummaryDataTable().getColumnModel().getColumn(i).setPreferredWidth(250);
				}
				view.getTablePanel().revalidate();
				view.getTablePanel().repaint();
				view.getCardLayout3().show(view.getTablePanel(), "Summary Data");
				
			}
		});
		
		// Process chart data.
		
		JButton loadChartButton = view.getLoadChartButton();
		
		/**
		 * Adds an action listener to the load chart button to show data loaded into a chart based on the user's selections.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		loadChartButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ChartContext chartContext = new ChartContext();
				model.loadData(query);
				ResultSet rs = model.getData();
				
				// Date Error Checking:
				
				String startDate = "";
				String endDate = "";
				String timeComboBoxSelection = view.getTimeGranularityComboBox().getSelectedItem().toString();
				
				if (timeComboBoxSelection.equals("Both Monthly and Yearly")) {
					startDate = view.getStartYearComboBox2().getSelectedItem().toString() + "-" + view.getStartMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					endDate = view.getEndYearComboBox2().getSelectedItem().toString() + "-" + view.getEndMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					if (!fullDateErrorChecking(startDate, endDate))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				} 
				else if (timeComboBoxSelection.equals("Monthly")) {
					String endMonth = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String startMonth = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String selectedYear = view.getYearComboBox().getSelectedItem().toString();
					startDate = selectedYear + "-" + startMonth;
					endDate = selectedYear + "-" + endMonth;
					if (!partialDateErrorChecking(startMonth, endMonth))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}			
				} 
				else {
					String endYear = view.getEndYearComboBox().getSelectedItem().toString();
					String startYear = view.getStartYearComboBox().getSelectedItem().toString();
					startDate = startYear + "-01";
					endDate = endYear + "-12";
					if (!partialDateErrorChecking(startYear, endYear))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				}
				
				// End of Date Error Checking
				
				if (view.getChartTypesComboBox().getSelectedItem().equals("Line Chart")) {
					
					chartContext.setChartStrategy(new LineChartStrategy());
					
					if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces") || 
							view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Towns")) {
						
						if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces")) {
							
							if (emptySelectionChecking(view.getProvinceList1().getSelectedItem().toString()) || emptySelectionChecking(view.getProvinceList2().getSelectedItem().toString())) {
								JOptionPane.showMessageDialog(view.getFrame(), "One or more selections are empty / raw data has not been loaded.");
								return;
							}
							
							chartContext.drawChartFor2Series(rs, view.getProvinceList1().getSelectedItem().toString(),
									view.getProvinceList2().getSelectedItem().toString());
							
						} else {
							
							if (emptySelectionChecking(view.getTownList1().getSelectedItem().toString()) || emptySelectionChecking(view.getTownList2().getSelectedItem().toString())) {
								JOptionPane.showMessageDialog(view.getFrame(), "One or more selections are empty / raw data has not been loaded.");
								return;
							}
							chartContext.drawChartFor2Series(rs, view.getTownList1().getSelectedItem().toString(),
									view.getTownList2().getSelectedItem().toString());
						}
						
					} else {
						
						if (view.getGeographicalParametersComboBox().getSelectedItem().equals("3 Provinces")) {
							
							if (emptySelectionChecking(view.getThreeProvinceList1().getSelectedItem().toString()) || emptySelectionChecking(view.getThreeProvinceList2().getSelectedItem().toString())
									|| emptySelectionChecking(view.getThreeProvinceList3().getSelectedItem().toString())) {
								JOptionPane.showMessageDialog(view.getFrame(), "One or more selections are empty / raw data has not been loaded.");
								return;
							}
							
							chartContext.drawChartFor3Series(rs, view.getThreeProvinceList1().getSelectedItem().toString(),
									view.getThreeProvinceList2().getSelectedItem().toString(),
									view.getThreeProvinceList3().getSelectedItem().toString());
							
						} else {
							
							if (emptySelectionChecking(view.getThreeTownList1().getSelectedItem().toString()) || emptySelectionChecking(view.getThreeTownList2().getSelectedItem().toString())
									|| emptySelectionChecking(view.getThreeTownList3().getSelectedItem().toString())) {
								JOptionPane.showMessageDialog(view.getFrame(), "One or more selections are empty / raw data has not been loaded.");
								return;
							}
							
							chartContext.drawChartFor3Series(rs, view.getThreeTownList1().getSelectedItem().toString(),
									view.getThreeTownList2().getSelectedItem().toString(),
									view.getThreeTownList3().getSelectedItem().toString());
						}
						
						
					}
					
				} else {
					
					chartContext.setChartStrategy(new BarChartStrategy());
					
					if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces") || 
							view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Towns")) {
						
						if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces")) {
							chartContext.drawChartFor2Series(rs, view.getProvinceList1().getSelectedItem().toString(),
									view.getProvinceList2().getSelectedItem().toString());
						} else {
							chartContext.drawChartFor2Series(rs, view.getTownList1().getSelectedItem().toString(),
									view.getTownList2().getSelectedItem().toString());
						}
						
					} else {
						
						if (view.getGeographicalParametersComboBox().getSelectedItem().equals("3 Provinces")) {
							chartContext.drawChartFor3Series(rs, view.getThreeProvinceList1().getSelectedItem().toString(),
									view.getThreeProvinceList2().getSelectedItem().toString(),
									view.getThreeProvinceList3().getSelectedItem().toString());
						} else {
							chartContext.drawChartFor3Series(rs, view.getThreeTownList1().getSelectedItem().toString(),
									view.getThreeTownList2().getSelectedItem().toString(),
									view.getThreeTownList3().getSelectedItem().toString());
						}
						
					}
					
				}
				
			}
		});
		
		// T-Testing
		
		JButton loadTestButton = view.getLoadTestButton();
		
		/**
		 * Adds an action listener to the load test button to show values resulting from a statistical test based on the user's selections.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		loadTestButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				TestContext testContext = new TestContext();
				model.loadData(query);
				ResultSet rs = model.getData();
				
				// Date Error Checking:
				
				String startDateCheck = "";
				String endDateCheck = "";
				String timeComboBoxSelection = view.getTimeGranularityComboBox().getSelectedItem().toString();
				
				if (timeComboBoxSelection.equals("Both Monthly and Yearly")) {
					startDateCheck = view.getStartYearComboBox2().getSelectedItem().toString() + "-" + view.getStartMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					endDateCheck = view.getEndYearComboBox2().getSelectedItem().toString() + "-" + view.getEndMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					if (!fullDateErrorChecking(startDateCheck, endDateCheck))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				} 
				else if (timeComboBoxSelection.equals("Monthly")) {
					String endMonth = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String startMonth = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String selectedYear = view.getYearComboBox().getSelectedItem().toString();
					startDateCheck = selectedYear + "-" + startMonth;
					endDateCheck = selectedYear + "-" + endMonth;
					if (!partialDateErrorChecking(startMonth, endMonth))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}			
				} 
				else {
					String endYear = view.getEndYearComboBox().getSelectedItem().toString();
					String startYear = view.getStartYearComboBox().getSelectedItem().toString();
					startDateCheck = startYear + "-01";
					endDateCheck = endYear + "-12";
					if (!partialDateErrorChecking(startYear, endYear))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				}
				
				// End of Date Error Checking
				
				testContext.setTestStrategy(new t_TestStrategy());
				
				// Display error message when either 3 provinces or 3 towns is selected
				if (view.getGeographicalParametersComboBox().getSelectedItem().equals("3 Provinces") 
						|| view.getGeographicalParametersComboBox().getSelectedItem().equals("3 Towns")) {
					
					JOptionPane.showMessageDialog(view.getFrame(), "Please select 2 provinces or 2 towns");
					
				}
				else {
					// Check if either of the two province inputs are empty
					if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces")) {
						if (emptySelectionChecking(view.getProvinceList1().getSelectedItem().toString()) 
								|| emptySelectionChecking(view.getProvinceList2().getSelectedItem().toString())) {
							
							JOptionPane.showMessageDialog(view.getFrame(), "One or more selections are empty / test can not perform.");
							return;
						}
						
						String[] provinceInput = new String[1];
						provinceInput[0] = view.getProvinceList1().getSelectedItem().toString();
						String startDate = view.startYearComboBox2.getSelectedItem().toString() + "-" + view.startMonthComboBox2.getSelectedItem().toString().substring(0, 2);
						String endDate = view.endYearComboBox2.getSelectedItem().toString() + "-" + view.endMonthComboBox2.getSelectedItem().toString().substring(0, 2);
						String query1 = QueryFactory.createQuery("t_Test", provinceInput, startDate, endDate).getQuery();
						
						String[] provinceInput2 = new String[1];
						provinceInput2[0] = view.getProvinceList2().getSelectedItem().toString();
						String query2 = QueryFactory.createQuery("t_Test", provinceInput2, startDate, endDate).getQuery();
						
						testContext.t_Test(rs, query1, query2);
					
						
				}
				else {
						// Check if either of the two town inputs are empty
						if (emptySelectionChecking(view.getTownList1().getSelectedItem().toString()) 
								|| emptySelectionChecking(view.getTownList2().getSelectedItem().toString())) {
							
							JOptionPane.showMessageDialog(view.getFrame(), "One or more selections are empty / test can not perform .");
							return;
						}
						
						String[] townInput = new String[1];
						townInput[0] = view.getTownList1().getSelectedItem().toString();
						String startDate = view.startYearComboBox2.getSelectedItem().toString() + "-" + view.startMonthComboBox2.getSelectedItem().toString().substring(0, 2);
						String endDate = view.endYearComboBox2.getSelectedItem().toString() + "-" + view.endMonthComboBox2.getSelectedItem().toString().substring(0, 2);
						String query1 = QueryFactory.createQuery("t_Test", townInput, startDate, endDate).getQuery();
						
						String[] townInput2 = new String[1];
						townInput2[0] = view.getTownList2().getSelectedItem().toString();
						String query2 = QueryFactory.createQuery("t_Test", townInput2, startDate, endDate).getQuery();
						
						testContext.t_Test(rs, query1, query2);
						
					}
				}
				
				
			}
			
		});
		
		// Forecasting
		
		JButton loadForecastingButton = view.getLoadForecastingButton();
		
		/**
		 * Adds an action listener to the load forecasting button to start a forecasting algorithm.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		loadForecastingButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				// Date Error Checking:
				
				String startDateCheck = "";
				String endDateCheck = "";
				String timeComboBoxSelection = view.getTimeGranularityComboBox().getSelectedItem().toString();
				
				if (timeComboBoxSelection.equals("Both Monthly and Yearly")) {
					startDateCheck = view.getStartYearComboBox2().getSelectedItem().toString() + "-" + view.getStartMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					endDateCheck = view.getEndYearComboBox2().getSelectedItem().toString() + "-" + view.getEndMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					if (!fullDateErrorChecking(startDateCheck, endDateCheck))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				} 
				else if (timeComboBoxSelection.equals("Monthly")) {
					String endMonth = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String startMonth = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
					String selectedYear = view.getYearComboBox().getSelectedItem().toString();
					startDateCheck = selectedYear + "-" + startMonth;
					endDateCheck = selectedYear + "-" + endMonth;
					if (!partialDateErrorChecking(startMonth, endMonth))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}			
				} 
				else {
					String endYear = view.getEndYearComboBox().getSelectedItem().toString();
					String startYear = view.getStartYearComboBox().getSelectedItem().toString();
					startDateCheck = startYear + "-01";
					endDateCheck = endYear + "-12";
					if (!partialDateErrorChecking(startYear, endYear))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				}
				
				// End of Date Error Checking
				
				ForecastingContext forecastingContext = new ForecastingContext();
				forecastingContext.setForecastingStrategy(new LinearRegressionStrategy());
				
				if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces")) {
				
					String[] provinceInput = new String[1];
					provinceInput[0] = view.getProvinceList1().getSelectedItem().toString();
					String startDate = view.startYearComboBox2.getSelectedItem().toString() + "-" + view.startMonthComboBox2.getSelectedItem().toString().substring(0, 2);
					String endDate = view.endYearComboBox2.getSelectedItem().toString() + "-" + view.endMonthComboBox2.getSelectedItem().toString().substring(0, 2);
					String provinceQuery = QueryFactory.createQuery("Forecasting", provinceInput, startDate, endDate).getQuery();;
					
					forecastingContext.LinearRegressionForecasting(provinceInput[0], provinceQuery);
					
				}
				else if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Towns")) {
					String[] townInput = new String[1];
					townInput[0] = view.getTownList1().getSelectedItem().toString();
					String startDate = view.startYearComboBox2.getSelectedItem().toString() + "-" + view.startMonthComboBox2.getSelectedItem().toString().substring(0, 2);
					String endDate = view.endYearComboBox2.getSelectedItem().toString() + "-" + view.endMonthComboBox2.getSelectedItem().toString().substring(0, 2);
					String townQuery = QueryFactory.createQuery("Forecasting", townInput, startDate, endDate).getQuery();
					
					forecastingContext.LinearRegressionForecasting(townInput[0], townQuery);
				}
				else {
					JOptionPane.showMessageDialog(view.getFrame(), "Please select 2 provinces or 2 towns.");
					return;
				}
			}
		});
		
	}
	
	// Error checking helper methods.
	
	/**
	 * Checks if the date interval for both yearly and monthly selected from the user is valid.
	 * @param startDate the start date selected by the user
	 * @param endDate the end date selected by the user
	 * @return false if the date interval is invalid. true otherwise.
	 */
	
	private boolean fullDateErrorChecking(String startDate, String endDate) {
		Integer startYear = Integer.parseInt(startDate.substring(0, 4));
		Integer endYear = Integer.parseInt(endDate.substring(0, 4));
		Integer startMonth = Integer.parseInt(startDate.substring(5, 7));
		Integer endMonth = Integer.parseInt(endDate.substring(5, 7));
		
		if (startYear > endYear) {
			return false;
		}
		
		if (startYear.equals(endYear)) {
			if (startMonth > endMonth) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Checks if the date interval for just yearly or just monthly selected from the user is valid.
	 * @param startDate the start date selected by the user
	 * @param endDate the end date selected by the user
	 * @return false if the date interval is invalid. true otherwise.
	 */
	
	private boolean partialDateErrorChecking(String startDate, String endDate) {
		if (Integer.parseInt(startDate)> Integer.parseInt(endDate)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the user has made a selection for all fields required.
	 * @param arg the selection made from the user
	 * @return true if a selection is empty. false otherwise
	 */
	
	private boolean emptySelectionChecking(String arg) {
		if (arg.equals("0 - Empty")) 
			return true;
		
		return false;
		
		
	}
}