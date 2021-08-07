package br.com.uoutec.community.ediacaran.front;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder.In;

import br.com.uoutec.application.ClassUtil;
import br.com.uoutec.application.scanner.DefaultScanner;
import br.com.uoutec.application.scanner.TypeFilter;
import br.com.uoutec.application.scanner.vfs.Vfs;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagDoc;

public class CreateTagsTLD {

	private static final String TAB = "     ";
	
	@SuppressWarnings("unchecked")
	public static void main(String[] a) throws InstantiationException, IllegalAccessException, FileNotFoundException, IOException {

		DefaultScanner s = new DefaultScanner();
		s.setBasePackage(new String[] {"br.com.uoutec.community.ediacaran.front"});
		s.addIncludeFilter(new TypeFilter() {
			
			@Override
			public void setExpression(List<String> value) {
			}
			
			@SuppressWarnings("rawtypes")
			@Override
			public boolean accepts(String resource) {
				try {
					resource = Vfs.toClass(resource);
					Class clazz = ClassUtil.get(resource);
					return clazz.isAnnotationPresent(Tag.class);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
		});
		
		s.scan();
		
		List<Class<?>> clazzList = s.getClassList();
		
		Map<String, List<Class<?>>> tagsGroup = new HashMap<String, List<Class<?>>>();

		for(Class<?> clazz: clazzList) {
			
			Tag tag = clazz.getDeclaredAnnotation(Tag.class);
			
			if(tag == null) {
				continue;
			}
			
			List<Class<?>> list = tagsGroup.get(tag.uri());

			if(list == null) {
				list = new ArrayList<Class<?>>();
				tagsGroup.put(tag.uri(), list);
			}
			
			list.add(clazz);
			
		}
		
		
		for(Entry<String, List<Class<?>>> e: tagsGroup.entrySet()) {
			
			StringBuilder b = new StringBuilder();
			
			b.append("<taglib").append("\n");
			b.append(TAB).append("xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee web-jsptaglibrary_2_1.xsd\"").append("\n");
			b.append(TAB).append("xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"").append("\n");
			b.append(TAB).append("version=\"2.1\">").append("\n");
			b.append("\n");
			b.append(TAB).append("<tlib-version>1.0</tlib-version>").append("\n");
			b.append(TAB).append("<jsp-version>2.0</jsp-version>").append("\n");
			//b.append("<short-name>det</short-name>");
			b.append(TAB).append("<uri>").append(e.getKey()).append("</uri>").append("\n");
			
			for(Class<?> c: e.getValue()) {
				
				if(Modifier.isAbstract(c.getModifiers())) {
					continue;
				}
				
				Tag tag = c.getDeclaredAnnotation(Tag.class);
				TagDoc tagDoc = c.getDeclaredAnnotation(TagDoc.class);
				
				if(tag != null) {
					b.append(toXML(tag, tagDoc, c));

				}
				
			}
			
			b.append("</taglib>");
			
			String[] fileNameParts = e.getKey().split("\\/");
			String fileName = fileNameParts[fileNameParts.length-1] + ".tld";
			fileName = System.getProperty("user.dir") + "/src/main/resources/META-INF/" + fileName;
			
			try(FileOutputStream out = new FileOutputStream(fileName)){
				out.write(b.toString().getBytes("UTF-8"));
				out.flush();
			}
			
			//System.out.println(b.toString());
			
		}
		
	}
	
	private static StringBuilder toXML(Tag tag, TagDoc doc, Class<?> type) {
		StringBuilder b = new StringBuilder();
		
		String name = tag.name();
		BodyTypes body = tag.bodycontent();
		String[] info = doc == null? null : doc.value(); 
		
		b.append(TAB).append("").append("\n");
		b.append(TAB).append("<!-- ").append(name).append(" -->").append("\n");
		b.append(TAB).append("").append("\n");
		b.append(TAB).append("<tag>").append("\n");
		b.append(TAB).append(TAB).append("<name>").append(name).append("</name>").append("\n");
		b.append(TAB).append(TAB).append("<tag-class>").append(type.getName()).append("</tag-class>").append("\n");
		b.append(TAB).append(TAB).append("<body-content>").append(body.name().toLowerCase()).append("</body-content>").append("\n");
		b.append(TAB).append(TAB).append("<info><![CDATA[").append("\n");
		
		if(info != null) {
			for(String i: info) {
				b.append(i).append("\n");
			}
		}
		
		b.append(TAB).append(TAB).append("]]></info>").append("\n");
		
		Class<?> currentType = type;
		
		while(currentType != null) {
			
			Method[] methods = currentType.getDeclaredMethods();
			
			for(Method m: methods) {
				
				TagAttribute tagAttr = m.getDeclaredAnnotation(TagAttribute.class);
				TagDoc attrTagDoc = m.getDeclaredAnnotation(TagDoc.class);
				
				if(tagAttr != null && m.getName().startsWith("set")) {
					b.append(toXML(TAB+TAB, tagAttr, attrTagDoc, m));
				}
				
			}
			
			currentType = (Class<?>)currentType.getSuperclass();
		}
		
		b.append(TAB).append("</tag>").append("\n");
		b.append(TAB).append("\n");
		b.append(TAB).append("<!-- /").append(name).append(" -->").append("\n");
		
		return b;
	}

	private static StringBuilder toXML(String prefix, TagAttribute tag, TagDoc doc, Method m) {
		StringBuilder b = new StringBuilder();
		
		String name = Character.toLowerCase(m.getName().charAt(3)) + m.getName().substring(4);
		Class<?> type = m.getParameterTypes()[0];
		String displayName = tag.displayName();
		String icon = tag.icon();
		String value = tag.value();
		boolean required = tag.required();
		boolean fragment = tag.fragment();
		String[] info = doc == null? null : doc.value(); 
		
		b.append(prefix).append(TAB).append("\n");
		b.append(prefix).append(TAB).append("<attribute>").append("\n");
		b.append(prefix).append(TAB).append(TAB).append("<description><![CDATA[").append("\n");
		
		if(info != null) {
			for(String i: info) {
				b.append(i).append("\n");
			}
		}
		
		b.append(prefix).append(TAB).append(TAB).append("]]></description>").append("\n");

		if(!"".equals(displayName)) {
			b.append(prefix).append(TAB).append(TAB).append("<display-name>").append(displayName).append("</display-name>").append("\n");
		}
		
		if(!"".equals(icon)) {
			b.append(prefix).append(TAB).append(TAB).append("<icon>").append(icon).append("</icon>").append("\n");
		}
		
		b.append(prefix).append(TAB).append(TAB).append("<name>").append(!"".equals(value)? value : name).append("</name>").append("\n");
		b.append(prefix).append(TAB).append(TAB).append("<required>").append(required).append("</required>").append("\n");
		b.append(prefix).append(TAB).append(TAB).append("<type>").append(type.getName()).append("</type>").append("\n");
		b.append(prefix).append(TAB).append(TAB).append("<fragment>").append(fragment).append("</fragment>").append("\n");
		
		
		if(!"".equals(value)) {
			b.append(prefix).append(TAB).append(TAB).append("<deferred-method>").append("\n");
			b.append(prefix).append(TAB).append(TAB).append(TAB).append("<method-signature>").append("\n");
			b.append(prefix).append(TAB).append(TAB).append(TAB).append(TAB)
				.append(m.getReturnType().getName()).append(" ").append(m.getName()).append(" ").append("(").append(m.getParameterTypes()[0]).append(")")
				.append("\n");
			b.append(prefix).append(TAB).append(TAB).append(TAB).append("</method-signature>").append("\n");
			b.append(prefix).append(TAB).append(TAB).append("</deferred-method>").append("\n").append("\n");
		}
		
		b.append(prefix).append(TAB).append("</attribute>").append("\n");
		
		return b;
	}
	
}
