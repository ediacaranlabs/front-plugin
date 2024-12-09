$.AppContext.tabs = {};

/******************************************************************/
/* Tabs Type                                                      */
/******************************************************************/

$.AppContext.tabs.Type = function($obj){
	this.obj = $obj;
};

$.AppContext.tabs.Type.prototype.select = function($id){
	
	$(this.obj).find("[role='tab']").each(function(){
		var $tab = $(this);
		if($tab.attr("id") == $id){
			$tab.click();
		}

	});
	
};

$.AppContext.tabs.Type.prototype.setEnabled = function($id, $value){
	
	$(this.obj).find("[role='tab']").each(function(){
		var $tab = $(this);

		if($tab.attr("id") == $id){
			if($value){
				$tab.removeClass("disabled");
			}
			else{
				$tab.addClass("disabled");
			}
		}

	});
	
};

$.AppContext.tabs.Type.prototype.isEnabled = function($id){
	
	$(this.obj).find("[role='tab']").each(function(){
		var $tab = $(this);

		if($tab.attr("id") == $id){
			return $tab.hasClass("disabled");
		}

	});
	
	return false;
};

$.AppContext.tabs.Type.prototype.getItem = function($id){
	
	var $result = null;
	
	$(this.obj).find("[role='tab']").each(function(){
		var $tab = $(this);

		if($tab.attr("id") == $id){
			$result = new $.AppContext.tab.Type($tab, this.obj); 
		}

	});

	return $result;
		
};

$.AppContext.tabs.Type.prototype.getItens = function(){
	
	var $result = [];
	
	$(this.obj).find("[role='tab']").each(function(){
		var $tab = $(this);
		$result.push(new $.AppContext.tab.Type($tab, this.obj));
	});

	return $result;
		
};

/******************************************************************/
/* Tab Type                                                       */
/******************************************************************/

$.AppContext.tab = {};

$.AppContext.tab.Type = function($item, $tabs){
	this.obj = $item;
	this.tabs = $tabs;
};

$.AppContext.tab.Type.prototype.select = function(){
	this.obj.click();
};

$.AppContext.tab.Type.prototype.setEnabled = function($value){

	if($value){
		this.obj.removeClass("disabled");
	}
	else{
		this.obj.addClass("disabled");
	}
	
};

$.AppContext.tab.Type.prototype.isEnabled = function(){
	return this.obj.hasClass("disabled");
};


$.AppContext.onload(function(){			

$.AppContext.types.registerType(
	'tabs', 
	{
		type: $.AppContext.tabs.Type,
		accept : function ($e){
			return $e.getAttribute("component-type") == "tabs";
		}
	}
);
	
});