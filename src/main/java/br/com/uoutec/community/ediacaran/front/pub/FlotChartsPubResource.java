package br.com.uoutec.community.ediacaran.front.pub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Singleton;

import org.brandao.brutos.annotation.AcceptRequestType;
import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.ResponseType;
import org.brandao.brutos.annotation.web.MediaTypes;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}/flotcharts", defaultActionName="/")
@AcceptRequestType(MediaTypes.APPLICATION_JSON)
@ResponseType(MediaTypes.APPLICATION_JSON)
public class FlotChartsPubResource {

	private static Random r = new Random();
	
	private List<Integer> list;
	
	public FlotChartsPubResource() {
		this.list = null;
	}
	
	@Action(value="/")
	public Serializable loadData(){
		update();
		
		List<int[]> data = new ArrayList<int[]>();
		int index = 0;
		for(Integer v: list) {
			data.add(new int[] {index++, v});
		}
		
		Map<String, Object> series = new HashMap<>();
		series.put("data", data);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("series", Arrays.asList(series));
		
		return map;
	}

	private void update() {
		synchronized (FlotChartsPubResource.class) {
			
			if(list == null) {
				list = new ArrayList<Integer>();
				int y = r.nextInt(100);
				for(int i=0;i<100;i++) {
					list.add(y);
					y = getY(y);
				}
			}
			
			list.remove(0);
			int y = list.get(list.size() - 1);
			y = getY(y);
			list.add(y);
		}
	}
	
	private int getY(int x) {
		int v = (r.nextInt() % 5);
		int y;
		
		if((v > 0 && (x + v > 90)) || (v < 0 && (x + v < 30)) ) 
			y = x - v;
		else
			y = x + v;
		
		return y;
	}
}
