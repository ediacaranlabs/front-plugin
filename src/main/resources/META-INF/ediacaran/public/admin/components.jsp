<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
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
                	<ec:button label="btn default" />
                	<ec:button label="btn primary" type="primary"/>
                	<ec:button label="btn secondary"  type="secondary"/>
                	<ec:button label="btn success"  type="success"/>
                	<ec:button label="btn danger"  type="danger"/>
                	<ec:button label="btn warning"  type="warning"/>
                	<ec:button label="btn info"  type="info"/>
                	<ec:button label="btn light"  type="light"/>
                	<ec:button label="btn dark"  type="dark"/>
                	<ec:button label="btn link"  type="link"/>
                	<ec:button label="btn outline" outline="true"/>
				</ec:box-body>
				<ec:box-footer>
					<ec:prettify linenums="true"><button label="btn default" />
<button label="btn primary" type="primary"/>
<button label="btn secondary"  type="secondary"/>
<button label="btn success"  type="success"/>
<button label="btn danger"  type="danger"/>
<button label="btn warning"  type="warning"/>
<button label="btn info"  type="info"/>
<button label="btn light"  type="light"/>
<button label="btn dark"  type="dark"/>
<button label="btn link"  type="link"/>
<button label="btn outline" outline="true"/></ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box style="info">
				<ec:box-header>
					<h3>Button with icon</h3>
				</ec:box-header>
				<ec:box-body>
	                <p>You can add icon to any choosen buttons. See some example below:</p>
                	<ec:button label="button icon heart" type="success" size="sm" icon="heart" />
                	<ec:button label=" button icon camera" type="red" size="sm" icon="camera" />
                	<ec:button label="button icon briefcase" size="sm" outline="true" icon="briefcase"/>
				</ec:box-body>
				<ec:box-footer>
					<ec:prettify linenums="true"><button label="button icon heart" type="success" size="sm" icon="heart" />
<button label=" button icon camera" type="red" size="sm" icon="camera" />
<button label="button icon briefcase" size="sm" outline="true" icon="briefcase"/></ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
   	</ed:row>

<!-- 

	<ed:row>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3></h3>
				</ec:box-header>
				<ec:box-body>
				</ec:box-body>
				<ec:box-footer>
				</ec:box-footer>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3></h3>
				</ec:box-header>
				<ec:box-body>
				</ec:box-body>
				<ec:box-footer>
				</ec:box-footer>
			</ec:box>
		</ed:col>
   	</ed:row>

 -->	
	<ed:row>
		<ed:col size="6">
			<ec:box style="success">
				<ec:box-header>
					<h3>More variations</h3>
				</ec:box-header>
				<ec:box-body>
	                <p>Wrap a series of buttons with <code>&lt;ec:button-group/&gt;</code></p>
	                <ec:button-group>
	                	<ec:button label="primary" type="primary"/>
	                	<ec:button label="success" type="success"/>
	                	<ec:button label="warning" type="warning"/>
	                </ec:button-group>
				</ec:box-body>
				<ec:box-footer>
		            <ec:prettify linenums="true"><button label="primary" type="primary"/>
<button label="success" type="success"/>
<button label="warning" type="warning"/></ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box style="warning">
				<ec:box-header>
					<h3>Button sizes</h3>
				</ec:box-header>
				<ec:box-body>
	                <p>There are 5 button sizes: lg and sm.</p>
                	<ec:button label="Large button" size="lg"/>
                	<ec:button label="Small button" size="sm" type="success"/>
                	<ec:button label="btn block level" block="true" type="warning"/>
				</ec:box-body>
				<ec:box-footer>
		            <ec:prettify linenums="true"><button label="Large button" size="lg"/>
