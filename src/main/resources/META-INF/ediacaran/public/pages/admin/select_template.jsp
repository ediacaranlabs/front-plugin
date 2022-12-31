<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<ec:setTemplatePackage name="admin"/>

<ec:form 
	id="new_page_form"
	method="POST" 
	action="${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/new" 
	update="#content-body">
<ec:modal-header>
	<h4 class="modal-title">Select a template</h4>
	<ec:button id="close_model" extAttrs="modal-action=\"close\" aria-label=\"Close\"" 
		actionType="button" classStyle="close" label="×"/>
</ec:modal-header>
<ec:modal-body>
		<ed:row>
			<ed:col size="12">
				<ec:select label="Template" name="templateName">
					<c:forEach items="${templates}" var="template">
					<ec:option value="${template.key}">${template.value.name}</ec:option>
					</c:forEach>
				</ec:select>
			</ed:col>
		</ed:row>
</ec:modal-body>
<ec:modal-footer>
	<ec:button label="Create" actionType="submit">
		<ec:event type="click">
			$.AppContext.dialog.close();
		</ec:event>
	</ec:button>
</ec:modal-footer>
</ec:form>
