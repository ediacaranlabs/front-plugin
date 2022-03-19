package br.com.uoutec.community.ediacaran.front.components;

public interface ComponentData {

	String getExtAttrs();
	
	String getTemplate();

	String getWrapperTemplate();
	
	boolean isWrapper();
	
    String getDefaultTemplate();
	
    String getType();
    
}
