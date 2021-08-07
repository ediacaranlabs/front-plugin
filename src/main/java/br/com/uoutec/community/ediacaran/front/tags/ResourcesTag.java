package br.com.uoutec.community.ediacaran.front.tags;

import java.util.List;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.PublicResource;
import br.com.uoutec.community.ediacaran.front.theme.TemplateListVarsParser;
import br.com.uoutec.community.ediacaran.front.theme.Theme;

@Tag(name="resources", uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components")
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
		
    	List<PublicResource> resources = theme.getResourcesByType(type, packageName);
    	
		for(PublicResource pr: resources) {
			this.content
				.createNewItem()
				.put("path", pr.getPath())
				.put("resource", pr.getResource());
		}
		
	}

	public String getType() {
		return type;
	}

	@TagAttribute(required=true, fragment=true)
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
