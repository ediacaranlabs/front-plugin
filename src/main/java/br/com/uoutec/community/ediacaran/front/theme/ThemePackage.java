package br.com.uoutec.community.ediacaran.front.theme;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class ThemePackage {

	private String name;
	
	private String themePath;
	
	private String path;
	
	private ConcurrentMap<String, TemplateComponent> tagTemplates;

	private ConcurrentMap<String, List<PublicResource>> resources;
	
	private String context;
	
	public ThemePackage(String name, String context, String themePath, String path, ConcurrentMap<String, TemplateComponent> tagTemplates, 
			ConcurrentMap<String, List<PublicResource>> resources) {
		this.name = name;
		this.path = path;
		this.tagTemplates = tagTemplates;
		this.resources = resources;
		this.context = context;
		this.themePath = themePath;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public String getContext() {
		return context;
	}

	public String getThemePath() {
		return themePath;
	}

	public ConcurrentMap<String, TemplateComponent> getTagTemplates() {
		return tagTemplates;
	}

	public ConcurrentMap<String, List<PublicResource>> getResources() {
		return resources;
	}
	
}
