$.AppContext.gallery = {};

$.AppContext.gallery.apply = function($id){

	var $gallery = $('#' + $id + " .light-gallery");
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
	
};
