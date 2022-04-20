$.AppContext.sidebar = {};

$.AppContext.sidebar.resizeContent = function () {
	$.AppContext.sidebar.apply('');
};

$.AppContext.sidebar.apply = function($id){

	
	$("aside.sidebar").each(function() {

		var $bar = $(this);
		var $body = $($bar).parent()
		
		$($body).addClass('content-menu');
		
		var bodyHeight = $($body).outerHeight();
		
		var width = $(window).width();

		if(width > 1200){
			$($bar).css('height', bodyHeight + "px");
		}
		else{
			$($bar).css('height', "unset");
		}
	});
	
/*	
	$($id + " .content").each(function() {

		var $content = $(this);
		var $menuRef = $($content).attr("menu");
		
		if($menuRef == 'undefined'){
			return;
		}
		
		$($content).addClass('content-menu');
		
		$($menuRef).each(function() {

			var $menu = $(this);
			var contentHeight = $($content).outerHeight();
			var menuWidth = $($menu).outerWidth();

		});

		$($menuRef).each(function() {

			var $menuBody = $(this);
			var contentHeight = $($content).outerHeight();

			var width = $(window).width();

			if(width > 1200){
				$($menuBody).css('height', contentHeight + "px");
			}
			else{
				$($menuBody).css('height', "unset");
			}
			
			//$($content).css('height', contentHeight + "px");
			
		});
		
	});
*/
	
};

$.AppContext.onload(function(){			

	$.AppContext.sidebar.apply('');

	$(window).resize(function(){
		$.AppContext.sidebar.resizeContent();
	});
	
});