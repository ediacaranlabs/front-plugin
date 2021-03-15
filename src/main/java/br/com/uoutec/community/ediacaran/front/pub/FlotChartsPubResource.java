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
				for(int i=0;i<=100;i++) {
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
	
	@Action(value="/line-chart")
	public Serializable lineChart(){
		update();
		
		List<double[]> sinData = new ArrayList<double[]>();
		List<double[]> cosData = new ArrayList<double[]>();

		for(double i=0;i < 14; i += 0.5) {
			sinData.add(new double[] {i, Math.sin(i)});
			cosData.add(new double[] {i, Math.cos(i)});
		}
		
		Map<String, Object> yaxis = new HashMap<>();
		yaxis.put("min", "-1.5");
		yaxis.put("max", "1.5");
		
		Map<String, Object> xaxis = new HashMap<>();
		xaxis.put("min", "0.0");
		xaxis.put("max", "13.0");

		Map<String, Object> axes = new HashMap<>();
		axes.put("yaxis",yaxis);
		axes.put("xaxis",xaxis);
		
		Map<String, Object> sin = new HashMap<>();
		sin.put("data", sinData);
		sin.put("color", "#3c8dbc");
		
		Map<String, Object> cos = new HashMap<>();
		cos.put("data", cosData);
		cos.put("color", "#00c0ef");
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("series", Arrays.asList(sin,cos));
		map.put("axes",  axes);
		
		return map;
	}
	
}
