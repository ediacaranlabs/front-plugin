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
						<ec:flotcharts-series  label="Teste">
							<ec:flotcharts-data x="1" y="5"/>
							<ec:flotcharts-data x="2" y="6"/>
							<ec:flotcharts-data x="3" y="3"/>
							<ec:flotcharts-data x="4" y="7"/>
							<ec:flotcharts-data x="5" y="2"/>
						</ec:flotcharts-series>
						<ec:flotcharts-series  label="Teste 2" color="2" lines="true" bars="false" points="false" xaxis="0" yaxis="0" 
							clickable="false" hoverable="false" shadowSize="0" highlightColor="0"/>
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
 