package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.StringPattern.AbstractVarParser;

public class TemplateVarParser  extends AbstractVarParser{

	private String template;
	
	private Map<String, Object> vars;
	
	public TemplateVarParser(String template) {
		this(template, new HashMap<String, Object>());
	}

	public TemplateVarParser(String template, Map<String, Object> vars) {
		this.template = template;
		this.vars = vars;
	}
	
	public TemplateVarParser clear() {
		vars.clear();
		return this;
	}
	
	public TemplateVarParser put(String key, Object value) {
		vars.put(key, value);
		return this;
	}

	public TemplateVarParser remove(String key) {
		vars.remove(key);
		return this;
	}
	
	@Override
	public void parse(Writer writter) throws IOException {
		try {
			TemplatesManager.getTemplatesManager()
			.apply(
				template, 
				vars, 
				writter
			);			
		}
		catch(Throwable e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public String parse() throws IOException {
		throw new UnsupportedOperationException();
	}
}
