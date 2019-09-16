package br.com.uoutec.community.ediacaran.front.tags;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.PluginInstaller;
import br.com.uoutec.community.ediacaran.front.StringPattern;
import br.com.uoutec.community.ediacaran.plugins.PluginException;
import br.com.uoutec.community.ediacaran.plugins.PluginMetadata;
import br.com.uoutec.community.ediacaran.plugins.PluginPropertyValue;

public class HashMapTemplateCache implements TemplateCache{

	private Map<String, StringPattern> map;
	
	@Override
	public void configure(PluginMetadata config) throws PluginException {
		PluginPropertyValue cacheSize = config.getValue(PluginInstaller.CACHE_SIZE);
		int cacheSizeValue            = Integer.parseInt(cacheSize.getValue());
		this.map                      = new MaxSizeHashMap<String, StringPattern>(cacheSizeValue);
	}

	@Override
	public void register(String name, StringPattern template) {
		map.put(name, template);
	}

	@Override
	public void remove(String name) {
		map.remove(name);
	}

	public boolean contains(String name) {
		return map.containsKey(name);
	}
	
	@Override
	public StringPattern get(String name) {
		return map.get(name);
	}

	@Override
	public void clear() {
		map.clear();
	}

	private class MaxSizeHashMap<K, V> extends LinkedHashMap<K, V> {
		
		private static final long serialVersionUID = -5176009484990939592L;
		
		private final int maxSize;

	    public MaxSizeHashMap(int maxSize) {
	        this.maxSize = maxSize;
	    }

	    @Override
	    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
	        return size() > maxSize;
	    }
	}	
}
