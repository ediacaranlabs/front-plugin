package br.com.uoutec.community.ediacaran.front.tags.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Tag {

	String uri();
	
	String name();
	
	BodyTypes bodycontent() default BodyTypes.EMPTY;
	
}
