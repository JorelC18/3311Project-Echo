package SQLConnection;

import weka.core.Instances;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.RemoveWithValues;

public class LinearRegressionDataRetrieval {
	/**
	 * retrieve data from the selected location 
	 * @param inputQuery the query command to get the data
	 */
	
	public Instances LinearRegression_DataRetrieval(String inputQuery) throws Exception {
		InstanceQuery query = new InstanceQuery();
	    String sql = inputQuery;
	    query.setDatabaseURL("jdbc:mysql://localhost:3306/echodata");
	    query.setUsername("root");
	    query.setPassword("password");
	    query.setQuery(sql);
	    Instances data = query.retrieveInstances();

	    // remove null values from the data
	    RemoveWithValues removeWithValues = new RemoveWithValues();
	    removeWithValues.setAttributeIndex("2");
	    removeWithValues.setMatchMissingValues(true);
	    removeWithValues.setInvertSelection(false);
	    removeWithValues.setInputFormat(data);
	    data = Filter.useFilter(data, removeWithValues);

	    return data;
	}
}
