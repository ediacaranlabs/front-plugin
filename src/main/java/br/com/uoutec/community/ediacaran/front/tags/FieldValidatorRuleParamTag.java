package br.com.uoutec.community.ediacaran.front.tags;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.front.tags.FieldValidatorTag.ValidatorParamEntity;

public class FieldValidatorRuleParamTag extends AbstractSimpleTag {

	public static final String TEMPLATE = "/bootstrap4/components/content";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_PROPS) {{
			add("content");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_PROPERTY_PARSERS){{
				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((FieldValidatorRuleParamTag)component).getJspBody());
					}
				});
			}});
	
	private String name;
	
	private String value;
	
	private JspFragmentVarParser content;

	private ByteArrayOutputStream bout;
	
	private PrintWriter contentWriter;
	
	public FieldValidatorRuleParamTag() {
    	this.bout = new ByteArrayOutputStream();
    	this.contentWriter = new PrintWriter(bout);
	}
	
    protected Writer getOut() {
    	return contentWriter;
    }
	
    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	byte[] bValue = this.bout.toByteArray();
    	this.value = new String(bValue, "utf-8");
    	
    	FieldValidatorRuleTag tag = (FieldValidatorRuleTag)super.getParentTag();
    	tag.getValidator().getParams().add(new ValidatorParamEntity(this.name, this.value));
    	
    }
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	protected String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
    protected Set<String> getDefaultAttributes(){
    	return DEFAULT_ATTRS;
    }

    protected Set<String> getEmptyAttributes(){
    	return DEFAULT_EMPTY_ATTRIBUTES;
    }
    
    protected Map<String, AttributeParser> getAttributeParsers(){
    	return DEFAULT_ATTRIBUTE_PARSERS;
    }

    protected Set<String> getDefaultProperties(){
    	return DEFAULT_PROPS;
    }

    protected Map<String, AttributeParser> getPropertyParsers(){
    	return DEFAULT_PROPERTY_PARSERS;
    }
	
}
