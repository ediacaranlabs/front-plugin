$.AppContext.sidebar = {};

$.AppContext.sidebar.resizeContent = function () {
	var topMenuHeight = $(".top-menu").outerHeight();
	var footerHeight  = $("footer").outerHeight();
	var height        = $(window).height();
	height            = height - (footerHeight + topMenuHeight);
	$(".content").css('min-height', height + "px");
	$(".sidebar-body").css('min-height', ($(window).height() - $(".sidebar-body").offset().top) + "px");
};

$.AppContext.onload(function(){			

	$(window).resize(function(){
		$.AppContext.sidebar.resizeContent();
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
	
	$.AppContext.sidebar.resizeContent();
	
	var width = $(window).width();
	
	if(width < 1200){
		$('body').removeClass('show');
	}
	
});