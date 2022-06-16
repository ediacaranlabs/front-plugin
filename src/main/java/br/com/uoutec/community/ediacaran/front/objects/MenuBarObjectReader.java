package br.com.uoutec.community.ediacaran.front.objects;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.pub.Menu;
import br.com.uoutec.community.ediacaran.front.pub.MenuBar;

public class MenuBarObjectReader extends ObjectReaderImp{

	@Override
	@SuppressWarnings("unchecked")
	public Object read(InputStream stream) throws IOException {
		Map<String,Object> data = (Map<String, Object>) super.read(stream);
		return toObject(data);
	}

	@SuppressWarnings("unchecked")
	private MenuBar toObject(Map<String,Object> data) {
		
		String idDTA = (String) data.get("id");
		String nameDTA = (String) data.get("name");
		List<Map<String,Object>> itensDTA = (List<Map<String,Object>>) data.get("itens");
		
		if(idDTA == null || idDTA.trim().length() == 0) {
			return null;
		}
		
		MenuBar menuBar = new MenuBar(idDTA.trim());
		
		menuBar.setName(nameDTA);
		
		if(itensDTA != null) {
			
			for(Map<String,Object> i: itensDTA) {
			
				String idMenuDTA = (String)i.get("id");

				if(idMenuDTA != null && idMenuDTA.trim().length() > 0){
					addMenu(menuBar.addMenu(idMenuDTA.trim()),i);
				}
			}
			
		}
				
		return menuBar;
	}
	
	
	@SuppressWarnings("unchecked")
	private void addMenu(Menu menu, Map<String,Object> itemDTA) {
		
		String nameDTA = (String)itemDTA.get("name");
		String iconDTA = (String)itemDTA.get("icon");
		String resourceDTA = (String)itemDTA.get("resource");
		String bodyDTA = (String)itemDTA.get("body");
		String resourceBundleDTA = (String)itemDTA.get("resourceBundle");
		String badgeStyleDTA = (String)itemDTA.get("badgeStyle");
		String templateDTA = (String)itemDTA.get("template");
		String roleDTA = (String)itemDTA.get("role");
		String permissionDTA = (String)itemDTA.get("permission");
		Number orderDTA = (Number)itemDTA.get("order");
		List<Map<String,Object>> itensDTA = (List<Map<String,Object>>)itemDTA.get("itens");
		
		menu.setName(nameDTA);
		menu.setIcon(iconDTA);
		menu.setResource(resourceDTA);
		menu.setBody(bodyDTA);
		menu.setResourceBundle(resourceBundleDTA);
		menu.setBadgeStyle(badgeStyleDTA);
		menu.setTemplate(templateDTA);
		menu.setRole(roleDTA);
		menu.setPermission(permissionDTA);
		menu.setOrder(orderDTA == null? 0 : orderDTA.intValue());
		menu.setPersistent(true);
		
		if(itensDTA != null) {
			
			for(Map<String,Object> i: itensDTA) {
			
				String idMenuDTA = (String)i.get("id");

				if(idMenuDTA != null && idMenuDTA.trim().length() > 0){
					addMenu(menu.addItem(idMenuDTA.trim()),i);
				}
			}
			
		}
		
	}
	
}
