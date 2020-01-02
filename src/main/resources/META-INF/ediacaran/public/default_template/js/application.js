if (typeof jQuery === "undefined") {
  throw new Error("Application requires jQuery");
}

$.AppContext = {};

$.AppContext.vars = {
		
		asycLoadFunctions: new Array(),
		
		loadListeners: [],
		
		contextPath: "/",
		
		dialog: "#defaultDialog",
		
		panel: "#panel",

		loaded: false
};

$.AppContext.utils = {
		
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
			$.AppContext.utils.enableAsyncSubmit(local);
			$.AppContext.utils.enableAsyncGet(local);
		},

		enableAsyncSubmit: function (local){

			$((local? local + " " : "") + "form").each(function() {
				var $f           = $(this);
				var $destContent = $f.attr('dest-content');
				
				if(!$destContent){
					return;
				}
				
				$($f).removeAttr('onsubmit');
				$($f).unbind('submit');
				
				$($f).submit(function (event) {
			    	var $form = $(this);

			    	event.preventDefault();
			    	$.AppContext.utils.submit($form);
			        return false;
				});
					
			});
			
		},

		enableAsyncGet: function (local){

			$((local? local + " " : "") + "a[href]").each(function(e) {
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
		
		/* load content functions */
		
		loadResourceContent: function ($destContent, $resource){

			$.ajax({
			    type: 'GET',
			    url: $.AppContext.vars.contextPath + $resource,
			    success: function($data) {
			    	$.AppContext.loadListeners.executeBefore($resource);
		    		$.AppContext.utils.updateContent($destContent, $data);
			    	$.AppContext.loadListeners.executeAfter($resource);
		        }			    
			});
			
		},
		
		loadContent: function ($link){

			var $address     = $.AppContext.utils.getAddress($link);
			var $destContent = $.AppContext.utils.getDestContent($link);

			$.ajax({
			    type: 'GET',
			    url: $.AppContext.vars.contextPath + $address,
			    success: function($data) {
			    	if($.AppContext.utils.isModal($link)){
				    	$.AppContext.loadListeners.executeBefore($address);
			    		$.AppContext.dialog.create();
			    		$.AppContext.dialog.setContent($data);
			    		$.AppContext.dialog.setVisible(true);
				    	$.AppContext.loadListeners.executeAfter($address);
			    	}
			    	else{
				    	$.AppContext.loadListeners.executeBefore($address);
			    		$.AppContext.utils.updateContent($destContent, $data);
				    	$.AppContext.loadListeners.executeAfter($address);
			    	}
		        }			    
			});
			
		},

		/* send data function */
		
		submit: function ($form){

			var $bv = $form.data('bootstrapValidator');
			
			if($bv){
				$bv.resetForm();
				$form.bootstrapValidator('validate');
				
				if($bv.getInvalidFields().length){
					return;
				}
			}
			
			var $action      = $form.attr('action');
			var $method      = $form.attr('method');
			var $destContent = $form.attr('dest-content');
			
		    $.ajax({
		        type   : $method,
		        url    : $action,
		        data   : $($form).serialize(),
		        success: function ($data){
			    	$.AppContext.loadListeners.executeBefore($action);
		        	$.AppContext.utils.updateContent($destContent, $data);
			    	$.AppContext.loadListeners.executeAfter($action);
		        }
		    });
			
		},
		
		/* simple functions */
		
		updateContent: function ($local, $content){
			
			$content = 
				' <script type="text/javascript">' + 
				'  $.AppContext.utils.enableActions("' + $local + '");' +
				'</script>' + $content;
			
			$($local).html($content);

		},
		
		getDestContent: function (link){
			
			var address = link.attr("href");
			var len     = address.length;

			if(len <= 2){
				return null;
			}
			
			var start       = address.substring(0, 2);
			var destContent = link.attr('dest-content');

			if(start == '#!'){
				return destContent? destContent : $.AppContext.vars.painel;
			}
			else
			if(start == '#m'){
				return destContent? destContent : $.AppContext.vars.dialog;
			}
			
			return null;
		},

		isModal: function (link){
			
			var address = link.attr("href");
			var len     = address.length;

			if(len <= 2){
				return false;
			}
			
			var start = address.substring(0, 2);

			return start == '#m';
		},

		getAddress: function (link){
			
			var address = link.attr("href");
			var len     = address.length;

			if(len <= 2){
				return address;
			}
			
			var start   = address.substring(0, 2);

			if(start == '#!' || start == '#m'){
				return address.substring(2, address.length);
			}
			
			return address;
		},

		isApplyAsyncGet: function (link){

			var address = link.attr("href");
			var len     = address.length;

			if(len <= 2){
				return false;
			}
			
			var start = address.substring(0, 2);

			return start == '#!' || start == '#m';
		}
		
};

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
			
			$id = "#" + $id;
			
			$.AppContext.utils.updateContent($id + " .modal-content", $value);
			
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

$.AppContext.loadContentOnPanel = function ($resource){
	$.AppContext.utils.loadResourceContent($.AppContext.vars.painel, $resource);
};

$(function (){
	$.AppContext.vars.loaded = true;
	$.AppContext.utils.enableActions(null);
	$.AppContext.utils.executeAsyncLoad();
});
