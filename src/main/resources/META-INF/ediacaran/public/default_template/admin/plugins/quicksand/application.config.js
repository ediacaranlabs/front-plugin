$.AppContext.quicksand = {};

$.AppContext.onload(function(){
	
	$.AppContext.addLoadListener("quicksand-link-load", "^.*", {
		
		after: function(){
			/*
			var $data = $(" .light-gallery").clone();
			
			$('.filter li').click(function(e) {
				
				$(".filter li").removeClass("active");	

				var filterClass=$(this).attr('class').split(' ').slice(-1)[0];
				
				if (filterClass == 'all') {
					var $filteredData = $data.find('.item-thumbs');
				} else {
					var $filteredData = $data.find('.item-thumbs[data-type=' + filterClass + ']');
				}
				
				$(" .light-gallery").quicksand($filteredData, {
					duration: 600,
					adjustHeight: 'auto'
				}, function () {
				});	
				
				$(this).addClass("active"); 			
				return false;
			});
			*/
		}
	
	});
	
	
});

