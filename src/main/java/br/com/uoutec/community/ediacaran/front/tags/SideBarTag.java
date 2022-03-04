package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class SideBarTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/sidebar";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Integer size;
	
	private String align;
	
	public SideBarTag() {
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    		public void beforePrepareVars(Map<String, Object> vars) {
    			int offset = "right".equalsIgnoreCase(align)? (size == null? 11 : 12 - size) : -1; 
    			vars.put("offset", offset <= 0? "" : "offset-lg-" + offset + " offset-xl-" + offset);
    	    }
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

}
