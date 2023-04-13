package Query;

/**
 * Class for 2 location queries for summary data.
 * @author Jorel Louie Chim
 *
 */

public class TwoLocationSummaryQuery implements QueryInterface {
	
	private Query query;
	
	/**
	 * Constructor to set all instance variables.
	 * @param Query object created by user with correct parameters
	 */
	
	public TwoLocationSummaryQuery(Query query) {
		this.query = query;
	}
	
	/**
	 * Creates the query using the instance variables, and returns it.
	 * @return the query with all instance variables
	 */
	
	public String getQuery() {
		return "SELECT * FROM (SELECT FORMAT(AVG(VALUE), 2) AS \"" + query.getArg1() + " Average\" FROM echodata.echodata WHERE (GEO LIKE \"%" + query.getArg1() + "%\") AND (REF_DATE <= \"" + query.getEndDate() + "\" AND REF_DATE >= \"" + query.getStartDate() +"\")) AS A,\r\n"
				+ "(SELECT FORMAT(AVG(VALUE), 2) AS \"" + query.getArg2() +" Average\" FROM echodata.echodata WHERE (GEO LIKE \"%" + query.getArg2() + "%\") AND (REF_DATE <= \"" + query.getEndDate() + "\" AND REF_DATE >= \"" + query.getStartDate() + "\")) AS B,\r\n"
				+ "(SELECT FORMAT(STDDEV(VALUE), 2) AS \"" + query.getArg1() + " Standard Deviation\" FROM echodata.echodata WHERE (GEO LIKE \"%" + query.getArg1() + "%\") AND (REF_DATE <= \"" + query.getEndDate() + "\" AND REF_DATE >= \"" + query.getStartDate() + "\")) AS C,\r\n"
				+ "(SELECT FORMAT(STDDEV(VALUE), 2) AS \"" + query.getArg2() + " Standard Deviation\" FROM echodata.echodata WHERE (GEO LIKE \"%" + query.getArg2() + "%\") AND (REF_DATE <= \"" + query.getEndDate() + "\" AND REF_DATE >= \"" + query.getStartDate() + "\")) AS D,\r\n"
				+ "(SELECT MIN(VALUE) AS \"" + query.getArg1() + " Min\" FROM echodata.echodata WHERE (GEO LIKE \"%" + query.getArg1() + "%\") AND (REF_DATE <= \"" + query.getEndDate() + "\" AND REF_DATE >= \"" + query.getStartDate() + "\")) AS E,\r\n"
				+ "(SELECT MAX(VALUE) AS \"" + query.getArg1() + " Max\" FROM echodata.echodata WHERE (GEO LIKE \"%" + query.getArg1() + "%\") AND (REF_DATE <= \"" + query.getEndDate() + "\" AND REF_DATE >= \"" + query.getStartDate() + "\")) AS F,\r\n"
				+ "(SELECT MIN(VALUE) AS \"" + query.getArg2() + " Min\" FROM echodata.echodata WHERE (GEO LIKE \"%" + query.getArg2() + "%\") AND (REF_DATE <= \"" + query.getEndDate() + "\" AND REF_DATE >= \"" + query.getStartDate() + "\")) AS G,\r\n"
				+ "(SELECT MAX(VALUE) AS \"" + query.getArg2() + " Max\" FROM echodata.echodata WHERE (GEO LIKE \"%" + query.getArg2() + "%\") AND (REF_DATE <= \"" + query.getEndDate() + "\" AND REF_DATE >= \"" + query.getStartDate() + "\")) AS H;";
	}
}
