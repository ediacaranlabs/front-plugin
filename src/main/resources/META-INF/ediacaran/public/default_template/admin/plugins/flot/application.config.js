$.AppContext.flotcharts = {};

$.AppContext.flotcharts.charts = {};

$.AppContext.flotcharts.updateChart = function (chart, data){
	
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
          		  //alert(JSON.stringify(series[i]));
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