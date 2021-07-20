<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2>Plugins</h2>
			</div>
		</ed:col>
		<ed:col size="8">
			<ec:breadcrumb title="Plugins">
				<ec:breadcrumb-path icon="home" text="" lnk="#" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<ed:row>
	<ed:col size="3">
		<ec:box>
			<ec:box-header><h3>Menu</h3></ec:box-header>
			<ec:box-body>
				<ec:accordion>
					<c:forEach items="${Controller.groups}" var="group">
						<ec:accordion-item title="${group}">
							<ec:list>
								<c:forEach items="${Controller.getPlugins(group)}" var="plugin">
									<ec:list-item><a href="#!/plugins/ediacaran/front/admin/plugins/${plugin.metadata.code}" dest-content="#plugin_body">${plugin.metadata.name}</a></ec:list-item>
								</c:forEach>
							</ec:list>
						</ec:accordion-item>	
					</c:forEach>
				</ec:accordion>
			</ec:box-body>
		</ec:box>
	</ed:col>
	<ed:col size="9">
		<ec:box>
			<ec:box-header><h3>Configuração</h3></ec:box-header>
			<ec:box-body id="plugin_body">
			</ec:box-body>
		</ec:box>
	</ed:col>
</ed:row>