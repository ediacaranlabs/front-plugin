package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
		name="flotcharts", 
		uri="https://www.uoutec.com.br/ediacaran/tags/components", 
		bodycontent=BodyTypes.SCRIPTLESS
		)
public class FlotChartsTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/flotcharts";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String src;
	
	private Boolean togglingSeries; 
	
	private Integer updateFrequence;
	
	private String updateFrequenceUnit;
	
	public FlotChartsTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getSrc() {
		return src;
	}

	@TagAttribute
	public void setSrc(String src) {
		this.src = src;
	}

	public Boolean getTogglingSeries() {
		return togglingSeries;
	}

	@TagAttribute
	public void setTogglingSeries(Boolean togglingSeries) {
		this.togglingSeries = togglingSeries;
	}

	public Integer getUpdateFrequence() {
		return updateFrequence;
	}

	@TagAttribute
	public void setUpdateFrequence(Integer updateFrequence) {
		this.updateFrequence = updateFrequence;
	}

	public String getUpdateFrequenceUnit() {
		return updateFrequenceUnit;
	}

	@TagAttribute
	public void setUpdateFrequenceUnit(String updateFrequenceUnit) {
		this.updateFrequenceUnit = updateFrequenceUnit;
	}
	@Override
	public String getType() {
		return "flotcharts";
	}
	
}
