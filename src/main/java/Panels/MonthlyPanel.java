package Panels;

import MVC_Components.View;

import javax.swing.JOptionPane;

import Controllers.dateErrorChecking;

public class MonthlyPanel extends TimePanel {
	
	String startDate;
	String endDate;
	String startMonth;
	String endMonth;
	String selectedYear;
	
	@Override
	public String getPanelName() {
        return "Monthly";
    }
	
	@Override
	public String getStartDate(View view) {
		startMonth = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
		endMonth = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
		selectedYear = view.getYearComboBox().getSelectedItem().toString();
		startDate = selectedYear + "-" + startMonth;
		return startDate;
	}
	
	@Override
	public String getEndDate(View view) {
		startMonth = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
		endMonth = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
		selectedYear = view.getYearComboBox().getSelectedItem().toString();
		endDate = selectedYear + "-" + endMonth;
		return endDate;
	}

	@Override
	public void dateErrorChecking(View view, String startDate, String endDate) {
		if (!dateErrorChecking.partialDateErrorChecking(startMonth, endMonth)) {
			JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
			return;
		}
	}
}
