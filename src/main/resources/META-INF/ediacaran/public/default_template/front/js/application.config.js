
$.AppContext.resizeContent = function () {
	var topMenuHeight = $(".top-menu").outerHeight();
	var footerHeight  = $("footer").outerHeight();
	var height        = $(window).height();
	height            = height - (footerHeight + topMenuHeight);
	$(".content").css('min-height', height + "px");
	$(".sidebar-body").css('min-height', ($(window).height() - $(".sidebar-body").offset().top) + "px");
};

$.AppContext.onload(function(){			

	messages.setDefaultLanguage('pt-BR');
	messages.addSupportedLanguages('pt-BR');

	$.AppContext.vars.contextPath = "";
	$.AppContext.vars.painel      = "#content-body";
	$.AppContext.vars.dialog      = "#defaultDialog";
	$.AppContext.vars.language    = messages.getLanguage();
	
	$(window).resize(function(){
		//$.AppContext.resizeContent();
		var width = $(window).width();
		if(width < 1200){
			$('body').removeClass('show');
		}
	});
	
	$("aside[class^='sidebar'] a[class^='dropdown-item']").click(function(){
		var width = $(window).width();
		if(width < 1200){
			$('body').removeClass('show');
		}
	});
	
	$(".sidebar").hover(function(){
		$('body').addClass('hover-menu');
	}, function(){
		$('body').removeClass('hover-menu');
	});
	
	$(window).click(function(){
		if(!$('body').hasClass('hover-menu')){
			var width = $(window).width();
			if(width < 1200){
				$('body').removeClass('show');
			}
		}
	});
	
	//$.AppContext.resizeContent();
	
	var width = $(window).width();
	if(width < 1200){
		$('body').removeClass('show');
	}
	
	$.AppContext.addLoadListener("pretty-link-load", "^.*", {
		
		after: function(){
			PR.prettyPrint();
		}
	});
	
});