package br.com.uoutec.community.ediacaran.front.components;

import java.util.HashSet;
import java.util.Set;

public class ValidatorEntity {
	
	private String name;
	
	private String message;
	
	private boolean raw;
	
	private Set<ValidatorParamEntity> params;

	public ValidatorEntity(String name, String message, boolean raw) {
		super();
		this.name = name;
		this.message = message;
		this.raw = raw;
		this.params = new HashSet<ValidatorParamEntity>();
	}

	public String getName() {
		return name;
	}

	public String getMessage() {
		return message == null? null : (raw? message : "\"" + message +"\"");
	}

	public boolean isRaw() {
		return raw;
	}

	public Set<ValidatorParamEntity> getParams() {
		return params;
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
		ValidatorEntity other = (ValidatorEntity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
