package br.com.uoutec.community.ediacaran.front;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.uoutec.application.se.ApplicationBootstrapProvider;
import br.com.uoutec.community.ediacaran.ServerBootstrap;
import br.com.uoutec.community.ediacaran.front.pub.DataUtil;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.plugins.PluginProperties;
import br.com.uoutec.community.ediacaran.plugins.PluginsSuppliers;

public class FrontEdiacaranServlet extends HttpServlet{

	private static final long serialVersionUID = -1401919198240749640L;

	private ServerBootstrap serverBootstrap;
	
	private PluginProperties pluginProperties;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.serverBootstrap = (ServerBootstrap) ApplicationBootstrapProvider.getBootstrap();
		PluginsSuppliers pluginsSuppliers = EntityContextPlugin.getEntity(PluginsSuppliers.class);
		this.pluginProperties = pluginsSuppliers.getSupplier(PluginInstaller.PROVIDER).getProperties(PluginInstaller.PLUGIN);
	}
	
	public void doGet(HttpServletRequest req,
			HttpServletResponse res) throws ServletException,IOException {
	
		String path = req.getRequestURI().substring(req.getContextPath().length());
		
		Map<Object,Object> vars = loadPageData(path);
		String view             = getPageView(vars);
		
		if(view == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		req.setAttribute("vars", vars);
		req.getRequestDispatcher(view).include(req, res);
		res.addHeader("Content-Type", "text/html; charset=utf-8");
	}
	
	private String getPageView(Map<Object,Object> vars) throws ServletException {
		
		try {
			Object type;
			
			if(vars == null || (type = vars.get("template")) == null){
				return null;
			}
			
			String template = pluginProperties.getString("template");
			return "/" + template + "/" + type + ".jsp";
		}
		catch(Throwable e) {
			throw new ServletException(e);
		}
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
		
		InputStreamReader ir = new InputStreamReader(new FileInputStream(dta), "UTF-8");
		//String text = readData(dta);
		return (Map<Object, Object>) DataUtil.decode(ir);
	}
	
}
