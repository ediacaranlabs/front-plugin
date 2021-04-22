$.AppContext.types = {};

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

$.AppContext.types.Field = function(formID, fieldName){
	this.form = formID;
	this.name = fieldName;
};

$.AppContext.types.Field.prototype.setProperty = function(name, value){
	$('#' + this.form + ' input[name=' + this.name + ']').prop(name, value);
};

$.AppContext.types.Field.prototype.getProperty = function(name){
	var $result = $('#' + this.form + ' input[name=' + this.name + ']').prop(name);
	return $result;
};

$.AppContext.types.Field.prototype.registerEvent = function(name, f){
	//$.AppContext.events.add(component, type, handler)
};

$.AppContext.types.Field.prototype.unregisterEvent = function(name){
	//$.AppContext.events.add(component, type, handler)
};

$.AppContext.types.Field.prototype.setValue = function(value){
	
	var element = $('#' + this.form + ' input[name=' + this.name + ']');
	var elementType = element.prop("tagName").toLowerCase();
	
	if(elementType === 'input'){
		elementType = element.prop("type");
	}
	
	switch (elementType) {
		case 'radio':
			$('#' + this.form + ' input:radio[name=' + this.name + '][value=' + value + ']').prop('checked', true);
			break;
		case 'checkbox':
			$('#' + this.form + ' input:checkbox[name=' + this.name + '][value=' + value + ']').prop('checked', true);
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
	
};

$.AppContext.types.Field.prototype.getValue = function(){
	
	var element = $('#' + this.form + ' input[name=' + this.name + ']');
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
			return $('#' + this.form + ' textarea[name=' + this.name + ']:checked').val();
		default:
			return $('#' + this.form + ' input[name=' + this.name + ']:checked').val();
	}
	
};
