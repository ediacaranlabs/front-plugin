package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;

import br.com.uoutec.community.ediacaran.front.theme.ThemeException;

public class ResponseComponent extends Component {

	public static final String TEMPLATE = "/components/response";

	public static final String ESCAPE_CONTENT = ResponseComponent.class.getName() + ":escape";
	
	public String getTemplate() {
		return TEMPLATE;
	}

	public void build() throws ThemeException, IOException{
		Object old = setProperty(ESCAPE_CONTENT, Boolean.TRUE);
		
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
			setProperty(ESCAPE_CONTENT, old);	
		}
		
		
	}
	
}
