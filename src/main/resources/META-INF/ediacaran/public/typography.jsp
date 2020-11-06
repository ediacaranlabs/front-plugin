<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@page trimDirectiveWhitespaces="true" %>
<ec:load-data file="index.json" var="pageObjects" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<ec:include uri="/includes/head.jsp"/>
</head>

<body>

	<ec:include uri="/includes/header.jsp"/>
	
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
	
	<section class="content">
		<ed:container>
			<ed:row>
				<ed:col size="4">
		            <h4>H1-H6 Heading style</h4>
		            <h1>Heading H1</h1>
		            <h2>Heading H2</h2>
		            <h3>Heading H3</h3>
		            <h4>Heading H4</h4>
		            <h5>Heading H5</h5>
		            <h6>Heading H6</h6>
				</ed:col>
				<ed:col size="4">
		            <h4>Example of paragraph</h4>
		            <p><strong>Lorem ipsum dolor sit amet</strong>, consetetur sadipscing elitr, 
		            sed diam non mod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.</p>
		            <p class="lead">At vero eos et accusam et justo duo dolores et eabum.</p>
		            <p><em>Consetetur sadipscing elitr, sed diam non mod tempor invidunt ut labore et dolore magna 
		            aliquyam erat, sed diam voluptua. </em></p>
		            <p><small>Consetetur sadipscing elitr, sed diam non mod tempor invidunt ut labore et dolore magna 
		            aliquyam erat, sed diam voluptua. </small></p>
				</ed:col>
				<ed:col size="4">
		            <h4>Float image in paragraph</h4>
		            <img src="img/dummies/dummy-1.jpg" alt="" class="align-left" />
		            <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam non mod tempor invidunt ut 
		            labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et eabum. Stet clita kasd gubergren, no sea takimata sanctus
		            est Lorem ipsum dolor sit amet</p>
				</ed:col>
			</ed:row>
			
			<ed:row>
				<ed:col size="6">
		            <h4>Description</h4>
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
				</ed:col>
				<ed:col size="6">
		            <h4>Horizontal Description</h4>
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
				</ed:col>
			</ed:row>
			
			<ed:row>
				<ed:col size="3">
		            <h4>Unordered Lists</h4>
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
				</ed:col>
				<ed:col size="3">
		           <h4>Unstyled Lists</h4>
		           <ul class="unstyled">
		             <li>Lorem ipsum dolor sit amet</li>
		             <li>Consectetur adipiscing elit</li>
		             <li>Integer molestie lorem at massa</li>
		             <li>Facilisis in pretium nisl aliquet</li>
		             <li>Nulla volutpat aliquam velit
		               <ul>
		                 <li>Phasellus iaculis neque</li>
		                 <li>Purus sodales ultricies</li>
		                 <li>Vestibulum laoreet porttitor sem</li>
		                 <li>Ac tristique libero volutpat at</li>
		               </ul>
		             </li>
		             <li>Faucibus porta lacus fringilla vel</li>
		             <li>Aenean sit amet erat nunc</li>
		             <li>Eget porttitor lorem</li>
		           </ul>
				</ed:col>
				<ed:col size="3">
		            <h4>Ordered Lists</h4>
		            <ol>
		              <li>Lorem ipsum dolor sit amet</li>
		              <li>Consectetur adipiscing elit</li>
		              <li>Integer molestie lorem at massa</li>
		              <li>Facilisis in pretium nisl aliquet</li>
		              <li>Nulla volutpat aliquam velit
		                <ol>
		                  <li>Phasellus iaculis neque</li>
		                  <li>Purus sodales ultricies</li>
		                  <li>Vestibulum laoreet porttitor sem</li>
		                  <li>Ac tristique libero volutpat at</li>
		                </ol>
		              </li>
		              <li>Faucibus porta lacus fringilla vel</li>
		              <li>Aenean sit amet erat nunc</li>
		              <li>Eget porttitor lorem</li>
		            </ol>
				</ed:col>
				<ed:col size="3">
		            <h4>Unstyled Lists</h4>
		            <ol class="unstyled">
		              <li>Lorem ipsum dolor sit amet</li>
		              <li>Consectetur adipiscing elit</li>
		              <li>Integer molestie lorem at massa</li>
		              <li>Facilisis in pretium nisl aliquet</li>
		              <li>Nulla volutpat aliquam velit
		                <ol>
		                  <li>Phasellus iaculis neque</li>
		                  <li>Purus sodales ultricies</li>
		                  <li>Vestibulum laoreet porttitor sem</li>
		                  <li>Ac tristique libero volutpat at</li>
		                </ol>
		              </li>
		              <li>Faucibus porta lacus fringilla vel</li>
		              <li>Aenean sit amet erat nunc</li>
		              <li>Eget porttitor lorem</li>
		            </ol>
				</ed:col>
			</ed:row>			

			<ed:row>
				<ed:col size="12">
		            <h4>Inline Lists</h4>
		            <ul class="inline">
		              <li>1-List item one</li>
		              <li>2-List item two</li>
		              <li>3-List item three</li>
		              <li>4-List item four</li>
		              <li>5-List item five</li>
		              <li>6-List item six</li>
		              <li>7-List item seven</li>
		              <li>8-List item eight</li>
		              <li>9-List item nine</li>
		              <li>10-List item ten</li>
		            </ul>
				</ed:col>
			</ed:row>

			<ed:row>
				<ed:col size="4">
		            <h4>Blockquote</h4>
		            <blockquote>
		              <i class="icon-quote-left icon-2x"></i> Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam non mod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
		            </blockquote>
				</ed:col>
				<ed:col size="4">
		            <h4>Blockquote and cite</h4>
		            <blockquote>
		              <i class="icon-quote-left icon-2x"></i> Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam non mod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. <cite>John doe, Awesome Inc.</cite>
		            </blockquote>
				</ed:col>
				<ed:col size="4">
		            <h4>Pullquote left and right</h4>
		            <span class="pullquote-left">
						Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam non mod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. </span>
		            <span class="pullquote-right margintop10">
						Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam non mod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. </span>
				</ed:col>
			</ed:row>

			<ed:row>
				<ed:col size="12">
		            <h4>Preformatted text</h4>
		            <pre class="prettyprint linenums">
							 &lt;div class="span12"&gt;<br />
							 &nbsp;&nbsp;&lt;p&gt;Lorem ipsum dolor sit amet, consetetur sadipscing elitr..&lt;/p&gt;<br />
							 &nbsp;&nbsp;&lt;p&gt;Lorem ipsum dolor sit amet, consetetur sadipscing elitr..&lt;/p&gt;<br />
							 &nbsp;&nbsp;&lt;p&gt;Lorem ipsum dolor sit amet, consetetur sadipscing elitr..&lt;/p&gt;<br />
							 &lt;/div&gt;
						</pre>
				
				</ed:col>
			</ed:row>

		</ed:container>
	</section>

	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>