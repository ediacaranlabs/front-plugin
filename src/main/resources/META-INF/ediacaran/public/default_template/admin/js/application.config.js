$.AppContext.local = {};

$.AppContext.local.resizeContent = function () {
	var topMenuHeight = $(".top-menu").outerHeight();
	var footerHeight  = $("footer").outerHeight();
	var height        = $(window).height();
	height            = height - (footerHeight + topMenuHeight);
	
	$("#content-body").css('min-height', height + "px");
	$("#pageBody").css('height', $(window).height() + "px");
	$("#pageBody").css('min-height', $(window).height() + "px");
	
};

$.AppContext.onload(function(){			

	$.AppContext.addLoadListener("local-link-load", "^.*", {
		
		after: function(){
			$.AppContext.local.resizeContent();
		}
	
	});
	
	$(window).resize(function(){
		$.AppContext.local.resizeContent();
	});
	
	$.AppContext.local.resizeContent();
	
	$("aside.sidebar a[class^='dropdown-item']").click(function(){
		var width = $(window).width();
		if(width < 1200){
			$('#pageBody').collapse('toggle');
			//$('#pageBody').removeClass('show');
		}
	});
	
	$("aside.sidebar").hover(function(){
		$('#pageBody').addClass('hover-menu');
	}, function(){
		$('#pageBody').removeClass('hover-menu');
	});
	
	$(window).click(function(){
		
		if(!$('#pageBody').hasClass('hover-menu') && $('#pageBody').hasClass('show')){

			var width = $(window).width();
			
			if(width < 1200){
				$('#pageBody').collapse('toggle');
				//$('#pageBody').removeClass('show');
			}
			
		}
		
	});
	
	var width = $(window).width();
	
	if(width < 1200){
		$('#pageBody').removeClass('show');
	}
	
});