package br.com.uoutec.community.ediacaran.front.pub.widget;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.plugins.PublicBean;

@Singleton
public class Widgets implements PublicBean{

	private PropertyChangeSupport propertyChangeSupport;
	
	private List<Widget> itens;
	
	public Widgets(){
		this.propertyChangeSupport = new PropertyChangeSupport(this);
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
		
		propertyChangeSupport.fireIndexedPropertyChange("widget", itens.size() - 1, null, value);
		
	}

	public Widget getWidget(String name){
		Widget w = new Widget(name, null, 0);
		int index = this.itens.indexOf(w);
		return index != -1? this.itens.get(index) : null; 
	}
	
	public void removeWidget(String value) throws WidgetException{
		
		Widget w = new Widget(value, null, 0);
		this.itens.remove(w);
		
		propertyChangeSupport.fireIndexedPropertyChange("widget", itens.size(), w, null);
		
	}

	public List<Widget> getWidgets(){
		return this.itens;
	}
	
}
