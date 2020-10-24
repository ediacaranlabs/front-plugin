package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class DropdownTag  extends ComponentFormTag {

	public static final String TEMPLATE  = "/components/dropdown";
	
	public static final String TEMPLATE2 = "/components/split-dropdown";
	
	public static final String TEMPLATE_WRAPPER = "/components/dropdown-wrapper";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private String style; //primary, secondary, success, info, warning, danger
	
	private String size; //lg, sm
	
	private Boolean split;
	
	private String variation; //up, right, left
	
	private JspFragmentVarParser itens;
	
	public DropdownTag() {
	}
	
    protected String getWrapperTemplate() {
    	return TEMPLATE_WRAPPER;
    }
    
    protected String getDefaultTemplate() {
    	return split == null || !split? TEMPLATE : TEMPLATE2;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.itens = new JspFragmentVarParser(getJspBody());
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Boolean getSplit() {
		return split;
	}

	public void setSplit(Boolean split) {
		this.split = split;
	}

	public String getVariation() {
		return variation;
	}

	public void setVariation(String variation) {
		this.variation = variation;
	}

	public JspFragmentVarParser getItens() {
		return itens;
	}

	public void setItens(JspFragmentVarParser itens) {
		this.itens = itens;
	}
    
}
