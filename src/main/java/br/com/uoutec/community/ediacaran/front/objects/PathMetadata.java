package br.com.uoutec.community.ediacaran.front.objects;

public class PathMetadata{
	
	public String driver;
	
	public String path;
	
	public String id;

	public PathMetadata(String driver, String path, String id) {
		this.driver = driver;
		this.path = path;
		this.id = id;
	}

	public String getFullId() {
		return path + "/" + id;
	}
	
	public String getGlobalId() {
		return driver + "/" + path + "/" + id;
	}
	
	public String getDriver() {
		return driver;
	}

	public String getPath() {
		return path;
	}

	public String getId() {
		return id;
	}
	
}
