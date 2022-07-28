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
		<script>
			var $form = $.AppContext.utils.getById('page_edit_form');
			<c:forEach items="${id}" var="val_id">
				var ${val_id.key}_form = $form.getField('${val_id.key}');
				${val_id.key}_form.setValue('${val_id.value}');
				${val_id.key}_form.setProperty('disabled', true);
			</c:forEach>
			
		</script>
	</c:if>
</div>
