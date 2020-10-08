package br.com.uoutec.community.ediacaran.front.tema;

import java.io.Writer;
import java.util.Map;

public interface Tema {
	
	void applyTagTemplate(String template, Map<String,Object> vars, Writer out) throws TemaException;
	
	void applyTagTemplate(String template, Writer out, Object ... vars) throws TemaException;
	
	String getContext();
	
	String getBase();
	
}
