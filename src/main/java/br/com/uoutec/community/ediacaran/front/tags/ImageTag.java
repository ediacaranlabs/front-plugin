package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class ImageTag  extends AbstractTag {

	public static final String TEMPLATE  = "/bootstrap4/templates/components/image";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_ATTRS) {{
			//add("");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_ATTRIBUTE_PARSERS){{
			/*
			put("", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return value;
				}
				
				@Override
				public Object toValue(Object value) {
					return value;
				}
				
			});
			*/
			
		}});

	/* ------------ Attr ---------------*/
	
	private String src;

	/* ------------ Prop ---------------*/
	
	private String style; //fluid, thumbnail, rounded, circle (img-<...> | rounded | rounded-circle)
	
	private String align; //left, right, center (float-<...> | mx-auto d-block)
	
	public ImageTag() {
	}
	
    public void doInnerTag() throws JspException, IOException{
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("attr",           super.toAttrs());
			vars.put("style",   style == null? null : (style.equals("rounded")? " rounded") : (style.eq) );
			vars.put("type",    this.style == null? "" : new String(" btn-").concat(this.style));
			vars.put("size",    this.size == null? "" : new String(" btn-").concat(this.size));
			vars.put("variation",    this.variation == null? "" : new String(" drop").concat(this.variation));
			vars.put("itens", new JspFragmentVarParser(getJspBody()));
			
			TemplatesManager.getTemplatesManager()
				.apply(
						this.getTemplate() == null? 
								(split == null || !split? TEMPLATE : TEMPLATE2) : 
								this.getTemplate(), 
						vars, getJspContext().getOut()
				);
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }
	
}
