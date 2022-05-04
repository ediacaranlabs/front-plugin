$.AppContext.sidebar = {};

$.AppContext.sidebar.resizeContent = function () {
	$.AppContext.sidebar.apply('');
};

$.AppContext.sidebar.getSideBar = function ($ref) {
	var maxLoop = 100;
	var loop = 0;
	while(!$ref.is('body')){
		
		console.log($ref.attr("id"));
		
		if($ref.hasClass('sidebar-group')){
			return $ref;
		}
		
		$ref = $($ref).parent()
		
		if(loop > maxLoop){
			break;
		}
		loop++;
	}
};

$.AppContext.sidebar.apply = function($id){

	$($id + " aside.sidebar").each(function() {

		var $bar = $(this);
		var $body = $($bar).parent()
		var $content = $($body).find(".sidebar-content");
		console.log($($content).height());
		
		var bodyHeight = $($body).outerHeight();
		var barHeight = $($bar).outerHeight();
		
/*		
		if($bar.hasClass('auto-height')){
			
			if(bodyHeight > barHeight){
				$($bar).css('max-height', bodyHeight + "px");
				$($bar).css('min-height', bodyHeight + "px");
			}
			else{
				$($body).css('max-height', barHeight + "px");
				$($body).css('min-height', barHeight + "px");
			}
			
		}
		else{
			$($bar).css('max-height', bodyHeight + "px");
			$($bar).css('min-height', bodyHeight + "px");
		}
*/

	});
		
};

$.AppContext.onload(function(){			

	$.AppContext.sidebar.apply('');

	$(window).resize(function(){
		$.AppContext.sidebar.resizeContent();
	});
	
	$.AppContext.eventListeners.addListener('sidebar-link-load',{
		
		fireEvent : function($e){
			
			if($e.sourceID === 'utils.enableActions' && $e.type === 'after'){
				
				$.AppContext.sidebar.apply($e.data.local);
				
				$($e.data.local + " img").on("load", function(){
					$.AppContext.sidebar.resizeContent();
				});
				
			}
		}
	
	});
	
	$.AppContext.addLoadListener("sidebar-link-load", "^.*", {
		
		after: function(){
			$.AppContext.sidebar.resizeContent();
			$("img").on("load", function(){
				$.AppContext.sidebar.resizeContent();
			});
		}
	
	});
	
	$("aside.sidebar a[class^='nav-link']").click(function(){
		
		if($(this).hasClass("dropdown-toggle")){
			return;
		}
		
		var width = $(window).width();
		if(width < 1200){
			$.AppContext.sidebar.getSideBar($(this)).collapse('toggle');
		}
	});

	$("aside.sidebar a[class^='dropdown-item']").click(function(){
		
		var width = $(window).width();
		if(width < 1200){
			$.AppContext.sidebar.getSideBar($(this)).collapse('toggle');
		}
		
	});
	
	$("aside.sidebar").hover(function(){
		$.AppContext.sidebar.getSideBar($(this)).addClass('hover-menu');
	}, function(){
		$.AppContext.sidebar.getSideBar($(this)).removeClass('hover-menu');
	});
	
	$(window).click(function(){
		
		var width = $(window).width();
		
		if(!(width < 1200)){
			return;
		}
		
		$(".sidebar-group").each(function() {

			if(!$(this).hasClass('hover-menu') && $(this).hasClass('show')){
				$(this).collapse('toggle');
			}
				
		});
		
	});	
	
});