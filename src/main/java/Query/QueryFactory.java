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
	
	public static QueryInterface createQuery(String queryType, String[] args, String startDate, String endDate) {		
		Query query;
		if (queryType.equals("2 Provinces") || queryType.equals("2 Towns")) {
			query = new Query(args[0], args[1], startDate, endDate);
			return new TwoLocationRawQuery(query);
		}
		else if (queryType.equals("3 Provinces") || queryType.equals("3 Towns")) {
			query = new Query(args[0], args[1], args[2], startDate, endDate);
			return new ThreeLocationRawQuery(query);
		}
		else if (queryType.equals("2 Summary")) {
			query = new Query(args[0], args[1], startDate, endDate);
			return new TwoLocationSummaryQuery(query);
		}
		else if (queryType.equals("3 Summary")) {
			query = new Query(args[0], args[1], args[2], startDate, endDate);
			return new ThreeLocationSummaryQuery(query);
		}
		else if (queryType.equals("t_Test")) {
			query = new Query(args[0], startDate, endDate);
			return new t_TestQuery(query);
		}
		else if (queryType.equals("Forecasting")) {
			query = new Query(args[0], startDate, endDate);
			return new ForecastingQuery(query);
		}
		else {
			throw new IllegalArgumentException("Wrong query type");
		}
	}
	
}
