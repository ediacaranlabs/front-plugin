package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="flotcharts-data", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FlotChartsDataTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/flotcharts-data";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String flotchartid;
	
	private String flotchartseriesid;
	
	private String label;
	
	private Double x;
	
	private Double y; 
	
	public FlotChartsDataTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	protected void beforeBuildComponent(Component component) {
		FlotChartsSeriesTagComponent fcstg = (FlotChartsSeriesTagComponent)getParentTag();
		flotchartid = fcstg.getFlotchartid();
		flotchartseriesid = fcstg.getId();
		label = fcstg.getLabel();
	}
	
	public Double getX() {
		return x;
	}

	@TagAttribute
	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	@TagAttribute
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

	@TagAttribute
	public void setLabel(String label) {
		this.label = label;
	}

	public String getFlotchartseriesid() {
		return flotchartseriesid;
	}

	public void setFlotchartseriesid(String flotchartseriesid) {
		this.flotchartseriesid = flotchartseriesid;
	}
	
	@Override
	public String getType() {
		return "flotcharts-data";
	}
	
}
