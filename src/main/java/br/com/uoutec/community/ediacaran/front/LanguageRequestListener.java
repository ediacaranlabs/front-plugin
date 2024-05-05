package br.com.uoutec.community.ediacaran.front;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Locale;

import javax.inject.Singleton;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import br.com.uoutec.community.ediacaran.system.i18n.PluginLanguageUtils;
import br.com.uoutec.ediacaran.core.EdiacaranEventListener;
import br.com.uoutec.ediacaran.core.EdiacaranEventObject;
import br.com.uoutec.ediacaran.web.ContextInitializer;
import br.com.uoutec.i18n.MessageBundleThread;

@Singleton
public class LanguageRequestListener implements EdiacaranEventListener{

	public static final String LOCALE_VAR                       = "locale";

	public static final String DEFAULT_LOCALE_VAR               = "default_locales";
	
	public static final String LANGUAGE_VAR                     = "lang";
	
	public LanguageRequestListener() {
	}
	
	@Override
	public void onEvent(EdiacaranEventObject event) {

		if(event.getSource() instanceof ContextInitializer) {
			
			if("requestInitialized".equals(event.getType())) {
				AccessController.doPrivileged((PrivilegedAction<Object>)()->{
					requestInitialized((ServletRequestEvent)event.getData());
					return null;
				});
			}
			else
			if("requestDestroyed".equals(event.getType())) {
				AccessController.doPrivileged((PrivilegedAction<Object>)()->{
					requestDestroyed((ServletRequestEvent)event.getData());
						return null;
				});
			}
		}
	}

	private void requestInitialized(ServletRequestEvent arg0) {
		HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();
		Locale locale = getCurrentLocale(request);
		
		request.setAttribute(LOCALE_VAR,           locale);
		request.setAttribute(DEFAULT_LOCALE_VAR,   Locale.getDefault());
		
		MessageBundleThread.setThreadLocale(locale);
	}

	private void requestDestroyed(ServletRequestEvent arg0) {
		MessageBundleThread.destroyThreadLocale();
	}
	
	private Locale getCurrentLocale(HttpServletRequest request){
		try{
			String langVar = null;
			Locale locale = null;
			Cookie[] cookies = request.getCookies();
			
			if(cookies != null) {
				for(Cookie c: cookies) {
					if("app-locale".equals(c.getName())){
						langVar = c.getValue();
					}
				}
			}
			if(langVar != null) {
				locale = getLocale(langVar);
				if(locale != null) {
					return locale;
				}
			}
			
			langVar = (String)request.getParameter(LANGUAGE_VAR);
			
			if(langVar != null){
				locale = getLocale(langVar);
				if(locale != null) {
					return locale;
				}
			}
			
			return request.getLocale();
		}
		catch(Throwable e){
			return Locale.getDefault();
		}
	}

	private Locale getLocale(String lang) {
		return PluginLanguageUtils.toLocale(lang);
	}
	
}
