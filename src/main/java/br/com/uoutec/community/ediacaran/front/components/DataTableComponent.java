package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;

import br.com.uoutec.community.ediacaran.front.theme.ThemeException;

public class DataTableComponent extends FormComponent {

	public static final String TEMPLATE = "/components/data-table";

	public static final String DATA_TABLE = DataTableComponent.class.getSimpleName() + ":form";
	
	public String getTemplate() {
		return TEMPLATE;
	}
	
	public void build() throws ThemeException, IOException{
		
		if(getProperty(DATA_TABLE) != null) {
			throw new IllegalStateException("nested data table not allowed");
		}
		
		try {
			setProperty(DATA_TABLE, this);
			super.build();
		}
		finally {
			setProperty(DATA_TABLE, null);	
		}
		
		
	}
	
}
