package br.com.uoutec.community.ediacaran.front.tags;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.application.se.ApplicationBootstrapProvider;
import br.com.uoutec.community.ediacaran.ServerBootstrap;

public class LoadDataTag  extends AbstractSimpleComponent {
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String file;
	
	private String var;
	
	public LoadDataTag() {
	}
	
    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	
    	ServerBootstrap sb     = (ServerBootstrap) ApplicationBootstrapProvider.getBootstrap();
    	Map<Object,Object> dta = ReadData.loadData(
    								file, 
									sb.getWebapp()/*.getCanonicalFile()*/, 
									file.startsWith("/")? 
										sb.getWebapp() : 
										new File(sb.getWebapp(), super.getRequestPath())
								);
    	
    	super.getJspContext().setAttribute(var == null? "vars" : var, dta);
	}
	
    protected void applyTemplate(String template, 
    		Map<String,Object> vars, Writer out){
    }

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	protected String getDefaultTemplate() {
		return null;
	}

}
