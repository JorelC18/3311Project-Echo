package NHPIVisualizer;

public class ThreeTownQuery implements Query {
	
	private String town1;
	private String town2;
	private String town3;
	private String startDate;
	private String endDate;
	
	public ThreeTownQuery(String town1, String town2, String town3, String startDate, String endDate) {
		this.town1 = town1;
		this.town2 = town2;
		this.town3 = town3;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getQuery() {
		return "SELECT * FROM echodata.echodata WHERE (GEO=" + "\"" + town1 + "\"" + 
				" OR GEO=" + "\"" + town2 + "\"" + 
						" OR GEO=" + "\"" + town3 + "\")" + " AND (REF_DATE <= " + "\"" + 
				endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");";
	}
	
}
