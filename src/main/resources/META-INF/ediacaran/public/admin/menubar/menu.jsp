<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                    prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<c:set var="obj" value="${param1_}"/>
<c:set scope="request" var="countMenuID" value="${empty countMenuID? 1 : countMenuID + 1}"/>
<c:set var="menuID" value="${countMenuID}"/>

<ec:accordion id="MenuID_${menuID}">
	<ec:accordion-item id="menu_item_${menuID}" title="${obj.name}">
	<ed:row>
		<ed:col size="3" classStyle="form-group has-feedback">
			<input type="hidden" value="${obj.id}" name="id">
			<ec:textfield label="Icon" value="${obj.icon}"/>
		</ed:col>
		<ed:col size="9" classStyle="form-group has-feedback">
			<ec:textfield label="Nome" name="name" value="${obj.name}">
				<ec:event type="keyup">

					var $accordionItem = $.AppContext.utils.getById("menu_item_${menuID}");
					
					$accordionItem.setValue('title', $event.source.getProperty('value'));
					
				</ec:event>
			</ec:textfield>
		</ed:col>
	</ed:row>
	<ed:row>
		<ed:col size="12" classStyle="form-group has-feedback">
			<ec:textfield label="Resource" name="rawResource" value="${obj.rawResource}"/>
		</ed:col>
	</ed:row>
	<ed:row>
		<ed:col size="9">
		</ed:col>
		<ed:col size="3">
			<ec:button align="right" label="Delete" classStyle="last-item">
				<ec:event type="click">
				
					var $obj = $.AppContext.utils.getById("MenuID_${menuID}");
					
					$obj.setProperty("removed", true);
					$obj.setVisible(false);
					
				</ec:event>
			</ec:button>
			<span></span>
			<ec:button label="Add Menu" align="right">
				<ec:event type="click">
				
					$.AppContext.utils.appendContentByID(
						'${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/new',
						'list_menus_${menuID}'
					);
					
				</ec:event>
			</ec:button>
		</ed:col>
	</ed:row>
	<ed:row>
		<ed:col size="12" id="list_menus_${menuID}">
			<!-- List Menus -->
			<c:forEach var="item" items="${obj.itens}">
				<c:set scope="request" var="param1_" value="${item}"/>
				<c:set scope="request" var="d_eep" value="${empty d_eep? 1 : d_eep + 1}"/>
				<c:if test="${d_eep < 3}">
					<c:import url="/admin/menubar/menu.jsp"/>
				</c:if>
				<c:set scope="request" var="d_eep" value="${d_eep - 1}"/>
			</c:forEach>
		</ed:col>
	</ed:row>
	</ec:accordion-item>
</ec:accordion>
