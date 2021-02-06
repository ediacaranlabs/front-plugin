package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class FlotChartsTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/flotcharts";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String src;
	
	private Boolean togglingSeries; 
	
	private Integer updateFrequence;
	
	private String updateFrequenceUnit;
	
	private JspFragmentVarParser content;
	
	public FlotChartsTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}
    
	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Boolean getTogglingSeries() {
		return togglingSeries;
	}

	public void setTogglingSeries(Boolean togglingSeries) {
		this.togglingSeries = togglingSeries;
	}

	public Integer getUpdateFrequence() {
		return updateFrequence;
	}

	public void setUpdateFrequence(Integer updateFrequence) {
		this.updateFrequence = updateFrequence;
	}

	public String getUpdateFrequenceUnit() {
		return updateFrequenceUnit;
	}

	public void setUpdateFrequenceUnit(String updateFrequenceUnit) {
		this.updateFrequenceUnit = updateFrequenceUnit;
	}
	
}
