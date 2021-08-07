package br.com.uoutec.community.ediacaran.front.tags.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface TagAttribute {

	String displayName() default "";

	String icon() default "";

	String value() default "";
	
	boolean required() default false;
	
	boolean fragment() default true;
	
}
