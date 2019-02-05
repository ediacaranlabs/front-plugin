<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
<div class="row">
	<c:if test="${!empty entity['title']}">
		<div class="span${requestScope.width}">
			<h4>${entity['title']}</h4>
		</div>
		<div class="clearfix"></div>
	</c:if>
	<c:choose>
		<c:when test="${entity['content'].getClass().simpleName == 'String'}">
			<c:if test="${entity['wrapper']}"><div class="span${requestScope.width}"></c:if>
			${entity['content']}
			<c:if test="${entity['wrapper']}"></div></c:if>
		</c:when>
		<c:otherwise>
			<c:set var="entity" value="${entity['content']}" scope="request" />
			<c:if test="${entity['wrapper']}"><div class="span${requestScope.width}"></c:if>
				<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp" />
			<c:if test="${entity['wrapper']}"></div></c:if>
		</c:otherwise>
	</c:choose>
</div>
