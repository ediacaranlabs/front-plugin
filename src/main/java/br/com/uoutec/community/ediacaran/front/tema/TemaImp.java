package br.com.uoutec.community.ediacaran.front.tema;

import java.io.Writer;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import br.com.uoutec.community.ediacaran.front.tags.AttributeParser;

public class TemaImp implements Tema{

	public String name;
	
	public String context;
	
	public String path;
	
	public ConcurrentMap<String, TemaPackage> packages;
	

	public TemaImp(String name, String context, String path, ConcurrentMap<String, TemaPackage> packages) {
		this.name = name;
		this.packages = packages;
		this.path = path;
	}

	@Override
	public void applyTagTemplate(String template, String packageName, Map<String, Object> vars, Writer out) throws TemaException {
		
		TemaPackage temaPackage = getPackage(packageName);
		
		ConcurrentMap<String, TagTemplate> tagTemplates = temaPackage.getTagTemplates();
		
		TagTemplate p = tagTemplates.get(template);
		
		if(p == null) {
			throw new TemaException("template not found: " + template);
		}
		
		p.applyTagTemplate(template, vars, out);
	}

	@Override
	public void applyTagTemplate(String template, String packageName, Writer out, Object... vars) throws TemaException {
		
		TemaPackage temaPackage = getPackage(packageName);
		
		ConcurrentMap<String, TagTemplate> tagTemplates = temaPackage.getTagTemplates();
		
		TagTemplate p = tagTemplates.get(template);
		
		if(p == null) {
			throw new TemaException("template not found: " + template);
		}
		
		p.applyTagTemplate(template, out, vars);
	}

	@Override
	public String getContext() {
		return context;
	}

	@Override
	public String getTemplate(String name) {
		TemaPackage temaPackage = getPackage(name);
		return path + temaPackage.getPath();
	}

	@Override
	public Set<String> getAttributes(Object tag, String packageName) {
		return null;
	}

	@Override
	public Set<String> getEmptyAttributes(Object tag, String packageName) {
		return null;
	}

	@Override
	public Map<String, AttributeParser> getAttributesParser(Object tag, String packageName) {
		return null;
	}

	@Override
	public Set<String> getProperties(Object tag, String packageName) {
		return null;
	}

	@Override
	public Map<String, AttributeParser> getPropertiesParse(Object tag, String packageName) {
		return null;
	}

	private TemaPackage getPackage(String name) throws TemaException {
		
		if(name == null) {
			name = "front";
		}
		
		TemaPackage temaPackage = packages.get(name);
		
		if(temaPackage == null) {
			
			temaPackage = packages.get("front");
			
			if(temaPackage == null) {
				throw new TemaException("tema package not found: " + this.name + "/" + name);
			}
		}
		
		return temaPackage;
	}
	
}
