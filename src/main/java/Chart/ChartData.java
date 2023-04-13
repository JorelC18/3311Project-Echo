package Chart;

import java.sql.ResultSet;

public class ChartData {

	private ResultSet resultSet;
	private String selection1;
	private String selection2;
	private String selection3;
	
	public ChartData(ResultSet resultSet, String selection1, String selection2) {
		this.resultSet = resultSet;
		this.selection1 = selection1;
		this.selection2 = selection2;
	}
	
	public ChartData(ResultSet resultSet, String selection1, String selection2, String selection3) {
		this.resultSet = resultSet;
		this.selection1 = selection1;
		this.selection2 = selection2;
		this.selection3 = selection3;
	}
	
	public ResultSet getResultSet() {
        return resultSet;
    }

    public String getSelection1() {
        return selection1;
    }

    public String getSelection2() {
        return selection2;
    }

    public String getSelection3() {
        return selection3;
    }
}
