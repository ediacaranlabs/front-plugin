$.AppContext.imageField = {};

$.AppContext.imageField.resizeQueue = new Array();

$.AppContext.imageField.resizeQueueProccess = function(){
	
	/*
	while($.AppContext.imageField.resizeQueue.length > 0){
		var $fTmp = $.AppContext.imageField.resizeQueue.shift();
		$fTmp();
	}
	*/
	
	var $fTmp = {};
	var $exist = false;

	while($.AppContext.imageField.resizeQueue.length > 0){
		var $obj = $.AppContext.imageField.resizeQueue.shift();
		$fTmp[$obj.id] = $obj.func;
		$exist = true;
	}
	
	for (var $id in $fTmp) {
		$fTmp[$id]();
	}

	/*if($exist){
		setTimeout(function(){$.AppContext.imageField.resizeQueueProccess()}, 1);
	}
	else{*/
		setTimeout(function(){$.AppContext.imageField.resizeQueueProccess()}, 50);
	//}
		
	/*
	if($.AppContext.imageField.resizeQueue.length > 0){
		var $fTmp = $.AppContext.imageField.resizeQueue.shift();
		$fTmp();
		setTimeout(function(){$.AppContext.imageField.resizeQueueProccess()}, 10);
	}
	else{
		setTimeout(function(){$.AppContext.imageField.resizeQueueProccess()}, 100);
	}
	*/
};
	 
setTimeout($.AppContext.imageField.resizeQueueProccess(), 300);

$.AppContext.imageField.apply = function ($id, $name, $width, $height, $type, $default){
	
	var $root      = $('#' + $id)
	var $croppie   = $root.find(".croppie-image");
	var $button    = $('#' + $id + "_button");//$root.find("button[type='button']");
	var $fileField = $root.find("input[type='file']");
	var $border    = $root.find("input[name='" + $name + ".border']");
	var $url       = $default;
	
	$border.val($type);
	 
	//$croppie.width($width);
	//$croppie.height($height);

	var $croppieWidth = $($root).width();
	var $croppieHeight = $($root).width()*($height/$width);
	
	//$croppie.width($croppieWidth);
	//$croppie.height($croppieHeight);

	//$root.width($($croppie).outerWidth());
	$root.height('auto');
	
	var $croppieObj = $($croppie).croppie({
		viewport: {
			width: $croppieWidth,
			height: $croppieHeight,
			type: $type
		},
		boundary: {
			width: $croppieWidth,
			height: $croppieHeight
		},
		showZoomer: false
	});

	/*
	setTimeout(function(){
		$croppieObj.croppie(
				'bind',{
					url: $default
		});
		
	},
	500);
	*/
	
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
		$url = URL.createObjectURL(event.target.files[0]);
		
		$croppieObj.croppie('bind', {
			url: $url,
		});
		
	});
	
	$button.click(function(){
		$fileField.click();
	});
	
	$croppieObj.croppie(
			'bind',{
				url: $url
	});

	$root.show('slow', function(){
		
		setTimeout(function(){
			
			var $croppieWidth = $($root).width();
			var $croppieHeight = $($root).width()*($height/$width);
			
			$croppieObj.croppie(
					'bind',{
						viewport: {
							width: $croppieWidth,
							height: $croppieHeight,
							type: $type
						},
						boundary: {
							width: $croppieWidth,
							height: $croppieHeight
						},
						url: $url
			});
			
		},
		100);
		
		console.log("show croppie: " + $id);
	});

	$root.bind('resize', function(){

		var $croppieWidth = $($root).width();
		var $croppieHeight = $($root).width()*($height/$width);

		var $f = function(){

			$opts = $croppieObj.croppie('get');
				
			var $croppiePoints      = $opts.points;
			var $croppieZoom        = $opts.zoom;
			var $croppieOrientation = $opts.orientation;
			
			$croppieObj.croppie('destroy');

			$croppieObj = $($croppie).croppie({
					viewport: {
						width: $croppieWidth,
						height: $croppieHeight,
						type: $type
					},
					boundary: {
						width: $croppieWidth,
						height: $croppieHeight
					},
					showZoomer: false
				});
			
			$croppieObj.croppie(
					'bind',{
						url: $url,
						points: $croppiePoints, 
						orientation: $croppieZoom, 
						zoom: $croppieOrientation
			});
			
			console.log("resize croppie: " + $id + " > " + $croppieWidth + "x" + $croppieHeight);
			
		};
		
		$.AppContext.imageField.resizeQueue.push({ func: $f, id: $id });
    });
	
};