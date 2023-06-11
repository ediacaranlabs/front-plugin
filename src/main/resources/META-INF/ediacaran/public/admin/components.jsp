<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@page trimDirectiveWhitespaces="true" %>
	<section class="inner-headline">
		<ed:row>
			<ed:col size="4">
				<div class="inner-heading">
					<h2>Components</h2>
				</div>
			</ed:col>
			<ed:col size="8">
				<ec:breadcrumb title="Components">
					<ec:breadcrumb-path icon="home" text="" lnk="#" />
					<ec:breadcrumb-path icon="" text="Features" lnk="#" />
				</ec:breadcrumb>
			</ed:col>
		</ed:row>
	</section>

   	<ed:row>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Button types</h3>
				</ec:box-header>
				<ec:box-body>
					<ed:row>
						<ed:col size="4">
		                	<ec:button label="btn default" />
						</ed:col>
						<ed:col size="4">
		                	<ec:button label="btn primary" style="primary"/>
						</ed:col>
						<ed:col size="4">
        		        	<ec:button label="btn secondary"  style="secondary"/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="4">
                			<ec:button label="btn success"  style="success"/>
						</ed:col>
						<ed:col size="4">
        		        	<ec:button label="btn danger"  style="danger"/>
						</ed:col>
						<ed:col size="4">
		                	<ec:button label="btn warning"  style="warning"/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="4">
		                	<ec:button label="btn info"  style="info"/>
						</ed:col>
						<ed:col size="4">
        		        	<ec:button label="btn light"  style="light"/>
						</ed:col>
						<ed:col size="4">
                			<ec:button label="btn dark"  style="dark"/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="4">
		                	<ec:button label="btn link"  style="link"/>
						</ed:col>
						<ed:col size="4">
						</ed:col>
						<ed:col size="4">
						</ed:col>
					</ed:row>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box style="warning">
				<ec:box-header>
					<h3>Button sizes</h3>
				</ec:box-header>
				<ec:box-body>
					<ed:row>
						<ed:col size="12">
                			<ec:button label="Large button" size="lg"/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12">
        		        	<ec:button label="Small button" size="sm" style="success"/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12">
		                	<ec:button label="btn block level" block="true" style="warning"/>
						</ed:col>
					</ed:row>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>

	<ed:row>
		<ed:col size="6">
			<ec:box style="success">
				<ec:box-header>
					<h3>Button group</h3>
				</ec:box-header>
				<ec:box-body>
	                <ec:button-group>
	                	<ec:button label="primary" style="primary"/>
	                	<ec:button label="success" style="success"/>
	                	<ec:button label="warning" style="warning"/>
	                </ec:button-group>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box style="info">
				<ec:box-header>
					<h3>Button with icon</h3>
				</ec:box-header>
				<ec:box-body>
					<ed:row>
						<ed:col size="4">
                			<ec:button label="heart" style="success" size="sm" icon="heart" />
						</ed:col>
						<ed:col size="4">
        		        	<ec:button label="camera" style="red" size="sm" icon="camera" />
						</ed:col>
						<ed:col size="4">
		                	<ec:button label="briefcase" size="sm" outline="true" icon="briefcase"/>
						</ed:col>
					</ed:row>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>

	<ed:row>
		<ed:col size="6">
			<ec:box style="success">
				<ec:box-header>
					<h3>Dropdown</h3>
				</ec:box-header>
				<ec:box-body>
					<ed:row>
						<ed:col>
							<ec:dropdown label="Teste" style="primary">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
						<ed:col>
							<ec:dropdown label="Teste" style="secondary">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
						<ed:col>
							<ec:dropdown label="Teste" style="success">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
					</ed:row>
					
					<ed:row>
						<ed:col>
							<ec:dropdown label="Teste" style="info">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
						<ed:col>
							<ec:dropdown label="Teste" style="warning">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
						<ed:col>
							<ec:dropdown label="Teste" style="danger">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
					</ed:row>					
					
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="6">
		
			<ec:box style="success">
				<ec:box-header>
					<h3>Dropdown split</h3>
				</ec:box-header>
				<ec:box-body>
					<ed:row>
						<ed:col>
							<ec:dropdown label="Teste" style="primary" split="true">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
						<ed:col>
							<ec:dropdown label="Teste" style="secondary" split="true">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
						<ed:col>
							<ec:dropdown label="Teste" style="success" split="true">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
					</ed:row>
					
					<ed:row>
						<ed:col>
							<ec:dropdown label="Teste" style="info" split="true">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
						<ed:col>
							<ec:dropdown label="Teste" style="warning" split="true">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
						<ed:col>
							<ec:dropdown label="Teste" style="danger" split="true">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
					</ed:row>					
					
				</ec:box-body>
			</ec:box>
		
		</ed:col>
   	</ed:row>

	<ed:row>
		<ed:col size="6">

			<ec:box style="success">
				<ec:box-header>
					<h3>Dropdown variation</h3>
				</ec:box-header>
				<ec:box-body>
					<ed:row>
						<ed:col>
							<ec:dropdown label="Teste" style="primary" variation="up">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
						<ed:col>
							<ec:dropdown label="Teste" style="secondary" variation="right">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
						<ed:col>
							<ec:dropdown label="Teste" style="success" variation="left">
								<ec:dropdown-item src="#">Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
					</ed:row>
				</ec:box-body>
			</ec:box>

		</ed:col>
		<ed:col size="6">
			<ec:box style="success">
				<ec:box-header>
					<h3>Dropdown icon</h3>
				</ec:box-header>
				<ec:box-body>
					<ed:row>
						<ed:col>
							<ec:dropdown label="Teste" style="primary">
								<ec:dropdown-item src="#"><ec:icon icon="heart" size="1"/> Test 1</ec:dropdown-item>
								<ec:dropdown-item src="#"><ec:icon icon="camera" size="1"/> Test 2</ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#"><ec:icon icon="briefcase" size="1"/> Test 3</ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
					</ed:row>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>

	<ed:row>
		<ed:col size="6">
			<ec:box style="success">
				<ec:box-header>
					<h3>Dropdown badge</h3>
				</ec:box-header>
				<ec:box-body>
					<ed:row>
						<ed:col>
							<ec:dropdown label="Teste" style="primary">
								<ec:dropdown-item src="#">Test 1 <ec:badge style="danger">10</ec:badge></ec:dropdown-item>
								<ec:dropdown-item src="#">Test 2 <ec:badge style="success">0</ec:badge></ec:dropdown-item>
								<ec:dropdown-separator/>
								<ec:dropdown-item src="#">Test 3 <ec:badge style="warning">1</ec:badge></ec:dropdown-item>
							</ec:dropdown>
						</ed:col>
					</ed:row>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="6">
		</ed:col>
   	</ed:row>
   	
	<ed:row>
		<ed:col size="6">
			<ec:box style="danger">
				<ec:box-header>
					<h3>Tabs</h3>
				</ec:box-header>
				<ec:box-body>
					<ec:tabs>
						<ec:tabs-item active="true" icon="briefcase" title="One">
		                    <p><strong>Augue iriure</strong> dolorum per ex, ne iisque ornatus veritus duo. Ex nobis integre 
		                    lucilius sit, pri ea falli ludus appareat. Eum quodsi fuisset id, nostro patrioque qui id. Nominati 
		                    eloquentiam in mea.</p>
		                    <p>No eum sanctus vituperata reformidans, dicant abhorreant ut pro. Duo id enim iisque praesent, 
		                    amet intellegat per et, solet referrentur eum et.</p>
						</ec:tabs-item>
						<ec:tabs-item title="two">
							<p>Tale dolor mea ex, te enim assum suscipit cum, vix aliquid omittantur in. Duo eu cibo dolorum 
							menandri, nam sumo dicit admodum ei. Ne mazim commune honestatis cum, mentitum phaedrum sit et.</p>
						</ec:tabs-item>
						<ec:tabs-item title="three">
	                    	<p>Cu cum commodo regione definiebas. Cum ea eros laboramus, audire deseruisse his at, munere 
	                    	aeterno ut quo. Et ius doming causae philosophia, vitae bonorum intellegat usu cu.</p>
						</ec:tabs-item>
					</ec:tabs>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Accordion</h3>
				</ec:box-header>
				<ec:box-body>
	                <ec:accordion>
	                	<ec:accordion-item title="one">
		                    <p><strong>Augue iriure</strong> dolorum per ex, ne iisque ornatus veritus duo. Ex nobis integre 
		                    lucilius sit, pri ea falli ludus appareat. Eum quodsi fuisset id, nostro patrioque qui id. Nominati 
		                    eloquentiam in mea.</p>
		                    <p>No eum sanctus vituperata reformidans, dicant abhorreant ut pro. Duo id enim iisque praesent, 
		                    amet intellegat per et, solet referrentur eum et.</p>
	                	</ec:accordion-item>
	                	<ec:accordion-item title="two">
		                    <p>Tale dolor mea ex, te enim assum suscipit cum, vix aliquid omittantur in. Duo eu cibo dolorum menandri, 
		                    nam sumo dicit admodum ei. Ne mazim commune honestatis cum, mentitum phaedrum sit et.</p>
	                	</ec:accordion-item>
	                	<ec:accordion-item title="three">
		                    <p>Cu cum commodo regione definiebas. Cum ea eros laboramus, audire deseruisse his at, munere aeterno ut 
		                    quo. Et ius doming causae philosophia, vitae bonorum intellegat usu cu.</p>
	                	</ec:accordion-item>
	                </ec:accordion>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>

	<ed:row>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Event</h3>
				</ec:box-header>
				<ec:box-body>
                	<ec:button label="btn default">
                		<ec:event type="click">
                			alert("click");
                		</ec:event>
                	</ec:button>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Alerts</h3>
				</ec:box-header>
				<ec:box-body>
	                <ec:alert>
	                	<strong>Warning!</strong> Best check yo self, you're not looking too good.
	                </ec:alert>
	                <ec:alert style="danger">
	                	<strong>Darger!</strong> Best check yo self, you're not looking too good.
	                </ec:alert>
	                <ec:alert style="success">
	                	<strong>Success</strong> Best check yo self, you're not looking too good.
	                </ec:alert>
	                <ec:alert style="info">
	                	<strong>Info!</strong> Best check yo self, you're not looking too good.
	                </ec:alert>
				</ec:box-body>
			</ec:box>
		</ed:col>
		
   	</ed:row>

	<ed:row>
		<ed:col size="12">
			<ec:box>
				<ec:box-header>
					<h3>Nivo</h3>
				</ec:box-header>
				<ec:box-body>
					<ec:nivo-slider>
						<ec:nivo-slider-item link="#" image="/img/slides/nivo/bg-1.jpg" title="DNS Din�mico">
							<p><strong>Augue iriure</strong> dolorum per ex, ne iisque ornatus veritus duo. Ex nobis integre 
		                    lucilius sit, pri ea falli ludus appareat. Eum quodsi fuisset id, nostro patrioque qui id. Nominati 
		                    eloquentiam in mea.</p>
						</ec:nivo-slider-item>
						<ec:nivo-slider-item link="#" image="/img/slides/nivo/bg-2.jpg" title="Calypte Cache">
							<p><strong>Augue iriure</strong> dolorum per ex, ne iisque ornatus veritus duo. Ex nobis integre 
		                    lucilius sit, pri ea falli ludus appareat. Eum quodsi fuisset id, nostro patrioque qui id. Nominati 
		                    eloquentiam in mea.</p>
						</ec:nivo-slider-item>
						<ec:nivo-slider-item link="#" image="/img/slides/nivo/bg-3.jpg" title="Brutos Framework">
							<p><strong>Augue iriure</strong> dolorum per ex, ne iisque ornatus veritus duo. Ex nobis integre 
		                    lucilius sit, pri ea falli ludus appareat. Eum quodsi fuisset id, nostro patrioque qui id. Nominati 
		                    eloquentiam in mea.</p>
						</ec:nivo-slider-item>
					</ec:nivo-slider>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>

	<ed:row>
		<ed:col size="12">
			<ec:box>
				<ec:box-header>
					<h3>Gallery</h3>
				</ec:box-header>
				<ec:box-body>
					<ec:gallery cols="4">
						<ec:gallery-filters>
							<ec:gallery-filter name="All" code="all"/>
							<ec:gallery-filter name="Filter A" code="filter_a"/>
						</ec:gallery-filters>
						<ec:gallery-images>
							<ec:gallery-image filter="filter_a" 
								icon="/img/gallery/thumbs/image-01.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/img/gallery/full/image-01-full.jpg" 
								title="The City" />
							<ec:gallery-image filter="filter_a" 
								icon="/img/gallery/thumbs/image-02.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/img/gallery/full/image-02-full.jpg" 
								title="The City"/>
							<ec:gallery-image filter="filter_a" 
								icon="/img/gallery/thumbs/image-03.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/img/gallery/full/image-03-full.jpg" 
								title="The City"/>
							<ec:gallery-image
								icon="/img/gallery/thumbs/image-04.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/img/gallery/full/image-04-full.jpg" 
								title="The City"/>
							<ec:gallery-image 
								icon="/img/gallery/thumbs/image-05.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/img/gallery/full/image-05-full.jpg" 
								title="The City"/>
							<ec:gallery-image filter="filter_a" 
								icon="/img/gallery/thumbs/image-06.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/img/gallery/full/image-06-full.jpg" 
								title="The City"/>
							<ec:gallery-image 
								icon="/img/gallery/thumbs/image-07.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/img/gallery/full/image-07-full.jpg" 
								title="The City"/>
							<ec:gallery-image filter="filter_a" 
								icon="/img/gallery/thumbs/image-08.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/img/gallery/full/image-08-full.jpg" 
								title="The City"/>
						</ec:gallery-images>
					</ec:gallery>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>
   	
	<ed:row>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Testimonial</h3>
				</ec:box-header>
				<ec:box-body>
	                <ec:testimonial name="Testimonial name" image="/img/user.png">
		                There are many variations of passages of randomised words which don't look even slightly 
		                believable. You need to be sure there isn't anything embarrassing of text.
		                <ec:testimonial-info>
		                	Name, <a href="#">www.sitename.com</a>
		                </ec:testimonial-info>
	                </ec:testimonial>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Carousel</h3>
				</ec:box-header>
				<ec:box-body>
					<ec:carousel>
						<ec:carousel-item>
							<a href="#"><ec:image src="/img/gallery/thumbs/image-01.jpg"/></a>
						</ec:carousel-item> 
						<ec:carousel-item>
							<a href="#"><ec:image src="/img/gallery/thumbs/image-02.jpg"/></a>
						</ec:carousel-item> 
						<ec:carousel-item>
							<a href="#"><ec:image src="/img/gallery/thumbs/image-03.jpg"/></a>
						</ec:carousel-item> 
					</ec:carousel>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>
