$.AppContext.autocomplete = {};

$.AppContext.autocomplete.apply = function($resource, $fieldID, $fieldListID){
	
	var $field     = $( "#" + $fieldID );
	var $fieldList = $( "#" + $fieldListID );
	
	$fieldList.hide();
	$fieldList.css('max-width', $field.outerWidth() + "px");
	
	$field.addClass('autocomplete-field');
	$field.attr("select-data", $fieldList.attr("id"));
	
	$field
	.on( "resize", function() {
		$fieldList.css('max-width', $field.outerWidth() + "px");
	})								
	.on("input", function(){

		$.AppContext.autocomplete.search(
			$resource,
			{ "value" : $field.val() },
			$field,
			$fieldList
		);

	})
	.on("click", function(){

		$.AppContext.autocomplete.search(
			$resource,
			{ "value" : $field.val() },
			$field,
			$fieldList
		);

	})
	.on("focusout", function(){
			setTimeout(function(){
				$fieldList.hide();
			}, 500);
	})
	.hover(
		function(){
			$field.addClass('hover-autocomplete');
		}, 
		function(){
			$field.removeClass('hover-autocomplete');
		}
	);
	
	$field.after($fieldList);
};

$.AppContext.autocomplete.search = function($resource, $request, $field, $fieldList){

	$.AppContext.utils.postJson(
			$resource,
			$request,
			function(obj){
				
				$fieldList.empty();

				if(obj.length == 0 ){
					$fieldList.hide();
					return;
				}
				else{
					
					if(obj.length > 4 ){
						$fieldList.attr("size", 4);
					}
					else
					if(obj.length < 2 ){
						$fieldList.attr("size", 2);
					}
					else{
						$fieldList.attr("size", obj.length);
					}
					
					$fieldList.show();
				}
				
				//update values
				for (let i in obj) {
					$fieldList.append( "<li value=\"" + obj[i].value + "\">" + obj[i].label + "</li>" );
				}
				
				$field.hover(function(){
					$field.addClass('hover-autocomplete');
				}, function(){
					$field.removeClass('hover-autocomplete');
				});
			
				
				$fieldList.find("li")
				.each(function() {
					
					$(this).click(function(){
						
						var $evt = {
				    		sourceID: "autocomplete.change",
				    		type: "before",
				    		data: {
								id: $(this).attr("id"),
								name: $(this).attr("name"),
								value: $(this).attr("value")
				        	}
					    };
			    		$.AppContext.eventListeners.fireEvent($evt);
			    								
						$field.val($evt.data.value);
						$fieldList.hide();
						
						var $evt = {
				    		sourceID: "autocomplete.change",
				    		type: "after",
				    		data: {
								id: $(this).attr("id"),
								name: $(this).attr("name"),
								value: $(this).attr("value")
				        	}
					    };
			    		$.AppContext.eventListeners.fireEvent($evt);
						
					})
					.hover(function(){
						
						$(this).addClass('select-autocomplete');
						
					}, function(){
						
						$fieldList.find("li")
						.each(function() {
							$(this).removeClass('select-autocomplete');
						});

					});
					
				});
				
			}
	);
	
};
							