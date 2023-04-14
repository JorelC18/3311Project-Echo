package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Arrays;
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

public class summaryDataController extends Controller {
	public summaryDataController(View view, Model model) {
		super(view, model);
	}

	public QueryInterface processSummaryData(final View view, final Model model) {
		JButton loadSummaryDataButton = view.getLoadSummaryDataButton();
		
		loadSummaryDataButton.addActionListener(new ActionListener() {
			
			/**
			 * Adds an action listener to the load summary data button to show data loaded into a table based on the user's selections.
			 * @param e The ActionEvent object that represents the user's action taken
			 */
			
			public void actionPerformed(ActionEvent e) {
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
				QueryInterface summaryQuery;
				
				Controller parentController = new summaryDataController(view, model);
				parentController.setupTimeGranularityMap();
				parentController.setupGeoComboBoxMap();
				
				if (parentController.geoComboBoxSelection.equals("2 Provinces") || parentController.geoComboBoxSelection.equals("2 Towns"))  {
					
					summaryQuery = QueryFactory.createQuery("2 Summary", parentController.args, parentController.startDate, parentController.endDate);
					
					s1AvgHeader = parentController.args[0] + " Average";
					s2AvgHeader = parentController.args[1] + " Average";
					s1STDHeader = parentController.args[0] + " Standard Deviation";
					s2STDHeader = parentController.args[1] + " Standard Deviation";
					s1MinHeader = parentController.args[0] + " Min";
					s1MaxHeader = parentController.args[0] + " Max";
					s2MinHeader = parentController.args[1] + " Min";
					s2MaxHeader = parentController.args[1] + " Max";
					
				} else {
					
					summaryQuery = QueryFactory.createQuery("3 Summary", parentController.args, parentController.startDate, parentController.endDate);
					
					s1AvgHeader = parentController.args[0] + " Average";
					s2AvgHeader = parentController.args[1] + " Average";
					s3AvgHeader = parentController.args[2] + " Average";
					s1STDHeader = parentController.args[0] + " Standard Deviation";
					s2STDHeader = parentController.args[1] + " Standard Deviation";
					s3STDHeader = parentController.args[2] + " Standard Deviation";
					s1MinHeader = parentController.args[0] + " Min";
					s1MaxHeader = parentController.args[0] + " Max";
					s2MinHeader = parentController.args[1] + " Min";
					s2MaxHeader = parentController.args[1] + " Max";
					s3MinHeader = parentController.args[2] + " Min";
					s3MaxHeader = parentController.args[2] + " Max";
					
				} 
				
				model.loadData(summaryQuery);
				result = model.getData();
				DefaultTableModel tableModel = new DefaultTableModel();
				try {
					if (parentController.geoComboBoxSelection.equals("2 Provinces") || parentController.geoComboBoxSelection.equals("2 Towns")) {
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