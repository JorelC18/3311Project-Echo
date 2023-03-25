package Query;

public class t_TestQuery implements Query {
	private String selection1;
	private String startDate;
	private String endDate;
	
	public t_TestQuery(String selection1, String startDate, String endDate) {
		this.selection1 = selection1;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getQuery() {
		return "SELECT VALUE FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\"" + ")" + " AND (REF_DATE <= " + "\"" + 
		endDate + "\" AND REF_DATE >= " + "\"" + startDate + "\");";
	}
}
