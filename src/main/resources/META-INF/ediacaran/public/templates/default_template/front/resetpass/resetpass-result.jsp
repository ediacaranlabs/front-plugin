<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="display:none">>@web-result=${empty exception}</div>
<c:if test="${!empty exception}">
	<div class="alert alert-danger alert-dismissable col-xs-12">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		${exception.message}
	</div>				
</c:if>
<c:if test="${empty exception}">
	<script type="text/javascript">
		var resetpassForm = $.AppContext.utils.getById("resetpassForm");
		resetpassForm.setVisible(false);

		var successMsg = $.AppContext.utils.getById("success_result");
		successMsg.setVisible(true);
	</script>
</c:if>