package NHPIVisualizer;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/*
 * Controller part of MVC architecture.
 * Controller - processes all logic and requests. works with both view and model.
 * Has actionListeners...
 */

public class GUIController {
	
	private GUIView view;
	//private GUIModel model;
	private ResultSet result;
	private ResultSet result2;
	private Query query;
	
	public GUIController(final GUIView view, final GUIModel model) {
		
		this.view = view;
		//this.model = model;
		
		JComboBox<String> geographicalParametersComboBox = view.getGeographicalParametersComboBox();
		
		geographicalParametersComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = (String) view.getGeographicalParametersComboBox().getSelectedItem();
				
				if (selected.equals("2 Provinces")) {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "Province");
				} else if (selected.equals("2 Towns")) {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "Town");
				} else if (selected.equals("3 Provinces")) {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "3 Provinces");
				} else {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "3 Towns");
				}
			}
		});
		
		JComboBox<String> timeGranularityComboBox = view.getTimeGranularityComboBox();
		
		timeGranularityComboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String selected = (String) view.getTimeGranularityComboBox().getSelectedItem();
				
				if (selected.equals("Both Monthly and Yearly")) {
					view.getCardLayout2().show(view.getTimeParametersBMYSubPanel(), "Both");
				} else if (selected.equals("Monthly")) {
					view.getCardLayout2().show(view.getTimeParametersBMYSubPanel(), "Monthly");
				} else {
					view.getCardLayout2().show(view.getTimeParametersBMYSubPanel(), "Yearly");
				}
			}
		});
		
		
		
		JButton loadRawDataButton = view.getLoadRawDataButton();
		
		loadRawDataButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String selected = view.getGeographicalParametersComboBox().getSelectedItem().toString();
				String selected2 = view.getTimeGranularityComboBox().getSelectedItem().toString();
				String[] args = new String[3];
				String startDate = "";
				String endDate = "";
				
				
				if (checkBothMonthlyAndYearly(selected2)) {
					endDate = view.getEndYearComboBox2().getSelectedItem().toString() + "-" + view.getEndMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					startDate = view.getStartYearComboBox2().getSelectedItem().toString() + "-" + view.getStartMonthComboBox2().getSelectedItem().toString().substring(0, 2);
				} else if (checkMonthly(selected2)) {
					endDate = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
					startDate = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
				} else {
					endDate = view.getEndYearComboBox().getSelectedItem().toString();
					startDate = view.getStartYearComboBox().getSelectedItem().toString();
				}
				
				if (!dateErrorChecking(startDate, endDate)) 
					JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
				
				
				if (selected.equals("2 Provinces"))  {
					args[0] = view.getProvinceList1().getSelectedItem().toString();
					args[1] = view.getProvinceList2().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1])) 
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
					query = QueryFactory.createQuery("2 Provinces", args, startDate, endDate);
				} else if (selected.equals("2 Towns")) {
					args[0] = view.getTownList1().getSelectedItem().toString();
					args[1] = view.getTownList2().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1])) 
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
					query = QueryFactory.createQuery("2 Towns", args, startDate, endDate);
				} else if (selected.equals("3 Provinces")) {
					args[0] = view.getThreeProvinceList1().getSelectedItem().toString();
					args[1] = view.getThreeProvinceList2().getSelectedItem().toString();
					args[2] = view.getThreeProvinceList3().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1]) || emptySelectionChecking(args[2])) 
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
					query = QueryFactory.createQuery("3 Provinces", args, startDate, endDate);
				} else {
					args[0] = view.getThreeTownList1().getSelectedItem().toString();
					args[1] = view.getThreeTownList2().getSelectedItem().toString();
					args[2] = view.getThreeTownList3().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1]) || emptySelectionChecking(args[2])) 
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
					query = QueryFactory.createQuery("3 Towns", args, startDate, endDate);
				}
				
				// ADD DATERRORCHECKING... GETRESULTSET, and add rs to table.
				
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
				
				System.out.println("-------------------------------------------------");
				System.out.println(query);
				System.out.println(startDate);
				System.out.println(endDate);
				System.out.println(args[0] + " " + args[1] + " " + args[2]);
				System.out.println("-------------------------------------------------");
			}
		});
		
		JButton loadSummaryDataButton = view.getLoadSummaryDataButton();
		
		loadSummaryDataButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String selected = view.getGeographicalParametersComboBox().getSelectedItem().toString();
				String selected2 = view.getTimeGranularityComboBox().getSelectedItem().toString();
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
				Query query2;
				ResultSet result;
				
				if (checkBothMonthlyAndYearly(selected2)) {
					endDate = view.getEndYearComboBox2().getSelectedItem().toString() + "-" + view.getEndMonthComboBox2().getSelectedItem().toString().substring(0, 2);
					startDate = view.getStartYearComboBox2().getSelectedItem().toString() + "-" + view.getStartMonthComboBox2().getSelectedItem().toString().substring(0, 2);
				} else if (checkMonthly(selected2)) {
					endDate = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
					startDate = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
				} else {
					endDate = view.getEndYearComboBox().getSelectedItem().toString();
					startDate = view.getStartYearComboBox().getSelectedItem().toString();
				}
				
				if (!dateErrorChecking(startDate, endDate)) 
					JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
				
				
				if (selected.equals("2 Provinces"))  {
					args[0] = view.getProvinceList1().getSelectedItem().toString();
					args[1] = view.getProvinceList2().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1])) 
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
					query2 = QueryFactory.createQuery("2 Summary", args, startDate, endDate);
					
					s1AvgHeader = view.getProvinceList1().getSelectedItem() + " Average";
					s2AvgHeader = view.getProvinceList2().getSelectedItem() + " Average";
					s1STDHeader = view.getProvinceList1().getSelectedItem() + " Standard Deviation";
					s2STDHeader = view.getProvinceList2().getSelectedItem() + " Standard Deviation";
					s1MinHeader = view.getProvinceList1().getSelectedItem() + " Min";
					s1MaxHeader = view.getProvinceList1().getSelectedItem() + " Max";
					s2MinHeader = view.getProvinceList2().getSelectedItem() + " Min";
					s2MaxHeader = view.getProvinceList2().getSelectedItem() + " Max";
					
				} else if (selected.equals("2 Towns")) {
					args[0] = view.getTownList1().getSelectedItem().toString();
					args[1] = view.getTownList2().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1])) 
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
					query2 = QueryFactory.createQuery("2 Summary", args, startDate, endDate);
					
					s1AvgHeader = view.getTownList1().getSelectedItem() + " Average";
					s2AvgHeader = view.getTownList2().getSelectedItem() + " Average";
					s1STDHeader = view.getTownList1().getSelectedItem() + " Standard Deviation";
					s2STDHeader = view.getTownList2().getSelectedItem() + " Standard Deviation";
					s1MinHeader = view.getTownList1().getSelectedItem() + " Min";
					s1MaxHeader = view.getTownList1().getSelectedItem() + " Max";
					s2MinHeader = view.getTownList2().getSelectedItem() + " Min";
					s2MaxHeader = view.getTownList2().getSelectedItem() + " Max";
					
				} else if (selected.equals("3 Provinces")) {
					args[0] = view.getThreeProvinceList1().getSelectedItem().toString();
					args[1] = view.getThreeProvinceList2().getSelectedItem().toString();
					args[2] = view.getThreeProvinceList3().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1]) || emptySelectionChecking(args[2])) 
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
					query2 = QueryFactory.createQuery("3 Summary", args, startDate, endDate);
					
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
					
				} else {
					args[0] = view.getThreeTownList1().getSelectedItem().toString();
					args[1] = view.getThreeTownList2().getSelectedItem().toString();
					args[2] = view.getThreeTownList3().getSelectedItem().toString();
					if (emptySelectionChecking(args[0]) || emptySelectionChecking(args[1]) || emptySelectionChecking(args[2])) 
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
					query2 = QueryFactory.createQuery("3 Summary", args, startDate, endDate);
					
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
				
				model.loadData(query2);
				result2 = model.getData();
				DefaultTableModel tableModel = new DefaultTableModel();
				try {
					if (selected.equals("2 Provinces") || selected.equals("2 Towns")) {
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
				
				System.out.println("-------------------------------------------------");
				System.out.println(query);
				System.out.println(s1MaxHeader);
				System.out.println(result2.toString());
				System.out.println(startDate);
				System.out.println(endDate);
				System.out.println(args[0] + " " + args[1]);
				System.out.println("-------------------------------------------------");
			}
		});
		
		JButton loadChartButton = view.getLoadChartButton();
		
		loadChartButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ChartContext chartContext = new ChartContext();
				model.loadData(query);
				ResultSet rs = model.getData();
				
				if (view.getChartTypesComboBox().getSelectedItem().equals("Line Chart")) {
					
					chartContext.setChartStrategy(new LineChartStrategy());
					
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
					
				} else {
					
					chartContext.setChartStrategy(new BarChartStrategy());
					
					if (view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Provinces") || 
							view.getGeographicalParametersComboBox().getSelectedItem().equals("2 Towns")) {
						
						//chartContext.drawChartFor2Series(rs);
						
					} else {
						
						//chartContext.drawChartFor3Series(rs);
						
					}
					
				}
				
				
				
				
			}
		});
		
	}
	
	private boolean dateErrorChecking(String startDate, String endDate) {
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
	
	private boolean emptySelectionChecking(String arg) {
		if (arg.equals("0 - Empty")) 
			return true;
		
		return false;
		
		
	}
	
	private boolean checkBothMonthlyAndYearly(String selected) {
		if (selected.equals("Both Monthly and Yearly")) 
			return true;
		return false;
	}
	
	private boolean checkMonthly(String selected) {
		if (selected.equals("Monthly")) 
			return true;
		return false;
	}
	
//	private boolean checkYearly(String selected) {
//		if (selected.equals("Yearly")) 
//			return true;
//		return false;
//	}
//	
	
}
