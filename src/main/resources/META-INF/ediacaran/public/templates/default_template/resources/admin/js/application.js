if (typeof jQuery === "undefined") {
  throw new Error("Application requires jQuery");
}

$.AppContext = {};

$.AppContext.pages = {};

$.AppContext.vars = {
		
		asycLoadFunctions: new Array(),
		
		loadListeners: [],
		
		contextPath: "/",
		
		dialog: "defaultDialog",
		
		panel: "panel",

		document: null,

		loaded: false
};

$.AppContext.events = {

		add: function (component, type, handler){
			//$('#' + component).on(type, handler);
			$('#' + component).on(type, function(event){
				handler({
					'sourceID' : component,
					'eventType': type,
					'handler' : event.originalEvent,
					'source': $.AppContext.utils.toObject(this),
					'originalSource': $(this)
				});
			});
		},
		
		remove: function (component, type){
			$('#' + component).off(type);
		}
		
};

$.AppContext.utils = {
		
		content: {

			append: function ($local, $content){
				$.AppContext.utils.updateContentData($local, $content, "append");
			},
	
			insert: function ($local, $content){
				$.AppContext.utils.updateContentData($local, $content, "insert");
			},
	
			update: function ($local, $content){
				$.AppContext.utils.updateContentData($local, $content, null);
			}
			
		},
		
		toObject: function(obj){
			return $.AppContext.utils.getById($(obj).attr('id'));
		},
		
		getById: function(id){
			return $.AppContext.utils.getByAdvise('#' + id);
		},

		getByAdvise: function($obj){
			try{
				$obj = $($obj);
				
				var $e = new $.AppContext.types.Object($obj);
				 
				var $type = $.AppContext.types.getType($e);
		
				var $o = new $type($obj);
				$o.getTagName();
				return $o;
			}
			catch($err){
				 console.log("Error", $err.stack);
				 return $o;
			}
		},
		
		setContentById: function(id, value){
			$('#' + id).html(value);
		},
		

		//getComponentByClass: function(id){
		//	return $('.' + id );
		//},
		
		/* async load functions */
		
		executeAsyncLoad: function(){
		
			var $f = $.AppContext.vars.asycLoadFunctions.pop();
			
			while(typeof $f !== "undefined" ){
				try{
					$f();
				}
				catch($ex){
					console.log($ex);
				}
				
				$f = $.AppContext.vars.asycLoadFunctions.pop();
			}
			
		},
		
		enableActions: function (local){
			
	    	$.AppContext.eventListeners.fireEvent({
	    		sourceID: "utils.enableActions",
	    		type: "before",
	    		data: { "local" : local}
		    });
			
			$.AppContext.utils.enableAsyncSubmit(local);
			$.AppContext.utils.enableAsyncGet(local);
			
	    	$.AppContext.eventListeners.fireEvent({
	    		sourceID: "utils.enableActions",
	    		type: "after",
	    		data: { "local" : local}
		    });
			
		},

		enableAsyncSubmit: function (local){

			var $region = local == null? '' : '#' + local;
			
			$($region + " form button[type=submit]").each(function() {
				
				var $e = $(this);
				
				if( $e.is("button") && $e.attr("type") == "submit" ) {
			        
					if($e.attr('asyncEnabled')){
						return;
					}
					
					$e.click(function(event){
						
						var $b = $(this);

						var $form = $b.attr("form") !== undefined? $('#' + $b.attr("form")) : $b.parents('form:first');
						
						$form.attr("action",       $b.attr("formaction") !== undefined?   $b.attr("formaction")   : $form.attr("action_default")       );
						$form.attr("enctype",      $b.attr("formenctype") !== undefined?  $b.attr("formenctype")  : $form.attr("enctype_default")      );
						$form.attr("method",       $b.attr("formmethod") !== undefined?   $b.attr("formmethod")   : $form.attr("method_default")       );
						$form.attr("target",       $b.attr("formtarget") !== undefined?   $b.attr("formtarget")   : $form.attr("target_default")       );
						$form.attr("dest-content", $b.attr("dest-content") !== undefined? $b.attr("dest-content") : $form.attr("dest_content_default") );
						
						var $destContent = $form.attr('dest-content');
						var $address     = $form.attr('action');
						var actionType   = $address === undefined? "" : $address.substring(0, 2);
						
						if(actionType === '#!' || actionType === '#m'){
					    	event.preventDefault();
					    	$.AppContext.utils.submit($form);
						}


					});
					
					$e.attr('asyncEnabled', true);

			    }					
			});
			
			$($region + " form").each(function() {
				
				var $f = $(this);
				
				if($f.attr('asyncEnabled')){
					return;
				}
				
				var $destContent = $f.attr('dest-content');
				var $address     = $f.attr('action');
				var actionType   = $address === undefined? "" : $address.substring(0, 2);

				if( (actionType !== '#!' && actionType !== '#m') && !$destContent){
					return;
				}
				
				$f.removeAttr('onsubmit');
				$f.unbind('submit');
				
				$f.attr("action_default", $f.attr("action"));
				$f.attr("enctype_default", $f.attr("enctype"));
				$f.attr("method_default", $f.attr("method"));
				$f.attr("target_default", $f.attr("target"));
				$f.attr("dest_content_default", $f.attr("dest-content"));

				
				$($f).submit(function (event) {

			    	var $form = $(this);
			    	
			    	event.preventDefault();
			    	$.AppContext.utils.submit($form);
				});
					
				$f.attr('asyncEnabled', true);
				
			});
			
		},

		enableAsyncGet: function (local){

			var $region = local == null? '' : '#' + local;
			
			$($region + " a[href]").each(function(e) {
				var $lnk = $(this);
				
				if($.AppContext.utils.isApplyAsyncGet($lnk)){
					$($lnk).removeAttr('onclick');
					
					$($lnk).on('click', function (event) {
						$.AppContext.utils.loadContent($(this));
				        return false;
					});
				}
					

			});
			
		},
		
		loadJson: function (resource, success = null, error = null){

		    $.ajax({
		        type   : 'GET',
		        contentType: 'application/json',
			    dataType: 'json',
		        url    : $.AppContext.vars.contextPath + resource,
		        success: success,
		        error: error
		    });
			
		},
		
		postJson: function (resource, request, success = null, error = null){
			
			$.ajax({
			    type: 'POST',
			    //headers: { 
			    //    'Accept': 'application/json',
			    //    'Content-Type': 'application/json' 
			    //},
			    contentType: "application/json; charset=utf-8",
			    url: $.AppContext.vars.contextPath + resource,
			    data: JSON.stringify(request),
			    traditional: true,
			    //data: request,
			    success: success,
			    error : error
			});
			
		},		
		
		/* load content functions */
		
		loadResourceContent: function ($destContent, $resource, $isModal = false){
			
			var $address     = $.AppContext.utils.getAddress($resource);
			var $destContent = $.AppContext.utils.getDestContent($resource, $destContent);
			var $modal       = $isModal? true : $.AppContext.utils.isModal($resource);
			
			$.AppContext.utils.send('GET', $.AppContext.vars.contextPath + $address, null, null, $destContent, $modal, null, null, null);
		},

		updateContent: function ($resource, $isModal = false){
			
			var $address     = $.AppContext.utils.getAddress($resource);
			var $destContent = $.AppContext.utils.getDestContent($resource);
			var $modal       = $isModal? true : $.AppContext.utils.isModal($resource);
			
			$.AppContext.utils.send('GET', $.AppContext.vars.contextPath + $address, null, null, $destContent, $modal, null, null, null);
		},

		updateContentByID: function ($resource, $destContent, $isModal = false){
			
			var $address     = $.AppContext.utils.getAddress($resource);
			var $destContent = $.AppContext.utils.getDestContent($resource, $destContent);
			var $modal       = $isModal? true : $.AppContext.utils.isModal($resource);
			
			$.AppContext.utils.send('GET', $.AppContext.vars.contextPath + $address, null, null, $destContent, $modal, null, null, null);
		},
		
		appendContentByID: function ($resource, $destContent, $isModal = false){
			
			var $address     = $.AppContext.utils.getAddress($resource);
			var $destContent = $.AppContext.utils.getDestContent($resource, $destContent);
			var $modal       = $isModal? true : $.AppContext.utils.isModal($resource);
			
			$.AppContext.utils.send('GET', $.AppContext.vars.contextPath + $address, null, null, $destContent, $modal, 'append', null, null);
		},

		insertContentByID: function ($resource, $destContent, $isModal = false){
			
			var $address     = $.AppContext.utils.getAddress($resource);
			var $destContent = $.AppContext.utils.getDestContent($resource, $destContent);
			var $modal       = $isModal? true : $.AppContext.utils.isModal($resource);
			
			$.AppContext.utils.send('GET', $.AppContext.vars.contextPath + $address, null, null, $destContent, $modal, 'insert', null, null);
		},
		
		loadContent: function ($link){

			var $address     = $.AppContext.utils.getAddress($link.attr("href"));
			var $destContent = $.AppContext.utils.getDestContent($link.attr("href"), $link.attr('dest-content'));
			var $modal       = $.AppContext.utils.isModal($link.attr("href"));

			$.AppContext.utils.send('GET', $.AppContext.vars.contextPath + $address, null, null, $destContent, $modal, null, null, null);
		},

		/* send data function */
		
		submit: function ($form, $validate = true, $resource = null, $dest = null, $success = null, $error = null){

			var $bv = $form.data('bootstrapValidator');
			
			if($validate && $bv){
				try{
					$bv.resetForm();
				}
				catch($e){
					console.log($e);
				}
				
				$form.bootstrapValidator('validate');
				
				if($bv.getInvalidFields().length){
					return;
				}
			}
			
			var $action      = $resource == null? $form.attr('action') : $resource;
			var $modal       = $.AppContext.utils.isModal($action);
			var $method      = $form.attr('method');
			var $destContent = $dest == null? $form.attr('dest-content') : $dest;
			var $enctype     = $form.attr('enctype');
			var $data;//        = $enctype === 'multipart/form-data'? new FormData($form[0]) : $($form).serialize();

			var $formObj = new $.AppContext.types.Form($form);
			$formObj.updateFieldIndex();
			$formObj.updateFieldNames();

			if($enctype === 'multipart/form-data'){
				$data = new FormData($form[0]);
			}
			else
			if($enctype === 'json'){
				$data = new FormData($form[0]);
				$data = Object.fromEntries($data.entries())
				$data = JSON.stringify($data);
			}
			else{
				$data = $($form).serialize();
			}
			
			//$formObj.resetFieldNames();
			
		    $destContent = $.AppContext.utils.getDestContent($action, $destContent);
		    $action      = $.AppContext.utils.getAddress($action);

			var $successWrapper = function($e){
				
				$formObj.updateFieldIndex();
				$formObj.updateFieldNames();
				
				if($success){
					$success($e);
				}
				
			};

			var $errorWrapper = function($e){
				
				$formObj.updateFieldIndex();
				$formObj.updateFieldNames();
				
				if($error){
					$error($e);
				}
				
			};
			
			$.AppContext.utils.send($method, $action, $data, $enctype, $destContent, $modal, null, $successWrapper, $errorWrapper);
			
		},
		
		send: function ($method, $action, $data, $enctype, $destContent, $modal, $destContentType = null, $success = null, $error = null){

		    var opts = {
		        type   : $method,
		        url    : $action,
		        data   : $data,
		        success: function ($data){

		        	var $evt = {
				    		sourceID: "utils.send",
				    		type: "after",
				    		data: {
				        		opts: opts,
				        		success: true,
				        		data: $data
				        	}
					    };
		        	
		        	
			    	$.AppContext.eventListeners.fireEvent($evt);
			    	
			    	$data = $evt.data.data;
				    
				    if($destContent != null){
						$data = $.AppContext.utils.applyTemplate($destContent, $data);
					}
					
			    	if($modal){
				    	$.AppContext.loadListeners.executeBefore($action);
			    		$.AppContext.dialog.create();
			    		$.AppContext.dialog.setContent($data);
			    		$.AppContext.dialog.setVisible(true);
				    	$.AppContext.loadListeners.executeAfter($action);
			    	}
			    	else{
				    	$.AppContext.loadListeners.executeBefore($action);
			    		$.AppContext.utils.updateContentData($destContent, $data, $destContentType);
				    	$.AppContext.loadListeners.executeAfter($action);
			    	}		        	
			    	
			    	if($success){
						$success($data);
					}
		        },
		        error: function ($data){
		        	
		        	var $evt = {
				    		sourceID: "utils.send",
				    		type: "after",
				    		data: {
				        		opts: opts,
				        		success: false,
				        		data: $data
				        	}
					    };
		        	
			    	$.AppContext.eventListeners.fireEvent($evt);
			    	
			    	$data = $evt.data.data;
		        	
			    	if($error){
						$error($data);
					}
		        	
		        }
		    };
			
		    
		    if($enctype === 'multipart/form-data') {
		    	opts.enctype = $enctype;
		    	opts.processData = false;
		    	opts.contentType = false;		    	
		    }
		    else
		    if($enctype === 'json') {
		    	opts.contentType = "application/json; charset=utf-8";
		    	opts.traditional = true;
		    	opts.enctype = null;
		    }
		    
	    	var $evt = {
	    		sourceID: "utils.send",
	    		type: "before",
	    		data: opts
		    };
	    	
	    	$.AppContext.eventListeners.fireEvent($evt);
	    	
	    	opts = $evt.data;

		    $.ajax(opts);
			
		},
		
		/* simple functions */
		
		applyTemplate: function($id, $data){
			var $template = $( "#" + $id + "_front_code");

			if(!$template.hasClass("code-template")){
				return $data;
			}
			
			var $code = $template.html();
			$code = $code.replaceAll("&amp;", "&");
			$code = $code.replaceAll("&lt;", "<");
			$code = $code.replaceAll("&gt;", ">");
			$code = $code.replace(/^\s*\<\!\-\-/, "<!--\r\n");
			$code = $code.replace(/\-\-\>\s*$/, "\r\n-->");
			$code = "var $func = " + $code;
			
			eval( $code );
			
			$data = $func($data);
			
			return $data;
		},

		updateContentData: function ($local, $content, $position = null){
			
			$content = 
				'<script type="text/javascript">' + 
				'   $.AppContext.utils.enableActions("' + $local + '");' +
				'</script>' +
				$content; 

			if($position === 'append'){
				$('#' + $local).append($content);
			}
			else
			if($position === 'insert'){
				$('#' + $local).prepend($content);
			}
			else{
				$('#' + $local).html($content);
			}
			//window.scrollTo(0,0);
		},
		
		getDestContent: function ($address, $destContent){
			
			if($destContent === undefined){

				var len = $address.length;

				if(len <= 2){
					return null;
				}
 				
				var start = $address.substring(0, 2);

				if(start == '#!'){
					return $.AppContext.vars.painel;
				}
				else
				if(start == '#m'){
					return $.AppContext.vars.dialog;
				}
				
			}

			return $destContent;
			
		},

		isModal: function ($address){
			
			var len = $address.length;

			if(len <= 2){
				return false;
			}
			
			var start = $address.substring(0, 2);

			return start == '#m';
		},

		getAddress: function ($address){
			
			var len = $address.length;

			if(len <= 2){
				return $address;
			}
			
			var start   = $address.substring(0, 2);

			if(start == '#!' || start == '#m'){
				return $address.substring(2, $address.length);
			}
			
			return $address;
		},

		isApplyAsyncGet: function (link){

			var address = link.attr("href");
			var len     = address.length;

			if(len <= 2){
				return false;
			}
			
			var start = address.substring(0, 2);

			return start == '#!' || start == '#m';
		},
		
		// https://stackoverflow.com/questions/171251/how-can-i-merge-properties-of-two-javascript-objects-dynamically
		updateProperties: function (obj1, obj2) {

			for(var p in obj2) {
				try {
					//Property in destination object set; update its value.
					if (obj2[p].constructor==Object) {
						obj1[p] = $.AppContext.utils.updateProperties(obj1[p], obj2[p]);
					}
					else{
						obj1[p] = obj2[p];
					}

			    } 
				catch(e){
			      // Property in destination object not set; create it and set its value.
			      obj1[p] = obj2[p];
			    }
				
			  }

			return obj1;
		}
		
};

