package NHPIVisualizer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
	private JPanel geographicalParametersSubPanel;
	private JPanel geographicalParametersSubPanel1;
	private JPanel geographicalParametersSubPanel2;
	private JPanel timeParametersPanel;
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
    private JLabel timeGranularityLabel;
    private JLabel loadChartLabel;
    
    // Combo boxes
    public static JComboBox<String> geographicalParametersComboBox;
    public static JComboBox<String> provinceList1;
    public static JComboBox<String> provinceList2;
    public static JComboBox<String> townList1;
    public static JComboBox<String> townList2;
    public static JComboBox<String> startMonthComboBox;
    public static JComboBox<String> startYearComboBox;
    public static JComboBox<String> endMonthComboBox;
    public static JComboBox<String> endYearComboBox;
    public static JComboBox<String> chartTypesComboBox;
    
    // Buttons
    private JButton loadRawDataButton;
    private JButton loadSummaryDataButton;
    private JButton loadChartButton;
    private JButton resetButton;
    
    // Tables
    private JTable table;
    
    // Scroll Panes
    private JScrollPane scrollPane;
    
    // To initialize all GUI components. Also to set up layout
    public GUIView() {
    	
    	// JFrame:
		frame = new JFrame("NHPI Visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1300, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// JPanels:
		geographicalParametersPanel = new JPanel();
		geographicalParametersSubPanel = new JPanel();
		geographicalParametersSubPanel1 = new JPanel();
		geographicalParametersSubPanel2 = new JPanel();
		timeParametersPanel = new JPanel();
		tablePanel = new JPanel();
		loadChartPanel = new JPanel();
		buttonsPanel = new JPanel();
		overallPanel = new JPanel();
		startNestedPanel = new JPanel();
		
		// JLabels:
		geographicalParametersLabel = new JLabel("Province or Town?");
		startMonthLabel = new JLabel("Start Month:");
		startYearLabel = new JLabel("Start Year:");
		endMonthLabel = new JLabel("End Month:");
		endYearLabel = new JLabel("End Year:");
		timeGranularityLabel = new JLabel("Monthly or Yearly?");
		
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
        startMonthComboBox = new JComboBox<String>(months);
		startYearComboBox = new JComboBox<String>(years);
		endMonthComboBox = new JComboBox<String>(months);
		endYearComboBox = new JComboBox<String>(years);
		chartTypesComboBox = new JComboBox<String>(chartTypes);
		
		// JButtons:
		loadRawDataButton = new JButton("Load Raw Data");
		loadSummaryDataButton = new JButton("Load Summary Data");
		loadChartButton = new JButton("Load Chart");
		resetButton = new JButton("Reset");
		
		// Setting up layouts for panels:
		CardLayout cardLayout1 = new CardLayout();
		geographicalParametersSubPanel.setLayout(cardLayout1);
		timeParametersPanel.setLayout(new CardLayout());
		tablePanel.setLayout(new BorderLayout());
		loadChartPanel.setLayout(new BorderLayout());
		buttonsPanel.setLayout(new FlowLayout());
		overallPanel.setLayout(new BorderLayout());
		startNestedPanel.setLayout(new BorderLayout());
		
		/*
		 * *** ADDING COMPONENTS TO PANELS ***
		 */
		
		// Geographical Parameters Panel:
		geographicalParametersPanel.add(geographicalParametersLabel);
		geographicalParametersPanel.add(geographicalParametersComboBox);
		geographicalParametersSubPanel1.add(provinceList1);
		geographicalParametersSubPanel1.add(provinceList2);
		geographicalParametersSubPanel2.add(townList1);
		geographicalParametersSubPanel2.add(townList2);
		geographicalParametersSubPanel.add(geographicalParametersSubPanel1, "1");
		geographicalParametersSubPanel.add(geographicalParametersSubPanel2, "2");
		cardLayout1.show(geographicalParametersSubPanel, "1");
		geographicalParametersPanel.setVisible(true);
		geographicalParametersPanel.add(geographicalParametersSubPanel);
		frame.add(geographicalParametersPanel);
		frame.pack();
		System.out.println("check");
		
		// Time Parameters Panel:
		
		
		
    }
    
    public void displayData(ResultSet data) {
    	
    }
    
    
}
