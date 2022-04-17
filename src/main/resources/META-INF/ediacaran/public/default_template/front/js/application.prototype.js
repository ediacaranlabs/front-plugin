$.AppContext.types = {};

/*---------------------------------------------------------------*/
/* Form                                                          */
/*---------------------------------------------------------------*/

$.AppContext.types.Form = function($form){
	this.form = $form;
};

$.AppContext.types.Form.prototype.submit = function(){
	$.AppContext.utils.submit(this.form);
};

$.AppContext.types.Form.prototype.getField = function(name, type = null){
	
	type = type == null? '' : type;
	
	var $field = $(this.form).find(type + '[name=' + name + ']');
	var $form = this.form;
	
	if($field.length == 0){
		return null;
	}
	
	var $map = {};
	
	$field.each(function(){
		
		$type = $(this).prop("tagName").toLowerCase();
		
		if($type == 'input'){
			$type =  $(this).attr('type');
		}
		
		$arr = $map[$type];
		
		if($arr == null){
			$arr = new Array();
			$map[$type] = $arr;
		}
		
		$arr.push($(this));
	});

	var $result = new Array();

	for (var $p in $map) {
		
		if($p == 'checkbox' || $p == 'radio'){
			$result.push(new $.AppContext.types.Field($form, name, $p));
		}
		else{
			
			for(var $i of $map[$p]){
				$result.push(new $.AppContext.types.Field($form, $i, $p));
			}
			
		}
		
	}
	
	return $result.length == 1? $result[0] : $result;
	
};

$.AppContext.types.Form.prototype.registerEvent = function(name, handler){

	$.AppContext.events.add(this.form, name, handler);

};

$.AppContext.types.Form.prototype.unregisterEvent = function(name){

	$.AppContext.events.remove(this.form, name);

};

/*---------------------------------------------------------------*/
/* Field                                                         */
/*---------------------------------------------------------------*/

$.AppContext.types.Field = function($form, $field, $type){
	this.form  = $form;
	this.field = $field;
	this.type  = $type;
};

$.AppContext.types.Field.prototype.setProperty = function(name, value){

	/*
	if(!Array.isArray(this.field)){
		$(this.field).prop(name, value);
	}
	*/
	
	if(this.type == 'checkbox' || this.type == 'radio'){
		$(this.form).find('input:' + this.type + '[name=' + this.field + ']').each(function() {
			$(this).prop(name, value);
		});
	}
	if(this.type == 'select'){
		$(this.form).find('select[name=' + this.field + ']').prop(name, value);
	}
	else{
		$(this.field).val(value);
	}
	
};

$.AppContext.types.Field.prototype.getProperty = function(name){
	
	$result = null;

	if(this.type == 'checkbox' || this.type == 'radio' || this.type == 'select'){
		$result = $(this.form).find('input' + ':' + this.type + '[name=' + this.field + ']').first().prop(name);
	}
	else
	if(this.type == 'select'){
		$result = $(this.form).find('select[name=' + this.field + ']').prop(name);
	}
	else{
		$result = $(this.field).prop(name);
	}
	
	return $result;
	
	/*
	var $result = null;
	
	if(!Array.isArray(this.field)){
		$result = $(this.field).prop(name);
	}
	
	return result;
	*/
	
};

$.AppContext.types.Field.prototype.registerEvent = function(name, handler){

	if(this.type !== 'checkbox' && this.type !== 'radio'){
		$.AppContext.events.add(this.field, name, handler);
	}

};

$.AppContext.types.Field.prototype.unregisterEvent = function(name){

	if(this.type !== 'checkbox' && this.type !== 'radio'){
		$.AppContext.events.remove(this.field, name);
	}

};

$.AppContext.types.Field.prototype.setValue = function(value){

	if(this.type == 'checkbox' || this.type == 'radio'){
		$(this.form).find('input:' + this.type + '[value=' + value + ']').prop('checked', true);
	}
	else
	if(this.type == 'select' ){
		$(this.field).find('option[value=' + value + ']').attr('selected','selected');
	}
	else{
		$(this.field).val(value);
	}
	
};

$.AppContext.types.Field.prototype.getValue = function(){

	$result = null;

	if(this.type == 'checkbox' || this.type == 'radio'){
		$result = $(this.form).find('input' + ':' + this.type + '[name=' + this.name + ']:checked').val();
	}
	else
	if(this.type == 'select' ){
		$result = $(this.field).find('option:selected').val();
	}
	else{
		$result = $(this.field).val();
	}
	
	return $result;
	
};

$.AppContext.types.Field.prototype.getOptions = function(){
	
	var $result = null;
	
	if(this.type == 'checkbox' || this.type == 'radio'){
		var $options = $(this.form).filter('input' + ':' + this.type + '[name=' + this.name + ']');

		$options.each(function(){
			$result.push(new $.AppContext.types.Option(this.form, this.field, $(this), this.type));
		});
		
	}
	else
	if(this.type === 'select'){
		var $options = $(this.field).filter('option');

		$options.each(function(){
			$result.push(new $.AppContext.types.Option(this.form, this.field, $(this), this.type));
		});
		
	}
	
	return $result;
	
};

/*---------------------------------------------------------------*/
/* Option                                                         */
/*---------------------------------------------------------------*/

$.AppContext.types.Option = function($form, $field, $option, $type){
	this.form   = $form;
	this.field  = $field;
	this.option = $option;
	this.type   = $type;
};

$.AppContext.types.Option.prototype.getDescription = function(){
	
	$result = null;
	
	if(this.type == 'option'){
		$result = $(this.option).html();
	}
	else{
		$result = $(this.form).filter('label[for=' + $(this.field).attr('id') + ']').html();
	}
	
	return $result;
};

$.AppContext.types.Option.prototype.getValue = function(){
	
	$result = $(this.option).attr('value');
	
	return $result;
	
};

$.AppContext.types.Option.prototype.setProperty = function(name, value){

	$(this.option).prop(name, value);
	
};

$.AppContext.types.Option.prototype.getProperty = function(name){
	
	var $result = $(this.option).prop(name);
	
	return result;
	
};

/*---------------------------------------------------------------*/
/* Object                                                        */
/*---------------------------------------------------------------*/

$.AppContext.types.Object = function($ref){
	this.obj = $ref;
};

$.AppContext.types.Object.prototype.setProperty = function(name, value){

	$(this.obj).prop(name, value);
	
};

$.AppContext.types.Object.prototype.getProperty = function(name){
	
	var $result = $(this.obj).prop(name);
	
	return result;
	
};

$.AppContext.types.Field.prototype.registerEvent = function(name, handler){

	$.AppContext.events.add(this.obj, name, handler);

};

$.AppContext.types.Field.prototype.unregisterEvent = function(name){

	$.AppContext.events.remove(this.obj, name);

};

$.AppContext.types._map = {};
$.AppContext.types._map['form'] = $.AppContext.types.Form;
$.AppContext.types._map['object'] = $.AppContext.types.Object;