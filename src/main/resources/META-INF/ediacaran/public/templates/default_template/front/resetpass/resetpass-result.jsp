<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<c:if test="${!empty exception}">
	<ec:alert style="danger">
		<div id="exceptionMessage">${exception.message}</div>
	</ec:alert>
</c:if>
<c:if test="${empty exception}">
	<script type="text/javascript">
		var resetpassForm = $.AppContext.utils.getById("resetpassForm");
		resetpassForm.setVisible(false);

		var successMsg = $.AppContext.utils.getById("success_result");
		successMsg.setVisible(true);
	</script>
</c:if>