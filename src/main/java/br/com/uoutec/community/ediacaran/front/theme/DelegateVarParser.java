package br.com.uoutec.community.ediacaran.front.theme;

import java.io.IOException;
import java.io.Writer;

import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;

public class DelegateVarParser implements VarParser{

	private VarParser varParser;
	
	public VarParser getVarParser() {
		return varParser;
	}

	public void setVarParser(VarParser varParser) {
		this.varParser = varParser;
	}

	@Override
	public void parse(Writer writter) throws IOException {
		if(varParser != null) {
			varParser.parse(writter);
		}
	}

}
