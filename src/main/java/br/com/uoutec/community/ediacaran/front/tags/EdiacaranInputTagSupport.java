package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.brandao.brutos.bean.BeanInstance;

public abstract class EdiacaranInputTagSupport extends EdiacaranSimpleTagSupport{

	public static final String ID_COUNT   = "_component_id_count";

	public static final String ATTR_FORMAT = "([a-z-_]+)=([^\\;]+)";

	@SuppressWarnings("serial")
	private static final Set<String> props = new HashSet<String>(EdiacaranSimpleTagSupport.DEFAULT_ATTRS) {{
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
	
	private Boolean enabled; //disabled
	
	private String max;
	
	private Integer maxlength;
	
	private String min;
	
	private String pattern;
	
	private Boolean readonly; //readonly
	
	private String required; //required
	
	private Integer size;
	
	private Integer step;
	
	private String value;
	
	private String name;
	
	private String type;

	private String accept;
	
	private String alt;
	
	private Boolean checked;

	private String src;
	
	private Boolean autocomplete; //on off
	
	private Boolean autofocus; //autofocus
	
	private String form;
	
	private String formaction;
	
	private String formenctype;
	
	private String formmethod;
	
	private Boolean formnovalidate;
	
	private String formtarget;
	
	private Integer height;
	
	private Integer width;
	
	private String list;
	
	private Boolean multiple; //multiple
	
	private String placeholder;
	
}
