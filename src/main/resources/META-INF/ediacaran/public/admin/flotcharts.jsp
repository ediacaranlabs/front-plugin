<%@taglib uri="http://java.sun.com/jsp/jstl/core"                              prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"                         prefix="fn"%>
	<section class="inner-headline">
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
	</section>

<style>
#donut_chart {
	height: 300px;
}

#area_chart {
	height: 300px;
}

#bar_chart {
	height: 300px;
}

#line_chart {
	height: 300px;
}

#interactive {
	height: 300px;
}
</style>
<ed:row>
	<ed:col size="12">
		<ec:box>
			<ec:box-header>
				<h3><ec:icon icon="bar-chart" size="1"/> Interactive Area Chart</h3>
			</ec:box-header>
			<ec:box-body>
				<!-- https://github.com/flot/flot/blob/master/API.md | http://www.flotcharts.org/ -->
				<ec:flotcharts id="interactive" src="/plugins/ediacaran/front/admin/flotcharts" togglingSeries="false" 
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
		</ec:box>
	</ed:col>
</ed:row>
<ed:row>
	<ed:col size="6">
		<ec:box>
			<ec:box-header>
				<h3><ec:icon icon="bar-chart" size="1"/> Line chart</h3>
			</ec:box-header>
			<ec:box-body>
				<ec:flotcharts id="line_chart" src="/plugins/ediacaran/front/admin/flotcharts/line-chart">
					<ec:property-config name="config">
						<ec:property-config name="grid">
							<ec:property-config name="hoverable" value="true"/>
							<ec:property-config name="borderColor" value="#f3f3f3"/>
							<ec:property-config name="borderWidth" value="1"/>
							<ec:property-config name="tickColor" value="#f3f3f3"/>
						</ec:property-config>
						<ec:property-config name="series">
								<ec:property-config name="shadowSize" value="0"/>
								<ec:property-config name="lines">
									<ec:property-config name="show" value="true"/>
								</ec:property-config>
								<ec:property-config name="points">
									<ec:property-config name="show" value="true"/>
								</ec:property-config>
						</ec:property-config>
						<ec:property-config name="lines">
								<ec:property-config name="fill" value="false"/>
								<ec:property-config-list name="color">
									<ec:property-config value="#3c8dbc"/>
									<ec:property-config value="#f56954"/>
								</ec:property-config-list>
						</ec:property-config>
						<ec:property-config name="xaxis">
								<ec:property-config name="show" value="true"/>
						</ec:property-config>
						<ec:property-config name="yaxis">
								<ec:property-config name="show" value="true"/>
						</ec:property-config>
					</ec:property-config>
					
					<ec:flotcharts-series label="sin"/>
					<ec:flotcharts-series label="cos"/>
					
				</ec:flotcharts>
	
			</ec:box-body>
		</ec:box>
	</ed:col>
	<ed:col size="6">
		<ec:box>
			<ec:box-header>
				<h3><ec:icon icon="bar-chart" size="1"/> Bar chart</h3>
			</ec:box-header>
			<ec:box-body>
				<!-- https://github.com/flot/flot/blob/master/API.md | http://www.flotcharts.org/ -->
				<ec:flotcharts id="bar_chart">
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
					
					<ec:flotcharts-series label="bar">
						<ec:flotcharts-data x="1" y="10"/>
						<ec:flotcharts-data x="2" y="8"/>
						<ec:flotcharts-data x="3" y="4"/>
						<ec:flotcharts-data x="4" y="13"/>
						<ec:flotcharts-data x="5" y="17"/>
						<ec:flotcharts-data x="6" y="9"/>
					</ec:flotcharts-series>
				</ec:flotcharts>
	
			</ec:box-body>
		</ec:box>
	</ed:col>
