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
				Query query = null;
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
				System.out.println(args[0] + " " + args[1]);
				System.out.println("-------------------------------------------------");
			}
		});
		
		JButton loadSummaryDataButton = view.getLoadSummaryDataButton();
		
		loadSummaryDataButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		JButton loadChartButton = view.getLoadChartButton();
		
		loadChartButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
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
	
	private boolean checkYearly(String selected) {
		if (selected.equals("Yearly")) 
			return true;
		return false;
	}
	
	
}
