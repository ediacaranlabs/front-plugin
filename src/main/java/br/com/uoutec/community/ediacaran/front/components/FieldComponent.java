package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;

import br.com.uoutec.community.ediacaran.front.theme.ThemeException;

public class FieldComponent extends Component {

	public static final String FORM = FieldComponent.class.getSimpleName() + ":form";
	
	public void build() throws ThemeException, IOException{
		
		if(getProperty(FORM) != null) {
			throw new IllegalStateException("nested field not allowed");
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
