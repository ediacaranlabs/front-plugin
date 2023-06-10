<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<ec:setTemplatePackage name="admin"/>

<ec:form 
	id="new_page_form"
	method="POST" 
	action="#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/delete">
<ec:modal-header>
	<h4 class="modal-title">Apagar página?</h4>
	<ec:button id="close_model" extAttrs="modal-action=\"close\" aria-label=\"Close\"" 
		actionType="button" classStyle="close" label="×"/>
</ec:modal-header>
<ec:modal-body>
		<ed:row>
			<ed:col size="12">
				<input type="hidden" value="${metadata.hashCode()}" name="gid">
				<input type="hidden" value="${metadata.path}" name="path">
				<input type="hidden" value="${metadata.id}" name="id">
				<input type="hidden" value="${metadata.locale}" name="locale">
				Deseja realmente apagar?
			</ed:col>
		</ed:row>
</ec:modal-body>
<ec:modal-footer>
	<ec:button label="Sim" actionType="submit">
		<ec:event type="click">
			$.AppContext.dialog.close();
		</ec:event>
	</ec:button>
	<ec:button label="Cancelar" actionType="button">
		<ec:event type="click">
			$.AppContext.dialog.close();
		</ec:event>
	</ec:button>
</ec:modal-footer>
</ec:form>
