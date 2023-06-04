$.AppContext.tinymce = {};

$.AppContext.tinymce.apply = function ($id){

	tinymce.remove($id + ' .tinymce');
 
	tinymce.init({
	        selector: $id + ' .tinymce',
	        strict_loading_mode : true,
	        menubar: false,
	        run_local: true,
	        setup: function (editor) {
	            editor.on('change', function () {
	                editor.save();
	            });
	        },
	        plugins: [
	          'a11ychecker','advlist','advcode','advtable','autolink','checklist','export',
	          'lists','link','image','charmap','preview','anchor','searchreplace','visualblocks',
	          'powerpaste','fullscreen','formatpainter','insertdatetime','media','table','help','wordcount'
	        ],
	        toolbar: 'undo redo | formatpainter casechange blocks | bold italic backcolor | ' +
	          'alignleft aligncenter alignright alignjustify | ' +
	          'bullist numlist checklist outdent indent | removeformat | a11ycheck code table help'
	      });
		 
	 
};

$.AppContext.onload(function(){			
		
	$.AppContext.tinymce.apply('');
	
	$.AppContext.addLoadListener("tinymce-link-load", "^.*", {
		
		after: function(){
			$.AppContext.tinymce.apply('#' + $.AppContext.vars.painel);
		}
	
	});
 
	$.AppContext.eventListeners.addListener("tinymce-save",{
		
		fireEvent: function($evt){
			if($evt.type === 'before' && $evt.sourceID === 'utils.send'){
				tinymce.triggerSave();
			}
		}
	});
	
});