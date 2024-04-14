package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;

import br.com.uoutec.community.ediacaran.front.theme.ThemeException;

public class FormComponent extends Component {

	public static final String TEMPLATE = "/components/form";

	public static final String FORM = FormComponent.class.getSimpleName() + ":form";
	
	public String getTemplate() {
		return TEMPLATE;
	}

	public void build() throws ThemeException, IOException{
		
		if(getProperty(FORM) != null) {
			throw new IllegalStateException("nested form not allowed");
		}
		
		try {
			setProperty(FORM, this);
			super.build();
		}
		finally {
			setProperty(FORM, null);	
		}
		
		
	}
	
}
