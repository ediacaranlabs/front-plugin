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
				<ed:col size="12">
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
			</ed:row>
			<ed:row>
				<ed:col size="6">
	                <h4>Button with icon</h4>
	                <p>You can add icon to any choosen buttons. See some example below:</p>
                	<ec:button label="button icon heart" type="success" size="sm" icon="heart" />
                	<ec:button label=" button icon camera" type="red" size="sm" icon="camera" />
                	<ec:button label="button icon briefcase" size="sm" outline="true" icon="briefcase"/>
		            <ec:prettify linenums="true">&lt;ec:button label=&quot;button icon heart&quot; type=&quot;success&quot; size=&quot;sm&quot; icon=&quot;heart&quot; /&gt;
&lt;ec:button label=&quot; button icon camera&quot; type=&quot;red&quot; size=&quot;sm&quot; icon=&quot;camera&quot; /&gt;
&lt;ec:button label=&quot;button icon briefcase&quot; size=&quot;sm&quot; outline=&quot;true&quot; icon=&quot;briefcase&quot;/&gt;</ec:prettify>
				</ed:col>
				<ed:col size="6">
	                <h4>More variations</h4>
	                <p>Wrap a series of buttons with <code>&lt;ec:button-group/&gt;</code></p>
	                <ec:button-group>
	                	<ec:button label="primary" type="primary"/>
	                	<ec:button label="success" type="success"/>
	                	<ec:button label="warning" type="warning"/>
	                </ec:button-group>
		            <ec:prettify linenums="true">&lt;ec:button-group&gt;
	&lt;ec:button label=&quot;primary&quot; type=&quot;primary&quot;/&gt;
	&lt;ec:button label=&quot;success&quot; type=&quot;success&quot;/&gt;
	&lt;ec:button label=&quot;warning&quot; type=&quot;warning&quot;/&gt;
&lt;/ec:button-group&gt;</ec:prettify>
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
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
				<ec:tabs id="tab0">
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
				
<ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home"
      aria-selected="true">Home</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
      aria-selected="false">Profile</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact"
      aria-selected="false">Contact</a>
  </li>
</ul>
<div class="tab-content" id="myTabContent">
  <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">...</div>
  <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">...</div>
  <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">...</div>
