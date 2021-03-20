$.AppContext.lightgallery = {};

$.AppContext.onload(function(){
	
	$.AppContext.addLoadListener("gallery-link-load", "^.*", {
		
		after: function(){
			
			var $data = $($.AppContext.vars.painel + " .light-gallery").clone();
			
			$('.filter li').click(function(e) {
				
				$(".filter li").removeClass("active");	

				var filterClass=$(this).attr('class').split(' ').slice(-1)[0];
				
				if (filterClass == 'all') {
					var $filteredData = $data.find('.item-thumbs');
				} else {
					var $filteredData = $data.find('.item-thumbs[data-type=' + filterClass + ']');
				}
				
				$($.AppContext.vars.painel + " .light-gallery").quicksand($filteredData, {
					duration: 600,
					adjustHeight: 'auto'
				}, function () {
				});	
				
				$(this).addClass("active"); 			
				return false;
			});
			
		}
	
	});
	
	
});

