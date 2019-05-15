package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import br.com.uoutec.community.ediacaran.front.StringPattern;

public class NivoTag extends BodyTagSupport {

	private static final long serialVersionUID = 748182107582888257L;

	private static final StringPattern NIVO_IMAGE_TEMPLATE = new StringPattern(
			"				<img src=\"{image}\" alt=\"{alt}\"\r\n" + 
			"					title=\"#caption-{count}\" />\r\n"
		);
	
	private static final StringPattern NIVO_CAPTION_TEMPLATE = new StringPattern(
			"						<div class=\"nivo-caption\" id=\"caption-{count}\">\r\n" + 
			"							<div>\r\n" + 
			"								<h2>\r\n" + 
			"									<strong>{title}</strong>\r\n" + 
			"								</h2>\r\n" + 
			"								<p>{content}</p>\r\n" + 
			"								<a href=\"{link}\" class=\"btn btn-nivo\">{btn_message}</a>\r\n" + 
			"							</div>\r\n" + 
			"						</div>\r\n"
		);

	private static final StringPattern NIVO_TEMPLATE = new StringPattern(
			"	<!-- Slider -->\r\n" + 
			"	<div>\r\n" + 
			"		<div class=\"nivo-slider\">\r\n" +
			"           {images}\r\n" +
			"		</div>\r\n" + 
			"		<div class=\"container\">\r\n" + 
			"			<div class=\"row\">\r\n" + 
			"				<div class=\"col-12\">\r\n" + 
			"                    {captions}\r\n" +
			"				</div>\r\n" + 
			"			</div>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"	<!-- end slider -->\r\n"
		);
	
	private List<Object[]> itens;

	private String button;
	
	public NivoTag() {
		this.itens = new ArrayList<Object[]>();
	}
	
	public int doAfterBody() {

		try {
			StringBuilder images = new StringBuilder();
			
			int i=0;
			for (Object[] item : itens) {
				images.append(NIVO_IMAGE_TEMPLATE.toString(item[0], "", i++));
			}

			StringBuilder caption = new StringBuilder();
			
			i=0;
			for (Object[] item : itens) {
				caption.append(NIVO_CAPTION_TEMPLATE.toString(i++, item[1], item[3], item[2], button == null? "Read more" : button));
			}

			JspWriter out = bodyContent.getEnclosingWriter();
			out.write(NIVO_TEMPLATE.toString(images,caption));
		} 
		catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return SKIP_BODY;
	}
	
	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public List<Object[]> getItens() {
		return itens;
	}
	
}
