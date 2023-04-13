package MVC_Components;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * View part of MVC architecture. It is used for all UI logic.
 * UI part is the GUI created with Java swing.
 * @author Jorel Louie Chim
 */

public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;

	// Frame
	private JFrame frame;
	
	// Panels
	private JPanel geographicalParametersPanel;
	private JPanel geographicalParametersMainSubPanel;
	private JPanel geographicalParametersPTSubPanel;
	private JPanel geographicalParametersProvinceSubPanel;
	private JPanel geographicalParametersTownSubPanel;
	private JPanel geographicalParameters3ProvinceSubPanel;
	private JPanel geographicalParameters3TownSubPanel;
	private JPanel timeParametersPanel;
	private JPanel timeParametersMainSubPanel;
	private JPanel timeParametersBMYSubPanel;
	private JPanel timeParametersBothSubPanel;
	private JPanel timeParametersMonthSubPanel;
	private JPanel timeParametersYearSubPanel;
	private JPanel tablePanel;
	private JPanel loadChartPanel;
	private JPanel buttonsPanel;
	private JPanel overallPanel;
	private JPanel startNestedPanel;
	
	// Card Layouts
	private CardLayout cardLayout1;
	private CardLayout cardLayout2;
	private CardLayout cardLayout3;
	
	// Labels
	private JLabel geographicalParametersLabel;
	private JLabel startMonthLabel;
	private JLabel startYearLabel;
    private JLabel endMonthLabel;
    private JLabel endYearLabel;
    private JLabel startMonthLabel2;
	private JLabel startYearLabel2;
    private JLabel endMonthLabel2;
    private JLabel endYearLabel2;
    private JLabel endYearLabel3;
    private JLabel timeGranularityLabel;
    private JLabel chartTypeLabel;
    
    // Combo boxes
    public JComboBox<String> geographicalParametersComboBox;
    public JComboBox<String> provinceList1;
    public JComboBox<String> provinceList2;
    public JComboBox<String> threeProvinceList1;
    public JComboBox<String> threeProvinceList2;
    public JComboBox<String> threeProvinceList3;
    public JComboBox<String> townList1;
    public JComboBox<String> townList2;
    public JComboBox<String> townList3;
    public JComboBox<String> threeTownList1;
    public JComboBox<String> threeTownList2;
    public JComboBox<String> threeTownList3;
    public JComboBox<String> timeGranularityComboBox;
    public JComboBox<String> startMonthComboBox;
    public JComboBox<String> startYearComboBox;
    public JComboBox<String> endMonthComboBox;
    public JComboBox<String> endYearComboBox;
    public JComboBox<String> startMonthComboBox2;
    public JComboBox<String> startYearComboBox2;
    public JComboBox<String> endMonthComboBox2;
    public JComboBox<String> endYearComboBox2;
    public JComboBox<String> yearComboBox;
    public JComboBox<String> chartTypesComboBox;
    
    // Buttons
    private JButton loadRawDataButton;
    private JButton loadSummaryDataButton;
    private JButton loadChartButton;
    private JButton loadTestButton;
    private JButton loadForecastingButton;
    
    
    // Tables
    private JTable rawDataTable;
    private JTable summaryDataTable;
    
    // Scroll Panes
    private JScrollPane rawDataScrollPane;
    private JScrollPane summaryDataScrollPane;
    
    // To initialize all GUI components. Also to set up layout
    public View() {
    	
    	// JFrame:
    	frame = new JFrame("NHPI Visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// JPanels:
		geographicalParametersPanel = new JPanel();
		geographicalParametersMainSubPanel = new JPanel();
		geographicalParametersPTSubPanel = new JPanel();
		geographicalParametersProvinceSubPanel = new JPanel();
		geographicalParametersTownSubPanel = new JPanel();
		geographicalParameters3ProvinceSubPanel = new JPanel();
		geographicalParameters3TownSubPanel = new JPanel();
		timeParametersPanel = new JPanel();
		timeParametersPanel.setPreferredSize(new Dimension(500, 500));
		timeParametersMainSubPanel = new JPanel();
		timeParametersBMYSubPanel = new JPanel();
		timeParametersBothSubPanel = new JPanel();
		timeParametersMonthSubPanel = new JPanel();
		timeParametersYearSubPanel = new JPanel();
		tablePanel = new JPanel();
		tablePanel.setPreferredSize(new Dimension(500, 500));
		loadChartPanel = new JPanel();
		buttonsPanel = new JPanel();
		overallPanel = new JPanel();
		startNestedPanel = new JPanel();
		
		// JLabels:
		geographicalParametersLabel = new JLabel("Province or Town?");
		timeGranularityLabel = new JLabel("Monthly or Yearly?");
		startMonthLabel = new JLabel("Start Month:");
		startYearLabel = new JLabel("Start Year:");
		endMonthLabel = new JLabel("End Month:");
		endYearLabel = new JLabel("End Year:");
		startMonthLabel2 = new JLabel("Start Month:");
		startYearLabel2 = new JLabel("Start Year:");
		endMonthLabel2 = new JLabel("End Month:");
		endYearLabel2 = new JLabel("End Year:");
		endYearLabel3 = new JLabel("Year:");
		chartTypeLabel = new JLabel("Chart Type:");
		
		/*
		 * Initializing data for Comboboxes:
		 */
		
		// Geographical Parameters:
		String[] geographicalParameters = {"2 Provinces", "2 Towns", 
					"3 Provinces", "3 Towns"};
		
		// Provinces:
		Vector<String> provincesNames = new Vector<String>();
        provincesNames.add("0 - Empty");
		provincesNames.add("Alberta");
		provincesNames.add("Atlantic Region");
		provincesNames.add("British Columbia");
		provincesNames.add("Canada");
		provincesNames.add("Manitoba");
		provincesNames.add("New Brunswick");
		provincesNames.add("Newfoundland and Labrador");
		provincesNames.add("Nova Scotia");
		provincesNames.add("Ontario");
		provincesNames.add("Prince Edward Island");
		provincesNames.add("Quebec");
		provincesNames.add("Saskatchewan");
		provincesNames.add("Prairie Region");
        provincesNames.sort(null);
        
        // Towns:
        Vector<String> townNames = new Vector<String>();
        townNames.add("0 - Empty");
		townNames.add("Calgary");
		townNames.add("Charlottetown");
		townNames.add("Edmonton");
		townNames.add("Greater Sudbury");
		townNames.add("Guelph");
		townNames.add("Halifax");
		townNames.add("Hamilton");
		townNames.add("Kelowna");
		townNames.add("Kitchener-Cambridge-Waterloo");
		townNames.add("London");
		townNames.add("Montreal");
		townNames.add("Oshawa");
		townNames.add("Ottawa-Gatineau-Ontario");
		townNames.add("Quebec");
		townNames.add("Regina");
		townNames.add("Saint John-Fredericton-and Moncton");
		townNames.add("Saskatoon");
		townNames.add("Sherbrooke");
		townNames.add("St. Catharines-Niagara");
		townNames.add("St. John's");
		townNames.add("Toronto");
		townNames.add("Trois-Rivieres");
		townNames.add("Vancouver");
		townNames.add("Victoria");
		townNames.add("Windsor");
		townNames.add("Winnipeg");
        townNames.sort(null);
        
        // Time Granularity Options:
        String[] timeGranularity = {"Both Monthly and Yearly", "Monthly", "Yearly"};
        
        // Start & End Months:
        String[] months = {"01-January", "02-February", "03-March", "04-April",
				"05-May", "06-June", "07-July", "08-August", "09-September", "10-October",
				"11-November", "12-December"};
        
        // Start & End Years:
        String[] years = new String[42];
		for (int i = 0; i < 42; i++) {
			int startYear = 1981;
			years[i] = String.valueOf(startYear + i);
		}
		
		// Chart Types:
		String[] chartTypes = {"Line Chart", "Bar Chart"};
		
		// JComboBoxes:
		geographicalParametersComboBox = new JComboBox<String>(geographicalParameters);
		provinceList1 = new JComboBox<String>(provincesNames);
        provinceList2 = new JComboBox<String>(provincesNames);
        threeProvinceList1 = new JComboBox<String>(provincesNames);
        threeProvinceList2 = new JComboBox<String>(provincesNames);
        threeProvinceList3 = new JComboBox<String>(provincesNames);
        townList1 = new JComboBox<String>(townNames);
        townList2 = new JComboBox<String>(townNames);
        townList3 = new JComboBox<String>(townNames);
        threeTownList1 = new JComboBox<String>(townNames);
        threeTownList2 = new JComboBox<String>(townNames);
        threeTownList3 = new JComboBox<String>(townNames);
        timeGranularityComboBox = new JComboBox<String>(timeGranularity);
        startMonthComboBox = new JComboBox<String>(months);
		startYearComboBox = new JComboBox<String>(years);
		endMonthComboBox = new JComboBox<String>(months);
		endYearComboBox = new JComboBox<String>(years);
		startMonthComboBox2 = new JComboBox<String>(months);
		startYearComboBox2 = new JComboBox<String>(years);
		endMonthComboBox2 = new JComboBox<String>(months);
		endYearComboBox2 = new JComboBox<String>(years);
		yearComboBox = new JComboBox<String>(years);
		chartTypesComboBox = new JComboBox<String>(chartTypes);
		
		// JButtons:
		
		loadRawDataButton = new JButton("Load Raw Data");
		loadSummaryDataButton = new JButton("Load Summary Data");
		loadChartButton = new JButton("Load Chart (Separate Window) (MUST Load Raw Data FIRST)");
		loadTestButton = new JButton("Load t_Test result");
		loadForecastingButton = new JButton("Forecast");
		
		
		// Setting up layouts for panels:
		
		cardLayout1 = new CardLayout();
		cardLayout2 = new CardLayout();
		cardLayout3 = new CardLayout();
		
		geographicalParametersPanel.setLayout(new FlowLayout());
		geographicalParametersMainSubPanel.setLayout(new BorderLayout());
		geographicalParametersPTSubPanel.setLayout(cardLayout1);
		geographicalParametersProvinceSubPanel.setLayout(new FlowLayout());
		geographicalParametersTownSubPanel.setLayout(new FlowLayout());
		geographicalParameters3ProvinceSubPanel.setLayout(new FlowLayout());
		geographicalParameters3TownSubPanel.setLayout(new FlowLayout());
		timeParametersPanel.setLayout(new BorderLayout());
		timeParametersMainSubPanel.setLayout(new FlowLayout());
		timeParametersBMYSubPanel.setLayout(cardLayout2);
		timeParametersBothSubPanel.setLayout(new FlowLayout());
		timeParametersMonthSubPanel.setLayout(new FlowLayout());
		timeParametersYearSubPanel.setLayout(new FlowLayout());
		tablePanel.setLayout(cardLayout3);
		loadChartPanel.setLayout(new BorderLayout());
		buttonsPanel.setLayout(new FlowLayout());
		overallPanel.setLayout(new BorderLayout());
		startNestedPanel.setLayout(new BorderLayout());
		
		/*
		 * *** ADDING COMPONENTS TO PANELS ***
		 */
		
		// Geographical Parameters Panel:
		geographicalParametersMainSubPanel.add(geographicalParametersLabel, BorderLayout.WEST);
		geographicalParametersMainSubPanel.add(geographicalParametersComboBox, BorderLayout.EAST);
		
		geographicalParametersProvinceSubPanel.add(provinceList1, FlowLayout.LEFT);
		geographicalParametersProvinceSubPanel.add(provinceList2);
		
		geographicalParametersTownSubPanel.add(townList1, FlowLayout.LEFT);
		geographicalParametersTownSubPanel.add(townList2);
		
		geographicalParameters3ProvinceSubPanel.add(threeProvinceList1, FlowLayout.LEFT);
		geographicalParameters3ProvinceSubPanel.add(threeProvinceList2);
		geographicalParameters3ProvinceSubPanel.add(threeProvinceList3);
		
		geographicalParameters3ProvinceSubPanel.add(threeProvinceList1, FlowLayout.LEFT);
		geographicalParameters3ProvinceSubPanel.add(threeProvinceList2);
		geographicalParameters3ProvinceSubPanel.add(threeProvinceList3);
		
		geographicalParameters3TownSubPanel.add(threeTownList1, FlowLayout.LEFT);
		geographicalParameters3TownSubPanel.add(threeTownList2);
		geographicalParameters3TownSubPanel.add(threeTownList3);
		
		geographicalParametersPTSubPanel.add(geographicalParametersProvinceSubPanel, "Province");
		geographicalParametersPTSubPanel.add(geographicalParametersTownSubPanel, "Town");
		geographicalParametersPTSubPanel.add(geographicalParameters3ProvinceSubPanel, "3 Provinces");
		geographicalParametersPTSubPanel.add(geographicalParameters3TownSubPanel, "3 Towns");
		geographicalParametersPanel.add(geographicalParametersMainSubPanel, FlowLayout.LEFT);
		geographicalParametersPanel.add(geographicalParametersPTSubPanel);
		frame.add(geographicalParametersPanel, BorderLayout.NORTH);
		
		
		
		// Time Parameters Panel:
		timeParametersMainSubPanel.add(timeGranularityLabel, BorderLayout.WEST);
		timeParametersMainSubPanel.add(timeGranularityComboBox, BorderLayout.EAST);
		
		timeParametersBothSubPanel.add(startMonthLabel2, FlowLayout.LEFT);
		timeParametersBothSubPanel.add(startMonthComboBox2);
		timeParametersBothSubPanel.add(startYearLabel2);
		timeParametersBothSubPanel.add(startYearComboBox2);
		timeParametersBothSubPanel.add(endMonthLabel2);
		timeParametersBothSubPanel.add(endMonthComboBox2);
		timeParametersBothSubPanel.add(endYearLabel2);
		timeParametersBothSubPanel.add(endYearComboBox2);
		
		timeParametersMonthSubPanel.add(startMonthLabel, FlowLayout.LEFT);
		timeParametersMonthSubPanel.add(startMonthComboBox);
		timeParametersMonthSubPanel.add(endMonthLabel);
		timeParametersMonthSubPanel.add(endMonthComboBox);
		timeParametersMonthSubPanel.add(endYearLabel3);
		timeParametersMonthSubPanel.add(yearComboBox);
		
		timeParametersYearSubPanel.add(startYearLabel, FlowLayout.LEFT);
		timeParametersYearSubPanel.add(startYearComboBox);
		timeParametersYearSubPanel.add(endYearLabel);
		timeParametersYearSubPanel.add(endYearComboBox);
		
		timeParametersBMYSubPanel.add(timeParametersBothSubPanel, "Both");
		timeParametersBMYSubPanel.add(timeParametersMonthSubPanel, "Monthly");
		timeParametersBMYSubPanel.add(timeParametersYearSubPanel, "Yearly");
		
		timeParametersPanel.add(timeParametersMainSubPanel, BorderLayout.NORTH);
		timeParametersPanel.add(timeParametersBMYSubPanel, BorderLayout.CENTER);

		frame.add(timeParametersPanel, BorderLayout.WEST);
		
		// Table Panel:
		
		rawDataTable = new JTable();
		rawDataScrollPane = new JScrollPane(rawDataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
				);
		rawDataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		summaryDataTable = new JTable();
		summaryDataScrollPane = new JScrollPane(summaryDataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
				);
		summaryDataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		tablePanel.add(rawDataScrollPane, "Raw Data");
		tablePanel.add(summaryDataScrollPane, "Summary Data");
		
		frame.add(tablePanel, BorderLayout.EAST);
		
		// Buttons Panel:
		
		buttonsPanel.add(chartTypeLabel, FlowLayout.LEFT);
		buttonsPanel.add(chartTypesComboBox);
		buttonsPanel.add(loadRawDataButton);
		buttonsPanel.add(loadSummaryDataButton);
		buttonsPanel.add(loadChartButton);
		buttonsPanel.add(loadTestButton);
		buttonsPanel.add(loadForecastingButton);
		
		frame.add(buttonsPanel, BorderLayout.SOUTH);
	
		frame.pack();
		
    }
    
    /**
     * Method to get a reference to the frame.
     * @return the frame
     */
    public JFrame getFrame() {
		return frame;
	}
	

	 /**
     * Method to get a reference to the geographical parameters panel.
     * @return the geographical parameters panel
     */

	public JPanel getGeographicalParametersPanel() {
		return geographicalParametersPanel;
	}



	/**
     * Method to get a reference to the geographical parameters main sub panel.
     * @return the geographical parameters main sub panel
     */


	public JPanel getGeographicalParametersMainSubPanel() {
		return geographicalParametersMainSubPanel;
	}


	/**
     * Method to get a reference to the geographical parameters PT sub panel.
     * @return the geographical parameters PT sub panel
     */



	public JPanel getGeographicalParametersPTSubPanel() {
		return geographicalParametersPTSubPanel;
	}

	/**
     * Method to get a reference to the geographical parameters province sub panel.
     * @return the geographical parameters province sub panel.
     */


	public JPanel getGeographicalParametersProvinceSubPanel() {
		return geographicalParametersProvinceSubPanel;
	}

	/**
     * Method to get a reference to the geographical parameters town sub panel.
     * @return the geographical parameters town sub panel.
     */


	public JPanel getGeographicalParametersTownSubPanel() {
		return geographicalParametersTownSubPanel;
	}


	/**
     * Method to get a reference to the time parameters panel.
     * @return the time parameters panel
     */

	public JPanel getTimeParametersPanel() {
		return timeParametersPanel;
	}


	/**
     * Method to get a reference to the time parameters main sub panel.
     * @return the time parameters main sub panel
     */

	public JPanel getTimeParametersMainSubPanel() {
		return timeParametersMainSubPanel;
	}

	/**
     * Method to get a reference to the time parameters BMY sub panel.
     * @return the time parameters BMY sub panel
     */


	public JPanel getTimeParametersBMYSubPanel() {
		return timeParametersBMYSubPanel;
	}


	/**
     * Method to get a reference to the time parameters both sub panel.
     * @return the time parameters both sub panel
     */


	public JPanel getTimeParametersBothSubPanel() {
		return timeParametersBothSubPanel;
	}


	/**
     * Method to get a reference to the time parameters month sub panel.
     * @return the time parameters month sub panel
     */



	public JPanel getTimeParametersMonthSubPanel() {
		return timeParametersMonthSubPanel;
	}


	/**
     * Method to get a reference to the time parameters year sub panel.
     * @return the time parameters year sub panel
     */



	public JPanel getTimeParametersYearSubPanel() {
		return timeParametersYearSubPanel;
	}


	/**
     * Method to get a reference to the table panel.
     * @return the table panel
     */



	public JPanel getTablePanel() {
		return tablePanel;
	}


	/**
     * Method to get a reference to the load chart panel.
     * @return the load chart panel
     */




	public JPanel getLoadChartPanel() {
		return loadChartPanel;
	}


	/**
     * Method to get a reference to the buttons panel.
     * @return the buttons panel
     */




	public JPanel getButtonsPanel() {
		return buttonsPanel;
	}


	/**
     * Method to get a reference to the overall panel.
     * @return the overall panel
     */




	public JPanel getOverallPanel() {
		return overallPanel;
	}


	/**
     * Method to get a reference to the start nested panel.
     * @return the start nested panel.
     */



	public JPanel getStartNestedPanel() {
		return startNestedPanel;
	}


	/**
     * Method to get a reference to card layout 1.
     * @return the first card layout
     */




	public CardLayout getCardLayout1() {
		return cardLayout1;
	}


	/**
     * Method to get a reference to card layout 2.
     * @return the second card layout
     */




	public CardLayout getCardLayout2() {
		return cardLayout2;
	}



	/**
     * Method to get a reference to card layout 3.
     * @return the third card layout
     */



	public CardLayout getCardLayout3() {
		return cardLayout3;
	}


	/**
     * Method to get a reference to the geographical parameters label.
     * @return the geographical parameters label
     */




	public JLabel getGeographicalParametersLabel() {
		return geographicalParametersLabel;
	}


	/**
     * Method to get a reference to the start month label.
     * @return the start month label
     */



	public JLabel getStartMonthLabel() {
		return startMonthLabel;
	}


	/**
     * Method to get a reference to the start year label.
     * @return the start year label
     */



	public JLabel getStartYearLabel() {
		return startYearLabel;
	}


	/**
     * Method to get a reference to the end month label.
     * @return the end month label
     */



	public JLabel getEndMonthLabel() {
		return endMonthLabel;
	}


	/**
     * Method to get a reference to the end year label.
     * @return the end year label
     */



	public JLabel getEndYearLabel() {
		return endYearLabel;
	}


	/**
     * Method to get a reference to the start month label 2.
     * @return the start month label 2
     */



	public JLabel getStartMonthLabel2() {
		return startMonthLabel2;
	}


	/**
     * Method to get a reference to the start year label 2.
     * @return the start year label 2
     */



	public JLabel getStartYearLabel2() {
		return startYearLabel2;
	}


	/**
     * Method to get a reference to the end month label 2.
     * @return the end month label 2
     */




	public JLabel getEndMonthLabel2() {
		return endMonthLabel2;
	}



	/**
     * Method to get a reference to the end year label 2.
     * @return the end year label 2
     */



	public JLabel getEndYearLabel2() {
		return endYearLabel2;
	}



	/**
     * Method to get a reference to the time granularity label.
     * @return the time granularity label
     */



	public JLabel getTimeGranularityLabel() {
		return timeGranularityLabel;
	}



	/**
     * Method to get a reference to the chart type label.
     * @return the chart type label
     */



	public JLabel getChartTypeLabel() {
		return chartTypeLabel;
	}


	/**
     * Method to get a reference to the geographical parameters combo box.
     * @return the geographical parameters combo box
     */



	public JComboBox<String> getGeographicalParametersComboBox() {
		return geographicalParametersComboBox;
	}


	/**
     * Method to get a reference to the provincelist1 combo box.
     * @return the provincelist1 combo box
     */



	public JComboBox<String> getProvinceList1() {
		return provinceList1;
	}


	/**
     * Method to get a reference to the provincelist2 combo box.
     * @return the provincelist2 combo box
     */



	public JComboBox<String> getProvinceList2() {
		return provinceList2;
	}

	
	/**
     * Method to get a reference to the townlist1 combo box.
     * @return the townlist1 combo box
     */



	public JComboBox<String> getTownList1() {
		return townList1;
	}


	/**
     * Method to get a reference to the townlist2 combo box.
     * @return the townlist2 combo box
     */



	public JComboBox<String> getTownList2() {
		return townList2;
	}

	
	/**
     * Method to get a reference to the time granularity combo box.
     * @return the time granularity combo box
     */



	public JComboBox<String> getTimeGranularityComboBox() {
		return timeGranularityComboBox;
	}


	/**
     * Method to get a reference to the start month combo box.
     * @return the start month combo box
     */



	public JComboBox<String> getStartMonthComboBox() {
		return startMonthComboBox;
	}


	/**
     * Method to get a reference to the start year combo box.
     * @return the start year combo box
     */



	public JComboBox<String> getStartYearComboBox() {
		return startYearComboBox;
	}


	/**
     * Method to get a reference to the end month combo box.
     * @return the end month combo box
     */



	public JComboBox<String> getEndMonthComboBox() {
		return endMonthComboBox;
	}


	/**
     * Method to get a reference to the end year combo box.
     * @return the end year combo box
     */



	public JComboBox<String> getEndYearComboBox() {
		return endYearComboBox;
	}


	/**
     * Method to get a reference to the second start month combo box.
     * @return the startMonth2 combo box
     */



	public JComboBox<String> getStartMonthComboBox2() {
		return startMonthComboBox2;
	}

	/**
     * Method to get a reference to the second start year combo box.
     * @return the startYear2 combo box
     */



	public JComboBox<String> getStartYearComboBox2() {
		return startYearComboBox2;
	}


	/**
     * Method to get a reference to the second end month combo box.
     * @return the endMonth2 combo box
     */



	public JComboBox<String> getEndMonthComboBox2() {
		return endMonthComboBox2;
	}


	/**
     * Method to get a reference to the second end year combo box.
     * @return the endYear2 combo box
     */



	public JComboBox<String> getEndYearComboBox2() {
		return endYearComboBox2;
	}


	/**
     * Method to get a reference to the chart types combo box.
     * @return the chart types combo box
     */



	public JComboBox<String> getChartTypesComboBox() {
		return chartTypesComboBox;
	}



	/**
     * Method to get a reference to the load raw data button.
     * @return the load raw data button
     */


	public JButton getLoadRawDataButton() {
		return loadRawDataButton;
	}


	/**
     * Method to get a reference to the load summary data button.
     * @return the load summary data button
     */



	public JButton getLoadSummaryDataButton() {
		return loadSummaryDataButton;
	}


	/**
     * Method to get a reference to the load chart button.
     * @return the load chart button
     */



	public JButton getLoadChartButton() {
		return loadChartButton;
	}
	
	/**
     * Method to get a reference to the load test button.
     * @return the load test button
     */
	
	public JButton getLoadTestButton() {
		return loadTestButton;
	}
	
	public JButton getLoadForecastingButton() {
		return loadForecastingButton;
	}

	/**
     * Method to get a reference to the raw data table.
     * @return the raw data table
     */

	public JTable getRawDataTable() {
		return rawDataTable;
	}


	/**
     * Method to get a reference to the summary data table.
     * @return the summary data table
     */


	public JTable getSummaryDataTable() {
		return summaryDataTable;
	}


	/**
     * Method to get a reference to the raw data scroll pane.
     * @return the raw data scroll pane
     */



	public JScrollPane getRawDataScrollPane() {
		return rawDataScrollPane;
	}


	/**
     * Method to get a reference to the summary data scroll pane.
     * @return the summary data scroll pane
     */



	public JScrollPane getSummaryDataScrollPane() {
		return summaryDataScrollPane;
	}

	
	/**
     * Method to get a reference to the geographical parameters 3 province sub panel.
     * @return the geographical parameters 3 province sub panel
     */



	public JPanel getGeographicalParameters3ProvinceSubPanel() {
		return geographicalParameters3ProvinceSubPanel;
	}

	/**
     * Method to get a reference to the geographical parameters 3 town sub panel.
     * @return the geographical parameters 3 town sub panel
     */


	public JPanel getGeographicalParameters3TownSubPanel() {
		return geographicalParameters3TownSubPanel;
	}

	/**
     * Method to get a reference to the first three province list combo box.
     * @return the first three province list combo box
     */

	public JComboBox<String> getThreeProvinceList1() {
		return threeProvinceList1;
	}
	
	
	/**
     * Method to get a reference to the second three province list combo box.
     * @return the second three province list combo box
     */


	public JComboBox<String> getThreeProvinceList2() {
		return threeProvinceList2;
	}
	
	/**
     * Method to get a reference to the third three province list combo box.
     * @return the third three province list combo box
     */
	


	public JComboBox<String> getThreeProvinceList3() {
		return threeProvinceList3;
	}
	
	/**
     * Method to get a reference to the third town list combo box.
     * @return the third town list combo box
     */
	


	public JComboBox<String> getTownList3() {
		return townList3;
	}
	
	/**
     * Method to get a reference to the first three town list combo box.
     * @return the first three town list combo box
     */


	public JComboBox<String> getThreeTownList1() {
		return threeTownList1;
	}
	

	/**
     * Method to get a reference to the second three town list combo box.
     * @return the second three town list combo box
     */


	public JComboBox<String> getThreeTownList2() {
		return threeTownList2;
	}
	
	/**
     * Method to get a reference to the third three town list combo box.
     * @return the third three town list combo box
     */
	
	public JComboBox<String> getThreeTownList3() {
		return threeTownList3;
	}
	
	/**
     * Method to get a reference to the year combo box.
     * @return the year combo box
     */
	
	public JComboBox<String> getYearComboBox() {
		return yearComboBox;
	}


}
