package br.com.uoutec.community.ediacaran.front.page;

public class PageNotFoundException  extends PageManagerException{

	private static final long serialVersionUID = -5856344904897577103L;

	public PageNotFoundException() {
		super();
	}

	public PageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PageNotFoundException(String message) {
		super(message);
	}

	public PageNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
