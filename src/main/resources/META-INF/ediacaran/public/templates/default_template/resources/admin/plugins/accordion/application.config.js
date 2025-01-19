$.AppContext.types.components.accordion = {};

/******************************************************************/
/* Accordion Type                                                 */
/******************************************************************/

$.AppContext.types.components.accordion.Accordion = function($obj){
	this.obj = $obj;
};

$.AppContext.types.components.accordion.Accordion.prototype.select = function($id){
	
	$(this.obj).find(".card").each(function(){
		var $card = $(this);

		if($card.attr("id") == $id){
			$card.find("[data-toggle='collapse']").click();
		}

	});
	
};

$.AppContext.types.components.accordion.Accordion.prototype.setEnabled = function($id, $value){
	
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

$.AppContext.types.components.accordion.Accordion.prototype.isEnabled = function($id){
	
	$(this.obj).find(".card").each(function(){
		var $card = $(this);

		if($card.attr("id") == $id){
			return $card.find("[data-toggle='collapse']").hasClass("disabled");
		}

	});
	
	return false;
};

$.AppContext.types.components.accordion.Accordion.prototype.getItem = function($id){
	
	var $result = null;
	var $localAccordion = this.obj;
	
	$($localAccordion).find(".card").each(function(){
		var $card = $(this);

		if($card.attr("id") == $id){
			$result = new $.AppContext.types.components.accordion.AccordionItem($card, $localAccordion);
		}

	});

	return $result;
		
};

$.AppContext.types.components.accordion.Accordion.prototype.getItens = function(){
	
	var $result = [];
	
	$(this.obj).find(".card").each(function(){
		var $card = $(this);
		$result.push(new $.AppContext.types.components.accordion.AccordionItem($card, this.obj));
	});

	return $result;
		
};

/******************************************************************/
/* Accordion-Item Type                                            */
/******************************************************************/


$.AppContext.types.components.accordion.AccordionItem = function($item, $accordion){
	this.obj = $item;
	this.accordion = $accordion;
};

$.AppContext.types.components.accordion.AccordionItem.prototype.select = function(){
	this.obj.find("[data-toggle='collapse']").click();
};

$.AppContext.types.components.accordion.AccordionItem.prototype.setTitle = function($value){
	this.obj.find("#" + this.obj.attr("id") + "_title").html($value);
};

$.AppContext.types.components.accordion.AccordionItem.prototype.setEnabled = function($value){

	if($value){
		this.obj.find("[data-toggle='collapse']").removeClass("disabled");
	}
	else{
		this.obj.find("[data-toggle='collapse']").addClass("disabled");
	}
	
};

$.AppContext.types.components.accordion.AccordionItem.prototype.isEnabled = function(){
	return this.obj.find("[data-toggle='collapse']").hasClass("disabled");
};

$.AppContext.types.components.accordion.AccordionItem.prototype.getNext = function(){
	
	var $result = null;
	var $selectNext = false;
	var $localAccordion = this.accordion;
	var $localItem = this.obj;
	
	$($localAccordion).find(".card").each(function(){
		
		var $card = $(this);
		
		if($selectNext && $result == null){
			$result =  new $.AppContext.types.components.accordion.AccordionItem($card, $localAccordion); 
		}

		if($card.attr("id") == $localItem.attr("id")){
			$selectNext = true;
		}
	});

	return $result;	
};

$.AppContext.types.components.accordion.AccordionItem.prototype.getPrevious = function(){

	var $result = null;
	var $previous = null;
	var $localAccordion = this.accordion;
	var $localItem = this.obj;
	
	$($localAccordion).find(".card").each(function(){
		var $card = $(this);
		
		if($card.attr("id") == $localItem.attr("id") && $result == null){
			$result = $previous;
		}
		
		$previous = new $.AppContext.types.components.accordion.AccordionItem($card, $localAccordion); 

	});

	return $result;

};

$.AppContext.onload(function(){			

$.AppContext.types.registerType(
	'accordion', 
	{
		type: $.AppContext.types.components.accordion.Accordion,
		accept : function ($e){
			return $e.getAttribute("component-type") == "accordion";
		}
	}
);
	
});