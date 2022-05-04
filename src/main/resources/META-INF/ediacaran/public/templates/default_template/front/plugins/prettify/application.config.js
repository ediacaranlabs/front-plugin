$.AppContext.prettify = {};

$.AppContext.onload(function(){			

	$.AppContext.addLoadListener("pretty-link-load", "^.*", {
		
		after: function(){
			PR.prettyPrint();
		}
	});
	
});