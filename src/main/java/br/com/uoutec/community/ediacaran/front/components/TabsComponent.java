package br.com.uoutec.community.ediacaran.front.components;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;
import br.com.uoutec.community.ediacaran.front.theme.TemplateListVarsParser;

public class TabsComponent 
	extends Component{

	private static final String CONTENT_ITEM = "/components/tabs-content-item";
	
	private static final String HEADER_ITEM = "/components/tabs-header-item";

	public static final String TEMPLATE  = "/components/tabs";
	
	private List<TabsItemComponent> itens;
	
	public TabsComponent() {
		this.itens = new ArrayList<>();
	}
	
	public void addItem(TabsItemComponent value) {
		this.itens.add(value);
	}
	
    protected void applyTemplate(String template, Map<String, Object> vars, Writer out){
    	
		TemplateListVarsParser header = new TemplateListVarsParser(HEADER_ITEM, getPackageTheme(), getTheme());
		TemplateListVarsParser body = new TemplateListVarsParser(CONTENT_ITEM, getPackageTheme(), getTheme());

		VarParser newHeader = (writter)->{
    		
    		for(TabsItemComponent e: itens) {
    			
    			header.createNewItem(e)
    			.put("parentID", getId())
    			.put("id", e.getId());
    			
    			body.createNewItem(e)
    			.put("parentID", getId())
    			.put("id", e.getId());

    		}
    		
    		header.parse(writter);
    	};
		
		vars.put("header", newHeader);
		vars.put("body", body);
		
		super.applyTemplate(template, vars, out);
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
}
