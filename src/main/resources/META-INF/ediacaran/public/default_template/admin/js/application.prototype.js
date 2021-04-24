$.AppContext.types = {};

/*---------------------------------------------------------------*/
/* Form                                                          */
/*---------------------------------------------------------------*/

$.AppContext.types.Form = function(formID){
	this.id = formID;
};

$.AppContext.types.Form.prototype.submit = function(){
	var $form = $.AppContext.utils.getComponentById(this.id);
	$.AppContext.utils.submit($form);
};

$.AppContext.types.Form.prototype.getField = function(name){
	var $field = $('#' + this.id + ' input[name=' + name + ']');
	return $field.length? new $.AppContext.types.Field(this.id, name) : null;
};

/*---------------------------------------------------------------*/
/* Field                                                         */
/*---------------------------------------------------------------*/

$.AppContext.types.Field = function(formID, fieldName){
	this.form = formID;
	this.name = fieldName;
};

$.AppContext.types.Field.prototype.setProperty = function(name, value){

	var element = $('#' + this.form + ' [name=' + this.name + ']');
	var elementType = element.prop("tagName").toLowerCase();
	var subType = null;
	
	if(elementType === 'input'){
		subType = element.prop("type");
	}

	if(subType == null || (subType !== 'checkbox' && subType !== 'radio') ){
		$('#' + this.form + ' ' + elementType + '[name=' + this.name + ']').prop(name, value);
	}
	
};

$.AppContext.types.Field.prototype.getProperty = function(name){
	
	var element = $('#' + this.form + ' [name=' + this.name + ']');
	var elementType = element.prop("tagName").toLowerCase();
	var subType = null;
	var result = null;
	
	if(elementType === 'input'){
		subType = element.prop("type");
	}

	if(subType == null || (subType !== 'checkbox' && subType !== 'radio') ){
		result = $('#' + this.form + ' ' + elementType + '[name=' + this.name + ']').prop(name);
	}
	
	return result;
	
};

$.AppContext.types.Field.prototype.registerEvent = function(name, handler){

	var element = $('#' + this.form + ' [name=' + this.name + ']');
	var elementType = element.prop("tagName").toLowerCase();
	var subType = null;
	
	if(elementType === 'input'){
		subType = element.prop("type");
	}

	if(subType == null || (subType !== 'checkbox' && subType !== 'radio') ){
		$('#' + this.form + ' ' + elementType + '[name=' + this.name + ']').on(name, handler);
	}
	

};

$.AppContext.types.Field.prototype.unregisterEvent = function(name){

	var element = $('#' + this.form + ' [name=' + this.name + ']');
	var elementType = element.prop("tagName").toLowerCase();
	var subType = null;
	
	if(elementType === 'input'){
		subType = element.prop("type");
	}

	if(subType == null || (subType !== 'checkbox' && subType !== 'radio') ){
		$('#' + this.form + ' ' + elementType + '[name=' + this.name + ']').off(name);
	}
	
};

$.AppContext.types.Field.prototype.setValue = function(value){
	
	var element = $('#' + this.form + ' [name=' + this.name + ']');
	var elementType = element.prop("tagName").toLowerCase();
	var subType = null;
	
	if(elementType === 'input'){
		subType = element.prop("type");
	}
	
	if(subType == 'checkbox' || subType == 'radio'){
		$('#' + this.form + ' ' + elementType + ':' + subType + '[name=' + this.name + '][value=' + value + ']').prop('checked', true);
	}
	else
	if(elementType === 'select'){
		$('#' + this.form + ' '+ elementType +'[name=' + this.name + '] option[value=' + value + ']').attr('selected','selected');
	}
	else{
		$('#' + this.form + ' ' + elementType + '[name=' + this.name + ']').val(value);
	}
	
/*
 
	var element = $('#' + this.form + ' [name=' + this.name + ']');
	var elementType = element.prop("tagName").toLowerCase();
	
	if(elementType === 'input'){
		elementType = element.prop("type");
	}
	
	switch (elementType) {
		case 'radio':
		case 'checkbox':
			$('#' + this.form + ' ' + elementType + ':' + subType + '[name=' + this.name + '][value=' + value + ']').prop('checked', true);
			break;
		case 'select':
			$('#' + this.form + ' select[name=' + this.name + '] option[value=' + value + ']').attr('selected','selected');
			break;
		case 'textarea':
			$('#' + this.form + ' textarea[name=' + this.name + ']').val(value);
			break;
		default:
			$('#' + this.form + ' input[name=' + this.name + ']').val(value);
			break;
	}
*/
	
};

