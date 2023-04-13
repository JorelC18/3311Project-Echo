package Query;

public class Query {
	private String arg1;
	private String arg2;
	private String arg3;
	private String startDate;
	private String endDate;
	
	public Query(String arg1, String startDate, String endDate) {
		this.arg1 = arg1;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Query(String arg1, String arg2, String startDate, String endDate) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Query(String arg1, String arg2, String arg3, String startDate, String endDate) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.arg3 = arg3;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getArg1() {
		return arg1;
	}

	public String getArg2() {
		return arg2;
	}

	public String getArg3() {
		return arg3;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
}
