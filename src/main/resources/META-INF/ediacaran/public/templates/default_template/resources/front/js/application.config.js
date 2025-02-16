$.AppContext.constants = {};

$.AppContext.constants.OPEN_TAG_ = '<';
$.AppContext.constants.END_TAG_  = '>';
$.AppContext.constants.DOUBLE_QUOTE = '\"';
$.AppContext.constants.JS_ID = 0;
$.AppContext.constants.getNextID = function(){
	var $id = $.AppContext.constants.JS_ID++;
	return "js_" + $id;
}

$.AppContext.local = {};

$.AppContext.local.resizeContent = function () {
	
	var topMenuHeight = $(".top-menu").outerHeight();
	var footerHeight  = $("footer").outerHeight();
	var height        = $(window).height();
	height            = height - (footerHeight + topMenuHeight);
	
	$("#content-body").css('min-height', height + "px");
	
	//console.log("window.height: " + $(document).height());
	
};

$.AppContext.onload(function(){			

	$.AppContext.eventListeners.addListener('local-link-load',{
		
		fireEvent : function($e){
			
			if($e.sourceID === 'utils.enableActions' && $e.type === 'after'){
				
				$.AppContext.sidebar.apply($e.data.local);
				
				$($e.data.local + " img").on("load", function(){
					$.AppContext.sidebar.resizeContent();
				});
				
			}
		}
	
	});
	
	$.AppContext.addLoadListener("local-link-load", "^.*", {
		
		after: function(){
			$.AppContext.local.resizeContent();
			$("img").on("load", function(){
				$.AppContext.local.resizeContent();
			});
		}
	
	});

	$(window).resize(function(){
		//console.log("window.height: " + $(window).height());
		$.AppContext.local.resizeContent();
	});
	
	$.AppContext.local.resizeContent();
	
});