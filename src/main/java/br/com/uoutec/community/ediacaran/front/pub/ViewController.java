package br.com.uoutec.community.ediacaran.front.pub;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
		
		File dta = new File(serverBootstrap.getWebapp(), path);
		
		if(!dta.exists() || !dta.canRead()) {
			return result.setResponseStatus(HttpStatus.NOT_FOUND);
		}
		
		String text = readData(dta);
		Map<?,?> data = DataUtil.decode(text);
		
		PluginMetadata pmd = pluginManager.findById(PluginInstaller.PLUGIN);
		PluginPropertyValue ppv = pmd.getValue("template");
		String template = ppv.getValue();
		
		Object type = data.get("template");
		
		result.setView("/plugins/community/ediacaran/front/" + template + "/" + type + ".jsp", true);
		result.add("vars", data);
		return result;
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
