package NHPIVisualizer;

public class TwoProvinceQuery implements Query {
	
	private String province1;
	private String province2;
	private String startDate;
	private String endDate;
	
	public TwoProvinceQuery(String province1, String province2, String startDate, String endDate) {
		this.province1 = province1;
		this.province2 = province2;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getQuery() {
		return "SELECT * FROM echodata.echodata WHERE (GEO=" + "\"" + province1 + "\"" + 
				" OR GEO=" + "\"" + province2 + "\")" + " AND (REF_DATE <= " + "\"" + 
				endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");";
	}
	
}
