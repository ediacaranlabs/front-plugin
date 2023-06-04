$.AppContext.types = {};

/*---------------------------------------------------------------*/
/* Object                                                        */
/*---------------------------------------------------------------*/

$.AppContext.types.Object = function($ref){
	this.obj = $ref;
};

$.AppContext.types.Object.prototype.setValue = function(name, value){
	var $id = $(this.obj).attr('id');
	$('#' + $id + '_' + name).html(value);
};

$.AppContext.types.Object.prototype.getValue = function(name){
	var $id = $(this.obj).attr('id');
	return $('#' + $id + '_' + name).html();
};

$.AppContext.types.Object.prototype.setProperty = function(name, value){

	if(value == null){
		$(this.obj).removeProp(name);
	}
	else{
		$(this.obj).prop(name, value);
	}
	
};

$.AppContext.types.Object.prototype.getProperty = function(name){
	
	var $result = $(this.obj).prop(name);
	
	return $result;
	
};

$.AppContext.types.Object.prototype.setAttribute = function(name, value){

	if(value == null){
		$(this.obj).removeAttr(name);
	}
	else{
		$(this.obj).attr(name, value);
	}
	
};

$.AppContext.types.Object.prototype.getAttribute = function(name){
	
	var $result = $(this.obj).attr(name);
	
	return $result;
	
};

$.AppContext.types.Object.prototype.setVisible = function($value){
	if($value){
		$(this.obj).show();
	}
	else{
		$(this.obj).hide();
	}
};

$.AppContext.types.Object.prototype.registerEvent = function(name, handler){
	$.AppContext.events.add($(this.obj).prop("id"), name, handler);
};

$.AppContext.types.Object.prototype.unregisterEvent = function(name){
	$.AppContext.events.remove($(this.obj).prop("id"), name);
};

$.AppContext.types.Object.prototype.addClass = function(value){
	$(this.obj).addClass(value);
};

$.AppContext.types.Object.prototype.removeClass = function(value){
	$(this.obj).removeClass(value);
};

$.AppContext.types.Object.prototype.containsClass = function(value){
	return $(this.obj).hasClass(value);
};

$.AppContext.types.Object.prototype.getParent = function(){
	$o = $(this.obj).parent();
	return $o != null? $.AppContext.utils.getByAdvise($o) : null;
};

$.AppContext.types.Object.prototype.getNext = function(){
	var $o = $(this.obj).next();
	return $o.length > 0? $.AppContext.utils.getByAdvise($o) : null;
};

$.AppContext.types.Object.prototype.getPrevious = function(){
	var $o = $(this.obj).prev();
	return $o.length > 0? $.AppContext.utils.getByAdvise($o) : null;
};

$.AppContext.types.Object.prototype.insertAfter = function($obj){
	$(this.obj).insertAfter($obj.obj);
};

$.AppContext.types.Object.prototype.insertBefore = function($obj){
	$(this.obj).insertBefore($obj.obj);
};

$.AppContext.types.Object.prototype.getFirstChild = function(){
	
	var $childs = $(this.obj).children();
	
	if($childs.length > 0 ) {
		var $first = $childs.first();
		return $first.length > 0? $.AppContext.utils.getByAdvise($first) : null;
	}

	return null;
};

$.AppContext.types.Object.prototype.each = function(e){
	
	$(this.obj).find('*').each(function () {
		var $o = $.AppContext.utils.getByAdvise($(this));
	    return e($o);
	});
	
};

$.AppContext.types.Object.prototype.getLeft = function(){
	return $(this.obj).offset().left;
};

$.AppContext.types.Object.prototype.getTop = function(){
	return $(this.obj).offset().top;
};

$.AppContext.types.Object.prototype.getHeight = function(){
	return $(this.obj).outerHeight();
};

$.AppContext.types.Object.prototype.getWidth = function(){
	return $(this.obj).outerWifth();
};

$.AppContext.types.Object.prototype.getTagName = function(){
	return $(this.obj).prop("tagName").toLowerCase();
};

$.AppContext.types.Object.prototype.eachParent = function($filter){
	
	$(this.obj).parents().each(function ($o){
		var $tmp = $.AppContext.utils.getByAdvise($o);
		return $filter(tmp);
	});
	
};

/*---------------------------------------------------------------*/
/* Form                                                          */
/*---------------------------------------------------------------*/

$.AppContext.types.Form = function($form){
	this.obj = $form;
};

$.AppContext.types.Form.prototype.submit = function($validate = true, $resource = null, $dest = null){
	$.AppContext.utils.submit(this.obj,$validate, $resource, $dest);
};

$.AppContext.types.Form.prototype.updateFieldIndex = function($groupPath = null, $no = null){

	if($no == null){
		$no = this
	}
	
	var $o = $no.getFirstChild();
	
	while($o != null){
		
		var $type = $o.getTagName();
		
		if($type == 'input' || $type == 'textarea' || $type == 'select' ){
			$o.setAttribute('formindex', $groupPath);
		}
		
		var $groupName = $o.getAttribute('formgroup');

		if($groupPath == null){
			this.updateFieldIndex($groupName, $o);
		}
		else
		if($groupName != null){
			this.updateFieldIndex($groupPath + "." + $groupName, $o);
		}
		else{
			this.updateFieldIndex($groupPath, $o);
		}
		
		$o = $o.getNext();

	}
	
};

$.AppContext.types.Form.prototype.updateFieldNames = function($no = null){

	if($no == null){
		$no = this
	}
	
	var $o = $no.getFirstChild();
	
	while($o != null){
		
		var $type = $o.getTagName();
		
		if($type == 'input' || $type == 'textarea' || $type == 'select' ){
			var $name = $o.getAttribute('name');
			var $formindex = $o.getAttribute('formindex');
			var lastIndex = $name.lastIndexOf('.');
			
			if(lastIndex > -1){
				$name = $name.slice(lastIndex + 1);
			}
			
			if($formindex != null){
				$o.setAttribute('name', $formindex + '.' + $name);
			}
			else{
				$o.setAttribute('name', $name);
			}
		}
		
		this.updateFieldNames($o);
		
		$o = $o.getNext();

	}
	
};


$.AppContext.types.Form.prototype.getField = function(name, type = null){
	
	type = type == null? '' : type;
	
	var $field = $(this.obj).find(type + '[name=' + name + ']');
	var $form = this.obj;
	
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
/* Option                                                        */
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

/* Object */

$.AppContext.types._map = {};

$.AppContext.types.registerType = function ($name, $prototype){
	
	$.AppContext.types._map[$name] = $prototype;

	if($name == 'object'){
		return;
	}
	
	for (var prop in $.AppContext.types.Object.prototype) {
		$prototype.prototype[prop] = $.AppContext.types.Object.prototype[prop];
	}
	
};

$.AppContext.types.unregisterType = function ($name, $prototype){
	delete $.AppContext.types._map[$name];
};

$.AppContext.types.registerType('form', $.AppContext.types.Form);
$.AppContext.types.registerType('object', $.AppContext.types.Object);
//$.AppContext.types._map['form'] = $.AppContext.types.Form;
//$.AppContext.types._map['object'] = $.AppContext.types.Object;

