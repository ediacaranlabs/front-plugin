package br.com.uoutec.community.ediacaran.front.objects;

public class MenubarObjectHandler implements ObjectHandler{

	private ObjectReader reader;
	
	private ObjectWriter writer;
	
	public MenubarObjectHandler() {
		this.reader = new MenuBarObjectReader();
		this.writer = new MenuBarObjectWriter();
	}
	@Override
	public String getType() {
		return "menu";
	}

	@Override
	public ObjectReader getReader() {
		return reader;
	}

	@Override
	public ObjectWriter getWriter() {
		return writer;
	}

	@Override
	public boolean accept(Object o) {
		return true;
	}

	@Override
	public boolean accept(String type) {
		return "menu".equals(type);
	}

}
