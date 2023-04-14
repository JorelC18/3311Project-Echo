package Panels;

import MVC_Components.View;

import javax.swing.JOptionPane;

import Controllers.dateErrorChecking;

/**
 * This class represents a panel that displays data for when the "Monthly" option is selected.
 * @author Jorel Louie Chim
 *
 */

public class MonthlyPanel extends TimePanel {
	
	String startDate;
	String endDate;
	String startMonth;
	String endMonth;
	String selectedYear;
	
	/**
	 * Returns the name of this panel.
	 * @return a string representing the name of this panel
	 */
	
	@Override
	public String getPanelName() {
        return "Monthly";
    }
	
	/**
	 * Returns the start date.
	 * @param the view representing the user interface
	 * @return a string containing the start date
	 */
	
	@Override
	public String getStartDate(View view) {
		startMonth = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
		endMonth = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
		selectedYear = view.getYearComboBox().getSelectedItem().toString();
		startDate = selectedYear + "-" + startMonth;
		return startDate;
	}
	
	/**
	 * Returns the end date.
	 * @param the view representing the user interface
	 * @return a string containing the end date
	 */
	
	@Override
	public String getEndDate(View view) {
		startMonth = view.getStartMonthComboBox().getSelectedItem().toString().substring(0, 2);
		endMonth = view.getEndMonthComboBox().getSelectedItem().toString().substring(0, 2);
		selectedYear = view.getYearComboBox().getSelectedItem().toString();
		endDate = selectedYear + "-" + endMonth;
		return endDate;
	}
	
	/**
	 * Checks if there is an error in the selection of dates.
	 * @param view the view representing the user interface
	 * @param startDate the start date
	 * @param endDate the end date
	 */

	@Override
	public void dateErrorChecking(View view, String startDate, String endDate) {
		if (!dateErrorChecking.partialDateErrorChecking(startMonth, endMonth)) {
			JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
			return;
		}
	}
}
