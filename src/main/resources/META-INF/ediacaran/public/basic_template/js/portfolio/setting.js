
jQuery(document).ready(function($){

if (jQuery().quicksand) {

 	// Clone applications to get a second collection
	var $data = $(".portfolio-thumbs").clone();
	
	//NOTE: Only filter on the main portfolio page, not on the subcategory pages
	$('.filter li').click(function(e) {
		$(".filter li").removeClass("active");	
		// Use the last category class as the category to filter by. This means that multiple categories are not supported (yet)
		var filterClass=$(this).attr('class').split(' ').slice(-1)[0];
		
		if (filterClass == 'all') {
			var $filteredData = $data.find('.item-thumbs');
		} else {
			var $filteredData = $data.find('.item-thumbs[data-type=' + filterClass + ']');
		}
		$(".portfolio-thumbs").quicksand($filteredData, {
			duration: 600,
			adjustHeight: 'auto'
		}, function () {
		
			// Portfolio fancybox
		$(".fancybox").fancybox({				
				padding : 0,
				beforeShow: function () {
					this.title = $(this.element).attr('title');
					this.title = '<h4>' + this.title + '</h4>' + '<p>' + $(this.element).parent().find('img').attr('alt') + '</p>';
				},
				helpers : {
					title : { type: 'inside' },
				},
			    tpl: {
				    closeBtn : '<a title="Close" class="fancybox-item fancybox-close" href="javascript:;"><i class="fa fa-remove"></i></a>',
				    next     : '<a title="Next" class="fancybox-nav fancybox-next" href="javascript:;"><span><i class="fa fa-angle-right"></i></span></a>',
				    prev     : '<a title="Previous" class="fancybox-nav fancybox-prev" href="javascript:;"><span><i class="fa fa-angle-left"></i></span></a>'
			    }
			});


		});	
		$(this).addClass("active"); 			
		return false;
	});
	
}//if quicksand

});