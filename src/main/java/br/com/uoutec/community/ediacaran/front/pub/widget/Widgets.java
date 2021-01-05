package br.com.uoutec.community.ediacaran.front.pub.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.plugins.PublicBean;

@Singleton
public class Widgets implements PublicBean{

	private List<Widget> itens;
	
	public Widgets(){
		this.itens = new ArrayList<Widget>();
	}
	
	public void addWidget(Widget value) throws WidgetException{
		
		if(this.itens.contains(value)){
			throw new WidgetException("widget já adicionado!");
		}
			
		this.itens.add(value);
		
		Collections.sort(this.itens, new Comparator<Widget>(){

			public int compare(Widget o1, Widget o2) {
				return o2.getOrder() - o1.getOrder();
			}
			
		});
		
	}

	public Widget getWidget(String name){
		Widget w = new Widget(null, name, 0);
		int index = this.itens.indexOf(w);
		return index != -1? this.itens.get(index) : null; 
	}
	
	public void removeWidget(String value) throws WidgetException{
		Widget w = new Widget(null, value, 0);
		this.itens.remove(w);
	}

	public List<Widget> getWidgets(){
		return this.itens;
	}
	
}
