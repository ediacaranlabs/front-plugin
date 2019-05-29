package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class ButtonTag extends EdiacaranSimpleTagSupport {

	public static final String TEMPLATE = "bootstrap4/templates/components/button";
	
	@SuppressWarnings("serial")
	private static final Set<String> empty = new HashSet<String>() {{
		add("classStyle");
		add("size");
		add("type");
		add("block");
		add("enabled");
	}};
	
	private String label;
	
	private String size;
	
	private String type;
	
	private Boolean block;
	
	private Boolean enabled;
	
	public ButtonTag() {
	}
	
    protected Set<String> getEmptyAttributes(){
    	return empty;
    }
	
    public void doTag() throws JspException, IOException{
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("size",    size == null               ? ""            : new String(" btn-").concat(size));
			vars.put("type",    type == null               ? "btn-primary" : new String(" btn-").concat(type));
			vars.put("block",   block == null              ? ""            : block? " btn-block" : ""        );
			vars.put("enabled", enabled != null && !enabled? " disabled"    : ""                             );
			vars.put("attr",    super.toAttrs()                                                              );
			vars.put("class",   super.getClassStyle()                                                        );
			vars.put("label",   label                                                                        );
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
