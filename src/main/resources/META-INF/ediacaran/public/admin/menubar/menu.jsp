<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                    prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<c:set var="obj" value="${param1_}"/>
<c:set scope="request" var="countMenuID" value="${empty countMenuID? 1 : countMenuID + 1}"/>
<c:set var="menuID" value="${countMenuID}"/>

<div id="MenuID_${menuID}" formgroup="menus" formgrouptype="index" draggable="true" class="menu-item">
<ec:accordion>
	<ec:accordion-item id="menu_item_${menuID}" title="${obj.name}">
	<ed:row>
		<ed:col size="3" classStyle="form-group has-feedback">
			<input type="hidden" value="${obj.id}" name="id">
			<ec:textfield label="Icon" value="${obj.icon}" name="icon" />
		</ed:col>
		<ed:col size="6" classStyle="form-group has-feedback">
			<ec:textfield label="Nome" name="name" value="${obj.name}">
				<ec:event type="keyup">

					var $accordionItem = $.AppContext.utils.getById("menu_item_${menuID}");
					
					$accordionItem.setValue('title', $event.source.getProperty('value'));
					
				</ec:event>
			</ec:textfield>
		</ed:col>
		<ed:col size="3" classStyle="form-group has-feedback">
			<ec:select label="Prioridade" name="order">
				<ec:option value="100" selected="${obj.order >= 100}">Alta</ec:option>
				<ec:option value="0" selected="${obj.order == 0}">Normal</ec:option>
				<ec:option value="-100" selected="${obj.order <= -100}">Baixa</ec:option>
			</ec:select>
		</ed:col>
	</ed:row>
	<ed:row>
		<ed:col size="12" classStyle="form-group has-feedback">
			<ec:textfield label="Resource" name="resource" value="${obj.rawResource}">
				<ec:autocomplete resource="${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/search-resource" />			
			</ec:textfield>
		</ed:col>
	</ed:row>
	<ed:row>
		<ed:col size="4" classStyle="form-group has-feedback">
			<ec:select label="Role" name="role">
				<ec:option selected="${empty obj.role}"></ec:option>
				<c:forEach items="${roles}"  var="role">
				<ec:option value="${role.id}" selected="${obj.hasRole(role.id)}">${role.description}</ec:option>
				</c:forEach>
			</ec:select>
		</ed:col>
		<ed:col size="8" classStyle="form-group has-feedback">
			<ec:textfield label="Permissões" name="permission" value="${obj.permission}"/>
		</ed:col>
	</ed:row>
	</ec:accordion-item>
</ec:accordion>
<c:forEach var="item" items="${obj.itens}">
	<c:set scope="request" var="param1_" value="${item}"/>
	<c:set scope="request" var="d_eep" value="${empty d_eep? 1 : d_eep + 1}"/>
	<c:if test="${d_eep < 3}">
		<c:import url="/admin/menubar/menu.jsp"/>
	</c:if>
	<c:set scope="request" var="d_eep" value="${d_eep - 1}"/>
</c:forEach>
</div>
<script type="text/javascript">
<!--
$.AppContext.onload(function(){
	var $accordionItem = $.AppContext.utils.getById("MenuID_${menuID}");
	pageContext.install($accordionItem);
	pageContext.updateFieldIndex();
});
//-->
</script>