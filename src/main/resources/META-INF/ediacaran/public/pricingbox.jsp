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
						<h2>Pricing boxes</h2>
					</div>
				</ed:col>
				<ed:col size="8">
					<ec:breadcrumb title="Components">
						<ec:breadcrumb-path icon="home" text="" lnk="#" />
						<ec:breadcrumb-path text="Features" lnk="#" />
					</ec:breadcrumb>
				</ed:col>
			</ed:row>
		</ed:container>
	</section>
	
    <section class="content">
		<ed:container>
			<ed:row>
				<ed:col size="12">
		            <h4>Example on <strong>3 columns</strong></h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="4">
		            <ec:price-box>
		            	<ec:price-box-header>
		            		<h3>Very <strong>Basic</strong></h3>
		            	</ec:price-box-header>
		            	<ec:price-box-terms>
		            		<h6>$15.00 / Month</h6>
		            	</ec:price-box-terms>
		            	<ec:price-box-body>
		            		<ec:price-box-item checked="true">applications</ec:price-box-item>
		            		<ec:price-box-item checked="true">upport available</ec:price-box-item>
		            		<ec:price-box-item>No hidden fees</ec:price-box-item>
		            		<ec:price-box-item>Free</ec:price-box-item>
		            		<ec:price-box-item>Stop anytime easily</ec:price-box-item>
		            	</ec:price-box-body>
		            	<ec:price-box-footer>
		            		<ec:button label="Register now" icon="bolt">
		            			<ec:event type="click">
		            				alert("Register!");
		            			</ec:event>
		            		</ec:button>
		            	</ec:price-box-footer>
		            </ec:price-box>
				</ed:col>
				<ed:col size="4">
		            <ec:price-box attractiveness="special">
		            	<ec:price-box-header>
		            		<h3>Good <strong>Choice</strong></h3>
		            	</ec:price-box-header>
		            	<ec:price-box-terms>
		            		<h6>$25.00 / Month</h6>
		            	</ec:price-box-terms>
		            	<ec:price-box-body>
		            		<ec:price-box-item checked="true">applications</ec:price-box-item>
		            		<ec:price-box-item checked="true">upport available</ec:price-box-item>
		            		<ec:price-box-item checked="true">No hidden fees</ec:price-box-item>
		            		<ec:price-box-item>Free</ec:price-box-item>
		            		<ec:price-box-item>Stop anytime easily</ec:price-box-item>
		            	</ec:price-box-body>
		            	<ec:price-box-footer>
		            		<ec:button label="Register now" icon="bolt">
		            			<ec:event type="click">
		            				alert("Register!");
		            			</ec:event>
		            		</ec:button>
		            	</ec:price-box-footer>
		            </ec:price-box>
				</ed:col>
				<ed:col size="4">
		            <ec:price-box>
		            	<ec:price-box-header>
		            		<h3>Just <strong>Happy</strong></h3>
		            	</ec:price-box-header>
		            	<ec:price-box-terms>
		            		<h6>$35.00 / Month</h6>
		            	</ec:price-box-terms>
		            	<ec:price-box-body>
		            		<ec:price-box-item checked="true">applications</ec:price-box-item>
		            		<ec:price-box-item checked="true">upport available</ec:price-box-item>
		            		<ec:price-box-item checked="true">No hidden fees</ec:price-box-item>
		            		<ec:price-box-item checked="true">Free</ec:price-box-item>
		            		<ec:price-box-item checked="true">Stop anytime easily</ec:price-box-item>
		            	</ec:price-box-body>
		            	<ec:price-box-footer>
		            		<ec:button label="Register now" icon="bolt">
		            			<ec:event type="click">
		            				alert("Register!");
		            			</ec:event>
		            		</ec:button>
		            	</ec:price-box-footer>
		            </ec:price-box>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="12">
					<ec:prettify linenums="true"><row>
	<col size="4">
           <price-box>
           	<price-box-header>
           		<h3>Very <strong>Basic</strong></h3>
           	</price-box-header>
           	<price-box-terms>
           		<h6>$15.00 / Month</h6>
           	</price-box-terms>
           	<price-box-body>
           		<price-box-item checked="true">applications</price-box-item>
           		<price-box-item checked="true">upport available</price-box-item>
           		<price-box-item>No hidden fees</price-box-item>
           		<price-box-item>Free</price-box-item>
           		<price-box-item>Stop anytime easily</price-box-item>
           	</price-box-body>
           	<price-box-footer>
           		<button label="Register now" icon="bolt">
           			<event type="click">
           				alert("Register!");
           			</event>
           		</button>
           	</price-box-footer>
           </price-box>
	</col>
	<col size="4">
           <price-box attractiveness="special">
           	<price-box-header>
           		<h3>Good <strong>Choice</strong></h3>
           	</price-box-header>
           	<price-box-terms>
           		<h6>$25.00 / Month</h6>
           	</price-box-terms>
           	<price-box-body>
           		<price-box-item checked="true">applications</price-box-item>
           		<price-box-item checked="true">upport available</price-box-item>
           		<price-box-item checked="true">No hidden fees</price-box-item>
           		<price-box-item>Free</price-box-item>
           		<price-box-item>Stop anytime easily</price-box-item>
           	</price-box-body>
           	<price-box-footer>
           		<button label="Register now" icon="bolt">
           			<event type="click">
           				alert("Register!");
           			</event>
           		</button>
           	</price-box-footer>
           </price-box>
	</col>
	<col size="4">
           <price-box>
           	<price-box-header>
           		<h3>Just <strong>Happy</strong></h3>
           	</price-box-header>
           	<price-box-terms>
           		<h6>$35.00 / Month</h6>
           	</price-box-terms>
           	<price-box-body>
           		<price-box-item checked="true">applications</price-box-item>
           		<price-box-item checked="true">upport available</price-box-item>
           		<price-box-item checked="true">No hidden fees</price-box-item>
           		<price-box-item checked="true">Free</price-box-item>
           		<price-box-item checked="true">Stop anytime easily</price-box-item>
           	</price-box-body>
           	<price-box-footer>
           		<button label="Register now" icon="bolt">
           			<event type="click">
           				alert("Register!");
           			</event>
           		</button>
           	</price-box-footer>
           </price-box>
	</col>
