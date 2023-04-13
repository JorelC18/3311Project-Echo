package Query;

/**
 * Class for t-test queries.
 * @author Jorel Louie Chim
 *
 */

public class t_TestQuery implements QueryInterface {
	
	private Query query;
	
	/**
	 * Constructor to set all instance variables.
	 * @param Query object created by user with correct parameters
	 */
	
	public t_TestQuery(Query query) {
		this.query = query;
	}
	
	/**
	 * Creates the query using the instance variables, and returns it.
	 * @return the query with all instance variables
	 */
	
	public String getQuery() {
		return "SELECT VALUE FROM echodata.echodata WHERE (GEO LIKE \"%" + query.getArg1() + "%\"" + ")" + " AND (REF_DATE <= " + "\"" + 
		query.getEndDate() + "\" AND REF_DATE >= " + "\"" + query.getStartDate() + "\");";
	}
}
