package br.com.uoutec.community.ediacaran.front.tema;

import java.io.Writer;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.front.tags.AttributeParser;

public class TemaImp implements Tema{

	private Map<String,StringPattern> tags;
	
	private String context;
	
	private String base;
	
	public TemaImp(Map<String, StringPattern> tags, String context, String base) {
		this.tags = tags;
		this.context = context;
		this.base = base;
	}

	@Override
	public void applyTagTemplate(String template, Map<String, Object> vars, Writer out) throws TemaException {
		
		StringPattern p = tags.get(template);
		
		if(p == null) {
			throw new TemaException("template not found: " + template);
		}
		
		try {
			p.toWriter(out, vars);
		}
		catch(Throwable e) {
			throw new TemaException(e);
		}
	}

	@Override
	public void applyTagTemplate(String template, Writer out, Object... vars) throws TemaException {
		
		StringPattern p = tags.get(template);
		
		if(p == null) {
			throw new TemaException("template not found: " + template);
		}

		try {
			p.toWriter(out, vars);
		}
		catch(Throwable e) {
			throw new TemaException(e);
		}
	}

	@Override
	public String getContext() {
		return context;
	}

	@Override
	public String getBase() {
		return base;
	}

	@Override
	public Set<String> getAttributes(Object tag) {
		return null;
	}

	@Override
	public Set<String> getEmptyAttributes(Object tag) {
		return null;
	}

	@Override
	public Map<String, AttributeParser> getAttributesParser(Object tag) {
		return null;
	}

	@Override
	public Set<String> getProperties(Object tag) {
		return null;
	}

	@Override
	public Map<String, AttributeParser> getPropertiesParse(Object tag) {
		return null;
	}

}
