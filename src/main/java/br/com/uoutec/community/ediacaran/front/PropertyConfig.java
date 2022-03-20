package br.com.uoutec.community.ediacaran.front;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("serial")
public class PropertyConfig extends HashMap<Object,Object>{
	
	private Object parentName;
	
	private PropertyConfig parent;
	
	public PropertyConfig() {
		this(null, null);
	}
	
	public PropertyConfig(Object name, PropertyConfig parent) {
		this.parent = parent;
		this.parentName = name;
	}
	
	public PropertyConfig parent() {
		return parent;
	}

	public PropertyConfig add(){
		
		Object o = parent.get(parentName);
		BuilderList list;
		
		if(o instanceof BuilderList) {
			list = ((BuilderList)o);
		}
		else {
			list = new BuilderList(this);
			parent.put(parentName, list);
		}
		
		PropertyConfig m = new PropertyConfig(null, this);
		list.add(m);
		return m;
	}

	public PropertyConfig with(Object key, Object ... value){
		
		if(value.length == 0) {
			PropertyConfig m = new PropertyConfig(key, this);
			put(key,m);
			return m;
		}
		else
		if(value.length == 1) {
			put(key,value[0]);
		}
		else {
			put(key, (Object)Arrays.asList(value));
		}
		
		return this;
	}

	public static class BuilderList extends ArrayList<Object>{
	
		private PropertyConfig parent;
		
		public BuilderList(PropertyConfig parent){
			super();
			this.parent = parent;
		}
		
		public PropertyConfig getParent() {
			return parent;
		}
	
	}
	
}

