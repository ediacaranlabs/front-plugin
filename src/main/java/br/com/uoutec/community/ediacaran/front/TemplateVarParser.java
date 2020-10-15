package br.com.uoutec.community.ediacaran.front;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import br.com.uoutec.community.ediacaran.system.tema.Tema;
import br.com.uoutec.community.ediacaran.system.tema.TemaException;

public class TemplateVarParser  extends AbstractVarParser{

	private String template;
	
	private String packageName;
	
	private Tema tema;
	
	private Map<String, Object> vars;
	
	public TemplateVarParser(String template, String packageName, Tema tema) {
		this(template, packageName, tema, new HashMap<String, Object>());
	}

	public TemplateVarParser(String template, String packageName, Tema tema, Map<String, Object> vars) {
		this.template = template;
		this.vars = vars;
		this.packageName = packageName;
		this.tema = tema;
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
	public void parse(Writer writter) throws TemaException {
		tema.applyTagTemplate(template, packageName, vars, writter);
	}

	@Override
	public String parse() throws IOException {
		throw new UnsupportedOperationException();
	}
}
