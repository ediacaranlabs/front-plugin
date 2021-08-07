package br.com.uoutec.community.ediacaran.front.tags.doc;

public @interface Tag {

	String uri();
	
	String name();
	
	BodyTypes bodycontent() default BodyTypes.EMPTY;
	
}
