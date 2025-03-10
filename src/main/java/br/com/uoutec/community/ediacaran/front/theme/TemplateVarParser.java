package br.com.uoutec.community.ediacaran.front.theme;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TemplateVarParser  extends AbstractVarParser{

	private String template;
	
	private String packageName;
	
	private Theme theme;
	
	private Map<String, Object> vars;
	
	private ComponentVars componentVars;

	public TemplateVarParser(String template, Theme theme) {
		this(template, null, new ComponentVarsBuilder(), theme);
	}
	
	public TemplateVarParser(String template, String packageName, Theme theme) {
		this(template, packageName, new ComponentVarsBuilder(), theme);
	}
	
	public TemplateVarParser(String template, String packageName, ComponentVars componentVars, Theme theme) {
		this.template = template;
		this.vars = new HashMap<String, Object>();
		this.packageName = packageName;
		this.theme = theme;
		this.componentVars = componentVars;
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
	public void parse(Writer writter) throws ThemeException {
		theme.buildComponent(template, packageName, componentVars, vars, writter);
	}

}
