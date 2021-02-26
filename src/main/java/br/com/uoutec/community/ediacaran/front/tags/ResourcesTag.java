package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import br.com.uoutec.community.ediacaran.system.theme.PublicResource;
import br.com.uoutec.community.ediacaran.system.theme.TemplateListVarsParser;
import br.com.uoutec.community.ediacaran.system.theme.Theme;

public class ResourcesTag extends AbstractSimpleComponent {
	
	public static final String TEMPLATE = "/components/resources";
	
	private String type;
	
	private TemplateListVarsParser content;
	
	public ResourcesTag() {
	}

	public void beforePrepareVars(Map<String, Object> vars) {
		
    	Theme theme        = getTheme();
    	String packageName = getThemePackage();
		
		this.content = new TemplateListVarsParser(TEMPLATE + "-" + type, packageName, theme);
		
    	ConcurrentMap<String, PublicResource> resources = theme.getResourcesByType(type, packageName);
    	
		for(PublicResource pr: resources.values()) {
			this.content
				.createNewItem()
				.put("path", pr.getPath())
				.put("resource", pr.getResource());
		}
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public TemplateListVarsParser getContent() {
		return content;
	}

	public void setContent(TemplateListVarsParser content) {
		this.content = content;
	}

	@Override
	protected String getDefaultTemplate() {
		return TEMPLATE;
	}


}