</ed:row>
<ed:row>
	<ed:col size="6">
		<ec:box>
			<ec:box-header>
				<h3><ec:icon icon="bar-chart" size="1"/> Area chart</h3>
			</ec:box-header>
			<ec:box-body>
				<ec:flotcharts id="area_chart">
					<ec:property-config name="config">
						<ec:property-config name="grid">
							<ec:property-config name="borderWidth" value="0"/>
						</ec:property-config>
						<ec:property-config name="series">
								<ec:property-config name="shadowSize" value="0"/>
								<ec:property-config name="color" value="#00c0ef"/>
								<ec:property-config name="lines">
									<ec:property-config name="fill" value="true"/>
								</ec:property-config>
						</ec:property-config>
						<ec:property-config name="xaxis">
								<ec:property-config name="show" value="false"/>
						</ec:property-config>
						<ec:property-config name="yaxis">
								<ec:property-config name="show" value="false"/>
						</ec:property-config>
					</ec:property-config>
					
					<ec:flotcharts-series label="area data">
						<ec:flotcharts-data x="2" y="88.0"/>
						<ec:flotcharts-data x="3" y="93.3"/> 
						<ec:flotcharts-data x="4" y="102.0"/> 
						<ec:flotcharts-data x="5" y="108.5"/> 
						<ec:flotcharts-data x="6" y="115.7"/> 
						<ec:flotcharts-data x="7" y="115.6"/>
						<ec:flotcharts-data x="8" y="124.6"/>
						<ec:flotcharts-data x="9" y="130.3"/>
						<ec:flotcharts-data x="10" y="134.3"/> 
						<ec:flotcharts-data x="11" y="141.4"/>
						<ec:flotcharts-data x="12" y="146.5"/>
						<ec:flotcharts-data x="13" y="151.7"/>
						<ec:flotcharts-data x="14" y="159.9"/>
						<ec:flotcharts-data x="15" y="165.4"/>
						<ec:flotcharts-data x="16" y="167.8"/>
						<ec:flotcharts-data x="17" y="168.7"/>
						<ec:flotcharts-data x="18" y="169.5"/>
						<ec:flotcharts-data x="19" y="168.0"/>
					</ec:flotcharts-series>
					
				</ec:flotcharts>
			</ec:box-body>
		</ec:box>
	</ed:col>
	<ed:col size="6">
		<ec:box>
			<ec:box-header>
				<h3><ec:icon icon="bar-chart" size="1"/> Donut chart</h3>
			</ec:box-header>
			<ec:box-body>
				<ec:flotcharts id="donut_chart">
					<ec:property-config name="config">
						<ec:property-config name="grid">
							<ec:property-config name="borderWidth" value="0"/>
						</ec:property-config>
						<ec:property-config name="series">
							<ec:property-config name="pie">
								<ec:property-config name="show" value="true"/>
								<ec:property-config name="radius" value="1"/>
								<ec:property-config name="innerRadius" value="0.5"/>
								<ec:property-config name="label">
										<ec:property-config name="show" value="true"/>
										<ec:property-config name="radius" value="0.6666666666666667"/>
										<ec:property-config name="threshold" value="0.1"/>
										<ec:property-config name="formatter" raw="true" value="$.AppContext.flotcharts.labelFormatter"/>
								</ec:property-config>
							</ec:property-config>
						</ec:property-config>
						<ec:property-config name="legend">
								<ec:property-config name="show" value="false"/>
						</ec:property-config>
					</ec:property-config>
					
					<ec:flotcharts-series label="Series2" data="30">
						<ec:property-config name="config">
							<ec:property-config name="color" value="#3c8dbc"/>
						</ec:property-config>					
					</ec:flotcharts-series>

					<ec:flotcharts-series label="Series3" data="20">
						<ec:property-config name="config">
							<ec:property-config name="color" value="#0073b7"/>
						</ec:property-config>					
					</ec:flotcharts-series>
					
					<ec:flotcharts-series label="Series4" data="50">
						<ec:property-config name="config">
							<ec:property-config name="color" value="#00c0ef"/>
						</ec:property-config>					
					</ec:flotcharts-series>
					
				</ec:flotcharts>
			</ec:box-body>
		</ec:box>
	</ed:col>
</ed:row>
