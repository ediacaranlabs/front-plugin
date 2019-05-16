package br.com.uoutec.community.ediacaran.front.tags;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.tagext.BodyTagSupport;

import org.brandao.brutos.bean.BeanInstance;

public abstract class EdiacaranBodyTagSupport extends BodyTagSupport{

	private static final long serialVersionUID = -4340864362251565376L;
	
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

	public String toAttrs(Map<String,String> template) {
		try {
			StringBuilder sb = new StringBuilder();
			BeanInstance i = new BeanInstance(this, EdiacaranBodyTagSupport.class);
			
			for(String p: props) {
				
				Object v = i.get(p);
				
				if(v != null) {
					
					if(sb.length() != 0) {
						sb.append(" ");
					}
					
					String t = template != null? template.get(p) : null;
					
					sb.append(p).append("=\"").append(t == null? v : t.replace("$1", String.valueOf(v))).append("\"");
					
				}
				
			}
			
			return sb.toString();
		}
		catch(Throwable e) {
			throw new IllegalStateException(e);
		}
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
	
}
