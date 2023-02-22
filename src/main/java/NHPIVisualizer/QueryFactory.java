package NHPIVisualizer;

public class QueryFactory {
	
	public static Query createQuery(String queryType, String[] args, String startDate, String endDate) {
		if (queryType.equals("2 Provinces")) {
			return new TwoProvinceQuery(args[0], args[1], startDate, endDate);
		} else if (queryType.equals("2 Towns")) {
			return new TwoTownQuery(args[0], args[1], startDate, endDate);
		} else if (queryType.equals("3 Provinces")) {
			return new ThreeProvinceQuery(args[0], args[1], args[2], startDate, endDate);
		} else if (queryType.equals("3 Towns")) {
			return new ThreeTownQuery(args[0], args[1], args[2], startDate, endDate);
		} else if (queryType.equals("2 Summary")) {
			return new TwoSummaryQuery(args[0], args[1], startDate, endDate);
		} else if (queryType.equals("3 Summary")) {
			return new ThreeSummaryQuery(args[0], args[1], args[2], startDate, endDate);
		} else {
			throw new IllegalArgumentException("Wrong query type");
		}
	}
	
}
