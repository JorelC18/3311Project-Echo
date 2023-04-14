package Panels;

import MVC_Components.View;

import javax.swing.JOptionPane;

import Controllers.dateErrorChecking;

/**
 * This class represents a panel that displays data for when the "Yearly" option is selected.
 * @author Jorel Louie Chim
 *
 */

public class YearlyPanel extends TimePanel {
	
	String startDate;
	String endDate;
	String startYear;
	String endYear;
	
	/**
	 * Returns the name of this panel.
	 * @return a string representing the name of this panel
	 */
	
	@Override
	public String getPanelName() {
        return "Yearly";
    }
	
	/**
	 * Returns the start date.
	 * @param the view representing the user interface
	 * @return a string containing the start date
	 */
	
	@Override
	public String getStartDate(View view) {
		startYear = view.getStartYearComboBox().getSelectedItem().toString();
		startDate = startYear + "-01";
		return startDate;
	}
	
	/**
	 * Returns the end date.
	 * @param the view representing the user interface
	 * @return a string containing the end date
	 */
	
	@Override
	public String getEndDate(View view) {
		endYear = view.getEndYearComboBox().getSelectedItem().toString();
		endDate = endYear + "-12";
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
		if (!dateErrorChecking.partialDateErrorChecking(startYear, endYear)) {
			JOptionPane.showMessageDialog(view.getFrame(), "Please enter a valid combination of dates.");
			return;
		}
	}
}
