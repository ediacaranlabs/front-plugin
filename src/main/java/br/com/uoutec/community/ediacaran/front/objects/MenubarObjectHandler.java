package br.com.uoutec.community.ediacaran.front.objects;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.pub.MenuBar;
import br.com.uoutec.community.ediacaran.system.repository.ObjectHandler;

public class MenubarObjectHandler implements ObjectHandler{

	private DataMenubarParser dataMenubarParser;
	
	private MenuBarObjectParser menuBarObjectParser;
	
	public MenubarObjectHandler() {
		this.dataMenubarParser = new DataMenubarParser();
		this.menuBarObjectParser = new MenuBarObjectParser();
	}
	@Override
	public String getType() {
		return "menu";
	}

	@Override
	public boolean accept(Object o) {
		return true;
	}

	@Override
	public boolean accept(String type) {
		return "menu".equals(type);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Object toObject(Object data) {
		return dataMenubarParser.toObject((Map<String, Object>) data);
	}
	@Override
	public Object toData(Object object) {
		return menuBarObjectParser.toData((MenuBar) object);
	}

}
