$.AppContext.events = {

		add: function (component, type, handler){
			$('#' + component).on(type, handler);
		},
		
		remove: function (component, type){
			$('#' + component).off(type);
		}
		
}