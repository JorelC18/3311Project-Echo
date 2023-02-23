package NHPIVisualizer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChartStrategy implements ChartStrategy {
	
	XYSeriesCollection dataset;
	
	
	public void drawChartFor2Series(ResultSet result, String selection1, String selection2) {
		
		try {
			dataset = new XYSeriesCollection();
			
			
			XYSeries series1 = new XYSeries(selection1);
			XYSeries series2 = new XYSeries(selection2);
			
			System.out.println(selection1 + ", " + selection2);
			
			while (result.next()) {
				String geo = result.getString("GEO");
				int refDate = Integer.parseInt(result.getString("REF_DATE").substring(0, 4));
				String valueString = result.getString("VALUE");
				double value;
				
				if (valueString != null && valueString != "") {
					value = Double.parseDouble(valueString);
					
					if (geo.contains(selection1)) 
						series1.add(refDate, value);
					else
						series2.add(refDate, value);
				}
				
				
					
			}
			
			dataset.addSeries(series1);
			dataset.addSeries(series2);
			
			JFreeChart chart = ChartFactory.createXYLineChart(String.format("%s vs %s", selection1, selection2), 
					"Date", "Value", dataset,
					PlotOrientation.VERTICAL, true, true, false);
			
			XYPlot plot = chart.getXYPlot();
			
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesPaint(1, Color.BLUE);
			renderer.setSeriesStroke(0, new BasicStroke(2.0f));
			renderer.setSeriesShapesVisible(0, false);
			renderer.setSeriesShapesVisible(1, false);

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
			String selection3) {
		
		try {
			dataset = new XYSeriesCollection();
			
			
			XYSeries series1 = new XYSeries(selection1);
			XYSeries series2 = new XYSeries(selection2);
			XYSeries series3 = new XYSeries(selection3);
			
			while (result.next()) {
				String geo = result.getString("GEO");
				int refDate = Integer.parseInt(result.getString("REF_DATE").substring(0, 4));
				String valueString = result.getString("VALUE");
				double value;
				
				if (valueString != null && valueString != "") {
					value = Double.parseDouble(valueString);
					
					if (geo.contains(selection1)) 
						series1.add(refDate, value);
					else if (geo.contains(selection2))
						series2.add(refDate, value);
					else
						series3.add(refDate, value);
				}
					
			}
			
			dataset.addSeries(series1);
			dataset.addSeries(series2);
			dataset.addSeries(series3);
			
			JFreeChart chart = ChartFactory.createXYLineChart(String.format("%s vs %s vs %s", selection1, selection2, selection3), 
					"Date", "Value", dataset,
					PlotOrientation.VERTICAL, true, true, false);
			
			XYPlot plot = chart.getXYPlot();
			
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesPaint(1, Color.BLUE);
			renderer.setSeriesPaint(2, Color.GREEN);
			renderer.setSeriesStroke(0, new BasicStroke(2.0f));
			renderer.setSeriesShapesVisible(0, false);
			renderer.setSeriesShapesVisible(1, false);
			renderer.setSeriesShapesVisible(2, false);


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
}
