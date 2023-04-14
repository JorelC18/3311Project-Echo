package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import MVC_Components.Model;
import MVC_Components.View;

public class rawDataController extends Controller {
	public rawDataController(View view, Model model) {
		super(view, model);
	}

	public void processRawData(final View view, final Model model) {
		JButton loadRawDataButton = view.getLoadRawDataButton();
		
		/**
		 * Adds an action listener to the load raw data button to show data loaded into a table based on the user's selections.
		 * @param e The ActionEvent object that represents the user's action taken
		 */
		
		loadRawDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller parentController = new rawDataController(view, model);
				parentController.setupTimeGranularityMap();
				parentController.setupGeoComboBoxMap();
				
				model.loadData(parentController.query);
				result = model.getData();
				
				DefaultTableModel tableModel = new DefaultTableModel();
				tableModel.setColumnIdentifiers(new String [] {"REF_DATE", "GEO", "NHPIs", "VALUE"});
				try {
					while (result.next()) {
						 tableModel.addRow(new Object [] {
					               result.getString("REF_DATE"), result.getString("GEO"), result.getString("NHPIs"), result.getString("VALUE")});
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				view.getRawDataTable().setModel(tableModel);
				view.getRawDataTable().getColumnModel().getColumn(1).setPreferredWidth(250);
				view.getTablePanel().revalidate();
				view.getTablePanel().repaint();
				view.getCardLayout3().show(view.getTablePanel(), "Raw Data");
			}
		});
	}
}
