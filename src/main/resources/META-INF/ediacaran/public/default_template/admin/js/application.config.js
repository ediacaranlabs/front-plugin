
$.AppContext.resizeContent = function () {
	var topMenuHeight = $(".top-menu").outerHeight();
	var footerHeight  = $("footer").outerHeight();
	var height        = $(window).height();
	height            = height - (footerHeight + topMenuHeight);
	$(".content").css('min-height', height + "px");
};

$.AppContext.onload(function(){			

	$(window).resize(function(){
		$.AppContext.resizeContent();	
	});
	
	$(".sidebar").hover(function(){
		$('body').addClass('hover-menu');
	}, function(){
		$('body').removeClass('hover-menu');
	});
	
	$(window).click(function(){
		if(!$('body').hasClass('hover-menu')){
			$('body').removeClass('show');
		}
	});
	
	$.AppContext.resizeContent();
	
});