$.AppContext.utils.mouse = {};

$.AppContext.utils.mouse.position = null;

$.AppContext.utils.mouse.installMouseMonitor = function (){
	
	window.addEventListener('mousemove', function (event){
		$.AppContext.utils.mouse.position = { x: event.clientX, y: event.clientY };
	});
	
};

$.AppContext.vars.eventListeners = [];

$.AppContext.eventListeners = {
		
		addListener: function($listenerID, $listener){
			$.AppContext.vars.eventListeners[$listenerID] = $listener;
		},
			
		removeListener: function($listenerID, $listener){
			delete $.AppContext.vars.eventListeners[$listenerID];
		},
		
		fireEvent: function($event){
			
			var $listeners = $.AppContext.vars.eventListeners
			var $i;
			
			for(var $l in $listeners){
				
				var $listener = $listeners[$l];
				
				if(typeof $listener.fireEvent !== 'undefined'){
					$listener.fireEvent($event);
				}
				
			}

			/*
			for($i=0;$i<$listeners.length; $i++){
				
				if(typeof $listeners[$i].fireEvent !== 'undefined'){
					$listeners[$i].fireEvent($event);
				}
				
			}
			*/
			
		},
		
		init: function(){
			$.AppContext.utils.loadJson(
					'/plugins/ediacaran/front/events', 
					function(e){
						
						e.forEach(function ($i){
							$.AppContext.eventListeners.fireEvent($i)
						});
						
					},
					function(e){
						console.log('couldn\'t get the events: ' + e);
					}
			);

			setTimeout(
				function () {
					$.AppContext.eventListeners.init();
				}, 
				3000
			);
		}
		
}

