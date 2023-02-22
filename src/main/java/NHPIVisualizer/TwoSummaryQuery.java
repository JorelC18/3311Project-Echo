package NHPIVisualizer;


public class TwoSummaryQuery implements Query {

	private String selection1;
	private String selection2;
	private String startDate;
	private String endDate;
	
	public TwoSummaryQuery(String selection1, String selection2, String startDate, String endDate) {
		this.selection1 = selection1;
		this.selection2 = selection2;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getQuery() {
		
		System.out.println("SELECT * FROM (SELECT FORMAT(AVG(VALUE), 2) AS \"" + selection1 + " Average\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate +"\")) AS A,\r\n"
									+ "(SELECT FORMAT(AVG(VALUE), 2) AS \"" + selection2 +" Average\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection2 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS B,\r\n"
									+ "(SELECT FORMAT(STDDEV(VALUE), 2) AS \"" + selection1 + " Standard Deviation\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS C,\r\n"
									+ "(SELECT FORMAT(STDDEV(VALUE), 2) AS \"" + selection2 + " Standard Deviation\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection2 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS D,\r\n"
									+ "(SELECT MIN(VALUE) AS \"" + selection1 + " Min\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS E,\r\n"
									+ "(SELECT MAX(VALUE) AS \"" + selection1 + " Max\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS F,\r\n"
									+ "(SELECT MIN(VALUE) AS \"" + selection2 + " Min\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection2 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS G,\r\n"
									+ "(SELECT MAX(VALUE) AS \"" + selection2 + " Max\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection2 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS H;");
		
		return "SELECT * FROM (SELECT FORMAT(AVG(VALUE), 2) AS \"" + selection1 + " Average\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate +"\")) AS A,\r\n"
				+ "(SELECT FORMAT(AVG(VALUE), 2) AS \"" + selection2 +" Average\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection2 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS B,\r\n"
				+ "(SELECT FORMAT(STDDEV(VALUE), 2) AS \"" + selection1 + " Standard Deviation\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS C,\r\n"
				+ "(SELECT FORMAT(STDDEV(VALUE), 2) AS \"" + selection2 + " Standard Deviation\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection2 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS D,\r\n"
				+ "(SELECT MIN(VALUE) AS \"" + selection1 + " Min\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS E,\r\n"
				+ "(SELECT MAX(VALUE) AS \"" + selection1 + " Max\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection1 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS F,\r\n"
				+ "(SELECT MIN(VALUE) AS \"" + selection2 + " Min\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection2 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS G,\r\n"
				+ "(SELECT MAX(VALUE) AS \"" + selection2 + " Max\" FROM echodata.echodata WHERE (GEO LIKE \"%" + selection2 + "%\") AND (REF_DATE <= \"" + endDate + "\" AND REF_DATE >= \"" + startDate + "\")) AS H;";
		
	}
}
