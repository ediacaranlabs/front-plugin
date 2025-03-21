$.AppContext.types = {};
$.AppContext.types.components = {};

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

$.AppContext.types.Object.prototype.isVisible = function(){
	return !$(this.obj).is(":hidden");
};

$.AppContext.types.Object.prototype.setEnabled = function($value){

	if($value){
		$(this.obj).removeClass("disabled");
	}
	else{
		$(this.obj).addClass("disabled");
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

$.AppContext.types.Object.prototype.each = function(e){
	
	$(this.obj).find('*').each(function () {
		var $o = $.AppContext.utils.getByAdvise($(this));
	    return e($o);
	});
	
};

$.AppContext.types.Object.prototype.remove = function(){
	$(this.obj).remove();
}

$.AppContext.types.Object.prototype.search = function($filter = null){
	
	var $result = [];

	$(this.obj).find('*').each(function () {
		var $o = $.AppContext.utils.getByAdvise($(this));
		if($filter == null || $filter($o)){
			$result.push($o);
		}
	});
	
	return $result;
};

$.AppContext.types.Object.prototype.getFirstChild = function(){
	
	var $childs = $(this.obj).children();
	
	if($childs.length > 0 ) {
		var $first = $childs.first();
		return $first.length > 0? $.AppContext.utils.getByAdvise($first) : null;
	}

	return null;
};

$.AppContext.types.Object.prototype.getLastChild = function($filter = null){
	
	
	var $result = null;
	
	$(this.obj).children().each(function () {
		var $o = $.AppContext.utils.getByAdvise($(this));
		if($filter == null || $filter($o)){
			$result = $o;
		}
	});
	
	return $result;
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
	var $prop = $(this.obj).prop("tagName");
	return $prop? $prop.toLowerCase() : $prop;
};

$.AppContext.types.Object.prototype.eachParent = function($filter){
	
	var $tag = $(this.obj).parent();
	var $i = 0;
	
	while($tag != null && $i < 100 ){

		var $tmp = $.AppContext.utils.getByAdvise($tag);
		$filter($tmp)
		$tag = $tag.parent();
		
		$i = $i + 1;
		
	}
	 
};

$.AppContext.types.Object.prototype.searchParent = function($filter = null){
	
	var $tag = $(this.obj).parent();
	var $i = 0;
	var $result = [];
	
	while($tag != null && $i < 100 ){

		var $tmp = $.AppContext.utils.getByAdvise($tag);
		
		if($filter == null || $filter($tmp)){
			$result.push($tmp);
		}
		
		$tag = $tag.parent();
		
		$i = $i + 1;
	} 
	
	return $result;
};

$.AppContext.types.Object.prototype.getFirstParent = function($filter = null){
	
	var $tag = $(this.obj).parent();
	var $i = 0;
	
	while($tag != null && $i < 100 ){

		var $tmp = $.AppContext.utils.getByAdvise($tag);
		
		if($filter == null || $filter($tmp)){
			return $tmp;
		}
		
		$tag = $tag.parent();
		
		$i = $i + 1;
	} 
	
	return null;
};

$.AppContext.types.Object.prototype.getLastParent = function($filter = null){
	
	var $tag = $(this.obj).parent();
	var $i = 0;
	var $result = null;
	
	while($tag != null && $i < 100 ){

		var $tmp = $.AppContext.utils.getByAdvise($tag);
		
		if($filter == null || $filter($tmp)){
			$result = $tmp;
		}
		
		$tag = $tag.parent();
		
		$i = $i + 1;
	} 
	
	return $result;
};

$.AppContext.types.Object.prototype.getForm = function(){
	
	var $formName = $(this.obj).attr("form");
	
	if($formName){
		return $.AppContext.utils.getById($formName);
	}

	return this.getFirstParent(function($e){
		return $e.getTagName() === 'form';
	});
	
};

$.AppContext.types.Object.prototype.getFormGroup = function(){
	
	var $group = this.getFirstParent(function($e){
		return $e.getAttribute("formgroup") != null;
	});

	if($group != null){
		return $group;
	}
	
	return null;
	
};

/*---------------------------------------------------------------*/
/* Form Group                                                    */
/*---------------------------------------------------------------*/

$.AppContext.types.FormGroup = function($obj){
	this.obj = $obj;
};

$.AppContext.types.FormGroup.prototype.getName = function(){
	return this.obj.attr("formgroup");
};

$.AppContext.types.FormGroup.prototype.getType = function(){
	return this.obj.attr("formgrouptype");
};

$.AppContext.types.FormGroup.prototype.getPath = function(){
	return this.obj.attr("group-path");
};

/*---------------------------------------------------------------*/
/* Form                                                          */
/*---------------------------------------------------------------*/

$.AppContext.types.Form = function($form){
	this.obj = $form;
};

$.AppContext.types.Form.prototype.submit = function($validate = true, $resource = null, $dest = null, $success = null, $error = null){
	if($resource != null){
		$.AppContext.utils.submit(this.obj,$validate, $resource, $dest, $success, $error);
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
				index: []
		};
	}
	
	var $o = $no.getFirstChild();
	
	while($o != null){
		
		var $type = $o.getTagName();
		
		if($type == 'input' || $type == 'textarea' || $type == 'select' ){
			
			$o.setAttribute('formindex', $group.path);
			
			if(!$o.getAttribute('originalname')){
				$o.setAttribute('originalname', $o.getAttribute('name'));
			}
			
		}
		
		var $groupName = $o.getAttribute('formgroup');
		
		if($groupName != null){

			if(!$group.index[$groupName]){
				$group.index[$groupName] = $o.getAttribute('formgrouptype') == 'index'? 0 : -1;
			}
			
			var $newGroup   = {
				path: $group.path != null? $group.path + "." + $groupName : $groupName,
				index: []
			};
			
			//$newGroup.path  = $group.path != null? $group.path + "." + $groupName : $groupName;
			//$newGroup.index =  [];

			if($group.index[$groupName] != -1){
				$newGroup.path           = $newGroup.path + '[' + $group.index[$groupName] +']';
				$group.index[$groupName] = $group.index[$groupName] + 1;
			}
			
			$o.setAttribute("group-path", $newGroup.path);
			
			this.updateFieldIndex($newGroup, $o);
		}
		else{
			this.updateFieldIndex($group, $o);
		}
		
		$o = $o.getNext();

	}
	
};

$.AppContext.types.Form.prototype.toObject = function($obj = null, $no = null){

	if($obj == null){
		$obj = {};
	}

	if($no == null){
		$no = this
	}
	
	var $o = $no.getFirstChild();
	
	while($o != null){
		
		var $type = $o.getTagName();
		
		if($type == 'input' || $type == 'textarea' || $type == 'select' ){
			var $f = this.getField($o.attr('name'))
			$obj[$f.getAttribute('originalname')] = $f.getValue();
		}
		
		var $groupName = $o.getAttribute('formgroup');
		
		if($groupName != null){
			
			if($o.getAttribute('formgrouptype') == 'index'){
				var $f = $obj[$groupName];
				
				if($f == null){
					$f = [];
					$obj[$groupName] = $f;
				}
				
				var $newObj = this.toObject(null, $o);
				
				$f.push($newObj);
			}
			else{
				$obj[$groupName] = this.toObject(null, $o);
			}
			
		}
		else{
			this.toObject($obj, $o);
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
			var $name = $o.getAttribute('originalname');
			var $formindex = $o.getAttribute('formindex');
			
			/*
			var lastIndex = $name.lastIndexOf('.');
			
			if(lastIndex > -1){
				$name = $name.slice(lastIndex + 1);
			}
			*/
			
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

$.AppContext.types.Form.prototype.resetFieldNames = function($no = null){

	if($no == null){
		$no = this
	}
	
	var $o = $no.getFirstChild();
	
	while($o != null){
		
		var $type = $o.getTagName();
		
		if($type == 'input' || $type == 'textarea' || $type == 'select' ){
			
			if($o.getAttribute('originalname')){
				$o.setAttribute('name', $o.getAttribute('originalname'));
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
			//alert($($i).attr('id'));
			if($($i).prop('checked')){
				$result = $($i).val();
			}
		}
	}
	else
	if(this.type == 'select' ){
		$result = $(this.field).find('option:selected').val();
	}
	else
	if(this.type == 'file'){
		if(this.field.files){
			var reader = new FileReader();
			var $url = URL.createObjectURL(this.field.files[0]);
			reader.readAsDataURL($url);
			$result = reader.result;
		}
		else{
			$result = null;
		}		
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
	return $(this.option).prop(name);
};

/* Object */

$.AppContext.types._map = {};

$.AppContext.types.extends = function ($type, $superType){

	for (var prop in $superType.prototype) {
		if(typeof($type.prototype[prop]) == 'undefined'){
			$type.prototype[prop] = $superType.prototype[prop];
		}
	}
	
};

$.AppContext.types.registerType = function ($name, $prototype){

	$.AppContext.types._map[$name] = $prototype;

	if($name == 'object'){
		return;
	}
	
	$.AppContext.types.extends($prototype.type, $.AppContext.types.Object);
	
	/*
	for (var prop in $.AppContext.types.Object.prototype) {
		if(typeof($prototype.type.prototype[prop]) == 'undefined'){
			$prototype.type.prototype[prop] = $.AppContext.types.Object.prototype[prop];
		}
	}
	*/
	
};

$.AppContext.types.unregisterType = function ($name){
	delete $.AppContext.types._map[$name];
};

$.AppContext.types.getType = function ($e){
	
	for (var $prop in $.AppContext.types._map) {
		var $val = $.AppContext.types._map[$prop];

		if($val.accept($e)){
			return $val.type;
		}
	}
	
	return $.AppContext.types.Object;
};

$.AppContext.types.registerType(
	'form', 
	{
		type: $.AppContext.types.Form,
		accept : function ($e){
			return $e.getTagName() === 'form';
		}
	}
);

$.AppContext.types.registerType(
	'formgroup', 
	{
		type: $.AppContext.types.FormGroup,
		accept : function ($e){
			return $e.getAttribute("formgroup") != null;
		}
	}
);