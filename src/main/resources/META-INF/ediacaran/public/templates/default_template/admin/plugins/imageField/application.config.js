$.AppContext.imageField = {};

$.AppContext.imageField.apply = function ($img){
	
};

$.AppContext.imageField.apply = function ($fileField, $width, $height, $type){
	
	var $root = null;
	var $croppie = null;
	var $croppieObj = $($croppie).croppie({
		viewport: {
			width: $width,
			height: $height,
			type: $type
		},
		boundary: {
			width: $width + 60,
			height: $height + 60
		}
	});
	
	$($fileField).on('change', function(){
		
		$url = null;
		
		$croppieObj.croppie('bind', {
			url: $url,
		});

	});
};
