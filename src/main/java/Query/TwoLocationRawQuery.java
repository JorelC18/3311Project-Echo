package Query;

public class TwoLocationRawQuery implements Query {
	private String selection1;
	private String selection2;
	private String startDate;
	private String endDate;
	
	public TwoLocationRawQuery(String selection1, String selection2, String startDate, String endDate) {
		this.selection1 = selection1;
		this.selection2 = selection2;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getQuery() {
		/*System.out.println("SELECT * FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\"" + " OR GEO LIKE \"%" + selection2 + "%\")" 
		+ " AND (REF_DATE <= " + "\"" + endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");");*/

		return "SELECT * FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\"" + " OR GEO LIKE \"%" + selection2 + "%\")" 
		+ " AND (REF_DATE <= " + "\"" + endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");";
	}
}
