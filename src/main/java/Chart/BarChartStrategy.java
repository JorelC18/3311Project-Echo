package Chart;

import java.sql.ResultSet;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * A class that implements the ChartStrategy interface and provides a bar chart strategy for drawing charts with 2 or 3 series
 * using the JFreeChart library.
 * @author Jorel Louie Chim
 */

public class BarChartStrategy implements ChartStrategy {
	
	DefaultCategoryDataset dataset;
	ResultSet result;
	String selection1;
	String selection2;
	String selection3;
	
	/**
	 * Draws a bar chart for 2 series based on the given ResultSet, and the 2 selections from the user.
	 * @param chartData a chart data object containing the result set and selections
	 */
	
	public void drawChartFor2Series(ChartData chartData) {
		
		dataset = new DefaultCategoryDataset();
		result = chartData.getResultSet();
		selection1 = chartData.getSelection1();
		selection2 = chartData.getSelection2();
		
		try {
			while (result.next()) {
				String geo = result.getString("GEO");
				String refDate = result.getString("REF_DATE").substring(0, 4);
				String valueString = result.getString("VALUE");
				double value;
				
				if (valueString != null && valueString != "") {
					value = Double.parseDouble(valueString);
					
					if (geo.contains(selection1)) 
						dataset.addValue(value, selection1, refDate);
					else
						dataset.addValue(value, selection2, refDate);
				}
				
				
					
			}
			
			JFreeChart barChart = ChartFactory.createBarChart(
					String.format("%s vs %s: Last Values in Corresponding Dates", selection1, selection2),
					"Date",
					"Last Value in Corresponding Date",
					dataset,
					PlotOrientation.VERTICAL,
					true,
					true,
					false
			);
			
			ChartPanel chartPanel = new ChartPanel(barChart);
			JFrame frame = new JFrame("My Chart");
	        frame.add(chartPanel);
	        frame.pack();
	        frame.setVisible(true);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Draws a bar chart for 2 series based on the given ResultSet, and the 3 selections from the user.
	 * @param chartData a chart data object containing the result set and selections
	 */

	public void drawChartFor3Series(ChartData chartData) {
		
		dataset = new DefaultCategoryDataset();
		result = chartData.getResultSet();
		selection1 = chartData.getSelection1();
		selection2 = chartData.getSelection2();
		selection3 = chartData.getSelection3();
		
		try {
			while (result.next()) {
				String geo = result.getString("GEO");
				String refDate = result.getString("REF_DATE").substring(0, 4);
				String valueString = result.getString("VALUE");
				double value;
				
				if (valueString != null && valueString != "") {
					value = Double.parseDouble(valueString);
					
					if (geo.contains(selection1)) 
						dataset.addValue(value, selection1, refDate);
					else if (geo.contains(selection2)) 
						dataset.addValue(value, selection2, refDate);
					else
						dataset.addValue(value, selection3, refDate);
				}
				
				
					
			}
			
			JFreeChart barChart = ChartFactory.createBarChart(
					String.format("%s vs %s vs %s: Last Values in Corresponding Dates", selection1, selection2, selection3),
					"Date",
					"Last Value in Corresponding Date",
					dataset,
					PlotOrientation.VERTICAL,
					true,
					true,
					false
			);
			
			ChartPanel chartPanel = new ChartPanel(barChart);
			JFrame frame = new JFrame("My Chart");
	        frame.add(chartPanel);
	        frame.pack();
	        frame.setVisible(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
