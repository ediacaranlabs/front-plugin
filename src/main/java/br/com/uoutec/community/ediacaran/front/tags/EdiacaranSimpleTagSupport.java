package br.com.uoutec.community.ediacaran.front.tags;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.brandao.brutos.bean.BeanInstance;

public abstract class EdiacaranSimpleTagSupport extends SimpleTagSupport{

	public static final String ID_COUNT   = "_component_id_count";

	public static final String ATTR_FORMAT = "([a-z-_]+)=([^\\;]+)";

	@SuppressWarnings("serial")
	private static final Set<String> props = new HashSet<String>() {{
		add("accesskey");
		add("classType");   
		add("contenteditable");
		add("contextmenu");
		add("dir");
		add("draggable");
		add("hidden");
		add("id");
		add("lang");
		add("spellcheck");
		add("style");
		add("tabindex");
		add("title");
	}};
	
	private String accesskey;
	
	private String classType;
	
	private Boolean contenteditable;
	
	private	String contextmenu;

	private String dir;

	private Boolean	draggable;

	private Boolean hidden;

	private	String id;

	private String lang;
	
	private Boolean spellcheck;
	
	private String style;
	
	private Integer tabindex;
			
	private String title;

	private String attr;
	
	private String template;
	
	public String toAttrs(Map<String,String> template, Set<String> ignore) {
		try {
			StringBuilder sb = new StringBuilder();
			BeanInstance i = new BeanInstance(this, EdiacaranSimpleTagSupport.class);
			
			for(String p: props) {
				
				Object v = i.get(p);
				
				if((ignore == null || !ignore.contains(p)) && v != null) {
					
					if(sb.length() != 0) {
						sb.append(" ");
					}
					
					String t = template != null? template.get(p) : null;
					
					sb
						.append(p.equals("classType")? "class" : p)
						.append("=\"")
							.append(t == null? v : t.replace("$1", String.valueOf(v)))
						.append("\"");
					
				}
				
			}
			
			return sb.toString();
		}
		catch(Throwable e) {
			throw new IllegalStateException(e);
		}
	}
	
	private Map<String,String> getAttrs(){
		if(attr == null) {
			return null;
		}
		
		Map<String,String> a = new HashMap<String,String>();
		
	}
	
    protected Object setProperty(String name, Object newValue) {
		Object old = (Integer) this.getJspContext().getAttribute(name);
		this.getJspContext().setAttribute(name, newValue);
    	return old;
    }

    protected Object getProperty(String name) {
    	return getProperty(name, null);
    }
    
    protected Object getProperty(String name, Object defaultValue) {
		Object val = (Integer) this.getJspContext().getAttribute(name);
    	return val == null? defaultValue : val;
    }
    
	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
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

	public String getId() {
		
		if(id == null) {
			Integer acc = (Integer) this.getJspContext().getAttribute(ID_COUNT);
			getJspContext().setAttribute(ID_COUNT, acc == null? 0 : acc.intValue() + 1);
			return String.valueOf(acc);
		}
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
}
