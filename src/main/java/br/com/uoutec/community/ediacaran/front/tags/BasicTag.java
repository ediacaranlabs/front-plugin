package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BasicTag extends AbstractTag{

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_ATTRS) {{
			add("accesskey");
			add("classType");   
			add("contenteditable");
			add("contextmenu");
			add("dir");
			add("draggable");
			add("hidden");
			add("lang");
			add("spellcheck");
			add("style");
			add("tabindex");
			add("title");
		}});

	protected static final Set<String> DEFAULT_EMPTY_ATTRIBUTES = 
			Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_EMPTY_ATTRIBUTES));
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_ATTRIBUTE_PARSERS){{
				
				put("classType", new AttributeParserImp() {
					
					@Override
					public String toName(String value) {
						return value == null? null : "class";
					}
					
				});

				put("draggable", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						return value != null && (Boolean)value? "true" : "false";
					}
				});
				
			}});
	
	private String accesskey;
	
	private String classStyle;
	
	private Boolean contenteditable;
	
	private	String contextmenu;

	private String dir;

	private Boolean	draggable;

	private Boolean hidden;

	private String lang;
	
	private Boolean spellcheck;
	
	private String style;
	
	private Integer tabindex;
			
	private String title;

    protected Set<String> getDefaultAttributes(){
    	return DEFAULT_ATTRS;
    }

    protected Set<String> getEmptyAttributes(){
    	return DEFAULT_EMPTY_ATTRIBUTES;
    }
    
    protected Map<String, AttributeParser> getAttributeParsers(){
    	return DEFAULT_ATTRIBUTE_PARSERS;
    }
    
	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public String getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

	public Boolean getContenteditable() {
		return contenteditable;
	}

	public void setContenteditable(Boolean contenteditable) {
		this.contenteditable = contenteditable;
	}

	public String getContextmenu() {
		return contextmenu;
	}

	public void setContextmenu(String contextmenu) {
		this.contextmenu = contextmenu;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public Boolean getDraggable() {
		return draggable;
	}

	public void setDraggable(Boolean draggable) {
		this.draggable = draggable;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Boolean getSpellcheck() {
		return spellcheck;
	}

	public void setSpellcheck(Boolean spellcheck) {
		this.spellcheck = spellcheck;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getTabindex() {
		return tabindex;
	}

	public void setTabindex(Integer tabindex) {
		this.tabindex = tabindex;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
