<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<div class="alert alert-${empty exception? 'success' : 'danger'} alert-dismissable">
	<div id="result-action" style="display: none">${empty vars.error? 'success' : 'error'}</div>
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	<c:if test="${!empty exception && exception.class.simpleName != 'ValidatorException'}">
		${exception.message}
	</c:if>
	<c:if test="${!empty exception && exception.class.simpleName == 'ValidatorException'}">
		<c:forEach items="${exception.causes}" var="cause">
			<p> * ${cause.key} : ${cause.value} </p> 
		</c:forEach>
	</c:if>
	<c:if test="${empty exception}">
		Update success!
	</c:if>
	<c:if test="${!empty id}">
		<ec:form method="POST" action="${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/edit" id="form_update" update="#pages_body">
			<input type="hidden" name="path" value="${id.path}/${id.id}">
			<input type="hidden" name="locale" value="${id.locale}">
		</ec:form>
		<script>
			var $form = $.AppContext.utils.getById('form_update');
			$form.submit();
		</script>
	</c:if>
</div>
