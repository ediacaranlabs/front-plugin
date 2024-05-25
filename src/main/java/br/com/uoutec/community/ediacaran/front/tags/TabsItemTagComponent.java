package br.com.uoutec.community.ediacaran.front.tags;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;
import br.com.uoutec.community.ediacaran.front.theme.Theme;
import br.com.uoutec.community.ediacaran.front.theme.ThemeException;

@Tag(
	name="tabs-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TabsItemTagComponent extends AbstractSimpleTagComponent {
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	private Boolean active;

	private String icon;
	
	protected void beforeBuildComponent(Component component) {
		
    	Object parent = getParentTag();
    	
    	if(parent instanceof TabsTagComponent) {
    		((TabsTagComponent)parent).add(this.getComponent());
    	}
		
	}

	protected void buildComponent(Component component) throws ThemeException, IOException {
		
		Theme theme = component.getTheme();
		String packageName = component.getPackageTheme();
		String template = "/components/content";
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		Writer writter = new PrintWriter(bout);
		theme.buildComponent(template, packageName, getComponent(), new HashMap<String, Object>(), writter);
		
		writter.flush();
		byte[] bytes = bout.toByteArray();
		String content = new String(bytes, 0, bytes.length);
		
		this.setContent(new VarParser(){
			
			public void parse(Writer writter) throws IOException{
				writter.write(content.toCharArray());
			}
			
			public String parse() throws IOException{
				return content;
			}
			
		});
		
		//o componente é construido em TabsTagComponent
	}
	
    public Map<String, Object> getProperties(Set<String> defaultProperties, Set<String> emptyProperties){
    	Map<String,Object> props = super.getProperties(defaultProperties, emptyProperties);
    	props.put("show", Boolean.TRUE.equals(active)?  "show" : "");
    	props.put("selected", active);
    	return props;
    }
    
	public String getTitle() {
		return title;
	}

	@TagAttribute
	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getActive() {
		return active;
	}

	@TagAttribute
	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getIcon() {
		return icon;
	}

	@TagAttribute
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Override
	public String getType() {
		return "tabs-item";
	}
	
}
