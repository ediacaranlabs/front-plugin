package br.com.uoutec.community.ediacaran.front.components;

import java.util.Map;

public class DataResultComponent extends Component {

	public static final String TEMPLATE = "/components/data-result";

	public static final String FORM = DataResultComponent.class.getSimpleName() + ":form";
	
	public String getTemplate() {
		return TEMPLATE;
	}

	protected void afterPrepareVars(Map<String, Object> vars) {
		String from = (String) vars.get("from");
		vars.put("from", getFrom(from));
	}

	/*
	protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
		String from = (String) vars.get("from");
		vars.put("from", getFrom(from));
    }
	*/
	
	protected String getFrom(String from) {
		
		if(from != null) {
			return from;
		}
		
		DataTableComponent dataTable = (DataTableComponent)getProperty(DataTableComponent.DATA_TABLE);

		if(dataTable == null) {
			throw new IllegalStateException("data table not found");
		}
	
		return dataTable.getId();
	}
	
}
