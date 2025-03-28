
$.AppContext.dataTable = {};

$.AppContext.dataTable.applyPages = function($dataTableObj,	$dataTableResult, $page, $totalPages, $hasNextPage = false){
	
	var $id = $dataTableObj.attr("id");
	
	$dataTableResult.find("div[class*=dataTablePagination]").each(function() {
		var $e = $(this);
		
		$e.html("");

		if($totalPages < 0){
			$e.append($('<a href="#' + $id + '" class="' + ($page <= 1? 'deactive' : '') + ' back-page">&laquo;</a>'));
			$e.append($('<span>...</span>'));
			$e.append($('<a href="#' + $id + '" class="' + (!$hasNextPage? 'deactive' : '') + ' next-page">&raquo;</a>'));
		}
		else
		if($totalPages < $page){
			return;
		}
		else
		if($totalPages == null || $totalPages == 0){
			return;
		}
		else
		if($totalPages <= 10){
			
			for($i=1;$i<$totalPages;$i++){

				if($i == $page){
					$e.append($('<a href="#' + $id + '" class="active">' + $i + '</a>'));
				}
				else{
					$e.append($('<a href="#' + $id + '">' + $i + '</a>'));
				}

			}
			
		}
		else
		if($page <= 3){
			var $i;
			
			for($i=1;$i<=4;$i++){

				if($i == $page){
					$e.append($('<a href="#' + $id + '" class="active">' + $i + '</a>'));
				}
				else{
					$e.append($('<a href="#' + $id + '">' + $i + '</a>'));
				}

			}

			$e.append($('<span>...</span>'));
			
			$e.append($('<a href="#' + $id + '">' + ($totalPages - 3) + '</a>'));
			$e.append($('<a href="#' + $id + '">' + ($totalPages - 2) + '</a>'));
			$e.append($('<a href="#' + $id + '">' + ($totalPages - 1) + '</a>'));
			$e.append($('<a href="#' + $id + '">' + $totalPages + '</a>'));
		}
		else
		if($totalPages - $page < 3){
			var $i;
			
			$e.append($('<a href="#' + $id + '">1</a>'));
			$e.append($('<a href="#' + $id + '">2</a>'));
			$e.append($('<a href="#' + $id + '">3</a>'));
			$e.append($('<a href="#' + $id + '">4</a>'));
			$e.append($('<span>...</span>'));
			
			for($i=$totalPages - 3;$i<=$totalPages;$i++){

				if($i == $page){
					$e.append($('<a href="#' + $id + '" class="active">' + $i + '</a>'));
				}
				else{
					$e.append($('<a href="#' + $id + '">' + $i + '</a>'));
				}

			}
		}
		else{
			
			$e.append($('<a href="#' + $id + '">1</a>'));
			if($page - 3 != 1){
				$e.append($('<span>...</span>'));
			}
			$e.append($('<a href="#' + $id + '">' + ($page - 2) + '</a>'));
			$e.append($('<a href="#' + $id + '">' + ($page - 1) + '</a>'));
			$e.append($('<a href="#' + $id + '" class="active">' + $page + '</a>'));
			$e.append($('<a href="#' + $id + '">' + ($page + 1) + '</a>'));
			$e.append($('<a href="#' + $id + '">' + ($page + 2) + '</a>'));
			if($page + 3 != $totalPages){
				$e.append($('<span>...</span>'));
			}
			$e.append($('<a href="#' + $id + '">' + $totalPages + '</a>'));
			
		}
		
		$e.find("a").each(function() {
			var $lnk = $(this);
			$lnk.click(function(){
				var $p = $(this).html();

				if($(this).hasClass("back-page")){
					var $pageVal = parseInt($("#" + $id + " input[name=page]").val()) - 1;
					if($pageVal <= 1){
						$("#" + $id + " input[name=page]").val("1");	
					}
					else{
						$("#" + $id + " input[name=page]").val($pageVal);
					}
				}
				else
				if($(this).hasClass("next-page")){
					var $pageVal = parseInt($("#" + $id + " input[name=page]").val()) + 1;
					$("#" + $id + " input[name=page]").val($pageVal);
				}
				else{
		    		$("#" + $id + " input[name=page]").val($p);
				}
				
	    		$("#" + $id).submit();
				
			});
		});
		
	});
};

$.AppContext.dataTable.apply = function($id, $template){
	
	var $dataTableObj = $( "#" + $id );
	var $dataTableResult = $( "#" + $id + "_result" );
	var $dataTableResultPagination = $dataTableResult.children("div[class*=dataTablePagination]");

	$dataTableObj.find("button[type=submit]").each(function() {
		var $e = $(this);
		$e.off("click");
		$e.click(function(){
			$("#" + $id + " input[name=page]").val("1");
		})
	});

	$($dataTableObj).removeAttr('onsubmit');
	$($dataTableObj).unbind('submit');
	$($dataTableObj).submit(function (event) {
		
    	event.preventDefault();

    	var $resource = $dataTableObj.attr("action");
    	$dataTableObj.attr("enctype", "json");
    	$dataTableObj.attr("method", "POST");
    	//var $data = new FormData($dataTableObj[0]);
    	//var $request = Object.fromEntries($data.entries());

	  	$dataTableObj.addClass("disabled");
	  	$dataTableResult.addClass("disabled");

		var $formObj = new $.AppContext.types.Form($dataTableObj);
		$formObj.submit(
			true, 
			$resource,
			null, 
			function ($response){
				var $tag = $template($response);
				$tag = $($tag);
				
				$tag.each(function() {
					$(this).addClass("dataTableRow");
				});

		    	$dataTableResult.find("[class*=dataTableRow]").each(function() {
					var $dta = $(this);
					$dta.remove();
				});

				$tag.insertBefore($dataTableResultPagination);
				$.AppContext.utils.enableActions($dataTableResult.attr("id"));
			  	$.AppContext.dataTable.applyPages(
			  			$dataTableObj,
			  			$dataTableResult,
			  			$response.page, 
			  			$response.maxPages,
			  			$response.hasNextPage);
			  	
			  	$dataTableObj.removeClass("disabled");
			  	$dataTableResult.removeClass("disabled");
			},
			function ($response){
			  	$dataTableObj.removeClass("disabled");
			  	$dataTableResult.removeClass("disabled");
			}
		);
		
	});				    		
	
};
				    	