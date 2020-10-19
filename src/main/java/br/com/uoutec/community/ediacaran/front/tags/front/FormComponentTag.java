package br.com.uoutec.community.ediacaran.front.tags.front;

@Deprecated
public abstract class FormComponentTag extends BasicTag{
/*
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = new HashSet<String>(BasicTag.DEFAULT_ATTRS) {{
		add("autofocus");
		add("enabled");
		add("form");
		add("formaction");
		add("formenctype");
		add("formmethod");
		add("formnovalidate");
		add("formtarget");
		add("name");
		add("type");
		add("value");
	}};
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(BasicTag.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("enabled", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value) {
					return value != null && !((Boolean)value)? "disabled" : "";
				}
			});

			put("autofocus", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value) {
					return value != null && ((Boolean)value)? "autofocus" : "";
				}
			});

		}});
	
	private Boolean enabled; //disabled
	
	private String value;
	
	private String name;
	
	private String type;

	private Boolean autocomplete; //on off
	
	private Boolean autofocus; //autofocus
	
	private String formaction;
	
	private String formenctype;
	
	private String formmethod;
	
	private Boolean formnovalidate;
	
	private String formtarget;
	
    protected Set<String> getDefaultAttributes(){
    	return DEFAULT_ATTRS;
    }
	
    protected Map<String, AttributeParser> getAttributeParsers(){
    	return DEFAULT_ATTRIBUTE_PARSERS;
    }

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getAutocomplete() {
		return autocomplete;
	}

	public void setAutocomplete(Boolean autocomplete) {
		this.autocomplete = autocomplete;
	}

	public Boolean getAutofocus() {
		return autofocus;
	}

	public void setAutofocus(Boolean autofocus) {
		this.autofocus = autofocus;
	}

	public String getFormaction() {
		return formaction;
	}

	public void setFormaction(String formaction) {
		this.formaction = formaction;
	}

	public String getFormenctype() {
		return formenctype;
	}

	public void setFormenctype(String formenctype) {
		this.formenctype = formenctype;
	}

	public String getFormmethod() {
		return formmethod;
	}

	public void setFormmethod(String formmethod) {
		this.formmethod = formmethod;
	}

	public Boolean getFormnovalidate() {
		return formnovalidate;
	}

	public void setFormnovalidate(Boolean formnovalidate) {
		this.formnovalidate = formnovalidate;
	}

	public String getFormtarget() {
		return formtarget;
	}

	public void setFormtarget(String formtarget) {
		this.formtarget = formtarget;
	}
*/    
}
