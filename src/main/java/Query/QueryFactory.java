package Query;

public class QueryFactory {
	
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
