$.AppContext.floatcharts = {};
$.AppContext.floatcharts.list = {};

$.AppContext.floatcharts.show = function(id){
	
	$.AppContext.floatcharts.list[id].obj = [];

	for (x in $.AppContext.floatcharts.list[id].series) {
		alert(JSON.stringify($.AppContext.floatcharts.list[id].series[x]));
		$.AppContext.floatcharts.list[id].obj.push($.AppContext.floatcharts.list[id].series[x]);
	}

	$.plot("#" + id, $.AppContext.floatcharts.list[id].obj);
	
};

$.AppContext.floatcharts.setData = function(id, label, data){
	$.AppContext.floatcharts.list[id].series[label].data = data;
};

$.AppContext.floatcharts.addData = function(id, label, data){
	$.AppContext.floatcharts.list[id].series[label].data.push(data);
};

$.AppContext.floatcharts.register = function(id, config){
	
	$.AppContext.floatcharts.list[id] = {};
	$.AppContext.floatcharts.list[id].series = {};
	$.AppContext.floatcharts.list[id].series[config.label] = config;
};

$.AppContext.floatcharts.remove = function(id, config){
	delete $.AppContext.floatcharts.list[id].series[config.label];
};