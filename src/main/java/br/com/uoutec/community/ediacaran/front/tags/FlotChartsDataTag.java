package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class FlotChartsDataTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/flotcharts-data";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String flotchartid;
	
	private String flotchartseriesid;
	
	private String label;
	
	private Double x;
	
	private Double y; 
	
	public FlotChartsDataTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

    protected void beforeApplyTemplate(String template, Map<String, Object> vars, Writer out) throws IOException {
		this.flotchartid = ((FlotChartsSeriesTag)super.getParentTag()).getFlotchartid();
		this.flotchartseriesid = ((FlotChartsSeriesTag)super.getParentTag()).getId();
		this.label = ((FlotChartsSeriesTag)super.getParentTag()).getLabel();
    }
	
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public String getFlotchartid() {
		return flotchartid;
	}

	public void setFlotchartid(String flotchartid) {
		this.flotchartid = flotchartid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getFlotchartseriesid() {
		return flotchartseriesid;
	}

	public void setFlotchartseriesid(String flotchartseriesid) {
		this.flotchartseriesid = flotchartseriesid;
	}
	
}
