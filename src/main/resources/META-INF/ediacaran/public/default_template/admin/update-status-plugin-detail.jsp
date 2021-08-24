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
		var $form = $.AppContext.utils.getById('status_config_fr');
		var $statusField = $form.getField('status');
		$statusField.setValue(${vars.status});
		$statusField.setProperty('disabled', false);
	</script>
</div>
