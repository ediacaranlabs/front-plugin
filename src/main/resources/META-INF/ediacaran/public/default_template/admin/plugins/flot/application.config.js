$.AppContext.floatcharts = {};
$.AppContext.floatcharts.list = {};

$.AppContext.floatcharts.show = function(id){
	
	$.AppContext.floatcharts.list[id].obj = [];

	for (x in $.AppContext.floatcharts.list[id].series) {
		
		var series = $.AppContext.floatcharts.list[id].series[x];
		var seriesConfig = null;
		
		if(!series.hasOwnProperty('config')){
			seriesConfig = series.config;
		}
		else{
			seriesConfig = {};
		}
		
		seriesConfig.label = series.label;
		seriesConfig.data = series.data;
		
		alert(JSON.stringify(seriesConfig));
		
		$.AppContext.floatcharts.list[id].obj.push(seriesConfig);
	}

	if($.AppContext.floatcharts.list[id].hasOwnProperty('config') && $.AppContext.floatcharts.list[id] != null){
		$.plot("#" + id, $.AppContext.floatcharts.list[id].obj, $.AppContext.floatcharts.list[id].config);
	}
	else{
		$.plot("#" + id, $.AppContext.floatcharts.list[id].obj);
	}
	
};

$.AppContext.floatcharts.setData = function(id, label, data){
	//alert(JSON.stringify($.AppContext.floatcharts.list[id].series[label]));
	$.AppContext.floatcharts.list[id].series[label].data = data;
};

$.AppContext.floatcharts.addData = function(id, label, data){
	$.AppContext.floatcharts.list[id].series[label].data.push(data);
};

$.AppContext.floatcharts.register = function(id, seriesConfig){

	alert(JSON.stringify(seriesConfig));
	
	$.AppContext.floatcharts.list[id] = {};
	$.AppContext.floatcharts.list[id].series = {};
	$.AppContext.floatcharts.list[id].series[seriesConfig.label] = seriesConfig;
};

$.AppContext.floatcharts.remove = function(id, config){
	delete $.AppContext.floatcharts.list[id].series[config.label];
};

$.AppContext.floatcharts.setConfig = function(id, config){
	
	if(!$.AppContext.floatcharts.list.hasOwnProperty(id)){
		$.AppContext.floatcharts.list[id] = {};
	}
	
	$.AppContext.floatcharts.list[id].config = config;
};

$.AppContext.floatcharts.setSeriesConfig = function(id, label, seriesConfig){
	
	if(!$.AppContext.floatcharts.list.hasOwnProperty(id)){
		$.AppContext.floatcharts.list[id] = {};
	}
	
	if(!$.AppContext.floatcharts.list[id].series.hasOwnProperty(label)){
		$.AppContext.floatcharts.list[id].series[label] = {};
	}
	
	$.AppContext.floatcharts.list[id].series[label].config = seriesConfig;

};