$.AppContext.loadListeners = {

		addListener: function($id, $resource, $listener){
			
			$.AppContext.vars.loadListeners[$id] = {
					
				id: $id,
				
				pattern: new RegExp($resource, 'i'),
				
				listener: $listener
					
			};
			
			/*
			$.AppContext.vars.loadListeners.push({
				
				id: $id,
				
				pattern: new RegExp($resource, 'i'),
				
				listener: $listener
				
			});
			*/
		},

		removeListener: function($id){
			
			if($.AppContext.vars.loadListeners.hasOwnProperty($id)){
				delete $.AppContext.vars.loadListeners[$id];
			}
		
			/*
			var $i;
			var $idx = -1;
			
			for($i=0;$i<$.AppContext.vars.loadListeners.length; $i++){
				if($.AppContext.vars.loadListeners[$i].id == $id){
					$idx = $1;
					break;
				}
			}
			
			if($idx != -1){
				$.AppContext.vars.loadListeners.splice($idx, 1);
			}
			*/
		},
		
		getListeners: function($resource){
			
			$result = new Array();

			for(var $l in $.AppContext.vars.loadListeners){
				if($.AppContext.vars.loadListeners[$l].pattern.test($resource)){
					$result.push($.AppContext.vars.loadListeners[$l].listener);
				}
				
			}
			
			/*
			var $i;
			
			for($i=0;$i<$.AppContext.vars.loadListeners.length; $i++){
				if($.AppContext.vars.loadListeners[$i].pattern.test($resource)){
					$result.push($.AppContext.vars.loadListeners[$i].listener);
				}
			}
			*/
			
			return $result;
		},
		
		executeBefore: function ($resource){
			
			$listeners = $.AppContext.loadListeners.getListeners($resource);
			
			var $i;
			
			for($i=0;$i<$listeners.length; $i++){
				if(typeof $listeners[$i].before !== 'undefined'){
					$listeners[$i].before();
				}
			}
			
		},

		executeAfter: function ($resource){
			
			$listeners = $.AppContext.loadListeners.getListeners($resource);
			
			var $i;
			
			for($i=0;$i<$listeners.length; $i++){
				if(typeof $listeners[$i].after !== 'undefined'){
					$listeners[$i].after();
				}
			}
			
		}
		
};

