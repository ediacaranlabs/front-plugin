package br.com.uoutec.community.ediacaran.front;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.uoutec.community.ediacaran.front.theme.PluginThemesManager;
import br.com.uoutec.ediacaran.core.EdiacaranEventListener;
import br.com.uoutec.ediacaran.core.EdiacaranEventObject;
import br.com.uoutec.ediacaran.core.plugins.PluginInitializer;
import br.com.uoutec.ediacaran.core.plugins.PluginNode;

@Singleton
public class ThemeEdiacaranListener implements EdiacaranEventListener{

	private static final Logger logger = LoggerFactory.getLogger(ThemeEdiacaranListener.class);
	
	@Override
	public void onEvent(EdiacaranEventObject event) {

		if(event.getSource() instanceof PluginInitializer) {
			
			if("installed".equals(event.getType())){
				startContext((PluginNode)event.getData());
			}
			else
			if("uninstalled".equals(event.getType())){
				stopContext((PluginNode)event.getData());
			}
			
		}
		
	}

	private void startContext(PluginNode node) {
		try {
			loadThemes();
		}
		catch(SecurityException ex) {
			logger.warn("don't have permission to load theme", ex);
		}
		catch(Throwable ex) {
			throw new RuntimeException(ex);
		}
	}
	
	private void stopContext(PluginNode node) {
		try {
			unloadThemes();
		}
		catch(SecurityException ex) {
			logger.warn("don't have permission to load theme", ex);
		}
		catch(Throwable ex) {
			throw new RuntimeException(ex);
		}
	}
	
	protected void loadThemes() throws Throwable {
		PluginThemesManager ptm = new PluginThemesManager();
		ptm.registerThemes();
	}

	protected void unloadThemes() throws Throwable {
		PluginThemesManager ptm = new PluginThemesManager();
		ptm.unregisterThemes();
	}
	
}
