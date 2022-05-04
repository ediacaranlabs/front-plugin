<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<div class="alert alert-${empty exception? 'success' : 'danger'} alert-dismissable">
	<div id="result-action" style="display: none">${empty exception? 'success' : 'error'}</div>
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	<c:if test="${!empty exception}">
		Update Fail!
	</c:if>
	<c:if test="${empty exception}">
		Update success!
		<script>
			$.AppContext.onload(function(){
				$.AppContext.loadContentOnPanel("/plugins/ediacaran/front/admin/plugins");
			});
		</script>
	</c:if>
</div>
