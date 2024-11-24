$.AppContext.types = {};

/*---------------------------------------------------------------*/
/* Object                                                        */
/*---------------------------------------------------------------*/

$.AppContext.types.Object = function($ref){
	this.obj = $ref;
};

//$.AppContext.types.Object.prototype.setValue = function(name, value){
$.AppContext.types.Object.prototype.setValue = function(value, name = null){
	var $id = $(this.obj).attr('id');
	//$('#' + $id + '_' + name).html(value);
	$('#' + $id + (name == null? '' : '_' + name) ).html(value);
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
	if($resource != null){
		$.AppContext.utils.submit(this.obj,$validate, $resource, $dest);
	}
	else{
		this.obj.submit();
	}
};

$.AppContext.types.Form.prototype.updateFieldIndex = function($group = null, $no = null){

	if($no == null){
		$no = this
	}
	
	if($group == null){
		$group = {
				path: null,
				index: $no.getAttribute('formgrouptype') == 'index'? 0 : -1
		};
	}
	
	var $o = $no.getFirstChild();
	
	while($o != null){
		
		var $type = $o.getTagName();
		
		if($type == 'input' || $type == 'textarea' || $type == 'select' ){
			$o.setAttribute('formindex', $group.path);
		}
		
		var $groupName = $o.getAttribute('formgroup');
		
		if($groupName != null){

			var $newGroup   = {};
			$newGroup.path  = $group.path != null? $group.path + "." + $groupName : $groupName;
			$newGroup.index =  $o.getAttribute('formgrouptype') == 'index'? 0 : -1;

			if($group.index != -1){
				$newGroup.path = $newGroup.path + '[' + $group.index +']';
				$group.index   = $group.index + 1;
			}
			
			this.updateFieldIndex($newGroup, $o);
		}
		else{
			this.updateFieldIndex($group, $o);
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
	
	var $field = $(this.obj).find(type + "[name='" + name + "']");
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
			$result.push(new $.AppContext.types.Field($form, $map[$p], $p));
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

	if(this.type == 'checkbox' || this.type == 'radio'){
		
		for(var $i of this.field){
			$($i).prop(name, value);
		}

	}
	else{
		$(this.field).prop(name, value);
	}
	
};

$.AppContext.types.Field.prototype.getProperty = function(name){
	
	var $result = null;

	if(this.type == 'checkbox' || this.type == 'radio'){
		$result = $(this.field[0]).prop(name);
	}
	else{
		$result = $(this.field).prop(name);
	}
	
	return $result;
	
};

$.AppContext.types.Field.prototype.setAttribute = function(name, value){

	if(this.type == 'checkbox' || this.type == 'radio'){
		
		for(var $i of this.field){
			$($i).attr(name, value);
		}

	}
	else{
		$(this.field).attr(name, value);
	}
	
};

$.AppContext.types.Field.prototype.getAttribute = function(name){
	
	var $result = null;

	if(this.type == 'checkbox' || this.type == 'radio'){
		$result = $(this.field[0]).attr(name);
	}
	else{
		$result = $(this.field).attr(name);
	}
	
	return $result;
	
};

$.AppContext.types.Field.prototype.registerEvent = function(name, handler){

	if(this.type == 'checkbox' || this.type == 'radio'){
		
		for(var $i of this.field){
			$.AppContext.events.add($($i).attr('id'), name, handler);
		}

	}
	else{
		$.AppContext.events.add($(this.field).attr('id'), name, handler);
	}
	
};

$.AppContext.types.Field.prototype.unregisterEvent = function(name){

	if(this.type == 'checkbox' || this.type == 'radio'){
		
		for(var $i of this.field){
			$.AppContext.events.remove($($i).attr('id'), name, handler);
		}

	}
	else{
		$.AppContext.events.remove($(this.field).attr('id'), name, handler);
	}

};

$.AppContext.types.Field.prototype.setValue = function(value){

	
	if(this.type == 'checkbox' || this.type == 'radio'){
		
		for(var $i of this.field){
			if($($i).attr('value') == value){
				$($i).prop('checked', true);
			}
		}

	}
	if(this.type == 'select' ){
		$(this.field).find('option[value=' + value + ']').attr('selected','selected');
	}
	else{
		$(this.field).val(value);
	}
	
	var $bv = this.form.data('bootstrapValidator');
	
	if($bv){
		$bv.resetForm();
	}
	
};

$.AppContext.types.Field.prototype.getValue = function(){

	$result = null;

	if(this.type == 'checkbox' || this.type == 'radio'){
		
		for(var $i of this.field){
			if($($i).prop('checked')){
				$result = $($i).val();
			}
		}
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
		
		for(var $i of this.field){
			$result.push(new $.AppContext.types.Option(this.form, this.field, $i, this.type));
		}
		
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
	if(this.type == 'checkbox' || this.type == 'radio'){
		$result = $(this.form).filter('label[for=' + $(this.option).attr('id') + ']').html();
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

