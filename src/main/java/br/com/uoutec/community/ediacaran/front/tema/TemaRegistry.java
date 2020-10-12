package br.com.uoutec.community.ediacaran.front.tema;

public interface TemaRegistry {

	void registerTemplate(String name, String context, String packageName);
	
	void registerTemplate(String name, String packageName, TagTemplate tagTemplate);
	
	Tema getCurrentTema();
	
	Tema getTema(String name);
	
	void unregisterTema(String name);
	
}
