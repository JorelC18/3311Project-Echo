package Query;

/**
 * Class for 2 location queries for raw data.
 * @author Jorel Louie Chim
 *
 */

public class TwoLocationRawQuery implements QueryInterface {
	
	private Query query;
	
	/**
	 * Constructor to set all instance variables.
	 * @param Query object created by user with correct parameters
	 */
	
	public TwoLocationRawQuery(Query query) {
		this.query = query;
	}
	
	/**
	 * Creates the query using the instance variables, and returns it.
	 * @return the query with all instance variables
	 */
	
	public String getQuery() {
		return "SELECT * FROM echodata.echodata WHERE (GEO LIKE \"%" + query.getArg1() + "%\"" + " OR GEO LIKE \"%" + query.getArg2() + "%\")" 
		+ " AND (REF_DATE <= " + "\"" + query.getEndDate() + "\" AND REF_DATE >= " + "\"" + query.getStartDate() + "\");";
	}
}
