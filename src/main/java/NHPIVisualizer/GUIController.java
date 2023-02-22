package NHPIVisualizer;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.*;


/*
 * Controller part of MVC architecture.
 * Controller - processes all logic and requests. works with both view and model.
 * Has actionListeners...
 */

public class GUIController {
	
	private GUIView view;

	
	public GUIController(final GUIView view) {
		
		this.view = view;
		
		JComboBox<String> geographicalParametersComboBox = view.getGeographicalParametersComboBox();
		
		geographicalParametersComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = (String) view.getGeographicalParametersComboBox().getSelectedItem();
				
				if (selected.equals("Province")) {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "Province");
				} else {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "Town");
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
		
		JButton addTimeSeriesButton = view.getAddTimeSeriesButton();
		
		addTimeSeriesButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String selected = (String) view.getTimeGranularityComboBox().getSelectedItem();
				
				if (selected.equals("Province")) {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "3 Provinces");
				} else {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "3 Towns");
				}
				
			}
		});
		
		JButton resetTimeSeriesButton = view.getResetButton();
		
		resetTimeSeriesButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String selected = (String) view.getGeographicalParametersComboBox().getSelectedItem();
				
				if (selected.equals("Province")) {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "Province");
				} else {
					view.getCardLayout1().show(view.getGeographicalParametersPTSubPanel(), "Town");
				}
				
			}
		});
		
		JButton loadRawDataButton = view.getLoadRawDataButton();
		
		loadRawDataButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				GUIModel model = GUIModel.createConnection(GUIModel.url, 
						GUIModel.username, 
						GUIModel.password
				);
				
				String currentCard = ((CardLayout) view.getGeographicalParametersPTSubPanel().getLayout()).toString();
				String currentCard2 = ((CardLayout) view.getTimeParametersBMYSubPanel().getLayout()).toString();
				String[] args = new String[3];
				String startDate = "";
				String endDate = "";
				Query query = null;
				
				if (checkBothMonthlyAndYearly(currentCard2)) {
					endDate = view.getEndYearComboBox().getSelectedItem().toString() + "-" + view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
					startDate = view.getStartYearComboBox().getSelectedItem().toString() + "-" + view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
				} else if (checkMonthly(currentCard2)) {
					endDate = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
					startDate = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
				} else if (checkYearly(currentCard2)) {
					endDate = view.getEndYearComboBox().getSelectedItem().toString();
					startDate = view.getStartYearComboBox().getSelectedItem().toString();
				}
				
				
				if (currentCard.equals("Province"))  {
					args[0] = view.getProvinceList1().getSelectedItem().toString();
					args[1] = view.getProvinceList2().getSelectedItem().toString();
					query = QueryFactory.createQuery("2 Provinces", args, startDate, endDate);
				} else if (currentCard.equals("Town")) {
					args[0] = view.getTownList1().getSelectedItem().toString();
					args[1] = view.getTownList2().getSelectedItem().toString();
					query = QueryFactory.createQuery("2 Towns", args, startDate, endDate);
				} else if (currentCard.equals("3 Provinces")) {
					args[0] = view.getThreeProvinceList1().getSelectedItem().toString();
					args[1] = view.getThreeProvinceList2().getSelectedItem().toString();
					args[2] = view.getThreeProvinceList3().getSelectedItem().toString();
					query = QueryFactory.createQuery("3 Provinces", args, startDate, endDate);
				} else {
					args[0] = view.getThreeTownList1().getSelectedItem().toString();
					args[1] = view.getThreeTownList2().getSelectedItem().toString();
					args[2] = view.getThreeTownList3().getSelectedItem().toString();
					query = QueryFactory.createQuery("3 Towns", args, startDate, endDate);
				}
				
				// ADD DATERRORCHECKING... GETRESULTSET.
				
				model.loadData(query);
				
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
	
	private boolean checkBothMonthlyAndYearly(String currentCard) {
		if (currentCard.equals("Both")) 
			return true;
		return false;
	}
	
	private boolean checkMonthly(String currentCard) {
		if (currentCard.equals("Monthly")) 
			return true;
		return false;
	}
	
	private boolean checkYearly(String currentCard) {
		if (currentCard.equals("Yearly")) 
			return true;
		return false;
	}
	
	
}
