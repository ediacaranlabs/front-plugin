$.AppContext.pages.update_status_plugin_detail = {};

$.AppContext.pages.update_status_plugin_detail.status_config_fr = {};

$.AppContext.pages.update_status_plugin_detail.status_config_fr.submit = function(){
	
	var $form = $.AppContext.utils.getComponentById('status_config_fr');
	$.AppContext.utils.submit($form);
	
};

$.AppContext.pages.update_status_plugin_detail.status_config_fr.setStatusValue = function(value){
	
	/*
	var $radios = $('#status_config_fr input:radio[name=status]');
    if($radios.is(':checked') === false) {
        $radios.filter('[value=' + value + ']').prop('checked', true);
    }	
	*/
	
    $('#status_config_fr input:radio[name=status][value=' + value + ']').click();
};
