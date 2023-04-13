package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import MVC_Components.Model;
import MVC_Components.View;
import Panels.BothMonthlyAndYearlyPanel;
import Panels.GeoPanel;
import Panels.MonthlyPanel;
import Panels.ProvincePanel;
import Panels.ThreeProvincesPanel;
import Panels.ThreeTownsPanel;
import Panels.TimePanel;
import Panels.TownPanel;
import Panels.YearlyPanel;
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
			
				HashMap<String, TimePanel> timeGranularityMap = new HashMap<String, TimePanel>();
				timeGranularityMap.put("Both Monthly and Yearly", new BothMonthlyAndYearlyPanel());
				timeGranularityMap.put("Monthly", new MonthlyPanel());
				timeGranularityMap.put("Yearly", new YearlyPanel());
				startDate = timeGranularityMap.get(timeComboBoxSelection).getStartDate(view);
				endDate = timeGranularityMap.get(timeComboBoxSelection).getEndDate(view);
				timeGranularityMap.get(timeComboBoxSelection).dateErrorChecking(view, startDate, endDate);
				
				HashMap<String, GeoPanel> geoComboBoxMap = new HashMap<String, GeoPanel>();
				geoComboBoxMap.put("2 Provinces", new ProvincePanel());
			    geoComboBoxMap.put("2 Towns", new TownPanel());
			    geoComboBoxMap.put("3 Provinces", new ThreeProvincesPanel());
			    geoComboBoxMap.put("3 Towns", new ThreeTownsPanel());
			    args = geoComboBoxMap.get(geoComboBoxSelection).getPreArgs(view);
			    geoComboBoxMap.get(geoComboBoxSelection).emptySelectionChecking(view);
				query = QueryFactory.createQuery(geoComboBoxSelection, args, startDate, endDate);
				
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
