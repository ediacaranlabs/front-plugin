
$.AppContext.onload(function(){			

	messages.setDefaultLanguage('pt-BR');
	messages.addSupportedLanguages('pt-BR');

	$.AppContext.vars.contextPath = "";
	$.AppContext.vars.painel      = "#content-body";
	$.AppContext.vars.dialog      = "#defaultDialog";
	$.AppContext.vars.language    = messages.getLanguage();

});