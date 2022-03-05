<%@taglib uri="http://java.sun.com/jsp/jstl/core"                              prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<style>
#donut_chart{
	width: 100%;
	height: 300px;
}
</style>
<ec:box>
	<ec:box-header>
		<h3>Donut chart</h3>
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
