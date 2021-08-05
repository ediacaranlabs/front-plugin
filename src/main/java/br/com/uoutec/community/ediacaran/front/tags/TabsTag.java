package br.com.uoutec.community.ediacaran.front.tags;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.theme.TemplateListVarsParser;

public class TabsTag extends AbstractPanelComponent {

	private static final long serialVersionUID = 748182107582888257L;

	private static final String CONTENT_ITEM = "/components/tabs-content-item";
	
	private static final String HEADER_ITEM = "/components/tabs-header-item";

	public static final String TEMPLATE  = "/components/tabs";
	
	private TemplateListVarsParser header;
	
	private TemplateListVarsParser content;
	
	private String style;
	
	private int index;
	
	public TabsTag() {
		this.index = 0;
	}
	
	public void add(TabsItemTag item) {
		index++;
		//boolean active = Boolean.TRUE.equals(item.getActive());
		
		header.createNewItem(item)
		.put("parentID", getId())
		.put("id", index);
		
		content.createNewItem(item)
		.put("parentID", getId())
		.put("id", index);
		
		/*
		header.createNewItem()
			.put("active", active? "active" : "")
			.put("parentID", getId())
			.put("id", index)
			.put("selected", active)
			.put("title", item.getTitle());
		*/
		
		/*
		content.createNewItem()
			.put("show", active? "show" : "")
			.put("active", active? "active" : "")
			.put("parentID", getId())
			.put("id", index)
			.put("content", item.toVarParser());
		*/
	}
	
    public int doStartTag() throws JspException {
    	this.index   = 1;
		this.header  = new TemplateListVarsParser(HEADER_ITEM, getPackageTheme(), getTheme());
		this.content = new TemplateListVarsParser(CONTENT_ITEM, getPackageTheme(), getTheme());
        return EVAL_BODY_BUFFERED;
    }


    public int doEndTag() throws JspException {
    	this.index   = 0;
		this.header  = null;
		this.content = null;
    	return super.doEndTag();
    }
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public int getNextIndex() {
		return ++index;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
