$.AppContext.floatcharts = {};
$.AppContext.floatcharts.list = {};

$.AppContext.floatcharts.show = function(id){
	
	$.AppContext.floatcharts.list[id].obj = [];

	for (x in $.AppContext.floatcharts.list[id].series) {
		$.AppContext.floatcharts.list[id].obj.push($.AppContext.floatcharts.list[id].series[x]);
	}

	alert(JSON.stringify($.AppContext.floatcharts.list[id].obj));
	alert(JSON.stringify($.AppContext.floatcharts.list[id].config));
	if($.AppContext.floatcharts.list[id].hasOwnProperty('config') && $.AppContext.floatcharts.list[id] != null){
		$.plot("#" + id, $.AppContext.floatcharts.list[id].obj, $.AppContext.floatcharts.list[id].config);
	}
	else{
		$.plot("#" + id, $.AppContext.floatcharts.list[id].obj);
	}
	
};

$.AppContext.floatcharts.setData = function(id, label, data){
	$.AppContext.floatcharts.list[id].series[label].data = data;
};

$.AppContext.floatcharts.addData = function(id, label, data){
	$.AppContext.floatcharts.list[id].series[label].data.push(data);
};

$.AppContext.floatcharts.register = function(id, seriesConfig){
	
	$.AppContext.floatcharts.list[id] = {};
	$.AppContext.floatcharts.list[id].series = {};
	$.AppContext.floatcharts.list[id].series[seriesConfig.label] = seriesConfig;
};

$.AppContext.floatcharts.remove = function(id, config){
	delete $.AppContext.floatcharts.list[id].series[config.label];
};

$.AppContext.floatcharts.setConfig = function(id, config){
	
	alert(JSON.stringify(config));
	
	if(!$.AppContext.floatcharts.list.hasOwnProperty(id)){
		$.AppContext.floatcharts.list[id] = {};
	}
	$.AppContext.floatcharts.list[id].config = config;
};
