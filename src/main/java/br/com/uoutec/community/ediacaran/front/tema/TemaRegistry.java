package br.com.uoutec.community.ediacaran.front.tema;

public interface TemaRegistry {

	void registerTema(String name, TemaLoader tema);
	
	Tema getCurrentTema();
	
	Tema getTema(String name);
	
	void unregisterTema(String name);
	
}
