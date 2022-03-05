<%@taglib uri="http://java.sun.com/jsp/jstl/core"                              prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<style>
#flw1_widget{
	width: 100%;
	height: 300px;
}
</style>
<ec:box>
	<ec:box-header>
		<h3>Bar chart</h3>
	</ec:box-header>
	<ec:box-body>
		<ec:flotcharts id="flw1_widget">
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
		</ec:flotcharts>

	</ec:box-body>
</ec:box>
