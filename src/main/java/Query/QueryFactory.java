package Query;

/**
 * Factory class for creating queries.
 * @author Jorel Louie Chim
 */

public class QueryFactory {
	
	/**
	 * Creates different types of queries based on the user's selections.
	 * @param queryType the query type selected from the user
	 * @param args the towns or provinces the user has selected
	 * @param startDate the start date the user has selected
	 * @param endDate the end date the user has selected
	 * @return a new query object depending on the query type
	 */
	
	public static Query createQuery(String queryType, String[] args, String startDate, String endDate) {		
		if (queryType.equals("2 Provinces") || queryType.equals("2 Towns")) {
			return new TwoLocationRawQuery(args[0], args[1], startDate, endDate);
		}
		else if (queryType.equals("3 Provinces") || queryType.equals("3 Towns")) {
			return new ThreeLocationRawQuery(args[0], args[1], args[2], startDate, endDate);
		}
		else if (queryType.equals("2 Summary")) {
			return new TwoLocationSummaryQuery(args[0], args[1], startDate, endDate);
		}
		else if (queryType.equals("3 Summary")) {
			return new ThreeLocationSummaryQuery(args[0], args[1], args[2], startDate, endDate);
		}
		else if (queryType.equals("t_Test")) {
			return new t_TestQuery(args[0], startDate, endDate);
		}
		else {
			throw new IllegalArgumentException("Wrong query type");
		}
	}
	
}
