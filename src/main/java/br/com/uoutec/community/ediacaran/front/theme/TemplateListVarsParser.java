package br.com.uoutec.community.ediacaran.front.theme;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateListVarsParser  extends AbstractVarParser{

	private String template;
	
	private List<Map<String, Object>> vars;
	
	private String packageName;
	
	private Theme theme;
	
	private Map<String, Object> current;
	
	public TemplateListVarsParser(String template, String packageName, Theme theme) {
		this(template, packageName, theme, new ArrayList<Map<String, Object>>());
	}

	public TemplateListVarsParser(String template, String packageName, Theme theme, List<Map<String, Object>> vars) {
		this.template = template;
		this.vars = vars;
		this.packageName = packageName;
		this.theme = theme;
	}
	
	public TemplateListVarsParser clear() {
		vars.clear();
		return this;
	}

	public TemplateListVarsParser createNewItem() {
		current = new HashMap<String, Object>();
		vars.add(current);
		return this;
	}

	public TemplateListVarsParser createNewItem(ComponentVars value) {
		current = new HashMap<String, Object>();
		vars.add(current);
		current.put(null, value);
		return this;
	}
	
	public TemplateListVarsParser put(String key, Object value) {
		current.put(key, value);
		return this;
	}

	public TemplateListVarsParser remove(Object ... value) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void parse(Writer writter) throws ThemeException {
		for(Map<String, Object> o: vars) {
			ComponentVars cv = (ComponentVars) o.get(null);
			theme.buildComponent(template, packageName, cv == null? new EmptyVarsBuilder() : cv, o, writter);
		}
	}

}
