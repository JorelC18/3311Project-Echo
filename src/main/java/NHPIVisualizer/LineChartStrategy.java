package NHPIVisualizer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChartStrategy implements ChartStrategy {
	
	XYSeriesCollection dataset;
	
	
	public void drawChartFor2Series(ResultSet result, String selection1, String selection2, String type) {
		
		try {
			dataset = new XYSeriesCollection();
			
			
			XYSeries series1 = new XYSeries("Series 1");
			XYSeries series2 = new XYSeries("Series 2");
			
			while (result.next()) {
				String geo = result.getString("GEO");
				double refDate = Double.parseDouble(result.getString("REF_DATE").substring(0, 4));
				double value = result.getDouble("VALUE");
				
				if (geo.contains(selection1))
					series1.add(refDate, value);
				else
					series2.add(refDate, value);
					
			}
			
			dataset.addSeries(series1);
			dataset.addSeries(series2);
			
			JFreeChart chart = ChartFactory.createXYLineChart(String.format("%s compared to %s", selection1, selection2), 
					"Date", "Value", dataset,
					PlotOrientation.VERTICAL, true, true, false);
			
			XYPlot plot = chart.getXYPlot();
			
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesStroke(0, new BasicStroke(2.0f));

			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);
			
			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);
			
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			
			JFrame frame = new JFrame();
			
			frame.add(chartPanel);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.pack();
			System.out.println("checkkk");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void drawChartFor3Series(ResultSet result, String selection1, String selection2,
			String selection3, String type) {
		// TODO Auto-generated method stub
		
	}
}