<button label="Small button" size="sm" type="success"/>
<button label="btn block level" block="true" type="warning"/></ec:prettify>
				</ec:box-footer>
			</ec:box>
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
				<ec:box-footer>
		            <ec:prettify linenums="true"><tabs>
	<tabs-item active="true" icon="briefcase" title="One">
        <p><strong>Augue iriure</strong> dolorum per ex, ne iisque ornatus veritus duo. Ex nobis integre 
        lucilius sit, pri ea falli ludus appareat. Eum quodsi fuisset id, nostro patrioque qui id. Nominati 
        eloquentiam in mea.</p>
        <p>No eum sanctus vituperata reformidans, dicant abhorreant ut pro. Duo id enim iisque praesent, 
        amet intellegat per et, solet referrentur eum et.</p>
	</tabs-item>
	<tabs-item title="two">
		<p>Tale dolor mea ex, te enim assum suscipit cum, vix aliquid omittantur in. Duo eu cibo dolorum 
		menandri, nam sumo dicit admodum ei. Ne mazim commune honestatis cum, mentitum phaedrum sit et.</p>
	</tabs-item>
	<tabs-item title="three">
       	<p>Cu cum commodo regione definiebas. Cum ea eros laboramus, audire deseruisse his at, munere 
       	aeterno ut quo. Et ius doming causae philosophia, vitae bonorum intellegat usu cu.</p>
	</tabs-item>
</tabs></ec:prettify>				
				</ec:box-footer>
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
				<ec:box-footer>
		            <ec:prettify linenums="true"><accordion>
	<accordion-item title="one">
	     <p><strong>Augue iriure</strong> dolorum per ex, ne iisque ornatus veritus duo. Ex nobis integre 
	     lucilius sit, pri ea falli ludus appareat. Eum quodsi fuisset id, nostro patrioque qui id. Nominati 
	     eloquentiam in mea.</p>
	     <p>No eum sanctus vituperata reformidans, dicant abhorreant ut pro. Duo id enim iisque praesent, 
	     amet intellegat per et, solet referrentur eum et.</p>
	</accordion-item>
	<accordion-item title="two">
	     <p>Tale dolor mea ex, te enim assum suscipit cum, vix aliquid omittantur in. Duo eu cibo dolorum menandri, 
	     nam sumo dicit admodum ei. Ne mazim commune honestatis cum, mentitum phaedrum sit et.</p>
	</accordion-item>
	<accordion-item title="three">
	     <p>Cu cum commodo regione definiebas. Cum ea eros laboramus, audire deseruisse his at, munere aeterno ut 
	     quo. Et ius doming causae philosophia, vitae bonorum intellegat usu cu.</p>
	</accordion-item>
</accordion></ec:prettify>				
				</ec:box-footer>
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
				<ec:box-footer>
					<ec:prettify linenums="true"><button label="btn default">
	<event type="click">
		alert("click");
	</event>
</button></ec:prettify>
				</ec:box-footer>
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
				<ec:box-footer>
					<ec:prettify linenums="true"><alert>
	<strong>Warning!</strong> Best check yo self, you're not looking too good.
</alert>
<alert style="error">
	<strong>Error!</strong> Best check yo self, you're not looking too good.
</alert>
<alert style="success">
	<strong>Success</strong> Best check yo self, you're not looking too good.
</alert>
<alert style="info">
	<strong>Info!</strong> Best check yo self, you're not looking too good.
</alert></ec:prettify>
				</ec:box-footer>
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
	                <ec:testimonial name="Testimonial name" image="/plugins/ediacaran/front/templates/default_template/img/testimonial/author1.png">
		                There are many variations of passages of randomised words which don't look even slightly 
		                believable. You need to be sure there isn't anything embarrassing of text.
		                <ec:testimonial-info>
		                	Name, <a href="#">www.sitename.com</a>
		                </ec:testimonial-info>
	                </ec:testimonial>
				</ec:box-body>
				<ec:box-footer>
					<ec:prettify linenums="true"><testimonial name="Testimonial name" image="img/testimonial/author1.png">
	There are many variations of passages of randomised words which don't look even slightly 
	believable. You need to be sure there isn't anything embarrassing of text.
	<testimonial-info>
		Name, <a href="#">www.sitename.com</a>
	</testimonial-info>
