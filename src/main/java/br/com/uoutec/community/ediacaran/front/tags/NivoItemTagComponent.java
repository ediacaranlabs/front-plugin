package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.ThemeException;

@Tag(
	name="nivo-slider-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class NivoItemTagComponent extends AbstractSimpleTagComponent {
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String image;
	
	private String title;
	
	private String link;
	
	protected void beforeBuildComponent(Component component) {
		
    	Object parent = getParentTag();
    	
    	if(parent instanceof NivoTagComponent) {
    		StringWriter stringWriter = new StringWriter();
    		if(this.getJspBody() != null) {
    			try {
    			this.getJspBody().invoke(stringWriter);
    			}
    			catch(Throwable ex) {
    				try(PrintWriter pw = new PrintWriter(stringWriter, true)) {
    					ex.printStackTrace(pw);
    				}
    				catch(Throwable fail) {
    					fail.printStackTrace();
    				}
    				
    			}
    		}
    		((NivoTagComponent)parent).add(this.image, this.title, this.link, stringWriter.toString());
    	}
		
	}

	protected void buildComponent(Component component) throws ThemeException, IOException {
		//o componente é construido em NivoTagComponent
	}
	
    public String getDefaultTemplate() {
    	return null;
    }
    
	public String getLink() {
		return link;
	}

	@TagAttribute(required=true)
	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	@TagAttribute(required=true)
	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	@TagAttribute(required=true)
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String getType() {
		return "nivo-item";
	}
	
}
