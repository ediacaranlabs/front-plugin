$.AppContext.imageField = {};

$.AppContext.imageField.apply = function ($img){
	
};

$.AppContext.imageField.apply = function ($fileField, $width, $height, $type){
	
	var $croppie   = $root.find(".croppie-image");
	var $button    = $root.find("button");
	var $fileField = $root.find("input[type='file']");
	var $fieldName = $fileField.attr('name');
	
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
	
	$button.click(function(){
		$fileField.click();
	});
	
};
