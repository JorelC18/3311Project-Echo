package Controllers;

import java.sql.ResultSet;
import java.util.HashMap;

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
import Query.QueryFactory;
import Query.QueryInterface;

public class Controller {
	ResultSet result;
	QueryInterface query;
	View view;
	Model model;
	String startDate = "";
	String endDate = "";
	String timeComboBoxSelection;
	String geoComboBoxSelection;
	HashMap<String, TimePanel> timeGranularityMap;
	HashMap<String, GeoPanel> geoComboBoxMap;
	String args[];
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}
	
	public void setupTimeGranularityMap() {
		timeComboBoxSelection = view.getTimeGranularityComboBox().getSelectedItem().toString();
		timeGranularityMap = new HashMap<String, TimePanel>();
		timeGranularityMap.put("Both Monthly and Yearly", new BothMonthlyAndYearlyPanel());
		timeGranularityMap.put("Monthly", new MonthlyPanel());
		timeGranularityMap.put("Yearly", new YearlyPanel());
		startDate = timeGranularityMap.get(timeComboBoxSelection).getStartDate(view);
		endDate = timeGranularityMap.get(timeComboBoxSelection).getEndDate(view);
		timeGranularityMap.get(timeComboBoxSelection).dateErrorChecking(view, startDate, endDate);
	}
	
	public void setupGeoComboBoxMap() {
		geoComboBoxSelection = view.getGeographicalParametersComboBox().getSelectedItem().toString();
		geoComboBoxMap = new HashMap<String, GeoPanel>();
		geoComboBoxMap.put("2 Provinces", new ProvincePanel());
	    geoComboBoxMap.put("2 Towns", new TownPanel());
	    geoComboBoxMap.put("3 Provinces", new ThreeProvincesPanel());
	    geoComboBoxMap.put("3 Towns", new ThreeTownsPanel());
	    args = geoComboBoxMap.get(geoComboBoxSelection).getPreArgs(view);
	    geoComboBoxMap.get(geoComboBoxSelection).emptySelectionChecking(view);
		query = QueryFactory.createQuery(geoComboBoxSelection, args, startDate, endDate);
	}
}
