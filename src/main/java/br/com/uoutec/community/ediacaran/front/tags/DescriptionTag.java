package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="description", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class DescriptionTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/description";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	private Boolean truncate;
	
	private Integer titleWidth;
	
	private Integer contentWidth;
	
	public DescriptionTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getTitle() {
		return title;
	}

	@TagAttribute
	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getTruncate() {
		return truncate;
	}

	@TagAttribute
	public void setTruncate(Boolean truncate) {
		this.truncate = truncate;
	}

	public Integer getTitleWidth() {
		return titleWidth;
	}

	@TagAttribute
	public void setTitleWidth(Integer titleWidth) {
		this.titleWidth = titleWidth;
	}

	public Integer getContentWidth() {
		return contentWidth;
	}

	@TagAttribute
	public void setContentWidth(Integer contentWidth) {
		this.contentWidth = contentWidth;
	}

}