$.AppContext.onload = function ($function){

	$.AppContext.vars.asycLoadFunctions.push($function);
	
	if($.AppContext.vars.loaded){
		$.AppContext.utils.executeAsyncLoad();
	}
		
};

$.AppContext.dialog = {

		create: function ($id){
			
			if(typeof $id === "undefined" ){
				$id = "defaultDialog";
			}

			$("#" + $id).remove();
			
			var dialog = 
				'<div id="' + $id + '" class="modal fade">' +
				'    <div class="modal-dialog">' +
				'        <div class="modal-content"></div>' +
				'    </div>' +
				'</div>';
			
			$('body').append(dialog);
		},
		
		setVisible: function ($value, $id){
			if(typeof $id === "undefined" ){
				$id = "defaultDialog";
			}
			
			$id = "#" + $id;
			
			$($id).modal($value? 'show' : 'hide');
		},

		setContent: function ($value, $id){
			if(typeof $id === "undefined" ){
				$id = "defaultDialog";
			}
			
			$.AppContext.utils.updateContentData($id + " .modal-content", $value);
			
			$($id).find("[modal-action='close']").click(function(){
				$($id).modal("hide");
				$($id).on('hidden.bs.modal', function (e) {
					$($id).remove();
				});
			});
		},
		
		close: function ($id){
			
			if(typeof $id === "undefined" ){
				$id = "defaultDialog";
			}
			
			$id = "#" + $id;
			
			$($id).modal("hide");
		}
		
		
};

