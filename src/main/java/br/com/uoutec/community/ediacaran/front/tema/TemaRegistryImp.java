package br.com.uoutec.community.ediacaran.front.tema;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.uoutec.community.ediacaran.plugins.PluginData;
import br.com.uoutec.community.ediacaran.plugins.PublicBean;

@Singleton
public class TemaRegistryImp implements TemaRegistry, PublicBean{

	private static final Logger logger = LoggerFactory.getLogger(TemaRegistry.class);
	
	private ConcurrentMap<String, TemaLoader> temas;
	
	private ConcurrentMap<String, Tema> cache;
	
	private PluginData pluginData;
	
	@Inject
	public TemaRegistryImp(PluginData pluginData) {
		this.temas = new ConcurrentHashMap<String, TemaLoader>();
		this.cache = new ConcurrentHashMap<String, Tema>();
		this.pluginData = pluginData;
	}
	
	@Override
	public void registerTema(String name, TemaLoader tema) {
		//TODO: security
		
		if(temas.put(name, tema) != null) {
			if(logger.isWarnEnabled()) {
				logger.warn("tema {} has been added", name);
			}
		}
		else
		if(logger.isTraceEnabled()) {
			logger.trace("language {} added", name);
		}
		
		
	}

	@Override
	public Tema getCurrentTema() {
		return getTema(pluginData.getPropertyValue("template"));
	}
	
	@Override
	public Tema getTema(String name) {
		
		Tema tema = cache.get(name);
		
		if(tema != null) {
			return tema;
		}
		
		TemaLoader loader = temas.get(name);
		
		if(loader == null) {
			throw new TemaException("tema loader not found: " + name);
		}
		
		tema = loader.loadTema();
		Tema cachedTema = cache.putIfAbsent(name, tema);
		return cachedTema != null? cachedTema : tema;
	}

	@Override
	public void unregisterTema(String name) {
		//TODO: security
		
		if(temas.remove(name) != null) {
			if(logger.isWarnEnabled()) {
				logger.warn("tema {} removed", name);
			}
		}
		else
		if(logger.isTraceEnabled()) {
			logger.trace("tema {} not found", name);
		}
		
	}

}
