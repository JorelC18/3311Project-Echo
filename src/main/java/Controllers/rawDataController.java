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

public class rawDataController {
	private ResultSet result;
	private QueryInterface query;
	
	public void processRawData(final View view, final Model model) {
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
					if (dateErrorChecking.emptySelectionChecking(args[0]) || dateErrorChecking.emptySelectionChecking(args[1])) {
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
						return;
					}
					query = QueryFactory.createQuery("2 Provinces", args, startDate, endDate);
				} 
				else if (geoComboBoxSelection.equals("2 Towns")) {
					args[0] = view.getTownList1().getSelectedItem().toString();
					args[1] = view.getTownList2().getSelectedItem().toString();
					if (dateErrorChecking.emptySelectionChecking(args[0]) || dateErrorChecking.emptySelectionChecking(args[1])) {
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
						return;
					}
					query = QueryFactory.createQuery("2 Towns", args, startDate, endDate);
				} 
				else if (geoComboBoxSelection.equals("3 Provinces")) {
					args[0] = view.getThreeProvinceList1().getSelectedItem().toString();
					args[1] = view.getThreeProvinceList2().getSelectedItem().toString();
					args[2] = view.getThreeProvinceList3().getSelectedItem().toString();
					if (dateErrorChecking.emptySelectionChecking(args[0]) || dateErrorChecking.emptySelectionChecking(args[1]) || dateErrorChecking.emptySelectionChecking(args[2])) {
						JOptionPane.showMessageDialog(view.getFrame(), "One or more selections is empty.");
						return;
					}
					query = QueryFactory.createQuery("3 Provinces", args, startDate, endDate);
				} 
				else {
					args[0] = view.getThreeTownList1().getSelectedItem().toString();
					args[1] = view.getThreeTownList2().getSelectedItem().toString();
					args[2] = view.getThreeTownList3().getSelectedItem().toString();
					if (dateErrorChecking.emptySelectionChecking(args[0]) || dateErrorChecking.emptySelectionChecking(args[1]) || dateErrorChecking.emptySelectionChecking(args[2])) {
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
	}
}
