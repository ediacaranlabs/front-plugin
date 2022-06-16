package br.com.uoutec.community.ediacaran.front.objects;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.pub.Menu;
import br.com.uoutec.community.ediacaran.front.pub.MenuBar;

public class MenuBarObjectWriter extends ObjectWriterImp{

	@Override
	public void write(Object obj, OutputStream stream) throws IOException {
		Map<String,Object> o = toObject((MenuBar)obj);
		super.write(o, stream);
	}

	private Map<String,Object> toObject(MenuBar menubar) {
		Map<String,Object> o = new HashMap<String,Object>();
		o.put("id", menubar.getId());
		o.put("name", menubar.getName());
		o.put("itens", toObject(menubar.getItens()));
		return o;
	}
	
	private List<Map<String,Object>> toObject(List<Menu> itens) {
		
		List<Map<String,Object>> im = new ArrayList<Map<String,Object>>();
		
		for(Menu m: itens) {
			if(m.isPersistent()){
				im.add(toObject(m));
			}
		}
		
		return im;
	}
	
	private Map<String,Object> toObject(Menu item) {
		Map<String,Object> i = new HashMap<String,Object>();
		
		i.put("id", item.getId());
		i.put("name", item.getName());
		i.put("icon", item.getIcon());
		i.put("resource", item.getResource());
		i.put("body", item.getBody());
		i.put("resourceBundle", item.getResourceBundle());
		i.put("badgeStyle", item.getBadgeStyle());
		i.put("template", item.getTemplate());
		i.put("itens", toObject(item.getItens()));
		i.put("role", item.getRole());
		i.put("permission", item.getPermission());
		i.put("order", item.getOrder());
		i.put("persistent", item.isPersistent());
		
		return i;
	}
	
}
