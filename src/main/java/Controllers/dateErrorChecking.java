package Controllers;

public class dateErrorChecking {	
		/**
		 * Checks if the date interval for both yearly and monthly selected from the user is valid.
		 * @param startDate the start date selected by the user
		 * @param endDate the end date selected by the user
		 * @return false if the date interval is invalid. true otherwise.
		 */
		
		protected static boolean fullDateErrorChecking(String startDate, String endDate) {
			Integer startYear = Integer.parseInt(startDate.substring(0, 4));
			Integer endYear = Integer.parseInt(endDate.substring(0, 4));
			Integer startMonth = Integer.parseInt(startDate.substring(5, 7));
			Integer endMonth = Integer.parseInt(endDate.substring(5, 7));
			
			if (startYear > endYear) {
				return false;
			}
			
			if (startYear.equals(endYear)) {
				if (startMonth > endMonth) {
					return false;
				}
			}
			
			return true;
		}
		
		/**
		 * Checks if the date interval for just yearly or just monthly selected from the user is valid.
		 * @param startDate the start date selected by the user
		 * @param endDate the end date selected by the user
		 * @return false if the date interval is invalid. true otherwise.
		 */
		
		protected static boolean partialDateErrorChecking(String startDate, String endDate) {
			if (Integer.parseInt(startDate)> Integer.parseInt(endDate)) {
				return false;
			}
			return true;
		}
		
		/**
		 * Checks if the user has made a selection for all fields required.
		 * @param arg the selection made from the user
		 * @return true if a selection is empty. false otherwise
		 */
		
		protected static boolean emptySelectionChecking(String arg) {
			if (arg.equals("0 - Empty")) 
				return true;
			
			return false;
		}
}
