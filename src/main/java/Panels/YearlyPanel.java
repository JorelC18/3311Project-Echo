package Panels;

import MVC_Components.View;

import javax.swing.JOptionPane;

import Controllers.dateErrorChecking;

public class YearlyPanel extends TimePanel {
	
	String startDate;
	String endDate;
	String startYear;
	String endYear;
	
	@Override
	public String getPanelName() {
        return "Yearly";
    }
	
	@Override
	public String getStartDate(View view) {
		startYear = view.getStartYearComboBox().getSelectedItem().toString();
		startDate = startYear + "-01";
		return startDate;
	}
	
	@Override
	public String getEndDate(View view) {
		endYear = view.getEndYearComboBox().getSelectedItem().toString();
		endDate = endYear + "-12";
		return endDate;
	}

	@Override
	public void dateErrorChecking(View view, String startDate, String endDate) {
		if (!dateErrorChecking.partialDateErrorChecking(startYear, endYear)) {
			JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
			return;
		}
	}
}
