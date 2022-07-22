<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

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
					<ec:form method="POST" action="${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/edit" update="#pages_body">
						<input type="hidden" name="path" value="${item.path}">
						<input type="hidden" name="name" value="${item.id}">
						<input type="hidden" name="locale" value="${item.locale}">
						<ec:button actionType="submit" style="link" label="Editar"/>
						<ec:button style="link" label="Remover"/>
					</ec:form>
				</ec:table-col>
			</ec:table-row>
			</c:forEach>
		</ec:table-body>
	</ec:table>
