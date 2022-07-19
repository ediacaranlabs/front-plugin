<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<ec:form method="POST" action="${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/list" update="#pages_body">
	<ec:table style="sm" >
		<ec:table-header>
			<ec:table-col>Caminho</ec:table-col>
			<ec:table-col>Nome</ec:table-col>
			<ec:table-col>Idioma</ec:table-col>
			<ec:table-col>Ação</ec:table-col>
		</ec:table-header>
		<ec:table-body>
			<ec:table-row>
				<ec:table-col>#</ec:table-col>
				<ec:table-col><ec:textfield name="name" value="${name}"/></ec:table-col>
				<ec:table-col><ec:textfield name="locale" value="${locale}"/></ec:table-col>
				<ec:table-col align="center"><ec:button label="Pesqusiar" actionType="submit"/></ec:table-col>
			</ec:table-row>
			<c:forEach items="${itens}" var="item">
			<ec:table-row>
				<ec:table-col>${item.path}</ec:table-col>
				<ec:table-col>${item.id}</ec:table-col>
				<ec:table-col>${item.locale}</ec:table-col>
				<ec:table-col>
					<ec:dropdown label="Ação">
						<ec:dropdown-item src="#">Editar</ec:dropdown-item>
						<ec:dropdown-item src="#">Remover</ec:dropdown-item>
					</ec:dropdown>
				</ec:table-col>
			</ec:table-row>
			</c:forEach>
		</ec:table-body>
	</ec:table>
</ec:form>