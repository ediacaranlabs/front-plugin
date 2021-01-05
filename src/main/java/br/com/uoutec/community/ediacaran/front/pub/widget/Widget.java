package br.com.uoutec.community.ediacaran.front.pub.widget;

import br.com.uoutec.community.ediacaran.VarParser;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;

public class Widget {

	private String name;
	
	private String resource;
	
	private int order;

	public Widget() {
		this(null, null, 0);
	}
	
	public Widget(String name, String resource, int order) {
		this.name = name;
		this.resource = resource;
		this.order = order;
	}

	public String getRawResource() {
		return resource;
	}
	
	public String getResource() {
		VarParser varParser = EntityContextPlugin.getEntity(VarParser.class);
		return varParser.getValue(resource);
	}

	public String getName() {
		return name;
	}

	public int getOrder() {
		return order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Widget other = (Widget) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
