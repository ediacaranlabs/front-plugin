<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                    prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2>Editar Menubar</h2>
			</div>
		</ed:col>
		<ed:col size="8">
			<ec:breadcrumb title="Editar">
				<ec:breadcrumb-path icon="home" text="" lnk="#" />
				<ec:breadcrumb-path text="menubar" lnk="#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<ec:box>
	<ec:box-body>	
		<ed:row>
			<ed:col size="3" classStyle="form-group has-feedback">
				<ec:textfield label="ID" name="id" value="${menubar.id}"/>
			</ed:col>
			<ed:col size="9" classStyle="form-group has-feedback">
				<ec:textfield label="Nome" name="name" value="${menubar.name}"/>
			</ed:col>
		</ed:row>
		<ed:row>
			<ed:col size="12">
				<c:forEach varStatus="menubarItemStatus" var="menubarItem" items="${menubar.itens}">
				<ec:accordion>
					<ec:accordion-item title="${menubarItem.name}">
					<ed:row>
						<ed:col size="3" classStyle="form-group has-feedback">
							<ec:textfield label="ID" value="${menubarItem.id}" enabled="false"/>
						</ed:col>
						<ed:col size="3" classStyle="form-group has-feedback">
							<ec:textfield label="Icon" value="${menubarItem.icon}"/>
						</ed:col>
						<ed:col size="6" classStyle="form-group has-feedback">
							<ec:textfield label="Name" value="${menubarItem.name}"/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12" classStyle="form-group has-feedback">
							<ec:textfield label="Resource" value="${menubarItem.rawResource}"/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12">
							<c:forEach varStatus="menuItemStatus" var="menuItem" items="${menubarItem.itens}">
							<ec:accordion>
								<ec:accordion-item title="${menuItem.name}">
								<ed:row>
									<ed:col size="3" classStyle="form-group has-feedback">
										<ec:textfield label="ID" value="${menuItem.id}" enabled="false"/>
									</ed:col>
									<ed:col size="3" classStyle="form-group has-feedback">
										<ec:textfield label="Icon" value="${menuItem.icon}"/>
									</ed:col>
									<ed:col size="6" classStyle="form-group has-feedback">
										<ec:textfield label="Name" value="${menuItem.name}"/>
									</ed:col>
								</ed:row>
								<ed:row>
									<ed:col size="12" classStyle="form-group has-feedback">
										<ec:textfield label="Resource" value="${menuItem.rawResource}"/>
									</ed:col>
								</ed:row>
								</ec:accordion-item>
							</ec:accordion>
							</c:forEach>
						</ed:col>
					</ed:row>
					</ec:accordion-item>
				</ec:accordion>
				</c:forEach>
			</ed:col>
		</ed:row>
	</ec:box-body>
</ec:box>


<%--	
	<ed:row>
		<ed:col size="12" classStyle="form-group has-feedback">
			<ec:textfield name="resource">
				<ec:autocomplete resource="${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/search-resource" />
			</ec:textfield>
		</ed:col>
	</ed:row>
 --%>