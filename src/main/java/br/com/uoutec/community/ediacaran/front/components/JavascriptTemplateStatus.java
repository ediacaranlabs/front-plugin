package br.com.uoutec.community.ediacaran.front.components;

public class JavascriptTemplateStatus {

	private static ThreadLocal<Boolean> status = new ThreadLocal<>();
	
	public static boolean isActive() {
		Boolean s = status.get();
		return s == null? false : s.booleanValue();
	}
	
	static void active() {
		status.set(Boolean.TRUE);
	}
	
	static void deactive() {
		status.remove();
	}
	
}
