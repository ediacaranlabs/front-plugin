package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.TemplateListVarsParser;

@Tag(
	name="nivo-slider", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
	)
public class NivoTagComponent extends AbstractBodyTagComponent {

	private static final long serialVersionUID = 748182107582888257L;

	private static final String EMPTY_IMG_ALT = "";
	
	private static final String NIVO_IMAGE = "/components/nivo-image";
	
	private static final String NIVO_CAPTION = "/components/nivo-caption";

	public static final String TEMPLATE  = "/components/nivo";
	
	private TemplateListVarsParser images;
	
	private TemplateListVarsParser captions;
	
	private String button;
	
	private int index;
	
	public NivoTagComponent() {
	}
	
	public void add(String img, String title, String link, String content) {
		index++;
		
		images.createNewItem()
			.put("image", img)
			.put("alt", EMPTY_IMG_ALT)
			.put("count", index);
		

		captions.createNewItem()
			.put("count", index)
			.put("title", title)
			.put("content", content)
			.put("link", link)
			.put("btn_message", button == null? "Read more" : button);
		
	}
	
	protected void beforeBuildComponent(Component component) {
		this.index    = 1;
		this.images   = new TemplateListVarsParser(NIVO_IMAGE, component.getPackageTheme(), component.getTheme());
		this.captions = new TemplateListVarsParser(NIVO_CAPTION, component.getPackageTheme(), component.getTheme());
	}

	protected void afterBuildComponent(Component component) {
		this.index    = -1;
		this.images   = null;
		this.captions = null;
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public TemplateListVarsParser getImages() {
		return images;
	}

	public void setImages(TemplateListVarsParser images) {
		this.images = images;
	}

	public TemplateListVarsParser getCaptions() {
		return captions;
	}

	public void setCaptions(TemplateListVarsParser captions) {
		this.captions = captions;
	}

	public String getButton() {
		return button;
	}

	@TagAttribute
	public void setButton(String button) {
		this.button = button;
	}

	@Override
	public String getType() {
		return "nivo";
	}
	
}
