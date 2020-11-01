$.AppContext.onload(function(){
	
	  $(".fancybox").fancybox({
		    padding: 0,
		    autoResize: true,
		    beforeShow: function() {
		      this.title = $(this.element).attr('title');
		      this.title = '<h4>' + this.title + '</h4>' + '<p>' + $(this.element).parent().find('img').attr('alt') + '</p>';
		    },
		    helpers: {
		      title: {
		        type: 'inside'
		      },
		    },
		    tpl: {
			    closeBtn : '<a title="Close" class="fancybox-item fancybox-close" href="javascript:;"><i class="fa fa-remove"></i></a>',
			    next     : '<a title="Next" class="fancybox-nav fancybox-next" href="javascript:;"><span><i class="fa fa-angle-right"></i></span></a>',
			    prev     : '<a title="Previous" class="fancybox-nav fancybox-prev" href="javascript:;"><span><i class="fa fa-angle-left"></i></span></a>'
		    }
		  });
	
});

