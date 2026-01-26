package br.com.uoutec.community.ediacaran.front.components;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class TabsItemComponent 
	extends Component{

	private static final String CONTENT_ITEM = "/components/tabs-content-item";
	
	private static final String HEADER_ITEM = "/components/tabs-header-item";
	
	private StringBuffer header;

	private StringBuffer content;
	
    protected void applyTemplate(String template, Map<String, Object> vars, Writer out){
    	this.header = createContent(HEADER_ITEM, vars, out);
    	this.content = createContent(CONTENT_ITEM, vars, out);
    }

    private StringBuffer createContent(String template, Map<String, Object> vars, Writer out){
    	
    	StringWriter sOut = new StringWriter();
    	
    	try{
	    	getTheme().buildComponent(template, getPackageTheme(), this, vars, sOut);
    	}
    	finally {
    		try {
    			sOut.close();
    		}
    		catch(Throwable ex) {
    		}
    	}
    	
    	return sOut.getBuffer(); 
    }

	public StringBuffer getHeader() {
		return header;
	}

	public StringBuffer getContent() {
		return content;
	}
    
	
}
