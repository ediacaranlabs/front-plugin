jQuery(function ($) {

	$( ".navbar-toggler" ).each(function() {
		var $dataTarget = $(this).attr("data-target");
		
		if($(this).parents("section[class='sidebar']").length > 0){
			
			$(this).click(function(){
				var $root = $($dataTarget).parents("section[class='sidebar']");
				if($($dataTarget).hasClass("show")){
					$root.find("nav").removeClass("show");
					$root.find(".content").removeClass("show");
					
					//$($dataTarget).parent().removeClass("show");
					//$($dataTarget).find(".content").first().removeClass("show");
				}
				else{
					$root.find("nav").addClass("show");
					$root.find(".content").addClass("show");

					//$($dataTarget).parent().addClass("show");
					//$($dataTarget).find(".content").first().addClass("show");
				}
			});
		}
	});
	
});