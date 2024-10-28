package br.com.uoutec.community.ediacaran.front.pub;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.front.UserEventListenerManager;
import br.com.uoutec.community.ediacaran.front.UserEventListenerManager.UserEvent;
import br.com.uoutec.filter.invoker.annotation.EnableFilters;

@Singleton
public class UserEventsProvider {

	@Inject
	public UserEventListenerManager userEventListenerManager;
	
	@EnableFilters(UserEventListenerManager.class)
	public UserEvent[] getEvents() throws InterruptedException{
		return userEventListenerManager.getEvents();
	}
	
}
