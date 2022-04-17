$.AppContext.i18n = {}


$.AppContext.i18n.setDefaultLanguage = function(value){
	this.defaultLanguage = value;
};

$.AppContext.i18n.getDefaultLanguage = function(){
	return this.defaultLanguage;
};

$.AppContext.i18n.addSupportedLanguages = function(value){
	if(this.supportedLanguages === undefined){
		this.supportedLanguages = {};
	}
	this.supportedLanguages[value] = true;
};

$.AppContext.i18n.removeSupportedLanguages = function(value){
	if(this.supportedLanguages !== undefined){
		this.supportedLanguages[value] = false;
	}
};

$.AppContext.i18n.getLanguage = function(){
	
	if(this.supportedLanguages === undefined){
		return this.defaultLanguage;
	}

	var lang;
	
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

$.AppContext.i18n.getValue = function(resource, key){
	if(this.resources === undefined){
		return null;
	}
	
	if(this.resources[resource] === undefined){
		return null;
	}
	
	return this.resources[resource][key] === undefined? null : this.resources[resource][key];
};

$.AppContext.i18n.setValue = function(resource, key, value){
	if(this.resources === undefined){
		this.resources = {};
	}

	if(this.resources[resource] === undefined){
		this.resources[resource] = {};
	}
	
	return this.resources[resource][key] = value;
};

$.AppContext.i18n.deleteValues = function(resource){
	if(this.resources === undefined){
		return 
	}

	if(this.resources[resource] === undefined){
		return;
	}
	
	delete this.resources[resource];
};

$.AppContext.i18n.apply = function($e){
};

