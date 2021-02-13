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
