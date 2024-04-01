package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class FrontTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/front-script";
	
	/* ------------ Attr ---------------*/
	
	public FrontTagComponent() {
	}
	
    public Map<String, Object> getProperties(Set<String> defaultProperties, Set<String> emptyProperties){
    	Map<String, Object> map = super.getProperties(defaultProperties, emptyProperties);
    	
    	for(Entry<String,Object> e: map.entrySet()) {
    		if(e.getValue() instanceof String) {
    			String value = (String)e.getValue();
    			
				if(value.startsWith("#{") && value.endsWith("}")) {
					value = value.substring(2, value.length() - 1);
					value = "' + " + value + "'";
					e.setValue(value);
				}
    			
    		}
    	}
    	
    	return map;
    }
	
    public String getWrapperTemplate() {
    	return TEMPLATE;
    }
	
}
