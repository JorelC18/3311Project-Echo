package Query;

/**
 * Class for 2 location queries for raw data.
 * @author Jorel Louie Chim
 *
 */

public class TwoLocationRawQuery implements Query {
	
	private String selection1;
	private String selection2;
	private String startDate;
	private String endDate;
	
	/**
	 * Constructor to set all instance variables.
	 * @param selection1 first location selected by the user
	 * @param selection2 second location selected by the user
	 * @param startDate start date selected by the user
	 * @param endDate end date selected by the user
	 */
	
	public TwoLocationRawQuery(String selection1, String selection2, String startDate, String endDate) {
		this.selection1 = selection1;
		this.selection2 = selection2;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * Creates the query using the instance variables, and returns it.
	 * @return the query with all instance variables
	 */
	
	public String getQuery() {
		/*System.out.println("SELECT * FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\"" + " OR GEO LIKE \"%" + selection2 + "%\")" 
		+ " AND (REF_DATE <= " + "\"" + endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");");*/

		return "SELECT * FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\"" + " OR GEO LIKE \"%" + selection2 + "%\")" 
		+ " AND (REF_DATE <= " + "\"" + endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");";
	}
}
