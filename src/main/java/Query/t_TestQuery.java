package Query;

/**
 * Class for t-test queries.
 * @author Jorel Louie Chim
 *
 */

public class t_TestQuery implements Query {
	
	private String selection1;
	private String startDate;
	private String endDate;
	
	/**
	 * Constructor to set all instance variables.
	 * @param selection1 selection made by the user
	 * @param startDate start date selected by the user
	 * @param endDate end date selected by the user
	 */
	
	public t_TestQuery(String selection1, String startDate, String endDate) {
		this.selection1 = selection1;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * Creates the query using the instance variables, and returns it.
	 * @return the query with all instance variables
	 */
	
	public String getQuery() {
		return "SELECT VALUE FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\"" + ")" + " AND (REF_DATE <= " + "\"" + 
		endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");";
	}
}
