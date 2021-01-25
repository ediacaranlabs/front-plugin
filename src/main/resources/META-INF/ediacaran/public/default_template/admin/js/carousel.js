$.AppContext.onload(function(){
	
	$.AppContext.addLoadListener("jcarousel-link-load", "^.*", {
			
		after: function(){

			var jcarousel = $($.AppContext.vars.painel + ' .jcarousel');
		
		    jcarousel
		        .on('jcarousel:reload jcarousel:create', function () {
		            var carousel = $(this),
		                width = carousel.innerWidth();
		
		            /*
		            if (width >= 600) {
		                width = width / 3;
		            } else if (width >= 350) {
		                width = width / 2;
		            }
		            */
		            carousel.jcarousel('items').css('width', Math.ceil(width) + 'px');
		        })
		        .jcarousel({
		            wrap: 'circular'
		        });
		
		    $('.jcarousel-control-prev')
		        .jcarouselControl({
		            target: '-=1'
		        });
		
		    $('.jcarousel-control-next')
		        .jcarouselControl({
		            target: '+=1'
		        });
		
		    $('.jcarousel-pagination')
		        .on('jcarouselpagination:active', 'a', function() {
		            $(this).addClass('active');
		        })
		        .on('jcarouselpagination:inactive', 'a', function() {
		            $(this).removeClass('active');
		        })
		        .on('click', function(e) {
		            e.preventDefault();
		        })
		        .jcarouselPagination({
		            perPage: 1,
		            item: function(page) {
		                return '<a href="#' + page + '">' + page + '</a>';
		            }
		        });
	    
		}
	
	});
	
});
