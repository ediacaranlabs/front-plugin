$.AppContext.accordion = {};

$.AppContext.accordion.Type = function($obj){
	this.obj = $obj;
};

$.AppContext.accordion.Type.prototype.select = function($id){
	
	$(this.obj).find(".card").each(function(){
		var $card = $(this);

		if($card.attr("id") == $id){
			$card.find("[data-toggle='collapse']").click();
		}

	});
	
};

$.AppContext.accordion.Type.prototype.setEnabled = function($id, $value){
	
	$(this.obj).find(".card").each(function(){
		var $card = $(this);

		if($card.attr("id") == $id){
			if($value){
				$card.find("[data-toggle='collapse']").removeClass("disabled");
			}
			else{
				$card.find("[data-toggle='collapse']").addClass("disabled");
			}
		}

	});
	
};

$.AppContext.accordion.Type.prototype.isEnabled = function($id){
	
	$(this.obj).find(".card").each(function(){
		var $card = $(this);

		if($card.attr("id") == $id){
			return $card.find("[data-toggle='collapse']").hasClass("disabled");
		}

	});
	
	return false;
};

$.AppContext.onload(function(){			

$.AppContext.types.registerType(
	'accordion', 
	{
		type: $.AppContext.accordion.Type,
		accept : function ($e){
			return $e.getAttribute("component-type") == "accordion";
		}
	}
);
	
});