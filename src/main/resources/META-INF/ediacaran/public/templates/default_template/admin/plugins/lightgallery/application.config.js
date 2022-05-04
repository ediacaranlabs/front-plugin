$.AppContext.lightgallery = {};

$.AppContext.onload(function(){
	
	$.AppContext.addLoadListener("gallery-link-load", "^.*", {
		
		after: function(){
			
			$($.AppContext.vars.painel + " .light-gallery").each(function() {

				var $gallery = $(this);
				var $data    = $gallery.clone();
				var $filters = $("#" + $gallery.attr("gallery") + "_filters");
				
				$($gallery).lightGallery();
				
				$('li', $filters).click(function($e) {
					
					$("li", $filters).removeClass("active");
					
					var filterClass = $(this).attr('class').split(' ').slice(-1)[0];

					if (filterClass == 'all') {
						var $filteredData = $data.find('.item-thumbs');
					}
					else{
						var $filteredData = $data.find('.item-thumbs[data-type=' + filterClass + ']');
					}
					
					$($gallery).quicksand($filteredData, {
						duration: 600,
						adjustHeight: 'auto'
					}, function () {
						var ins = $gallery.first().data('lightGallery');
						ins.destroy(true);
						$($gallery).lightGallery();
					});	
					
					$(this).addClass("active"); 			
					
					return false;
				});
				
			});
		}
	});
	
	
});