$.AppContext.imageField = {};

$.AppContext.imageField.apply = function ($id, $name, $width, $height, $type, $default){
	
	var $root      = $('#' + $id)
	var $croppie   = $root.find(".croppie-image");
	var $button    = $('#' + $id + "_button");//$root.find("button[type='button']");
	var $fileField = $root.find("input[type='file']");
	var $border    = $root.find("input[name='" + $name + ".border']");
	
	$border.val($type);
	 
	$croppie.width($width);
	$croppie.height($height);

	$root.width($($croppie).outerWidth());
	$root.height('auto');
	
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

	setTimeout(function(){
		$croppieObj.croppie(
				'bind',{
					url: $default
		});
		
	},
	500);
	
	$croppieObj.on('update.croppie', function(ev, cropData) {
		
		var $x1 = $($root).find("input[name='" + $name + ".x1']");
		var $x2 = $($root).find("input[name='" + $name + ".x2']");
		var $y1 = $($root).find("input[name='" + $name + ".y1']");
		var $y2 = $($root).find("input[name='" + $name + ".y2']");

		$x1.val(cropData.points[0]);
		$y1.val(cropData.points[1]);
		
		$x2.val(cropData.points[2]);
		$y2.val(cropData.points[3]);

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