$.AppContext.addLoadListener = function ($id, $resource, $listener){
	$.AppContext.loadListeners.addListener($id, $resource, $listener);
};

$.AppContext.removeLoadListener = function ($id){
	$.AppContext.loadListeners.removeListener($id);
};

$.AppContext.openLink = function ($link){
	window.location.href = $link;
};

$.AppContext.loadContent = function ($destContent, $resource){
	$.AppContext.utils.loadResourceContent($destContent, $resource);
};

$.AppContext.get = function ($destContent, $resource){
	$.AppContext.utils.loadResourceContent($destContent, $resource);
};

$.AppContext.post = function ($form, $validate = true, $resource = null, $dest = null){
	$.AppContext.utils.submit($form, $validate, $resource, $dest);
};

$.AppContext.loadContentForm = function ($form, $validate = true, $resource = null, $dest = null){
	$.AppContext.utils.submit($form, $validate, $resource, $dest);
};

$.AppContext.loadContentOnPanel = function ($resource){
	$.AppContext.utils.loadResourceContent($.AppContext.vars.painel, $resource);
};

$(function (){
	$.AppContext.vars.document = $.AppContext.utils.getByAdvise('body'); 
	
	$.AppContext.utils.mouse.installMouseMonitor();
	$.AppContext.utils.enableActions(null);
	$.AppContext.utils.executeAsyncLoad();
	$.AppContext.eventListeners.init();

	var $pathIndex = location.href.indexOf("#!");
	if($pathIndex > 0){
		var $path = location.href.substring($pathIndex + 2);
		$.AppContext.loadContentOnPanel($path);
	}
	
	$.AppContext.vars.loaded   = true;
});
