package br.com.uoutec.community.ediacaran.front.pub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import br.com.uoutec.community.ediacaran.front.UserEventListenerManager;

@SessionScoped
public class UserEventListenerManagerImp 
	implements UserEventListenerManager, Serializable{

	private static final long serialVersionUID = -8619724438245447103L;
	
	private List<UserEvent> list;
	
	public UserEventListenerManagerImp() {
		this.list = new ArrayList<UserEvent>();
	}
	
	@Override
	public synchronized void asyncFireEvent(UserEvent e) {
		list.add(e);
	}

	@Override
	public synchronized UserEvent[] getEvents() {
		
		UserEvent[] events = 
				list.stream()
					.toArray(size -> new UserEvent[size]);
		
		list.clear();
		
		return events;
	}

	
}