</row></ec:prettify>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="12">
		            <h4>Example on <strong>4 columns</strong></h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="3">
		            <ec:price-box>
		            	<ec:price-box-header>
		            		<h3>Very <strong>Basic</strong></h3>
		            	</ec:price-box-header>
		            	<ec:price-box-terms>
		            		<h6>$15.00 / Month</h6>
		            	</ec:price-box-terms>
		            	<ec:price-box-body>
		            		<ec:price-box-item checked="true">applications</ec:price-box-item>
		            		<ec:price-box-item>upport available</ec:price-box-item>
		            		<ec:price-box-item>No hidden fees</ec:price-box-item>
		            		<ec:price-box-item>Free</ec:price-box-item>
		            		<ec:price-box-item>Stop anytime easily</ec:price-box-item>
		            	</ec:price-box-body>
		            	<ec:price-box-footer>
		            		<ec:button label="Register now" icon="bolt">
		            			<ec:event type="click">
		            				alert("Register!");
		            			</ec:event>
		            		</ec:button>
		            	</ec:price-box-footer>
		            </ec:price-box>
				</ed:col>
				<ed:col size="3">
		            <ec:price-box attractiveness="special">
		            	<ec:price-box-header>
		            		<h3>Good <strong>Choice</strong></h3>
		            	</ec:price-box-header>
		            	<ec:price-box-terms>
		            		<h6>$25.00 / Month</h6>
		            	</ec:price-box-terms>
		            	<ec:price-box-body>
		            		<ec:price-box-item checked="true">applications</ec:price-box-item>
		            		<ec:price-box-item checked="true">upport available</ec:price-box-item>
		            		<ec:price-box-item checked="true">No hidden fees</ec:price-box-item>
		            		<ec:price-box-item>Free</ec:price-box-item>
		            		<ec:price-box-item>Stop anytime easily</ec:price-box-item>
		            	</ec:price-box-body>
		            	<ec:price-box-footer>
		            		<ec:button label="Register now" icon="bolt">
		            			<ec:event type="click">
		            				alert("Register!");
		            			</ec:event>
		            		</ec:button>
		            	</ec:price-box-footer>
		            </ec:price-box>
				</ed:col>
				<ed:col size="3">
		            <ec:price-box>
		            	<ec:price-box-header>
		            		<h3>Just <strong>Happy</strong></h3>
		            	</ec:price-box-header>
		            	<ec:price-box-terms>
		            		<h6>$35.00 / Month</h6>
		            	</ec:price-box-terms>
		            	<ec:price-box-body>
		            		<ec:price-box-item checked="true">applications</ec:price-box-item>
		            		<ec:price-box-item checked="true">upport available</ec:price-box-item>
		            		<ec:price-box-item checked="true">No hidden fees</ec:price-box-item>
		            		<ec:price-box-item checked="true">Free</ec:price-box-item>
		            		<ec:price-box-item>Stop anytime easily</ec:price-box-item>
		            	</ec:price-box-body>
		            	<ec:price-box-footer>
		            		<ec:button label="Register now" icon="bolt">
		            			<ec:event type="click">
		            				alert("Register!");
		            			</ec:event>
		            		</ec:button>
		            	</ec:price-box-footer>
		            </ec:price-box>
				</ed:col>
				<ed:col size="3">
		            <ec:price-box>
		            	<ec:price-box-header>
		            		<h3>Just <strong>Happy</strong></h3>
		            	</ec:price-box-header>
		            	<ec:price-box-terms>
		            		<h6>$35.00 / Month</h6>
		            	</ec:price-box-terms>
		            	<ec:price-box-body>
		            		<ec:price-box-item checked="true">applications</ec:price-box-item>
		            		<ec:price-box-item checked="true">upport available</ec:price-box-item>
		            		<ec:price-box-item checked="true">No hidden fees</ec:price-box-item>
		            		<ec:price-box-item checked="true">Free</ec:price-box-item>
		            		<ec:price-box-item checked="true">Stop anytime easily</ec:price-box-item>
		            	</ec:price-box-body>
		            	<ec:price-box-footer>
		            		<ec:button label="Register now" icon="bolt">
		            			<ec:event type="click">
		            				alert("Register!");
		            			</ec:event>
		            		</ec:button>
		            	</ec:price-box-footer>
		            </ec:price-box>
				</ed:col>
			</ed:row>
		</ed:container>    
    </section>
	 
        	 
	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>