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
 
  #interactive {
 	height: 300px;
 }
 
 </style>
 <%-- 
<script type="text/javascript">
<!--
	var data        = [],
	totalPoints = 100
	
	function getRandomData() {
	
		if (data.length > 0) {
		data = data.slice(1)
		}
	
		while (data.length < totalPoints) {
			
			var prev = data.length > 0 ? data[data.length - 1] : 50,
			    y    = prev + Math.random() * 10 - 5
			
			if (y < 0) {
			  y = 0
			} else if (y > 100) {
			  y = 100
			}
			
			data.push(y)
		}
	
		var res = []
		for (var i = 0; i < data.length; ++i) {
			res.push([i, data[i]])
		}
		
		return res
	}
	
	var updateInterval = 500;

	function update() {
		var dta = getRandomData();
		$.AppContext.flotcharts.updateChart('interactive',{
			series: [
				{
					data: dta
				}
			]
		});
		setTimeout(update, updateInterval)
	}

	$.AppContext.onload(function(){
		console.log('teste');
		update();
	});
	
	//-->
</script>
--%>
     	<ed:row>
		<ed:col size="12">
			<ec:box>
				<ec:box-header>
					<h3>Interactive Area Chart</h3>
				</ec:box-header>
				<ec:box-body>
					<!-- https://github.com/flot/flot/blob/master/API.md | http://www.flotcharts.org/ -->
					<ec:flotcharts id="interactive" src="/plugins/ediacaran/front/adm/flotcharts" togglingSeries="false" 
							updateFrequence="1000" updateFrequenceUnit="MILLISECONDS">
						<ec:property-config name="config">
							<ec:property-config name="grid">
								<ec:property-config name="borderWidth" value="1"/>
								<ec:property-config name="borderColor" value="#f3f3f3"/>
								<ec:property-config name="tickColor" value="#f3f3f3"/>
							</ec:property-config>
							<ec:property-config name="series">
									<ec:property-config name="color" value="#3c8dbc"/>
									<ec:property-config name="lines">
										<ec:property-config name="show" value="true"/>
										<ec:property-config name="lineWidth" value="2"/>
										<ec:property-config name="fill" value="true"/>
									</ec:property-config>
							</ec:property-config>
							<ec:property-config name="yaxis">
									<ec:property-config name="min" value="0"/>
									<ec:property-config name="max" value="100"/>
									<ec:property-config name="show" value="true"/>
							</ec:property-config>
							<ec:property-config name="xaxis">
									<ec:property-config name="show" value="true"/>
									<ec:property-config name="min" value="0"/>
									<ec:property-config name="max" value="100"/>
							</ec:property-config>
						</ec:property-config>
						<ec:flotcharts-series label="teste">
						</ec:flotcharts-series>
					</ec:flotcharts>
				</ec:box-body>
				<ec:box-footer>
					<ec:prettify linenums="true">
					</ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
   	</ed:row>
 
 
    	<ed:row>
		<ed:col size="6">
			<ec:box>
				<ec:box-header>
					<h3>Bar chart</h3>
				</ec:box-header>
				<ec:box-body>
					<!-- https://github.com/flot/flot/blob/master/API.md | http://www.flotcharts.org/ -->
					<ec:flotcharts>
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
						
						<ec:flotcharts-series label="Teste">
							<ec:flotcharts-data x="1" y="10"/>
							<ec:flotcharts-data x="2" y="8"/>
							<ec:flotcharts-data x="3" y="4"/>
							<ec:flotcharts-data x="4" y="13"/>
							<ec:flotcharts-data x="5" y="17"/>
							<ec:flotcharts-data x="6" y="9"/>
						</ec:flotcharts-series>
						<%--
						<ec:flotcharts-series  label="Teste 2" color="2" lines="true" bars="false" points="false" xaxis="0" yaxis="0" 
							clickable="false" hoverable="false" shadowSize="0" highlightColor="0"/>
						--%>
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
				</ec:box-body>
				<ec:box-footer>
					<ec:prettify linenums="true">
					</ec:prettify>
				</ec:box-footer>
			</ec:box>
		</ed:col>
   	</ed:row>
 