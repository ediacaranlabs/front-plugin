package br.com.uoutec.community.ediacaran.front.pub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
		
		MapBuilder m = new MapBuilder(); 
		m
			.with("series")
				.add()
					.with("data", data);
		
		return m;
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
		
		MapBuilder m = new MapBuilder(); 
		m
			.with("axes")
				.with("yaxis")
					.with("min", "-1.5")
					.with("max", "1.5")
			.parent() //axes
				.with("xaxis")
					.with("min", "0.0")
					.with("max", "13.0")
			.parent()//axes
		.parent() //map
			.with("series")
				.add()
					.with("data", sinData)
					.with("color", "#3c8dbc")
			.parent() //series
				.add()
					.with("data", cosData)
					.with("color", "#3c8dbc");
		return m;
	}
	
	@SuppressWarnings("serial")
	public static class MapBuilder 
		extends HashMap<Object,Object>{
		
		private Object parentName;
		
		private MapBuilder parent;
		
		public MapBuilder() {
			this(null, null);
		}
		
		public MapBuilder(Object name, MapBuilder parent) {
			this.parent = parent;
			this.parentName = name;
		}
		
		public MapBuilder parent() {
			return parent;
		}

		public MapBuilder add(){
			
			Object o = parent.get(parentName);
			BuilderList list;
			
			if(o instanceof BuilderList) {
				list = ((BuilderList)o);
			}
			else {
				list = new BuilderList(this);
				parent.put(parentName, list);
			}
			
			MapBuilder m = new MapBuilder(null, this);
			list.add(m);
			return m;
		}
		
		public MapBuilder with(Object key, Object ...value){
			
			if(value.length == 0) {
				MapBuilder m = new MapBuilder(key, this);
				put(key,m);
				return m;
			}
			else
			if(value.length == 1) {
				put(key,value[0]);
			}
			else {
				put(key, (Object)Arrays.asList(value));
			}
			
			return this;
		}
		
	}
	
	@SuppressWarnings("serial")
	public static class BuilderList extends ArrayList<Object>{
		
		private MapBuilder parent;

		public BuilderList(MapBuilder parent){
			super();
			this.parent = parent;
		}

		public MapBuilder getParent() {
			return parent;
		}
		
	}
	
}
