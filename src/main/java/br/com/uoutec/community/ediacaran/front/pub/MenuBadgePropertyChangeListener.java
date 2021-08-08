package br.com.uoutec.community.ediacaran.front.pub;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import br.com.uoutec.community.ediacaran.front.UserEventListenerManager;
import br.com.uoutec.community.ediacaran.front.UserEventListenerManager.UserEvent;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;

public class MenuBadgePropertyChangeListener implements PropertyChangeListener{

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		String value = (String)evt.getNewValue();
		
		UserEventListenerManager userEventListenerManager = 
				EntityContextPlugin.getEntity(UserEventListenerManager.class);
		
		userEventListenerManager
			.asyncFireEvent(
					new UserEvent(((Menu)evt.getSource()).getId(), "menu.badge.change", value));
		
	}

}
