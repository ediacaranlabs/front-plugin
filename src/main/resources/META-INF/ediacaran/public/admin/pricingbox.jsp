<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@page trimDirectiveWhitespaces="true" %>
	<section class="inner-headline">
			<ed:row>
				<ed:col size="4">
					<div class="inner-heading">
						<h2>Pricing boxes</h2>
					</div>
				</ed:col>
				<ed:col size="8">
					<ec:breadcrumb title="Pricing boxes">
						<ec:breadcrumb-path icon="home" text="" lnk="#" />
						<ec:breadcrumb-path text="Features" lnk="#" />
					</ec:breadcrumb>
				</ed:col>
			</ed:row>
	</section>
	
   	<ed:row>
		<ed:col size="12">
			<ec:box>
				<ec:box-header>
					<h3>Example on 3 columns</h3>
				</ec:box-header>
				<ec:box-body>
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
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>
   	
   	<ed:row>
		<ed:col size="12">
			<ec:box>
				<ec:box-header>
					<h3>Example on 4 columns</h3>
				</ec:box-header>
				<ec:box-body>
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
				</ec:box-body>
			</ec:box>
		</ed:col>
   	</ed:row>