$.AppContext.types.Field.prototype.getValue = function(){
	
	var element = $('#' + this.form + ' [name=' + this.name + ']');
	var elementType = element.prop("tagName").toLowerCase();
	var subType = null;
	var result = null;
	
	if(elementType === 'input'){
		subType = element.prop("type");
	}
	
	if(subType == 'checkbox' || subType == 'radio'){
		result = $('#' + this.form + ' ' + elementType + ':' + subType + '[name=' + this.name + ']:checked').val();
	}
	else
	if(elementType === 'select'){
		result = $('#' + this.form + ' ' + elementType + '[name=' + this.name + '] option:selected').val();
	}
	else{
		result = $('#' + this.form + ' ' + elementType + '[name=' + this.name + ']').val();
	}
	
	return result;
	
	/*
	var element = $('#' + this.form + ' [name=' + this.name + ']');
	var elementType = element.prop("tagName").toLowerCase();
	
	if(elementType === 'input'){
		elementType = element.prop("type");
	}
	
	switch (elementType) {
		case 'radio':
			return $('#' + this.form + ' input:radio[name=' + this.name + ']:checked').val();
		case 'checkbox':
			return $('#' + this.form + ' input:checkbox[name=' + this.name + ']:checked').val();
		case 'select':
			return $('#' + this.form + ' select[name=' + this.name + '] option:selected').val();
		case 'textarea':
			return $('#' + this.form + ' textarea[name=' + this.name + ']').val();
		default:
			return $('#' + this.form + ' input[name=' + this.name + ']').val();
	}
	*/
};

$.AppContext.types.Field.prototype.getOptions = function(){
	
	var element     = $('#' + this.form + ' [name=' + this.name + ']');
	var elementType = element.prop("tagName").toLowerCase();
	var subType     = null;
	var result      = new Array();
	
	if(elementType === 'input'){
		subType = element.prop("type");
	}
	
	if(subType == 'checkbox' || subType == 'radio'){
		var options = $('#' + this.form + ' ' + elementType + ':' + subType + '[name=' + this.name + ']');

		options.each(function(){
			var value = $(this).attr("value");
			result.push( new $.AppContext.types.Option(this.form, this.name, value) );
		});
	}
	else
	if(elementType === 'select'){
		var options = $('#' + this.form + ' ' + elementType + '[name=' + this.name + '] option');
		
		options.each(function(){
			var value = $(this).attr("value");
			result.push( new $.AppContext.types.Option(this.form, this.name, value) );
		});
		
	}
	
	return result;
	
};

$.AppContext.types.Field.prototype.addOptions = function(value, description){
	
	//$("#selectList").append(new Option("option text", "value"));
	
	var element     = $('#' + this.form + ' [name=' + this.name + ']');
	var elementType = element.prop("tagName").toLowerCase();
	var subType     = null;
	var result      = new Array();
	
	if(elementType === 'input'){
		subType = element.prop("type");
	}
	
	if(subType == 'checkbox' || subType == 'radio'){
		var options = $('#' + this.form + ' ' + elementType + ':' + subType + '[name=' + this.name + ']');

		options.each(function(){
			var value = $(this).attr("value");
			result.push( new $.AppContext.types.Option(this.form, this.name, value) );
		});
	}
	else
	if(elementType === 'select'){
		var options = $('#' + this.form + ' ' + elementType + '[name=' + this.name + '] option');
		
		options.each(function(){
			var value = $(this).attr("value");
			result.push( new $.AppContext.types.Option(this.form, this.name, value) );
		});
		
	}
	
	return result;
	
};
