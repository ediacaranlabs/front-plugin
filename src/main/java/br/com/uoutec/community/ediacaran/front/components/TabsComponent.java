package br.com.uoutec.community.ediacaran.front.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;

public class TabsComponent 
	extends Component{

	public static final String ITENS_TEMPLATE  = "/components/content";
	
	//private static final String CONTENT_ITEM = "/components/tabs-content-item";
	
	//private static final String HEADER_ITEM = "/components/tabs-header-item";

	public static final String TEMPLATE  = "/components/tabs";
	
	private List<TabsItemComponent> itens;
	
	public TabsComponent() {
		this.itens = new ArrayList<>();
	}
	
	public void addItem(TabsItemComponent value) {
		this.itens.add(value);
	}
	
	public Map<String, Object> prepareVars(Map<String, PropertyParser> propertyParsers, Set<String> defaultProperties,
			Map<String, PropertyParser> attributeParsers, Set<String> emptyAttributes, Set<String> defaultAttributes) {
		
		Map<String, Object> vars = super.prepareVars(propertyParsers, defaultProperties, attributeParsers, emptyAttributes, defaultAttributes);
		
		VarParser header = (w)->{
			for(TabsItemComponent i: itens) {
				StringBuffer sBuf = i.getHeader();
				w.write(sBuf.toString());
			}
		};

		VarParser body = (w)->{
			for(TabsItemComponent i: itens) {
				StringBuffer sBuf = i.getContent();
				w.write(sBuf.toString());
			}
		};
		
		/*
		TemplateListVarsParser header = new TemplateListVarsParser(HEADER_ITEM, getPackageTheme(), getTheme());
		TemplateListVarsParser body = new TemplateListVarsParser(CONTENT_ITEM, getPackageTheme(), getTheme());
		*/
		vars.put("header", header);
		vars.put("body", body);

		/*
		for(TabsItemComponent e: itens) {

			header.createNewItem(e)
			.put("parentID", getId())
			.put("id", e.getId());
			
			body.createNewItem(e)
			.put("parentID", getId())
			.put("id", e.getId());
			
		}
		*/
		
		return vars;
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
}
