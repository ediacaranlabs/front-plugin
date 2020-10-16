package br.com.uoutec.community.ediacaran.front.tags.front;

import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.system.tema.StringPattern;
import br.com.uoutec.community.ediacaran.system.tema.TagTemplate;
import br.com.uoutec.community.ediacaran.system.tema.TemaException;

public abstract class AbstractTagTemplate implements TagTemplate{

	private StringPattern sp;
	
	public AbstractTagTemplate(String template) {
		this.sp = new StringPattern(template);
	}
	
	@Override
	public void applyTagTemplate(Map<String, Object> vars, Writer out) throws TemaException {
		try {
			sp.toWriter(out, vars);
		}
		catch(Throwable e) {
			throw new TemaException(e);
		}
	}

	@Override
	public void applyTagTemplate(Writer out, Object... vars) throws TemaException {
		try {
			sp.toWriter(out, vars);
		}
		catch(Throwable e) {
			throw new TemaException(e);
		}
	}
    
}
