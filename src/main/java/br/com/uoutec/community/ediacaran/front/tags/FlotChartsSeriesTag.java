package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class FlotChartsSeriesTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/flotcharts-series";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	
	private String flotchartid;
	
	private String label;
	
	private String color;
	
	private Boolean lines;
	
	private Boolean bars;
	
	private Boolean points;
	
	private Integer xaxis;
	
	private Integer yaxis;
	
	private Boolean clickable;
	
	private Boolean hoverable;
	
	private Integer shadowSize;
	
	private String highlightColor;
	
	private JspFragmentVarParser content;
	
	public FlotChartsSeriesTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

    protected void beforeApplyTemplate(String template, Map<String, Object> vars, Writer out) throws IOException {
		this.flotchartid = ((FlotChartsTag)super.getParentTag()).getId();
    }
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean getLines() {
		return lines;
	}

	public void setLines(Boolean lines) {
		this.lines = lines;
	}

	public Boolean getBars() {
		return bars;
	}

	public void setBars(Boolean bars) {
		this.bars = bars;
	}

	public Boolean getPoints() {
		return points;
	}

	public void setPoints(Boolean points) {
		this.points = points;
	}

	public Integer getXaxis() {
		return xaxis;
	}

	public void setXaxis(Integer xaxis) {
		this.xaxis = xaxis;
	}

	public Integer getYaxis() {
		return yaxis;
	}

	public void setYaxis(Integer yaxis) {
		this.yaxis = yaxis;
	}

	public Boolean getClickable() {
		return clickable;
	}

	public void setClickable(Boolean clickable) {
		this.clickable = clickable;
	}

	public Boolean getHoverable() {
		return hoverable;
	}

	public void setHoverable(Boolean hoverable) {
		this.hoverable = hoverable;
	}

	public Integer getShadowSize() {
		return shadowSize;
	}

	public void setShadowSize(Integer shadowSize) {
		this.shadowSize = shadowSize;
	}

	public String getHighlightColor() {
		return highlightColor;
	}

	public void setHighlightColor(String highlightColor) {
		this.highlightColor = highlightColor;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	public String getFlotchartid() {
		return flotchartid;
	}

	public void setFlotchartid(String flotchartid) {
		this.flotchartid = flotchartid;
	}
	
}
