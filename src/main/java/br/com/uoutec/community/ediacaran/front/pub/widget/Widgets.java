package br.com.uoutec.community.ediacaran.front.pub.widget;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.security.BasicRoles;
import br.com.uoutec.community.ediacaran.security.Subject;
import br.com.uoutec.community.ediacaran.security.SubjectProvider;
import br.com.uoutec.ediacaran.core.plugins.PublicBean;

@Singleton
public class Widgets implements PublicBean{

	private final PropertyChangeSupport propertyChangeSupport;
	
	@Inject
	private SubjectProvider subjectProvider;
	
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
		Widget w = new Widget(name, null, null, null, 0);
		int index = this.itens.indexOf(w);
		return index != -1? this.itens.get(index) : null; 
	}
	
	public void removeWidget(String value) throws WidgetException{
		
		Widget w = new Widget(value, null, null, null, 0);
		this.itens.remove(w);
		
		propertyChangeSupport.fireIndexedPropertyChange("widget", itens.size(), w, null);
		
	}

	public List<Widget> getWidgets(){
		
		Subject subject = subjectProvider.getSubject();
		
		List<Widget> result = new ArrayList<>();
		
		itens.stream().forEach((e)->{
			
			if(e.getRole() == null) {
				result.add(e);
			}
			else
			if(!subject.isAuthenticated()) {
				for(String role: e.getRole()) {
					if(BasicRoles.NOT_AUTHENTICATED.equals(role)) {
						result.add(e);
						break;
					}
				}
			}
			else
			if(subject.isAuthenticated() && hasRole(subject, e.getRole())) {
			
				if(e.getPermission() == null || isPermitted(subject, e.getPermission())) {
					result.add(e);
				}
				
			}
			
		});
		
		return this.itens;
	}
	
	private boolean hasRole(Subject subject, String[] roles) {
		
		if(roles == null) {
			return false;
		}
		
		boolean[] values = subject.hasRoles(roles);

		for(boolean test: values) {
			
			if(test) {
				return true;
			}
		}
		
		return false;
	}

	private boolean isPermitted(Subject subject, String[] permissions) {
		
		if(permissions == null) {
			return false;
		}
		
		boolean[] values = subject.isPermitted(permissions);

		for(boolean test: values) {
			
			if(test) {
				return true;
			}
		}
		
		return false;
	}
	
}
