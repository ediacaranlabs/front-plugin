<%@taglib uri="http://java.sun.com/jsp/jstl/core"                              prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
	<section class="inner-headline">
		<ed:container>
			<ed:row>
				<ed:col size="4">
					<div class="inner-heading">
						<h2>Flotcharts</h2>
					</div>
				</ed:col>
				<ed:col size="8">
					<ec:breadcrumb title="Flotcharts">
						<ec:breadcrumb-path icon="home" text="" lnk="#" />
						<ec:breadcrumb-path text="Features" lnk="#" />
					</ec:breadcrumb>
				</ed:col>
			</ed:row>
		</ed:container>
	</section>

<!-- 
   	<ed:row>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Button types</h3>
				</ec:box-header>
				<ec:box-body>
				</ec:box-body>
				<ec:box-footer>
					<ec:prettify linenums="true">
					</ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box style="info">
				<ec:box-header>
					<h3>Button with icon</h3>
				</ec:box-header>
				<ec:box-body>
				</ec:box-body>
				<ec:box-footer>
					<ec:prettify linenums="true">
					</ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
   	</ed:row>
 -->
 <style>
 #flotchartstag25 {
 	height: 300px;
 }
 </style>
    	<ed:row>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Interactive Area Chart</h3>
				</ec:box-header>
				<ec:box-body>
					<!-- https://github.com/flot/flot/blob/master/API.md | http://www.flotcharts.org/ -->
					<ec:flotcharts src="/plugins/ediacaran/updade.js" togglingSeries="false" 
							updateFrequence="1" updateFrequenceUnit="SECONDS">
						<ec:property-config name="config">
							<ec:property-config name="grid">
								<ec:property-config name="borderWidth" value="1"/>
								<ec:property-config name="borderColor" value="#f3f3f3"/>
								<ec:property-config name="tickColor" value="#f3f3f3"/>
							</ec:property-config>
							<ec:property-config name="series">
									<ec:property-config name="bars">
										<ec:property-config name="show" value="true"/>
										<ec:property-config name="barWidth" value="0.5"/>
										<ec:property-config name="align" value="center"/>
									</ec:property-config>
							</ec:property-config>
							<ec:property-config-list name="colors">
									<ec:property-config value="#3c8dbc"/>
							</ec:property-config-list>
							<ec:property-config name="xaxis">
								<ec:property-config-list name="ticks">
									<ec:property-config-list>
										<ec:property-config value="1"/>
										<ec:property-config value="January"/>
									</ec:property-config-list>
									<ec:property-config-list>
										<ec:property-config value="2"/>
										<ec:property-config value="February"/>
									</ec:property-config-list>
									<ec:property-config-list>
										<ec:property-config value="3"/>
										<ec:property-config value="March"/>
									</ec:property-config-list>
									<ec:property-config-list>
										<ec:property-config value="4"/>
										<ec:property-config value="April"/>
									</ec:property-config-list>
									<ec:property-config-list>
										<ec:property-config value="5"/>
										<ec:property-config value="May"/>
									</ec:property-config-list>
									<ec:property-config-list>
										<ec:property-config value="6"/>
										<ec:property-config value="June"/>
									</ec:property-config-list>
								</ec:property-config-list>
							</ec:property-config>
						</ec:property-config>
						<ec:flotcharts-series label="Teste" color="1" lines="true">
							<ec:flotcharts-data x="1" y="10"/>
							<ec:flotcharts-data x="2" y="8"/>
							<ec:flotcharts-data x="3" y="4"/>
							<ec:flotcharts-data x="4" y="13"/>
							<ec:flotcharts-data x="5" y="17"/>
							<ec:flotcharts-data x="6" y="9"/>
						</ec:flotcharts-series>
					</ec:flotcharts>

				</ec:box-body>
				<ec:box-footer>
					<ec:prettify linenums="true">
					</ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
		<ed:col size="6">
			<ec:box style="info">
				<ec:box-header>
					<h3>Button with icon</h3>
				</ec:box-header>
				<ec:box-body>
				<script type="text/javascript">
				$(function() {
					var bar_data = {
						      data : [[1,10], [2,8], [3,4], [4,13], [5,17], [6,9]],
						      bars: { show: true }
						    }
						    $.plot('#placeholder', [bar_data], {
						      grid  : {
						        borderWidth: 1,
						        borderColor: '#f3f3f3',
						        tickColor  : '#f3f3f3'
						      },
						      series: {
						         bars: {
						          show: true, barWidth: 0.5, align: 'center',
						        },
						      },
						      colors: ['#3c8dbc'],
						      xaxis : {
						        ticks: [[1,'January'], [2,'February'], [3,'March'], [4,'April'], [5,'May'], [6,'June']]
						      }
						    })
				});

				</script>
				<div id="placeholder" style="height: 300px;"></div>
				
				</ec:box-body>
				<ec:box-footer>
					<ec:prettify linenums="true">
					</ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
   	</ed:row>
 