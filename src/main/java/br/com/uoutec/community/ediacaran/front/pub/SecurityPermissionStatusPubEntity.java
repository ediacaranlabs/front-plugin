package br.com.uoutec.community.ediacaran.front.pub;

import javax.validation.constraints.NotNull;

import org.brandao.brutos.annotation.Enumerated;
import org.brandao.brutos.annotation.EnumerationType;

import br.com.uoutec.ediacaran.core.security.PermissionType;
import br.com.uoutec.ediacaran.core.security.SecurityPermissionRequest;
import br.com.uoutec.ediacaran.core.security.SecurityPermissionStatus;

public class SecurityPermissionStatusPubEntity 
	extends GenericPubEntity<SecurityPermissionStatus>{

	private static final long serialVersionUID = -4554003415770404793L;

	@NotNull
	@Enumerated(EnumerationType.STRING)
	private PermissionType permission;
	
	private String name;
	
	private String actions;

	private Boolean enabled;
	
	public SecurityPermissionStatusPubEntity() {
	}
	
	@Override
	protected String getCodeType() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Class<?> getGenericType() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void loadProperties(GenericPubEntity<SecurityPermissionStatus> e) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean isEqualId(SecurityPermissionStatus instance) throws Throwable {
		return new SecurityPermissionRequest(permission, name, actions).equals(instance.getRequest());
	}

	@Override
	protected boolean hasId(SecurityPermissionStatus instance) throws Throwable {
		return permission != null && name != null;
	}

	@Override
	protected SecurityPermissionStatus reloadEntity() throws Throwable {
		return null;
	}

	@Override
	protected void throwReloadEntityFail() throws Throwable {
		throw new UnsupportedOperationException();
	}

	@Override
	protected SecurityPermissionStatus createNewInstance() throws Throwable {
		return new SecurityPermissionStatus();
	}

	@Override
	protected void copyTo(SecurityPermissionStatus o, boolean reload, boolean override, boolean validate)
			throws Throwable {
		
		SecurityPermissionRequest req = new SecurityPermissionRequest(permission, name, actions);
		o.setRequest(req);
		o.setEnabled(enabled);
		
	}

}
