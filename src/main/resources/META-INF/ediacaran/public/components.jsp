<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
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
		</ed:container>
	</section>
	
    <section class="content">
		<ed:container>
			<ed:row>
				<ed:col size="6">
                	<h4>Button types</h4>
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
				</ed:col>
				<ed:col size="6">
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
				</ed:col>
			</ed:row>

			<ed:row>
				<ed:col size="6">
	                <h4>Button with icon</h4>
	                <p>You can add icon to any choosen buttons. See some example below:</p>
                	<ec:button label="button icon heart" type="success" size="sm" icon="heart" />
                	<ec:button label=" button icon camera" type="red" size="sm" icon="camera" />
                	<ec:button label="button icon briefcase" size="sm" outline="true" icon="briefcase"/>
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><button label="button icon heart" type="success" size="sm" icon="heart" />
<button label=" button icon camera" type="red" size="sm" icon="camera" />
<button label="button icon briefcase" size="sm" outline="true" icon="briefcase"/></ec:prettify>
				</ed:col>
			</ed:row>				
			
			<ed:row>
				<ed:col size="6">
	                <h4>More variations</h4>
	                <p>Wrap a series of buttons with <code>&lt;ec:button-group/&gt;</code></p>
	                <ec:button-group>
	                	<ec:button label="primary" type="primary"/>
	                	<ec:button label="success" type="success"/>
	                	<ec:button label="warning" type="warning"/>
	                </ec:button-group>
				</ed:col>
				<ed:col size="6">
		            <ec:prettify linenums="true"><button label="primary" type="primary"/>
<button label="success" type="success"/>
<button label="warning" type="warning"/></ec:prettify>
				</ed:col>
			</ed:row>
			
			<ed:row>
				<ed:col size="6">
	                <h4>Button sizes</h4>
	                <p>There are 5 button sizes: lg and sm.</p>
                	<ec:button label="Large button" size="lg"/>
                	<ec:button label="Small button" size="sm" type="success"/>
                	<ec:button label="btn block level" block="true" type="warning"/>
				</ed:col>
				<ed:col size="6">
		            <ec:prettify linenums="true"><button label="Large button" size="lg"/>
<button label="Small button" size="sm" type="success"/>
<button label="btn block level" block="true" type="warning"/></ec:prettify>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
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
				</ed:col>
				<ed:col size="6">
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
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
	                <h4>Accordion</h4>
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
				</ed:col>
				<ed:col size="6">
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
				</ed:col>
			</ed:row>
			
			<ed:row>
				<ed:col size="6">
                	<h4>Event</h4>
                	<ec:button label="btn default">
                		<ec:event type="click">
                			alert("click");
                		</ec:event>
                	</ec:button>
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><button label="btn default">
	<event type="click">
		alert("click");
	</event>
</button></ec:prettify>
				</ed:col>
			</ed:row>

			<ed:row>
				<ed:col size="6">
	                <h4>Alerts</h4>
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
				</ed:col>
				<ed:col size="6">
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
				</ed:col>
			</ed:row>

			<ed:row>
				<ed:col size="6">
	                <h4>Testimonial</h4>
	                <ec:testimonial name="Testimonial name" image="/plugins/ediacaran/front/templates/default_template/img/testimonial/author1.png">
		                There are many variations of passages of randomised words which don't look even slightly 
		                believable. You need to be sure there isn't anything embarrassing of text.
		                <ec:testimonial-info>
		                	Name, <a href="#">www.sitename.com</a>
		                </ec:testimonial-info>
	                </ec:testimonial>
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><testimonial name="Testimonial name" image="img/testimonial/author1.png">
	There are many variations of passages of randomised words which don't look even slightly 
	believable. You need to be sure there isn't anything embarrassing of text.
	<testimonial-info>
		Name, <a href="#">www.sitename.com</a>
	</testimonial-info>
</testimonial></ec:prettify>
				</ed:col>
			</ed:row>
			
		</ed:container>    
    </section>
	 
	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>