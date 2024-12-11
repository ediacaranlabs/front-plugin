package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;

import br.com.uoutec.community.ediacaran.front.theme.ThemeException;

public class TemplateComponent extends Component {

	public static final String TEMPLATE = "/components/template";

	public String getTemplate() {
		return TEMPLATE;
	}

	public void build() throws ThemeException, IOException{
		Object old = setProperty(CodeEscapeWriter.ESCAPE_CONTENT, Boolean.TRUE);
		
		try {

			if(old != null) {
				Writer out = getOut();
				if(!(out instanceof CodeEscapeWriter)) {
					setOut(new CodeEscapeWriter(out));
				}
			}

			super.build();
		}
		finally {
			setProperty(CodeEscapeWriter.ESCAPE_CONTENT, old);	
		}
		
		
	}
	
}
