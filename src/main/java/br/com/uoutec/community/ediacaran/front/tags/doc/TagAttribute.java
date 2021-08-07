package br.com.uoutec.community.ediacaran.front.tags.doc;

public @interface TagAttribute {

	String displayName() default "";

	String icon() default "";

	String value() default "";
	
	boolean required() default false;
	
	boolean fragment() default true;
	
}
