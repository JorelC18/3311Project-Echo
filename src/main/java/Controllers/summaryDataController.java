package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import MVC_Components.Model;
import MVC_Components.View;
import Query.QueryInterface;
import Query.QueryFactory;

public class summaryDataController {
	private ResultSet result;
	private QueryInterface query;
	
	public QueryInterface processSummaryData(final View view, final Model model) {
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
				QueryInterface summaryQuery;
				
				if (timeComboBoxSelection.equals("Both Monthly and Yearly")) {
					startDate = view.getStartYearComboBox2().getSelectedItem().toString() + "-" + view.getStartMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					endDate = view.getEndYearComboBox2().getSelectedItem().toString() + "-" + view.getEndMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					if (!dateErrorChecking.fullDateErrorChecking(startDate, endDate))  {
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
					if (!dateErrorChecking.partialDateErrorChecking(startMonth, endMonth))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}			
				} 
				else {
					String endYear = view.getEndYearComboBox().getSelectedItem().toString();
					String startYear = view.getStartYearComboBox().getSelectedItem().toString();
					startDate = startYear + "-01";
					endDate = endYear + "-12";
					if (!dateErrorChecking.partialDateErrorChecking(startYear, endYear))  {
						JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
						return;
					}
				}
				
				if (geoComboBoxSelection.equals("2 Provinces"))  {
					args[0] = view.getProvinceList1().getSelectedItem().toString();
					args[1] = view.getProvinceList2().getSelectedItem().toString();
					if (dateErrorChecking.emptySelectionChecking(args[0]) || dateErrorChecking.emptySelectionChecking(args[1])) 
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
					if (dateErrorChecking.emptySelectionChecking(args[0]) || dateErrorChecking.emptySelectionChecking(args[1])) 
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
					if (dateErrorChecking.emptySelectionChecking(args[0]) || dateErrorChecking.emptySelectionChecking(args[1]) || dateErrorChecking.emptySelectionChecking(args[2])) 
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
					if (dateErrorChecking.emptySelectionChecking(args[0]) || dateErrorChecking.emptySelectionChecking(args[1]) || dateErrorChecking.emptySelectionChecking(args[2])) 
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
				result = model.getData();
				DefaultTableModel tableModel = new DefaultTableModel();
				try {
					if (geoComboBoxSelection.equals("2 Provinces") || geoComboBoxSelection.equals("2 Towns")) {
						tableModel.setColumnIdentifiers(new String [] {s1AvgHeader, s2AvgHeader, s1STDHeader, s2STDHeader,
								s1MinHeader, s1MaxHeader, s2MinHeader, s2MaxHeader});
						
						while (result.next()) {
							 tableModel.addRow(new Object [] {
						               result.getString(s1AvgHeader), result.getString(s2AvgHeader), 
						               result.getString(s1STDHeader), result.getString(s2STDHeader),
						               result.getString(s1MinHeader), result.getString(s1MaxHeader), 
						               result.getString(s2MinHeader), result.getString(s2MaxHeader)});
						}
					} else {
						tableModel.setColumnIdentifiers(new String [] {s1AvgHeader, s2AvgHeader, s3AvgHeader, 
								s1STDHeader, s2STDHeader, s3STDHeader,
								s1MinHeader, s1MaxHeader,
								s2MinHeader, s2MaxHeader,
								s3MinHeader, s3MaxHeader});
						
						while (result.next()) {
							 tableModel.addRow(new Object [] {
									   result.getString(s1AvgHeader), result.getString(s2AvgHeader), result.getString(s3AvgHeader), 
						               result.getString(s1STDHeader), result.getString(s2STDHeader), result.getString(s3STDHeader),
						               result.getString(s1MinHeader), result.getString(s1MaxHeader), 
						               result.getString(s2MinHeader), result.getString(s2MaxHeader),
						               result.getString(s3MinHeader), result.getString(s3MaxHeader)});
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
		return query;
	}
}
