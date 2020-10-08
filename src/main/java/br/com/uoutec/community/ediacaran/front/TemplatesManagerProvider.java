package br.com.uoutec.community.ediacaran.front;

@Deprecated
public class TemplatesManagerProvider {

	private static TemplatesManager templatesManager;

	public static TemplatesManager getTemplatesManager() {
		return templatesManager;
	}

	static void setTemplatesManager(TemplatesManager sTemplatesManager) {
		templatesManager = sTemplatesManager;
	}
	
}
