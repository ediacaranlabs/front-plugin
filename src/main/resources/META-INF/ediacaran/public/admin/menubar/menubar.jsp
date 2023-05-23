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
			<ed:col size="12" id="list_menus">
				<c:forEach varStatus="menubarItemStatus" var="menubarItem" items="${menubar.itens}">
				<c:set scope="request" var="param1_" value="${menubarItem}"/>
				<c:set scope="request" var="d_eep" value="0"/>
				<c:import url="/admin/menubar/menu.jsp"/>
				</c:forEach>
			</ed:col>
		</ed:row>
		
<%--		
	<ed:row>
		<ed:col size="12">
			<ec:button label="Add Menu" align="right" classStyle="last-item">
				<ec:event type="click">
				
					$.AppContext.utils.appendContentByID(
						'${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/new',
						'list_menus'
					);
					
				</ec:event>
			</ec:button>
			<ec:button align="right" label="Delete" classStyle="last-item">
				<ec:event type="click">
				</ec:event>
			</ec:button>
			<span></span>
			<ec:button label="Save" align="right" classStyle="last-item">
				<ec:event type="click">
				</ec:event>
			</ec:button>
		</ed:col>
	</ed:row>
 --%>
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