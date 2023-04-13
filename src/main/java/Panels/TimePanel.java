package Panels;

import MVC_Components.View;

public abstract class TimePanel {
	
	public abstract String getPanelName();
	
	public abstract String getStartDate(final View view);
	
	public abstract String getEndDate(final View view);
	
	public abstract void dateErrorChecking(final View view, String startDate, String endDate);

}
