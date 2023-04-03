package Query;

/**
 * Class for forecasting queries for forecasting algorithms.
 * @author Jorel Louie Chim
 */

public class ForecastingQuery implements Query {
	
	private String selection1;
	private String startDate;
	private String endDate;
	
	/**
	 * Constructor to set all instance variables.
	 * @param selection1 first location selected by the user
	 * @param startDate start date selected by the user
	 * @param endDate end date selected by the user
	 */
	
	public ForecastingQuery(String selection1, String startDate, String endDate) {
		this.selection1 = selection1;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * Creates the query using the instance variables, and returns it.
	 * @return the query with all instance variables
	 */
	
	public String getQuery() {
		return "SELECT REF_DATE, VALUE FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\"" + ")" + " AND (REF_DATE <= " + "\"" + 
		endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");";
	}
}
