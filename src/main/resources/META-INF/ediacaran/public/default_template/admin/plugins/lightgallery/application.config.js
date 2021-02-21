$.AppContext.lightgallery = {};

$.AppContext.onload(function(){
	
	$.AppContext.addLoadListener("gallery-link-load", "^.*", {
		
		after: function(){
			$($.AppContext.vars.painel + " .light-gallery").lightGallery();
		}
	
	});
	
	
});

