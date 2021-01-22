
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
	
	$.AppContext.resizeContent();
	
});