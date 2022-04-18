$.AppContext.sidebar = {};

$.AppContext.sidebar.resizeContent = function () {
	$.AppContext.sidebar.apply('');
};

$.AppContext.sidebar.apply = function($id){

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

		$($menuRef + " .sidebar-body").each(function() {

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
	
};

$.AppContext.onload(function(){			

	$.AppContext.sidebar.apply('');

	$(window).resize(function(){
		$.AppContext.sidebar.resizeContent();
	});
	
});