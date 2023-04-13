package Panels;

import MVC_Components.View;

import javax.swing.JOptionPane;

import Controllers.dateErrorChecking;

public class BothMonthlyAndYearlyPanel extends TimePanel {
	
	String startDate;
	String endDate;
	
	@Override
	public String getPanelName() {
        return "Both";
    }
	
	@Override
	public String getStartDate(View view) {
		startDate = view.getStartYearComboBox2().getSelectedItem().toString() + "-"
				+ view.getStartMonthComboBox2().getSelectedItem().toString().substring(0, 2);
		return startDate;
	}
	
	@Override
	public String getEndDate(View view) {
		endDate = view.getEndYearComboBox2().getSelectedItem().toString() + "-"
				+ view.getEndMonthComboBox2().getSelectedItem().toString().substring(0, 2);
		return endDate;
	}

	@Override
	public void dateErrorChecking(View view, String startDate, String endDate) {
		if (!dateErrorChecking.fullDateErrorChecking(startDate, endDate)) {
			JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
			return;
		}
	}
}
