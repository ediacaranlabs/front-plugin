package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="flotcharts-series", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FlotChartsSeriesTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/flotcharts-series";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	
	private String flotchartid;
	
	private String label;
	
	private String data;
	
	public FlotChartsSeriesTagComponent() {
	}
	
	protected void beforeBuildComponent(Component component) {
		//FlotChartsSeriesTagComponent fcstg = (FlotChartsSeriesTagComponent)getParentTag();
		FlotChartsTagComponent fctc = (FlotChartsTagComponent)getParentTag();
		flotchartid = fctc.getId();
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getLabel() {
		return label;
	}

	@TagAttribute
	public void setLabel(String label) {
		this.label = label;
	}

	public String getFlotchartid() {
		return flotchartid;
	}

	public void setFlotchartid(String flotchartid) {
		this.flotchartid = flotchartid;
	}

	public String getData() {
		return data;
	}

	@TagAttribute
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String getType() {
		return "flotcharts-series";
	}
	
}
