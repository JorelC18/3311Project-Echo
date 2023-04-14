package Panels;

import MVC_Components.View;

import javax.swing.JOptionPane;

import Controllers.dateErrorChecking;

/**
 * This class represents a panel that displays data for when the "Both Yearly and Monthly" option is selected.
 * @author Jorel Louie Chim
 *
 */

public class BothMonthlyAndYearlyPanel extends TimePanel {
	
	String startDate;
	String endDate;
	
	/**
	 * Returns the name of this panel.
	 * @return a string representing the name of this panel
	 */
	
	@Override
	public String getPanelName() {
        return "Both";
    }
	
	/**
	 * Returns the start date.
	 * @param view the view representing the user interface
	 * @return a string containing the start date
	 */
	
	@Override
	public String getStartDate(View view) {
		startDate = view.getStartYearComboBox2().getSelectedItem().toString() + "-"
				+ view.getStartMonthComboBox2().getSelectedItem().toString().substring(0, 2);
		return startDate;
	}
	
	/**
	 * Returns the end date.
	 * @param view the view representing the user interface
	 * @return a string containing the end date
	 */
	
	@Override
	public String getEndDate(View view) {
		endDate = view.getEndYearComboBox2().getSelectedItem().toString() + "-"
				+ view.getEndMonthComboBox2().getSelectedItem().toString().substring(0, 2);
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
		if (!dateErrorChecking.fullDateErrorChecking(startDate, endDate)) {
			JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
			return;
		}
	}
}
