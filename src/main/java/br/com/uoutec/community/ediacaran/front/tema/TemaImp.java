package br.com.uoutec.community.ediacaran.front.tema;

import java.io.Writer;
import java.util.Map;

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
	public String base() {
		return base;
	}

}
