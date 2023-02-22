package NHPIVisualizer;

public class TwoTownQuery implements Query {
	
	private String town1;
	private String town2;
	private String startDate;
	private String endDate;
	
	public TwoTownQuery(String town1, String town2, String startDate, String endDate) {
		this.town1 = town1;
		this.town2 = town2;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getQuery() {
		return "SELECT * FROM echodata.echodata WHERE (GEO=" + "\"" + town1 + "\"" + 
				" OR GEO=" + "\"" + town2 + "\")" + " AND (REF_DATE <= " + "\"" + 
				endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");";
	}
	
}
