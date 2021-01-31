<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
	
	<section class="inner-headline">
		<ed:container>
			<ed:row>
				<ed:col size="4">
					<div class="inner-heading">
						<h2>Typography</h2>
					</div>
				</ed:col>
				<ed:col size="8">
					<ec:breadcrumb title="Typography">
						<ec:breadcrumb-path icon="home" text="" lnk="#" />
						<ec:breadcrumb-path icon="" text="Features" lnk="#" />
					</ec:breadcrumb>
				</ed:col>
			</ed:row>
		</ed:container>
	</section>

<!-- 
   	<ed:row>
		<ed:col size="4">
			<ec:box>
				<ec:box-header>
					<h3></h3>
				</ec:box-header>
				<ec:box-body>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="4">
			<ec:box style="info">
				<ec:box-header>
					<h3></h3>
				</ec:box-header>
				<ec:box-body>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="4">
			<ec:box>
				<ec:box-header>
					<h3></h3>
				</ec:box-header>
				<ec:box-body>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>
 -->	
	
   	<ed:row>
		<ed:col size="4">
			<ec:box>
				<ec:box-header>
					<h3>H1-H6 Heading style</h3>
				</ec:box-header>
				<ec:box-body>
		            <h1>Heading H1</h1>
		            <h2>Heading H2</h2>
		            <h3>Heading H3</h3>
		            <h4>Heading H4</h4>
		            <h5>Heading H5</h5>
		            <h6>Heading H6</h6>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="4">
			<ec:box style="info">
				<ec:box-header>
					<h3>Example of paragraph</h3>
				</ec:box-header>
				<ec:box-body>
		            <p><strong>Lorem ipsum dolor sit amet</strong>, consetetur sadipscing elitr, 
		            sed diam non mod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</p>
		            <p class="lead">At vero eos et accusam et justo duo dolores et eabum.</p>
		            <p><em>Consetetur sadipscing elitr, sed diam non mod tempor invidunt ut labore et dolore magna 
		            aliquyam erat, sed diam voluptua. </em></p>
		            <p><small>Consetetur sadipscing elitr, sed diam non mod tempor invidunt ut labore et dolore magna 
		            aliquyam erat, sed diam voluptua. </small></p>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="4">
			<ec:box style="info">
				<ec:box-header>
					<h3>Float image in paragraph</h3>
				</ec:box-header>
				<ec:box-body>
					<ec:image src="/plugins/ediacaran/front/admin/img/dummy-1.jpg" align="left" />
		            <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam non mod tempor invidunt ut 
		            labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et eabum. Stet clita kasd gubergren, no sea takimata sanctus
		            est Lorem ipsum dolor sit amet</p>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>
	
   	<ed:row>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Description</h3>
				</ec:box-header>
				<ec:box-body>
		            <ec:description-list>
		            	<ec:description title="Description lists">
		            		<p>description list is perfect for defining terms.</p>
		            	</ec:description>
		            	<ec:description title="Euismod">
			              <p>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</p>
			              <p>Donec id elit non mi porta gravida at eget metus.</p>
		            	</ec:description>
		            	<ec:description title="Malesuada porta">
			              <p>Etiam porta sem malesuada magna mollis euismod.</p>
		            	</ec:description>
		            	<ec:description title="Felis euismod semper eget lacinia">
			              <p>Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo 
			              sit amet risus.</p>
		            	</ec:description>
		            </ec:description-list>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box style="info">
				<ec:box-header>
					<h3>Horizontal Description</h3>
				</ec:box-header>
				<ec:box-body>
		            <ec:description-list style="horizontal" >
		            	<ec:description title="Description lists">
		            		<p>description list is perfect for defining terms.</p>
		            	</ec:description>
		            	<ec:description title="Euismod">
			              <p>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</p>
			              <p>Donec id elit non mi porta gravida at eget metus.</p>
		            	</ec:description>
		            	<ec:description title="Malesuada porta">
			              <p>Etiam porta sem malesuada magna mollis euismod.</p>
		            	</ec:description>
		            	<ec:description title="Felis euismod semper eget lacinia">
			              <p>Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo 
			              sit amet risus.</p>
		            	</ec:description>
		            </ec:description-list>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>
	
   	<ed:row>
		<ed:col size="3">
			<ec:box>
				<ec:box-header>
					<h3>Unordered Lists</h3>
				</ec:box-header>
				<ec:box-body>
		            <ec:list>
		              <ec:list-item>Lorem ipsum dolor sit amet</ec:list-item>
		              <ec:list-item>Consectetur adipiscing elit</ec:list-item>
		              <ec:list-item>Integer molestie lorem at massa</ec:list-item>
		              <ec:list-item>Facilisis in pretium nisl aliquet</ec:list-item>
		              <ec:list-item>Nulla volutpat aliquam velit
		                <ec:list>
		                  <ec:list-item>Phasellus iaculis neque</ec:list-item>
		                  <ec:list-item>Purus sodales ultricies</ec:list-item>
		                  <ec:list-item>Vestibulum laoreet porttitor sem</ec:list-item>
		                  <ec:list-item>Ac tristique libero volutpat at</ec:list-item>
		                </ec:list>
		              </ec:list-item>
		              <ec:list-item>Faucibus porta lacus fringilla vel</ec:list-item>
		              <ec:list-item>Aenean sit amet erat nunc</ec:list-item>
		              <ec:list-item>Eget porttitor lorem</ec:list-item>
		            </ec:list>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="3">
			<ec:box style="info">
				<ec:box-header>
					<h3>Unstyled Lists</h3>
				</ec:box-header>
				<ec:box-body>
		            <ec:list style="unstyled">
		              <ec:list-item>Lorem ipsum dolor sit amet</ec:list-item>
		              <ec:list-item>Consectetur adipiscing elit</ec:list-item>
		              <ec:list-item>Integer molestie lorem at massa</ec:list-item>
		              <ec:list-item>Facilisis in pretium nisl aliquet</ec:list-item>
		              <ec:list-item>Nulla volutpat aliquam velit
		                <ec:list>
		                  <ec:list-item>Phasellus iaculis neque</ec:list-item>
		                  <ec:list-item>Purus sodales ultricies</ec:list-item>
		                  <ec:list-item>Vestibulum laoreet porttitor sem</ec:list-item>
		                  <ec:list-item>Ac tristique libero volutpat at</ec:list-item>
		                </ec:list>
		              </ec:list-item>
		              <ec:list-item>Faucibus porta lacus fringilla vel</ec:list-item>
		              <ec:list-item>Aenean sit amet erat nunc</ec:list-item>
		              <ec:list-item>Eget porttitor lorem</ec:list-item>
		            </ec:list>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="3">
			<ec:box>
				<ec:box-header>
					<h3>Ordered Lists</h3>
				</ec:box-header>
				<ec:box-body>
		            <ec:list style="ordered">
		              <ec:list-item>Lorem ipsum dolor sit amet</ec:list-item>
		              <ec:list-item>Consectetur adipiscing elit</ec:list-item>
		              <ec:list-item>Integer molestie lorem at massa</ec:list-item>
		              <ec:list-item>Facilisis in pretium nisl aliquet</ec:list-item>
		              <ec:list-item>Nulla volutpat aliquam velit
		                <ec:list style="ordered">
		                  <ec:list-item>Phasellus iaculis neque</ec:list-item>
		                  <ec:list-item>Purus sodales ultricies</ec:list-item>
		                  <ec:list-item>Vestibulum laoreet porttitor sem</ec:list-item>
		                  <ec:list-item>Ac tristique libero volutpat at</ec:list-item>
		                </ec:list>
		              </ec:list-item>
		              <ec:list-item>Faucibus porta lacus fringilla vel</ec:list-item>
		              <ec:list-item>Aenean sit amet erat nunc</ec:list-item>
		              <ec:list-item>Eget porttitor lorem</ec:list-item>
		            </ec:list>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="3">
			<ec:box>
				<ec:box-header>
					<h3>Unstyled Lists</h3>
				</ec:box-header>
				<ec:box-body>
		            <ec:list style="unstyled">
		              <ec:list-item>Lorem ipsum dolor sit amet</ec:list-item>
		              <ec:list-item>Consectetur adipiscing elit</ec:list-item>
		              <ec:list-item>Integer molestie lorem at massa</ec:list-item>
		              <ec:list-item>Facilisis in pretium nisl aliquet</ec:list-item>
		              <ec:list-item>Nulla volutpat aliquam velit
		                <ec:list style="ordered">
		                  <ec:list-item>Phasellus iaculis neque</ec:list-item>
		                  <ec:list-item>Purus sodales ultricies</ec:list-item>
		                  <ec:list-item>Vestibulum laoreet porttitor sem</ec:list-item>
		                  <ec:list-item>Ac tristique libero volutpat at</ec:list-item>
		                </ec:list>
		              </ec:list-item>
		              <ec:list-item>Faucibus porta lacus fringilla vel</ec:list-item>
		              <ec:list-item>Aenean sit amet erat nunc</ec:list-item>
		              <ec:list-item>Eget porttitor lorem</ec:list-item>
		            </ec:list>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>
	
   	<ed:row>
		<ed:col size="12">
			<ec:box>
				<ec:box-header>
					<h3>Inline Lists</h3>
				</ec:box-header>
				<ec:box-body>
		            <ec:list style="inline">
		              <ec:list-item>1-List item one</ec:list-item>
		              <ec:list-item>2-List item two</ec:list-item>
		              <ec:list-item>3-List item three</ec:list-item>
		              <ec:list-item>4-List item four</ec:list-item>
		              <ec:list-item>5-List item five</ec:list-item>
		              <ec:list-item>6-List item six</ec:list-item>
		              <ec:list-item>7-List item seven</ec:list-item>
		              <ec:list-item>8-List item eight</ec:list-item>
		              <ec:list-item>9-List item nine</ec:list-item>
		              <ec:list-item>10-List item ten</ec:list-item>
		            </ec:list>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>
	
   	<ed:row>
		<ed:col size="4">
			<ec:box>
				<ec:box-header>
					<h3>Blockquote</h3>
				</ec:box-header>
				<ec:box-body>
		            <ec:blockquote>
		            	Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam non mod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
	            	</ec:blockquote>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="4">
			<ec:box style="info">
				<ec:box-header>
					<h3>Blockquote and cite</h3>
				</ec:box-header>
				<ec:box-body>
		            <ec:blockquote cite="John doe, Awesome Inc.">
		            	Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam non mod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
	            	</ec:blockquote>
				</ec:box-body>
			</ec:box>
		</ed:col>
		<ed:col size="4">
			<ec:box>
				<ec:box-header>
					<h3>Pullquote left and right</h3>
				</ec:box-header>
				<ec:box-body>
		            <span class="pullquote-left">
						Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam non mod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. </span>
		            <span class="pullquote-right margintop10">
						Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam non mod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. </span>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>
	
   	<ed:row>
		<ed:col size="12">
			<ec:box>
				<ec:box-header>
					<h3>Preformatted text</h3>
				</ec:box-header>
				<ec:box-body>
		            <ec:prettify linenums="true"><div class="col-12">
	<p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr..</p>
	<p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr..</p>
	<p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr..</p>
</div></ec:prettify>
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>
