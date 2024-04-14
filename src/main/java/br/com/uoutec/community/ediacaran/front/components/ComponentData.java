package br.com.uoutec.community.ediacaran.front.components;

import java.util.Map;
import java.util.Set;

public interface ComponentData {

	String getExtAttrs();
	
	String getTemplate();

	String getWrapperTemplate();
	
	boolean isWrapper();
	
    String getDefaultTemplate();
	
    String getType();
    
    String getId();
    
    Map<String, Object> getProperties(Set<String> defaultProperties, Set<String> emptyProperties);
    
}
