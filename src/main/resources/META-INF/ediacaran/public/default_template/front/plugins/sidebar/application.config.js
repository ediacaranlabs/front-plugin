$.AppContext.sidebar = {};

$.AppContext.sidebar.resizeContent = function () {
};

$.AppContext.onload(function(){			

	$(window).resize(function(){
		$.AppContext.sidebar.resizeContent();
		//var width = $(window).width();
		//if(width < 1200){
		//	$('body').removeClass('show');
		//}
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
	
	$.AppContext.eventListeners.addListener('sidebar-link-load',{
		
		fireEvent : function($e){
			//alert(JSON.stringify($e));
		}
	
	});
	

	$.AppContext.addLoadListener("sidebar-link-load", "^.*", {
		
		after: function(){
			$.AppContext.sidebar.resizeContent();
		}
	
	});

	
	$.AppContext.sidebar.resizeContent();
	
	var width = $(window).width();
	
	if(width < 1200){
		$('body').removeClass('show');
	}
	
});