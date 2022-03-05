package br.com.uoutec.community.ediacaran.front.tags;

public abstract class BasicTag extends AbstractSimpleTagComponent{
	
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
