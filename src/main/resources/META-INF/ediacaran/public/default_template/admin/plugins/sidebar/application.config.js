$.AppContext.sidebar = {};

$.AppContext.sidebar.resizeContent = function () {
	$.AppContext.sidebar.apply('');
};

$.AppContext.sidebar.apply = function($id){

	$("aside.sidebar").each(function() {

		var $bar = $(this);
		var $body = $($bar).parent()
		
		var bodyHeight = $($body).outerHeight();
		var barHeight = $($bar).outerHeight();

		var width = $(window).width();

		console.log($($body).attr("id") + "->" + bodyHeight + " : " + $($bar).attr("id") + "->" + barHeight);
		
		if(width > 1200 || $bar.hasClass('static-height')){
			
			if($bar.hasClass('auto-height')){
				
				if(bodyHeight > barHeight){
					$($bar).css('height', bodyHeight + "px");
				}
				else{
					$($body).css('height', barHeight + "px");
				}
				
			}
			else{
				$($bar).css('height', bodyHeight + "px");
			}
			
		}
		else{
			$($bar).css('height', "unset");
		}
	});
	
};

$.AppContext.onload(function(){			

	$.AppContext.sidebar.apply('');

	$(window).resize(function(){
		$.AppContext.sidebar.resizeContent();
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
	
});