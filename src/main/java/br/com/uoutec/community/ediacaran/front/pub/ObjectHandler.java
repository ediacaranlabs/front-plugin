package br.com.uoutec.community.ediacaran.front.pub;

public interface ObjectHandler {

	String getType();
	
	ObjectReader getReader();
	
	ObjectWriter getWriter();
		
	boolean accept(Object o);
	
	boolean accept(String type);
	
}
