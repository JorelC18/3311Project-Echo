package Chart;

import java.sql.ResultSet;

/**
 * An object class for holding the chart data needed to draw charts.
 * @author Jorel Louie Chim
 *
 */

public class ChartData {

	private ResultSet resultSet;
	private String selection1;
	private String selection2;
	private String selection3;
	
	/** 
	 * Constructs a new ChartData object with a result set, and the first and second selections.
     * @param resultSet the result set containing the data for the chart
     * @param selection1 the first selection for the chart
     * @param selection2 the second selection for the chart
     */
	
	public ChartData(ResultSet resultSet, String selection1, String selection2) {
		this.resultSet = resultSet;
		this.selection1 = selection1;
		this.selection2 = selection2;
	}
	
	/** 
	 * Constructs a new ChartData object with a result set, and the first, second, and third selections.
     * @param resultSet the result set containing the data for the chart
     * @param selection1 the first selection for the chart
     * @param selection2 the second selection for the chart
     * @param selection3 the third selection for the chart
     */
	
	public ChartData(ResultSet resultSet, String selection1, String selection2, String selection3) {
		this.resultSet = resultSet;
		this.selection1 = selection1;
		this.selection2 = selection2;
		this.selection3 = selection3;
	}
	
	/**
	 * Returns the result set for the chart.
	 * @return the result set containing the data for the chart.
	 */
	
	public ResultSet getResultSet() {
        return resultSet;
    }

	/**
     * Returns the first selection for the chart.
     * @return the first selection for the chart
     */
	
    public String getSelection1() {
        return selection1;
    }
    
    /**
     * Returns the second selection for the chart.
     * @return the second selection for the chart
     */

    public String getSelection2() {
        return selection2;
    }
    
    /**
     * Returns the third selection for the chart.
     * @return the third selection for the chart
     */

    public String getSelection3() {
        return selection3;
    }
}