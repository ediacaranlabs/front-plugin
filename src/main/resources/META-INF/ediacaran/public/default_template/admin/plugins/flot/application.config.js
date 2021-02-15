$.AppContext.flotcharts = {};

$.AppContext.flotcharts.charts = {};

$.AppContext.flotcharts.update_charts = {};

$.AppContext.flotcharts.setAsyncUpdateChart = function (chart, time = 1000){

	if($.AppContext.flotcharts.charts[chart] == null){
		return;
	}
	
	if($.AppContext.flotcharts.charts[chart].edController == null){
		$.AppContext.flotcharts.charts[chart].edController = {};
	}
	
	$.AppContext.flotcharts.charts[chart].edController.asyncUpdateEnabled = time > 0; 
	$.AppContext.flotcharts.charts[chart].edController.asyncUpdateTime = time; 
	
	var exec = function(){
		
		$.AppContext.flotcharts.loadData(chart,$.AppContext.flotcharts.charts[chart].edController.resource);
		
		if($.AppContext.flotcharts.charts[chart].edController.asyncUpdateEnabled){
			setTimeout(exec, $.AppContext.flotcharts.charts[chart].edController.asyncUpdateTime);
		}
	}; 
 
	if($.AppContext.flotcharts.charts[chart].edController.asyncUpdateEnabled){
		setTimeout(exec, $.AppContext.flotcharts.charts[chart].edController.asyncUpdateTime);
	}
}

$.AppContext.flotcharts.loadData = function (chart, resource){
	
	var updater = function(data){
		//alert(JSON.stringify(data));
		$.AppContext.flotcharts.updateChart(chart, data);
	};
	
	$.AppContext.utils.loadJson(resource, updater);
	
}

$.AppContext.flotcharts.setResource = function (chart, value){

	if($.AppContext.flotcharts.charts[chart] == null){
		return;
	}
	
	if($.AppContext.flotcharts.charts[chart].edController == null){
		$.AppContext.flotcharts.charts[chart].edController = {};
	}
	
	$.AppContext.flotcharts.charts[chart].edController.resource = value; 
}

$.AppContext.flotcharts.updateChart = function (chart, data){
	
	//alert(chart + ": " + JSON.stringify(data));
	
	if($.AppContext.flotcharts.charts[chart] == null){
		return;
	}
	
	var plot       = $.AppContext.flotcharts.charts[chart];
	var series     = plot.getData();
	var options    = plot.getOptions();
	var newSeries  = data.series;
	var delSeries  = data.delSeries;
	var newOptions = data.options;
	
	if(newSeries != null){
		for (i = 0; i < newSeries.length; i++) {
			
			  if(i >= series.length){
				  if(newSeries[i] != null){
					  series.push(newSeries[i]);
				  }
			  }
			  else
              if(newSeries[i] != null){
          		  //alert(JSON.stringify(newSeries[i]));
				  $.AppContext.utils.updateProperties(series[i], newSeries[i]);
			  }
			  
		}
		
	}
	
	if(newOptions != null){
		$.AppContext.utils.updateProperties(options, newOptions);
	}

	if(delSeries != null){
		for (i = 0; i < delSeries.length; i++) {
			series.splice(delSeries[i], 1);
		}
	}
	
	plot.setData(series);
	
	if(newOptions != null){
		plot.setupGrid();
	}
	
	plot.draw()	
	
	/*
	series[0].data = someNewArray;
	series[0].color = 'blue'; // modify existing series
	series.push({data: [[0, 5], [1, 1], [2, 7]], color: 'green'}); // add a new one
	plot.setData(series); // you need to set the data so that flot will re-process any newly added series
	var opts = plot.getOptions() // get a reference to the options
	opts.xaxes[0].min = -1; // adjust an axis min, use the xaxes property NOT the xaxis
	// there is no need to "setOptions"...
	plot.setupGrid() // if you need to redraw the axis/grid
	plot.draw()
	*/	
};