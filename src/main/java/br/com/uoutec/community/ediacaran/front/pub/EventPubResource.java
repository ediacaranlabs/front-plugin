package br.com.uoutec.community.ediacaran.front.pub;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import org.brandao.brutos.annotation.AcceptRequestType;
import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.ActionStrategy;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.ResponseType;
import org.brandao.brutos.annotation.ScopeType;
import org.brandao.brutos.annotation.web.MediaTypes;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.RequestMethodTypes;
import org.brandao.brutos.annotation.web.WebActionStrategyType;

import br.com.uoutec.community.ediacaran.front.UserEventListenerManager;
import br.com.uoutec.community.ediacaran.front.UserEventListenerManager.UserEvent;

@Singleton
@AcceptRequestType(MediaTypes.APPLICATION_JSON)
@ResponseType(MediaTypes.APPLICATION_JSON)
@ActionStrategy(WebActionStrategyType.DETACHED)
public class EventPubResource {

	@Action(value="/events")
	@RequestMethod(RequestMethodTypes.GET)
	public synchronized Serializable events(
			@Basic(scope=ScopeType.IOC) UserEventListenerManager userEventListenerManager) throws InterruptedException{
		
		UserEvent[] evts = 
			userEventListenerManager.getEvents();
		
		if(evts.length == 0) {
			Thread.sleep(TimeUnit.SECONDS.toMillis(2));
		}
		
		return evts;
	}
	
}
