package br.com.uoutec.community.ediacaran.front.page;

public class PageExistsException  extends PageManagerException{

	private static final long serialVersionUID = -5856344904897577103L;

	public PageExistsException() {
		super();
	}

	public PageExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public PageExistsException(String message) {
		super(message);
	}

	public PageExistsException(Throwable cause) {
		super(cause);
	}
	
}
