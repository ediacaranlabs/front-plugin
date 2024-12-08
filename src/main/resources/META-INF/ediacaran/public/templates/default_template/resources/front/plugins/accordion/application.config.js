$.AppContext.accordion = {};

/******************************************************************/
/* Accordion Type                                                 */
/******************************************************************/

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

$.AppContext.accordion.Type.prototype.getItem = function($id){
	
	$(this.obj).find(".card").each(function(){
		var $card = $(this);

		if($card.attr("id") == $id){
			return new $.AppContext.accordion_item.Type($card, this.obj); 
		}

	});

	return null;
		
};

$.AppContext.accordion.Type.prototype.getItens = function(){
	
	var $result = [];
	
	$(this.obj).find(".card").each(function(){
		var $card = $(this);
		$result.push(new $.AppContext.accordion_item.Type($card, this.obj));
	});

	return $result;
		
};

/******************************************************************/
/* Accordion-Item Type                                            */
/******************************************************************/

$.AppContext.accordion_item = {};

$.AppContext.accordion_item.Type = function($item, $accordion){
	this.obj = $item;
	this.accordion = $accordion;
};

$.AppContext.accordion_item.Type.prototype.select = function(){
	this.obj.find("[data-toggle='collapse']").click();
};

$.AppContext.accordion_item.Type.prototype.setTitle = function($value){
	this.obj.find("#" + this.obj.attr("id") + "_title").html($value);
};

$.AppContext.accordion_item.Type.prototype.setEnabled = function($value){

	if($value){
		this.obj.find("[data-toggle='collapse']").removeClass("disabled");
	}
	else{
		this.obj.find("[data-toggle='collapse']").addClass("disabled");
	}
	
};

$.AppContext.accordion_item.Type.prototype.isEnabled = function(){
	return this.obj.find("[data-toggle='collapse']").hasClass("disabled");
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