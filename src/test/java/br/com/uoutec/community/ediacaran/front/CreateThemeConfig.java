package br.com.uoutec.community.ediacaran.front;

import java.lang.reflect.Modifier;
import java.util.List;

import br.com.uoutec.application.ClassUtil;
import br.com.uoutec.application.io.Vfs;
import br.com.uoutec.application.scanner.DefaultScanner;
import br.com.uoutec.application.scanner.TypeFilter;
import br.com.uoutec.community.ediacaran.front.theme.TemplateComponent;

public class CreateThemeConfig {

	@SuppressWarnings("unchecked")
	public static void main(String[] a) throws InstantiationException, IllegalAccessException {

		DefaultScanner s = new DefaultScanner();
		s.setBasePackage(new String[] {"br.com.uoutec.community.ediacaran.front.tags.front"});
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
					return TemplateComponent.class.isAssignableFrom(clazz);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
		});
		
		s.scan();
		
		List<Class<?>> clazzList = s.getClassList();
		
		for(Class<?> clazz: clazzList) {
			
			if(Modifier.isAbstract(clazz.getModifiers())) {
				continue;
			}
			
			//System.out.println(clazz.getName());
			TemplateComponent c = (TemplateComponent) clazz.newInstance();
			String tmp = c.getTemplate();
			tmp = tmp .replace("/default_template/front", "");
			tmp = tmp .replace(".tmp", "");
			
			System.out.println("default/front" + tmp + "=" + clazz.getName());
		}
	}
}
