package NHPIVisualizer;

public class ThreeProvinceQuery implements Query {
	
	private String province1;
	private String province2;
	private String province3;
	private String startDate;
	private String endDate;
	
	public ThreeProvinceQuery(String province1, String province2, String province3, String startDate, String endDate) {
		this.province1 = province1;
		this.province2 = province2;
		this.province3 = province3;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getQuery() {
		System.out.println("SELECT * FROM echodata.echodata WHERE (GEO LIKE \"%" + province1 + "%\"" 
				+ " OR GEO LIKE \"%" + province2 + "%\"" 
				+ " OR GEO LIKE \"%" + province3 + "%\")"
				+ " AND (REF_DATE <= " + "\"" + endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");");
		
		return "SELECT * FROM echodata.echodata WHERE (GEO LIKE \"%" + province1 + "%\"" 
				+ " OR GEO LIKE \"%" + province2 + "%\"" 
				+ " OR GEO LIKE \"%" + province3 + "%\")"
				+ " AND (REF_DATE <= " + "\"" + endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");";
	}
	
}