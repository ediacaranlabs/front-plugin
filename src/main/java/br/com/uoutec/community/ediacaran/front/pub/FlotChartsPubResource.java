package br.com.uoutec.community.ediacaran.front.pub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Singleton;

import org.brandao.brutos.annotation.AcceptRequestType;
import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.ResponseType;
import org.brandao.brutos.annotation.web.MediaTypes;

import br.com.uoutec.community.ediacaran.front.PropertyConfig;

@Singleton
@Controller(value="/flotcharts", defaultActionName="/")
public class FlotChartsPubResource {

	private static Random r = new Random();
	
	private List<Integer> list;
	
	public FlotChartsPubResource() {
		this.list = null;
	}
	
	@Action(value="/")
	@AcceptRequestType(MediaTypes.APPLICATION_JSON)
	@ResponseType(MediaTypes.APPLICATION_JSON)
	public synchronized Serializable loadData(){
		update();
		
		List<int[]> data = new ArrayList<int[]>();
		
		int index = 0;
		for(Integer v: list) {
			data.add(new int[] {index++, v});
		}
		
		PropertyConfig m = new PropertyConfig(); 
		m
			.with("series")
				.add()
					.with("data", data);
		
		return m;
	}

	private void update() {
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
	@AcceptRequestType(MediaTypes.APPLICATION_JSON)
	@ResponseType(MediaTypes.APPLICATION_JSON)
	public synchronized Serializable lineChart(){
		update();

		List<double[]> sinData = new ArrayList<double[]>();
		List<double[]> cosData = new ArrayList<double[]>();

		for(double i=0;i < 14; i += 0.5) {
			sinData.add(new double[] {i, Math.sin(i)});
			cosData.add(new double[] {i, Math.cos(i)});
		}
		
		PropertyConfig m = new PropertyConfig(); 
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
	
}