</testimonial></ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Nivo</h3>
				</ec:box-header>
				<ec:box-body>
					<ec:nivo-slider>
						<ec:nivo-slider-tem link="#" image="/plugins/ediacaran/front/templates/default_template/img/slides/nivo/bg-1.jpg" title="DNS Dinâmico"/>
						<ec:nivo-slider-tem link="#" image="/plugins/ediacaran/front/templates/default_template/img/slides/nivo/bg-2.jpg" title="Calypte Cache"/>
						<ec:nivo-slider-tem link="#" image="/plugins/ediacaran/front/templates/default_template/img/slides/nivo/bg-3.jpg" title="Brutos Framework"/>
					</ec:nivo-slider>
				</ec:box-body>
				<ec:box-footer>
					<ec:prettify linenums="true"><nivo-slider>
	<nivo-slider-tem link="#" image="/plugins/ediacaran/front/templates/default_template/img/slides/nivo/bg-1.jpg" title="DNS Dinâmico"/>
	<nivo-slider-tem link="#" image="/plugins/ediacaran/front/templates/default_template/img/slides/nivo/bg-2.jpg" title="Calypte Cache"/>
	<nivo-slider-tem link="#" image="/plugins/ediacaran/front/templates/default_template/img/slides/nivo/bg-3.jpg" title="Brutos Framework"/>
</nivo-slider></ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
   	</ed:row>
   	
	<ed:row>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Carousel</h3>
				</ec:box-header>
				<ec:box-body>
					<ec:carousel>
						<ec:carousel-item>
							<a href="#"><ec:image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-01.jpg"/></a>
						</ec:carousel-item> 
						<ec:carousel-item>
							<a href="#"><ec:image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-02.jpg"/></a>
						</ec:carousel-item> 
						<ec:carousel-item>
							<a href="#"><ec:image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-03.jpg"/></a>
						</ec:carousel-item> 
					</ec:carousel>
				</ec:box-body>
				<ec:box-footer>
					<ec:prettify linenums="true"><carousel>
	<carousel-item>
		<a href="#"><image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-01.jpg"/></a>
	</carousel-item> 
	<carousel-item>
		<a href="#"><image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-02.jpg"/></a>
	</carousel-item> 
	<carousel-item>
		<a href="#"><image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-03.jpg"/></a>
	</carousel-item> 
</carousel></ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Gallery</h3>
				</ec:box-header>
				<ec:box-body>
					<ec:gallery cols="4">
						<ec:gallery-images>
							<ec:gallery-image filter="all" 
								icon="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-01.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/plugins/ediacaran/front/templates/default_template/img/gallery/full/image-01-full.jpg" 
								title="The City"/>
							<ec:gallery-image filter="all" 
								icon="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-02.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/plugins/ediacaran/front/templates/default_template/img/gallery/full/image-02-full.jpg" 
								title="The City"/>
							<ec:gallery-image filter="all" 
								icon="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-03.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/plugins/ediacaran/front/templates/default_template/img/gallery/full/image-03-full.jpg" 
								title="The City"/>
							<ec:gallery-image filter="all" 
								icon="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-04.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/plugins/ediacaran/front/templates/default_template/img/gallery/full/image-04-full.jpg" 
								title="The City"/>
							<ec:gallery-image filter="all" 
								icon="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-05.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/plugins/ediacaran/front/templates/default_template/img/gallery/full/image-05-full.jpg" 
								title="The City"/>
							<ec:gallery-image filter="all" 
								icon="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-06.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/plugins/ediacaran/front/templates/default_template/img/gallery/full/image-06-full.jpg" 
								title="The City"/>
							<ec:gallery-image filter="all" 
								icon="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-07.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/plugins/ediacaran/front/templates/default_template/img/gallery/full/image-07-full.jpg" 
								title="The City"/>
							<ec:gallery-image filter="all" 
								icon="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-08.jpg" 
								text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
								src="/plugins/ediacaran/front/templates/default_template/img/gallery/full/image-08-full.jpg" 
								title="The City"/>
						</ec:gallery-images>
					</ec:gallery>
				</ec:box-body>
				<ec:box-footer>
					<ec:prettify linenums="true"><gallery cols="4">
	<gallery-images>
		<gallery-image filter="all" 
			icon="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-01.jpg" 
			text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
			src="/plugins/ediacaran/front/templates/default_template/img/gallery/full/image-01-full.jpg" 
			title="The City"/>
		<gallery-image filter="all" 
			icon="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-02.jpg" 
			text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
			src="/plugins/ediacaran/front/templates/default_template/img/gallery/full/image-02-full.jpg" 
			title="The City"/>
		...
	</gallery-images>
</gallery></ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
   	</ed:row>   	