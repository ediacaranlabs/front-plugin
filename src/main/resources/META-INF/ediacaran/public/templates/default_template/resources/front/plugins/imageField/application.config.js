$.AppContext.imageField = {};

$.AppContext.imageField.resizeQueue = new Array();

$.AppContext.imageField.resizeQueueProccess = function(){
	
	var $fTmp = {};

	while($.AppContext.imageField.resizeQueue.length > 0){
		var $obj = $.AppContext.imageField.resizeQueue.shift();
		$fTmp[$obj.id] = $obj.func;
		$exist = true;
	}
	
	for (var $id in $fTmp) {
		try{
			$fTmp[$id]();
		}
		catch($e){
		}	
	}

	setTimeout(function(){$.AppContext.imageField.resizeQueueProccess()}, 50);
};
	 
setTimeout($.AppContext.imageField.resizeQueueProccess(), 300);

$.AppContext.imageField.resize = function($url, $width, $height, $type, $root, $croppieObj, $croppie){
	
	var $objRoot = $.AppContext.utils.getById($root.attr("id"));
	
	if($objRoot == null){
		return;
	}
	
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
		
		console.log("resize: " + JSON.stringify($opts));

	};
	
	$.AppContext.imageField.resizeQueue.push({ func: $f, id: $root.attr("id") });
		
};

$.AppContext.imageField.show = function($url, $width, $height, $type, $root, $croppieObj, $croppie){
	
	var $objRoot = $.AppContext.utils.getById($root.attr("id"));
	
	if($objRoot == null){
		return;
	}
	
	var $croppieWidth = $($root).width();
	var $croppieHeight = $($root).width()*($height/$width);

	var $f = function(){

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
		});
		
		console.log("show: " + JSON.stringify($croppieObj.croppie('get')));

	};
	
	$.AppContext.imageField.resizeQueue.push({ func: $f, id: $root.attr("id") });
		
};

$.AppContext.imageField.apply = function ($id, $name, $width, $height, $type, $default){
	
	var $root      = $('#' + $id)
	var $croppie   = $root.find(".croppie-image");
	var $button    = $('#' + $id + "_button");//$root.find("button[type='button']");
	var $fileField = $root.find("input[type='file']");
	var $fieldName = $fileField.attr("name");
	var $border    = $root.find("input[name='" + $name + ".border']");
	var $url       = $default;
	
	$fieldName = $fieldName.substring(0, $fieldName.length - '.file'.length);
	 	
	$border.val($type);
	 
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

	$croppieObj.on('update.croppie', function(ev, cropData) {
		
		var $x1 = $($root).find("input[name='" + $fieldName + ".x1']");
		var $x2 = $($root).find("input[name='" + $fieldName + ".x2']");
		var $y1 = $($root).find("input[name='" + $fieldName + ".y1']");
		var $y2 = $($root).find("input[name='" + $fieldName + ".y2']");

		$x1.val(cropData.points[0]);
		$y1.val(cropData.points[1]);
		
		$x2.val(cropData.points[2]);
		$y2.val(cropData.points[3]);

		$opts = $croppieObj.croppie('get');
		console.log("zoom: " + JSON.stringify($opts));
	});


	$($fileField).on('change', function(event){
		$url = URL.createObjectURL(event.target.files[0]);

		$croppieObj.croppie('bind', {
			url: $url
		});
		console.log("change");
	});
	
	$button.click(function(){
		$fileField.click();
	});
	
	
	var $enableResize = false;
	
	var $show = function(){
		
		var $objRoot = $.AppContext.utils.getById($id);
		
		console.log("root: " + $objRoot);
		
		if($objRoot == null){
			return;
		}
		
		var $invisibleObj = $objRoot.getFirstParent(function($e){
			return !$e.isVisible();
		});

		console.log("inv: " + $invisibleObj);
		
		if($invisibleObj != null){
			console.log("inv: " + $invisibleObj.getAttribute("id"));
			setTimeout(function(){$show()}, 500);
		}
		else{
			$.AppContext.imageField.show($url, $width, $height, $type, $root, $croppieObj, $croppie);
			setTimeout(function(){
				$enableResize = true;
				console.log("show: " + $id);
			},200);
		}
		
	}
	
	$croppie.show(function(){
		setTimeout(function(){$show();}, 200);
	});

	
	$root.bind('resize', function(){

		if(!$enableResize){
			return;
		}
		
		$.AppContext.imageField.resize($url, $width, $height, $type, $root, $croppieObj, $croppie);

    });
	
	
	var $objRoot = $.AppContext.utils.toObject($root);
	
	var $invisibleObj = $objRoot.getFirstParent(function($e){
		return !$e.isVisible();
	});
	
	if($invisibleObj){
		
	}
};