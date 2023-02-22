package NHPIVisualizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

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
				
				
			}
		});
		
		JButton loadRawDataButton = view.getLoadRawDataButton();
		
		loadRawDataButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
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
	
	
}
