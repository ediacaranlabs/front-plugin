messages = {};

/**
 * Define o idioma padrão.
 * @param value idioma.
 */
messages.setDefaultLanguage = function(value){
	this.defaultLanguage = value;
};

/**
 * Obtém o idioma padrão.
 */
messages.getDefaultLanguage = function(){
	return this.defaultLanguage;
};

/**
 * Inclui o suporte a um idioma.
 * @param value idioma.
 */
messages.addSupportedLanguages = function(value){
	if(this.supportedLanguages === undefined){
		this.supportedLanguages = {};
	}
	this.supportedLanguages[value] = true;
};

/**
 * Remove o suporte a um idioma.
 * @param value idioma.
 */
messages.removeSupportedLanguages = function(value){
	if(this.supportedLanguages !== undefined){
		this.supportedLanguages[value] = false;
	}
};


/**
 * Obtém o idioma do navegador.
 * @returns Idioma.
 */
messages.getLanguage = function(){
	
	var lang = this.defaultLanguage;
	
	if(this.supportedLanguages === undefined){
		return lang;
	}
	
	if(navigator.language){
		lang = navigator.language;
	}
	else
	if(navigator.languages){
		lang = navigator.languages[0];
	}
	else
	if(navigator.browserLanguages){
		lang = navigator.browserLanguages[0];
	}
	else
	if(navigator.userLanguage){
		lang = navigator.userLanguage;
	}
	else
	if(navigator.systemLanguages){
		lang = navigator.systemLanguages[0];
	}
	
	return this.supportedLanguages[lang]? lang : this.defaultLanguage;
};

messages.getValue = function(resource, key){
	if(this.resources === undefined){
		return null;
	}
	
	if(messages.resources[resource] === undefined){
		return null;
	}
	
	return messages.resources[resource][key] === undefined? null : messages.resources[resource][key];
};

messages.setValue = function(resource, key, value){
	if(this.resources === undefined){
		this.resources = {};
	}

	if(messages.resources[resource] === undefined){
		messages.resources[resource] = {};
	}
	
	return messages.resources[resource][key] = value;
};

messages.load = function(pattern){
	pattern         = pattern.replace("${lang}", ApplicationContext.language);
	script          = document.createElement("script");
	script.language = "javascript";
	script.src      = ApplicationContext.contextPath + pattern;
	script.charset  = "utf-8";
	script.async    = false;
	
	//h = document.getElementsByTagName('head')[0];
	//h.appendChild(script);
	document.body.appendChild(script);
};