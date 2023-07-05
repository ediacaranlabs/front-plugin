<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<div class="alert alert-${empty exception? 'success' : 'danger'} alert-dismissable">
	<div id="result-action" style="display: none">${empty vars.error? 'success' : 'error'}</div>
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	<c:if test="${!empty exception && exception.getClass().getSimpleName() != 'ValidatorException'}">
		${exception.message}
	</c:if>
	<c:if test="${!empty exception && exception.getClass().getSimpleName() == 'ValidatorException'}">
		<c:forEach items="${exception.causes}" var="cause">
			<p> * ${cause.key} : ${cause.value} </p> 
		</c:forEach>
	</c:if>
	<c:if test="${empty exception}">
		Update success!
	</c:if>
	<c:if test="${!empty id}">
		<script>
			var $form = $.AppContext.utils.getById('menubarForm');
			$form.getField('path').setAttribute('readonly', true);
			$form.getField('id').setAttribute('readonly', true);
			$form.getField('locale').setAttribute('readonly', true);
			$form.getField('gid').setValue('${id.hashCode()}');
		</script>
	</c:if>
</div>
