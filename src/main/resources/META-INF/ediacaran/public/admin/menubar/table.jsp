<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                    prefix="fmt"%>
 
<ec:setTemplatePackage name="admin"/>

<ec:form>
<ed:row>
	<ed:col size="9">
		<ec:textfield name="path" label="Path" />
	</ed:col>
	<ed:col size="3" align="right">
		<ec:select name="locale" label="Locale">
			<ec:option value="" selected="true">Locale</ec:option>
			<c:forEach items="${locales}" var="loc">
			<ec:option value="${loc.key}">${loc.value}</ec:option>
			</c:forEach>
		</ec:select>
	</ed:col>
</ed:row>
<ed:row>
	<ed:col size="12">
		<ec:button
			actionType="submit" 
			action="#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/list"
			method="POST"
			update="pages_body" 
			label="Pesqusiar"/>
		<ec:button 
			actionType="submit"
			label="Novo"
			method="GET"
			action="#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/new"/>
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
			${item.pathMetadata.path.concat('/')}${item.pathMetadata.id} ${empty itemlocale? '' : '('.concat(item.locale).concat(')') }
	</ed:col>
	<ed:col size="2">${locales[item.locale]}</ed:col>
	<ed:col size="3" align="right">
		<ec:form method="POST">
			<input type="hidden" name="path" value="${item.pathMetadata.path}">
			<input type="hidden" name="id" value="${item.pathMetadata.id}">
			<input type="hidden" name="locale" value="${item.locale}">
			<ec:button 
				actionType="submit" 
				action="#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/edit"
				label="Editar"/>
			<ec:button 
				actionType="submit" 
				action="#m${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/confirm-delete"
				label="Apagar"/>
		</ec:form>
	</ed:col>
</ed:row>
</c:forEach>