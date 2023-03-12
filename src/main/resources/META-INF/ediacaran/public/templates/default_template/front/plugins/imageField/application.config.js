$.AppContext.imageField = {};

$.AppContext.imageField.apply = function ($root, $fieldName, $width, $height, $type, $default){
	
	var $croppie   = $root.find(".croppie-image");
	var $button    = $root.find("button");
	var $fileField = $root.find("input[type='file']");
	
	$root.width($width);
	$root.height($height);
	
	var $croppieObj = $($croppie).croppie({
		viewport: {
			width: $width,
			height: $height,
			type: $type
		},
		boundary: {
			width: $width,
			height: $height
		},
		showZoomer: false
	});

	$croppieObj.croppie(
			'bind',{
				url: $default
	});
	
	$croppieObj.on('update.croppie', function(ev, cropData) {
		
		var $x1 = $($root).find("input[name='" + $fieldName + ".x1']");
		var $x2 = $($root).find("input[name='" + $fieldName + ".x2']");
		var $y1 = $($root).find("input[name='" + $fieldName + ".y1']");
		var $y2 = $($root).find("input[name='" + $fieldName + ".y2']");

		$x1.val(cropData.points[0]);
		$y1.val(cropData.points[1]);
		
		$x2.val(cropData.points[2]);
		$y2.val(cropData.points[3]);

		/*
		console.log(
				 "x1=" + cropData.points[0] +
				",y1=" + cropData.points[1] +
				",x2=" + cropData.points[2] +
				",y2=" + cropData.points[3]
		);
		*/
		
	});

	$($fileField).on('change', function(event){
		var $url = URL.createObjectURL(event.target.files[0]);
		
		$croppieObj.croppie('bind', {
			url: $url,
		});
		
	});
	
	$button.click(function(){
		$fileField.click();
	});
	
};