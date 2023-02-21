package NHPIVisualizer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * View part of MVC architecture.
 * View - used for all UI logic.
 * UI part is the GUI created with Java swing.
 */

public class GUIView extends JFrame {
	
	// Main frame
	private JFrame frame;
	
	// Panels
	private JPanel geographicalParametersPanel;
	private JPanel geographicalParametersMainSubPanel;
	private JPanel geographicalParametersPTSubPanel;
	private JPanel geographicalParametersProvinceSubPanel;
	private JPanel geographicalParametersTownSubPanel;
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
    private JLabel timeGranularityLabel;
    private JLabel chartTypeLabel;
    
    // Combo boxes
    public static JComboBox<String> geographicalParametersComboBox;
    public static JComboBox<String> provinceList1;
    public static JComboBox<String> provinceList2;
    public static JComboBox<String> townList1;
    public static JComboBox<String> townList2;
    public static JComboBox<String> timeGranularityComboBox;
    public static JComboBox<String> startMonthComboBox;
    public static JComboBox<String> startYearComboBox;
    public static JComboBox<String> endMonthComboBox;
    public static JComboBox<String> endYearComboBox;
    public static JComboBox<String> startMonthComboBox2;
    public static JComboBox<String> startYearComboBox2;
    public static JComboBox<String> endMonthComboBox2;
    public static JComboBox<String> endYearComboBox2;
    public static JComboBox<String> chartTypesComboBox;
    
    // Buttons
    private JButton addTimeSeriesButton;
    private JButton loadRawDataButton;
    private JButton loadSummaryDataButton;
    private JButton loadChartButton;
    private JButton resetButton;
    
    // Tables
    private JTable rawDataTable;
    private JTable summaryDataTable;
    
    // Scroll Panes
    private JScrollPane scrollPane;
    
    // To initialize all GUI components. Also to set up layout
    public GUIView() {
    	
    	// JFrame:
		frame = new JFrame("NHPI Visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		//frame.setSize(800, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// JPanels:
		geographicalParametersPanel = new JPanel();
		geographicalParametersMainSubPanel = new JPanel();
		geographicalParametersPTSubPanel = new JPanel();
		geographicalParametersProvinceSubPanel = new JPanel();
		geographicalParametersTownSubPanel = new JPanel();
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
		chartTypeLabel = new JLabel("Chart Type:");
		
		/*
		 * Initializing data for Comboboxes:
		 */
		
		// Geographical Parameters:
		String[] geographicalParameters = {"Province", "Town"};
		
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
        
        // Monthly, Yearly or Both:
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
		String[] chartTypes = {"Line Chart", "Bar Chart", "Scatter Chart"};
		
		// JComboBoxes:
		geographicalParametersComboBox = new JComboBox<String>(geographicalParameters);
		provinceList1 = new JComboBox<String>(provincesNames);
        provinceList2 = new JComboBox<String>(provincesNames);
        townList1 = new JComboBox<String>(townNames);
        townList2 = new JComboBox<String>(townNames);
        timeGranularityComboBox = new JComboBox<String>(timeGranularity);
        startMonthComboBox = new JComboBox<String>(months);
		startYearComboBox = new JComboBox<String>(years);
		endMonthComboBox = new JComboBox<String>(months);
		endYearComboBox = new JComboBox<String>(years);
		startMonthComboBox2 = new JComboBox<String>(months);
		startYearComboBox2 = new JComboBox<String>(years);
		endMonthComboBox2 = new JComboBox<String>(months);
		endYearComboBox2 = new JComboBox<String>(years);
		chartTypesComboBox = new JComboBox<String>(chartTypes);
		
		// JButtons:
		addTimeSeriesButton = new JButton("Add Time-Series");
		loadRawDataButton = new JButton("Load Raw Data");
		loadSummaryDataButton = new JButton("Load Summary Data");
		loadChartButton = new JButton("Load Chart (Separate Window)");
		resetButton = new JButton("Reset");
		
		// Setting up layouts for panels:
		
		CardLayout cardLayout1 = new CardLayout();
		CardLayout cardLayout2 = new CardLayout();
		CardLayout cardLayout3 = new CardLayout();
		
		geographicalParametersPanel.setLayout(new FlowLayout());
		geographicalParametersMainSubPanel.setLayout(new BorderLayout());
		geographicalParametersPTSubPanel.setLayout(cardLayout1);
		geographicalParametersProvinceSubPanel.setLayout(new FlowLayout());
		geographicalParametersTownSubPanel.setLayout(new FlowLayout());
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
		geographicalParametersPanel.add(geographicalParametersMainSubPanel, FlowLayout.LEFT);
		geographicalParametersProvinceSubPanel.add(provinceList1, FlowLayout.LEFT);
		geographicalParametersProvinceSubPanel.add(provinceList2);
		geographicalParametersTownSubPanel.add(townList1, FlowLayout.LEFT);
		geographicalParametersTownSubPanel.add(townList2);
		geographicalParametersPTSubPanel.add(geographicalParametersProvinceSubPanel, "Province");
		geographicalParametersPTSubPanel.add(geographicalParametersTownSubPanel, "Town");
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
		
		
		
		scrollPane = new JScrollPane();
		frame.add(scrollPane, BorderLayout.EAST);
		
		
		// Buttons Panel:
		
		buttonsPanel.add(chartTypeLabel, FlowLayout.LEFT);
		buttonsPanel.add(chartTypesComboBox);
		buttonsPanel.add(addTimeSeriesButton);
		buttonsPanel.add(loadRawDataButton);
		buttonsPanel.add(loadSummaryDataButton);
		buttonsPanel.add(loadChartButton);
		buttonsPanel.add(resetButton);
		
		frame.add(buttonsPanel, BorderLayout.SOUTH);
		
		
		
		frame.pack();
		
    }
    
    public void displayData(ResultSet data) {
    	
    }
    
    
}
