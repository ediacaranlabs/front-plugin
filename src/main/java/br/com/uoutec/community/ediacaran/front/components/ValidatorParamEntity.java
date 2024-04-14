package br.com.uoutec.community.ediacaran.front.components;

public class ValidatorParamEntity {
	
	private String name;
	
	private String value;

	private boolean raw;
	
	public ValidatorParamEntity(String name, String value, boolean raw) {
		super();
		this.name = name;
		this.value = value;
		this.raw = raw;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value == null? null : (raw? value : "\"" + value +"\"");
	}

	public boolean isRaw() {
		return raw;
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
		ValidatorParamEntity other = (ValidatorParamEntity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
