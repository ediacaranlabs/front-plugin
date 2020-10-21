package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.TemplateListVarParser;
import br.com.uoutec.community.ediacaran.system.tema.TemplateVarParser;

public class NivoComponent extends AbstractPanelComponent {

	private static final long serialVersionUID = 748182107582888257L;

	private static final String EMPTY_IMG_ALT = "";
	
	private static final String NIVO_IMAGE = "/bootstrap4/components/nivo-image";
	
	private static final String NIVO_CAPTION = "/bootstrap4/components/nivo-caption";

	
	protected void loadConfiguration() {

		TEMPLATE  = "/bootstrap4/components/nivo";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
		}});
	
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("button");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
			}});

	}
}
