package br.com.uoutec.community.ediacaran.front.pub;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.ActionStrategy;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.ResponseType;
import org.brandao.brutos.annotation.web.MediaTypes;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.RequestMethodTypes;
import org.brandao.brutos.annotation.web.WebActionStrategyType;

import br.com.uoutec.community.ediacaran.front.UserEventListenerManager;
import br.com.uoutec.community.ediacaran.front.UserEventListenerManager.UserEvent;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;

@Singleton
@Controller
//@AcceptRequestType(MediaTypes.APPLICATION_JSON)
@ResponseType(MediaTypes.APPLICATION_JSON)
@ActionStrategy(WebActionStrategyType.DETACHED)
public class EventPubResource {

	@Action(value="/events")
	@RequestMethod(RequestMethodTypes.GET)
	public synchronized Serializable events() throws InterruptedException{
		
		UserEventListenerManager userEventListenerManager = EntityContextPlugin.getEntity(UserEventListenerManager.class);
		
		UserEvent[] evts = 
			userEventListenerManager.getEvents();
		
		if(evts.length == 0) {
			Thread.sleep(TimeUnit.SECONDS.toMillis(2));
		}
		
		return evts;
	}
	
}
