package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.tags.FieldValidatorTag.ValidatorEntity;
import br.com.uoutec.community.ediacaran.front.tags.FieldValidatorTag.ValidatorParamEntity;

public class FieldValidatorRuleParamTag extends AbstractBodyTag {

	private static final long serialVersionUID = 748182107582888257L;

	public static final String TEMPLATE  = "";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractBodyTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractBodyTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractBodyTag.DEFAULT_PROPS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractBodyTag.DEFAULT_PROPERTY_PARSERS){{
			}});
	
	private String name;
	
	private String message;
	
	private ValidatorParamEntity param;
	
    public void doInitBody() throws JspException {
		FieldValidatorTag tag = (FieldValidatorTag)super.getParentTag();
		
		if(tag == null) {
			throw new IllegalStateException("validator not found");
		}
    	
		this.param = new ValidatorParamEntity(name, message);
    }
	
    public int doAfterBody() throws JspException {
    	
    	ValidatorEntity tag = (ValidatorEntity)super.getParentTag();
    	tag.getParams().add(param);
    	
		this.param = null;
		
    	return SKIP_BODY;
    }
	
}
