<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<div class="alert alert-${empty vars.error? 'success' : 'danger'} alert-dismissable">
	<div id="result-action" style="display: none">${empty vars.error? 'success' : 'error'}</div>
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	<c:if test="${!empty vars.error}">
		${vars.error.message}
	</c:if>
	<c:if test="${empty vars.error}">
		Update success!
	</c:if>
	<script>
		$form = new $.AppContext.types.Form('status_config_fr');
		$statusField = $form.getField('status');
		$statusField.setValue(${vars.status});
		//$.AppContext.pages.update_status_plugin_detail.status_config_fr.setStatusValue(${vars.status});
	</script>
</div>
