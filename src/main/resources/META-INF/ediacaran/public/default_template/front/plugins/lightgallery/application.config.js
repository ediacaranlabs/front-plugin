$.AppContext.lightgallery = {};

$.AppContext.onload(function(){
	
	$.AppContext.addLoadListener("gallery-link-load", "^.*", {
		
		after: function(){
			$(" .light-gallery").lightGallery();
		}
	
	});
	
	
});

