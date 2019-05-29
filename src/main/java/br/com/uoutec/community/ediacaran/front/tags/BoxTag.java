package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class BoxTag extends BasicTag {

	public static final String TEMPLATE             = "bootstrap4/templates/components/box";
	
	@SuppressWarnings("serial")
	private static final Set<String> ignore = new HashSet<String>() {{
		add("class");
	}};
	
	private String icon;
	
	private String title;
	
	private String lnk;
	
	private String btn;
	
	public BoxTag() {
	}
	
    public void doTag() throws JspException, IOException{
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("attr",    super.toAttrs(null, ignore));
			vars.put("icon",    icon);
			vars.put("title",   title);
			vars.put("lnk",     lnk);
			vars.put("btn",     btn);
			vars.put("content", new JspFragmentVarParser(getJspBody()));
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLnk() {
		return lnk;
	}

	public void setLnk(String lnk) {
		this.lnk = lnk;
	}

	public String getBtn() {
		return btn;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}
	
}
