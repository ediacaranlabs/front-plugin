<%@taglib uri="http://java.sun.com/jsp/jstl/core"                              prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<style>
#interactive{
	width: 100%;
	height: 300px;
}
</style>
<ec:box>
	<ec:box-header>
		<h3>Interactive</h3>
	</ec:box-header>
	<ec:box-body>
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
</ec:box>
