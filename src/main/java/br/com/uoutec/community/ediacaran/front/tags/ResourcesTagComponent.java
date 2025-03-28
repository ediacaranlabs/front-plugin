package br.com.uoutec.community.ediacaran.front.tags;

import java.util.List;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.PublicResource;
import br.com.uoutec.community.ediacaran.front.theme.TemplateListVarsParser;
import br.com.uoutec.community.ediacaran.front.theme.Theme;

@Tag(name="resources", uri="https://www.uoutec.com.br/ediacaran/tags/components")
public class ResourcesTagComponent extends AbstractSimpleTagComponent {
	
	public static final String TEMPLATE = "/components/resources";
	
	private String type;
	
	public ResourcesTagComponent() {
	}

    protected Component createComponent() {
    	return new Component() {
    		
    		public void beforePrepareVars(Map<String, Object> vars) {
    			
    	    	Theme theme        = getTheme();
    	    	String packageName = getPackageTheme();
    			
    	    	TemplateListVarsParser content = new TemplateListVarsParser(TEMPLATE + "-" + type, packageName, theme);
    	    	ResourcesTagComponent.this.setContent(content);
    			
    	    	List<PublicResource> resources = theme.getResourcesByType(type, packageName);
    	    	
    			for(PublicResource pr: resources) {
    				content
    					.createNewItem()
    					.put("path", pr.getPath())
    					.put("resource", pr.getResource());
    			}
    			
    		}
    		
    	};
    }
	
	public String getType() {
		return type;
	}

	@TagAttribute(required=true, fragment=true)
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String getDefaultTemplate() {
		return TEMPLATE;
	}


}
