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
			Random r = new Random();
			if(list == null) {
				list = new ArrayList<Integer>();
				for(int i=0;i<100;i++) {
					list.add(r.nextInt(100));
				}
			}
			
			list.remove(0);list.add(r.nextInt(100));
		}
	}
}
