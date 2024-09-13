package br.com.uoutec.community.ediacaran.front.pub.widget;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;

import br.com.uoutec.ediacaran.core.VarParser;
import br.com.uoutec.ediacaran.core.plugins.EntityContextPlugin;

public class Widget {

	private final String name;
	
	private final String resource;
	
	private final String[] role;
	
	private final String[] permission;
	
	private int order;

	Widget(String name, String resource, String[] role, String[] permission, int order) {
		super();
		this.name = name;
		this.resource = resource;
		this.role = role;
		this.permission = permission;
		this.order = order;
	}

	@Generated("SparkTools")
	private Widget(Builder builder) {
		this.name = builder.name;
		this.resource = builder.resource;
		this.role = builder.role.isEmpty()? null :  builder.role.stream().toArray(String[]::new);
		this.permission = builder.permission.isEmpty()? null : builder.permission.stream().toArray(String[]::new);
		this.order = builder.order;
	}

	public String getName() {
		return name;
	}

	public String getRawResource() {
		return resource;
	}

	public String getResource() {
		VarParser varParser = EntityContextPlugin.getEntity(VarParser.class);
		return varParser.getValue(resource);
	}

	public String[] getRole() {
		return role;
	}

	public String[] getPermission() {
		return permission;
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

	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private String name;
		private String resource;
		private Set<String> role = new HashSet<>();
		private Set<String> permission = new HashSet<>();
		private int order;

		private Builder() {
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withResource(String resource) {
			this.resource = resource;
			return this;
		}

		public Builder withRole(String role) {
			this.role.add(role);
			return this;
		}

		public Builder withPermission(String[] permission) {
			this.permission.add(name);
			return this;
		}

		public Builder withOrder(int order) {
			this.order = order;
			return this;
		}

		public Widget build() {
			return new Widget(this);
		}
	}
	
}
