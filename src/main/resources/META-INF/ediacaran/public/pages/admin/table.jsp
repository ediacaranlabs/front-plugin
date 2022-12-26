<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<ed:row style="form">
	<ed:col size="7"><h6>Caminho</h6></ed:col>
	<ed:col size="2"><h6>Idioma</h6></ed:col>
	<ed:col size="3"><h6>Ação</h6></ed:col>
</ed:row>
<ec:form>
<ed:row style="form">
	<ed:col size="7"><ec:textfield name="path"/></ed:col>
	<ed:col size="2">
		<ec:select name="locale">
			<ec:option value="" selected="true"></ec:option>
			<c:forEach items="${locales}" var="loc">
			<ec:option value="${loc.key}">${loc.value}</ec:option>
			</c:forEach>
		</ec:select>
	</ed:col>
	<ed:col size="3">
		<ec:button 
			actionType="submit" 
			action="#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/list"
			method="POST"
			update="#pages_body" 
			label="Pesqusiar"/>
			
		<ec:button 
			actionType="submit"
			label="Novo"
			method="GET"
			action="#m${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/new"/>
			
<%--			
		<ec:button 
			actionType="button"
			label="Novo">
			<ec:event type="click">
				$.AppContext.utils.loadResourceContent(
					null, 
					"#m${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/new");
			</ec:event>
		</ec:button>
--%>

	</ed:col>
</ed:row>
</ec:form>
<c:forEach items="${itens}" var="item">
<ed:row style="form">
	<ed:col size="12">
		<hr>
	</ed:col>
</ed:row>
<ed:row style="form">
	<ed:col size="7">
	<c:choose>
		<c:when test="${empty item.locale}">
			<a href="${item.pathMetadata.path == '/'? '/' : item.pathMetadata.path.concat('/')}${item.pathMetadata.id}" target="_blank">${item.pathMetadata.path == '/'? '/' : item.pathMetadata.path.concat('/')}${item.pathMetadata.id}</a>
		</c:when>
		<c:otherwise>
			<a href="${item.pathMetadata.path == '/'? '/' : item.pathMetadata.path.concat('/')}${item.pathMetadata.id}?lang=${item.pathMetadata.locale}" target="_blank">${item.pathMetadata.path == '/'? '/' : item.pathMetadata.path.concat('/')}${item.pathMetadata.id}?lang=${item.locale}</a>
		</c:otherwise>
	</c:choose>
	</ed:col>
	<ed:col size="2">${locales[item.locale]}</ed:col>
	<ed:col size="3">
		<ec:form method="POST">
			<input type="hidden" name="path" value="${item.pathMetadata.path}">
			<input type="hidden" name="name" value="${item.pathMetadata.id}">
			<input type="hidden" name="locale" value="${item.locale}">
			<ec:button 
				actionType="submit" 
				action="#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/edit"
				label="Editar"/>
			<ec:button 
				actionType="submit" 
				action="#m${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/confirm-delete"
				label="Apagar"/>
		</ec:form>
	</ed:col>
</ed:row>

</c:forEach>

<%--
	<ec:table style="sm" >
		<ec:table-header>
			<ec:table-col>Caminho</ec:table-col>
			<ec:table-col>Nome</ec:table-col>
			<ec:table-col>Idioma</ec:table-col>
			<ec:table-col>Ação</ec:table-col>
		</ec:table-header>
		<ec:table-body>
			<c:forEach items="${itens}" var="item">
			<ec:table-row>
				<ec:table-col>${item.path}</ec:table-col>
				<ec:table-col>${item.id}</ec:table-col>
				<ec:table-col>${locales[item.locale]}</ec:table-col>
				<ec:table-col>
					<ec:form method="POST" update="#content-body">
						<input type="hidden" name="path" value="${item.path}">
						<input type="hidden" name="name" value="${item.id}">
						<input type="hidden" name="locale" value="${item.locale}">
						<ec:button 
							actionType="submit" 
							action="${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/edit"
							label="Editar"/>
						<ec:button style="link" label="Remover"/>
					</ec:form>
				</ec:table-col>
			</ec:table-row>
			</c:forEach>
		</ec:table-body>
	</ec:table>
--%>