</div>
				
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
				</ed:col>
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
			</ed:row>
			
		</ed:container>    
    </section>
	
    <section id="content">
      <div class="container">
        <div class="row demobtn">
          <div class="span12">
            <div class="row">
              <div class="span6">
                <h4>Tab <strong>top</strong></h4>
                <ul class="nav nav-tabs">
                  <li class="active"><a href="#one" data-toggle="tab"><i class="icon-briefcase"></i> One</a></li>
                  <li><a href="#two" data-toggle="tab">Two</a></li>
                  <li><a href="#three" data-toggle="tab">Three</a></li>
                </ul>
                <div class="tab-content">
                  <div class="tab-pane active" id="one">
                    <p>
                      <strong>Augue iriure</strong> dolorum per ex, ne iisque ornatus veritus duo. Ex nobis integre lucilius sit, pri ea falli ludus appareat. Eum quodsi fuisset id, nostro patrioque qui id. Nominati eloquentiam in mea.
                    </p>
                    <p>
                      No eum sanctus vituperata reformidans, dicant abhorreant ut pro. Duo id enim iisque praesent, amet intellegat per et, solet referrentur eum et.
                    </p>
                  </div>
                  <div class="tab-pane" id="two">
                    <p>
                      Tale dolor mea ex, te enim assum suscipit cum, vix aliquid omittantur in. Duo eu cibo dolorum menandri, nam sumo dicit admodum ei. Ne mazim commune honestatis cum, mentitum phaedrum sit et.
                    </p>
                  </div>
                  <div class="tab-pane" id="three">
                    <p>
                      Cu cum commodo regione definiebas. Cum ea eros laboramus, audire deseruisse his at, munere aeterno ut quo. Et ius doming causae philosophia, vitae bonorum intellegat usu cu.
                    </p>
                  </div>
                </div>
              </div>
              <div class="span6">
                <h4>Tabs <strong>left</strong></h4>
                <div class="tabbable tabs-left">
                  <ul class="nav nav-tabs">
                    <li class="active"><a href="#topone" data-toggle="tab"> One</a></li>
                    <li><a href="#toptwo" data-toggle="tab">Two</a></li>
                    <li><a href="#topthree" data-toggle="tab">Three</a></li>
                  </ul>
                  <div class="tab-content">
                    <div class="tab-pane active" id="topone">
                      <p>
                        <strong>Augue iriure</strong> dolorum per ex, ne iisque ornatus veritus duo. Ex nobis integre lucilius sit, pri ea falli ludus appareat. Eum quodsi fuisset id, nostro patrioque qui id. Nominati eloquentiam in mea.
                      </p>
                      <p>
                        No eum sanctus vituperata reformidans, dicant abhorreant ut pro. Duo id enim iisque praesent, amet intellegat per et, solet referrentur eum et.
                      </p>
                    </div>
                    <div class="tab-pane" id="toptwo">
                      <p>
                        Tale dolor mea ex, te enim assum suscipit cum, vix aliquid omittantur in. Duo eu cibo dolorum menandri, nam sumo dicit admodum ei. Ne mazim commune honestatis cum, mentitum phaedrum sit et.
                      </p>
                    </div>
                    <div class="tab-pane" id="topthree">
                      <p>
                        Cu cum commodo regione definiebas. Cum ea eros laboramus, audire deseruisse his at, munere aeterno ut quo. Et ius doming causae philosophia, vitae bonorum intellegat usu cu.
                      </p>
                      <p>
                        Tale dolor mea ex, te enim assum suscipit cum, vix aliquid omittantur in. Duo eu cibo dolorum menandri, nam sumo dicit admodum ei. Ne mazim commune honestatis cum, mentitum phaedrum sit et.
                      </p>
                    </div>
                  </div>
                </div>
                <!-- end tab -->
              </div>
            </div>
            <!-- divider -->
            <div class="row">
              <div class="span12">
                <div class="solidline">
                </div>
              </div>
            </div>
            <!-- end divider -->
            <div class="row">
              <div class="span6">
                <h4>Tabs <strong>right</strong></h4>
                <div class="tabbable tabs-right">
                  <ul class="nav nav-tabs">
                    <li class="active"><a href="#first" data-toggle="tab"><i class="icon-briefcase"></i> One</a></li>
                    <li><a href="#second" data-toggle="tab">Two</a></li>
                    <li><a href="#third" data-toggle="tab">Three</a></li>
                  </ul>
                  <div class="tab-content">
                    <div class="tab-pane active" id="first">
                      <p>
                        <strong>Augue iriure</strong> dolorum per ex, ne iisque ornatus veritus duo. Ex nobis integre lucilius sit, pri ea falli ludus appareat. Eum quodsi fuisset id, nostro patrioque qui id. Nominati eloquentiam in mea.
                      </p>
                      <p>
                        No eum sanctus vituperata reformidans, dicant abhorreant ut pro. Duo id enim iisque praesent, amet intellegat per et, solet referrentur eum et.
                      </p>
                    </div>
                    <div class="tab-pane" id="second">
                      <p>
                        Tale dolor mea ex, te enim assum suscipit cum, vix aliquid omittantur in. Duo eu cibo dolorum menandri, nam sumo dicit admodum ei. Ne mazim commune honestatis cum, mentitum phaedrum sit et.
                      </p>
                    </div>
                    <div class="tab-pane" id="third">
                      <p>
                        Cu cum commodo regione definiebas. Cum ea eros laboramus, audire deseruisse his at, munere aeterno ut quo. Et ius doming causae philosophia, vitae bonorum intellegat usu cu.
                      </p>
                      <p>
                        Tale dolor mea ex, te enim assum suscipit cum, vix aliquid omittantur in. Duo eu cibo dolorum menandri, nam sumo dicit admodum ei. Ne mazim commune honestatis cum, mentitum phaedrum sit et.
                      </p>
                    </div>
                  </div>
                </div>
                <!-- end tab -->
              </div>
              <div class="span6">
                <h4>Accordion</h4>
                <!-- start: Accordion -->
                <div class="accordion" id="accordion2">
                  <div class="accordion-group">
                    <div class="accordion-heading">
                      <a class="accordion-toggle active" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
									<i class="icon-minus"></i> Collapsible Group Item #1 </a>
                    </div>
                    <div id="collapseOne" class="accordion-body collapse in">
                      <div class="accordion-inner">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird
                        on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer
                        farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                      </div>
                    </div>
                  </div>
                  <div class="accordion-group">
                    <div class="accordion-heading">
                      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
									<i class="icon-plus"></i> Collapsible Group Item #2 </a>
                    </div>
                    <div id="collapseTwo" class="accordion-body collapse">
                      <div class="accordion-inner">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird
                        on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer
                        farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                      </div>
                    </div>
                  </div>
                  <div class="accordion-group">
                    <div class="accordion-heading">
                      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">
									<i class="icon-plus"></i> Collapsible Group Item #3 </a>
                    </div>
                    <div id="collapseThree" class="accordion-body collapse">
                      <div class="accordion-inner">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird
                        on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer
                        farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                      </div>
                    </div>
                  </div>
                </div>
                <!--end: Accordion -->
              </div>
            </div>
            <!-- divider -->
            <div class="row">
              <div class="span12">
                <div class="solidline">
                </div>
              </div>
            </div>
            <!-- end divider -->
            <div class="row">
              <div class="span6">
                <h4>Progress bars</h4>
                <div class="progress">
                  <div class="bar bar-success bar80">
                  </div>
                </div>
                <div class="progress progress-striped">
                  <div class="bar bar20">
                  </div>
                </div>
                <div class="progress progress-striped active">
                  <div class="bar bar-success bar40">
                  </div>
                </div>
                <div class="progress">
                  <div class="bar bar-info bar35">
                  </div>
                  <div class="bar bar-success bar30">
                  </div>
                  <div class="bar bar-warning bar15">
                  </div>
                  <div class="bar bar-danger bar10">
                  </div>
                </div>
              </div>
              <div class="span6">
                <h4>Pagination</h4>
                <p>
                  Links are customizable for different circumstances. Use <code>.disabled</code> for unclickable links and <code>.active</code> to indicate the current page.
                </p>
                <div class="bs-docs-example">
                  <div class="pagination">
                    <ul>
                      <li class="disabled"><a href="#">Prev</a></li>
                      <li class="active"><a href="#">1</a></li>
                      <li><a href="#">2</a></li>
                      <li><a href="#">3</a></li>
                      <li><a href="#">Next</a></li>
                    </ul>
                  </div>
                </div>
                <pre class="prettyprint linenums">
							 &lt;div class="pagination"&gt; &lt;ul&gt; &lt;li class="disabled"&gt;&lt;a href="#"&gt;Prev&lt;/a&gt;&lt;/li&gt; &lt;li class="active"&gt;&lt;a href="#"&gt;1&lt;/a&gt;&lt;/li&gt; &lt;li&gt;&lt;a href="#"&gt;2&lt;/a&gt;&lt;/li&gt; &lt;li&gt;&lt;a href="#"&gt;3&lt;/a&gt;&lt;/li&gt; &lt;li&gt;&lt;a href="#"&gt;Next&lt;/a&gt;&lt;/li&gt; &lt;/ul&gt; &lt;/div&gt;
						</pre>
              </div>
            </div>
            <!-- divider -->
            <div class="row">
              <div class="span12">
                <div class="solidline">
                </div>
              </div>
            </div>
            <!-- end divider -->
            <div class="row">
              <div class="span6">
                <h4 class="heading">Testimonial<span></span></h4>
                <div class="wrapper">
                  <div class="testimonial">
                    <p class="text">
                      <i class="icon-quote-left icon-48"></i> There are many variations of passages of randomised words which don't look even slightly believable. You need to be sure there isn't anything embarrassing of text.
                    </p>
                    <div class="author">
                      <img src="img/dummies/testimonial-author1.png" class="img-circle bordered" alt="" />
                      <p class="name">
                        Tom Shaun Dealova
                      </p>
                      <span class="info">MBC Entertainment, <a href="#">www.sitename.com</a></span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="span6">
                <h4>Alerts</h4>
                <div class="alert">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <strong>Warning!</strong> Best check yo self, you're not looking too good.
                </div>
                <div class="alert alert-error">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <strong>Oh snap!</strong> Change a few things up and try submitting again.
                </div>
                <div class="alert alert-success">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <strong>Well done!</strong> Change a few things up and try submitting again.
                </div>
                <div class="alert alert-info">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                  <strong>Heads up!</strong> Change a few things up and try submitting again.
                </div>
              </div>
            </div>
            <!-- divider -->
            <div class="row">
              <div class="span12">
                <div class="solidline">
                </div>
              </div>
            </div>
            <!-- end divider -->
          </div>
          <!-- end span12 -->
        </div>
      </div>
    </section>	

	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>