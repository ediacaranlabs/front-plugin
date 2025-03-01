package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.brandao.brutos.ConfigurableResultAction;
import org.brandao.brutos.RequestProvider;
import org.brandao.brutos.type.Type;
import org.brandao.brutos.type.TypeFactory;
import org.brandao.brutos.web.WebMvcRequest;

import br.com.uoutec.application.security.ContextSystemSecurityCheck;
import br.com.uoutec.community.ediacaran.front.SectionView;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;

@Tag(
	name="sectionView", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.EMPTY
)
public class SectionViewTag extends AbstractSimpleTagComponent {
	
	public static final String TEMPLATE = "/components/content";
	
	private SectionView section;
	
	public SectionViewTag() {
	}

    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	@Override
	public String getType() {
		return "include";
	}

	public VarParser getContent() {
		WebMvcRequest request = (WebMvcRequest)RequestProvider.getRequest();
		ConfigurableResultAction ra =
				(ConfigurableResultAction)ContextSystemSecurityCheck.doPrivileged(section, "getView", request);
		
		if(ra.getContentType() != null) {
			TypeFactory typeFactory = request.getApplicationContext().getTypeManager().getTypeFactory(ra.getContentType());
			Type type = typeFactory.getInstance();
			String content = type.toString(type);
			return new VarParser() {

				@Override
				public void parse(Writer writter) throws IOException {
					writter.write(content);
				}

			};
		}
		else {
			VarParser adviceVarParser = new IncludeVarParser(
					ra.getView(), 
					ra.isResolvedView(), 
					getComponent().getTheme(), 
					getComponent().getPackageTheme(), 
					getComponent().getPageContext()
			);
			
			
			return new VarParser() {

				@Override
				public void parse(Writer writter) throws IOException {
					Map<String,Object> cache = new HashMap<>();
					
					for(Entry<String,Object> e: ra.getVars().entrySet()) {
						cache.put(e.getKey(), request.getAttribute(e.getKey()));
					}
					
					try {
						for(Entry<String,Object> e: ra.getVars().entrySet()) {
							request.setAttribute(e.getKey(), e.getValue());
						}
						
						adviceVarParser.parse(writter);
					}
					finally {
						for(Entry<String,Object> e: cache.entrySet()) {
							request.setAttribute(e.getKey(), e.getValue());
						}
					}
				}

			};
		}
	}

	public SectionView getSection() {
		return section;
	}

	@TagAttribute(required = true)
	public void setSection(SectionView section) {
		this.section = section;
	}
	
}
