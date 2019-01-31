package br.com.uoutec.community.ediacaran.front.pub;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.ActionStrategy;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.web.WebActionStrategyType;
import org.brandao.brutos.web.HttpStatus;
import org.brandao.brutos.web.WebResultAction;

import br.com.uoutec.application.se.ApplicationBootstrapProvider;
import br.com.uoutec.community.ediacaran.PluginManager;
import br.com.uoutec.community.ediacaran.ServerBootstrap;
import br.com.uoutec.community.ediacaran.front.PluginInstaller;
import br.com.uoutec.community.ediacaran.plugins.PluginException;
import br.com.uoutec.community.ediacaran.plugins.PluginMetadata;
import br.com.uoutec.community.ediacaran.plugins.PluginPropertyValue;

@Controller
@Singleton
@ActionStrategy(WebActionStrategyType.DETACHED)
public class ViewController {

	@Transient
	@Inject
	private PluginManager pluginManager;
	
	private ServerBootstrap serverBootstrap;
	
	public ViewController() {
		this.serverBootstrap = (ServerBootstrap) ApplicationBootstrapProvider.getBootstrap();
	}
	
	@Action("/{path:[^\\s\\.]{1,256}\\.edc$}")
	public WebResultAction show(@Basic(bean="path")String path, WebResultAction result
			) throws IOException, PluginException {
		
		Map<Object,Object> vars = loadPageData(path);
		String view             = getPageView(vars);
		
		if(view == null) {
			return result.setResponseStatus(HttpStatus.NOT_FOUND);
		}
		
		result.setView(view, true);
		result.add("vars", vars);
		
		return result;
	}
	
	private String getPageView(Map<Object,Object> vars) throws PluginException {
		
		Object type;
		
		if(vars == null || (type = vars.get("template")) == null){
			return null;
		}
		
		PluginMetadata pmd = pluginManager.findById(PluginInstaller.PLUGIN);
		PluginPropertyValue ppv = pmd.getValue("template");
		String template = ppv.getValue();
		
		return "/plugins/community/ediacaran/front/" + template + "/" + type + ".jsp";
	}
	
	private Map<Object,Object> loadPageData(String path) throws IOException{
		
		File fData              = new File(serverBootstrap.getWebapp(), path);
		Map<Object,Object> data = getData(fData);
		
		if(data != null) {
			
			List<Map<Object,Object>> incData = loadIncludes(data);
			
			Map<Object,Object> vars = new HashMap<Object,Object>();
			
			for(Map<Object,Object> i: incData) {
				vars.putAll(i);
			}
			
			vars.putAll(data);
			return vars;
		}
		
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	private List<Map<Object,Object>> loadIncludes(Map<Object,Object> data) throws IOException{
		
		List<Object> inc = (List<Object>)data.get("includes");
		
		List<Map<Object,Object>> incData = new ArrayList<Map<Object,Object>>();
		
		if(inc != null) {
			
			for(Object i: inc) {
				
				File fInc = new File(serverBootstrap.getWebapp(), String.valueOf(i));
				Map<Object,Object> dataInc = getData(fInc);
				
				if(dataInc != null) {
					incData.add(dataInc);
				}
				
			}
			
		}
		
		return incData;
	}
	
	@SuppressWarnings("unchecked")
	private Map<Object,Object> getData(File dta) throws IOException{
		
		dta = dta.getCanonicalFile();
		
		if(!dta.getAbsolutePath().startsWith(serverBootstrap.getWebapp().getAbsolutePath())) {
			return null;
		}
		
		if(!dta.exists() || !dta.canRead()) {
			return null;
		}
		
		String text = readData(dta);
		return (Map<Object, Object>) DataUtil.decode(text);
	}
	
	private String readData(File f) throws IOException {
		byte[] b = new byte[2048];
		int l;
		
		StringBuilder builder = new StringBuilder();
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(f);
			
			while((l = fin.read(b)) != -1) {
				builder.append(new String(b, 0, l));
			}
			return builder.toString();
		}
		finally {
			if(fin != null) {
				fin.close();
			}
		}
	}
	
}
