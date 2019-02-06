<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>
<c:if test="${entity['body-wrapper']}"><div class="row"></c:if>
	<c:if test="${!empty entity['title']}">
		<c:if test="${entity['title-wrapper']}"><div class="span${requestScope.width}"></c:if>
			<h4>${entity['title']}</h4>
		<c:if test="${entity['title-wrapper']}"></div><div class="clearfix"></div></c:if>
	</c:if>
	<c:choose>
		<c:when test="${entity['content'].getClass().simpleName == 'String'}">
			<c:if test="${entity['content-wrapper']}"><div class="span${requestScope.width}"></c:if>
			${entity['content']}
			<c:if test="${entity['content-wrapper']}"></div></c:if>
		</c:when>
		<c:otherwise>
			<c:set var="entity" value="${entity['content']}" scope="request" />
			<c:if test="${entity['content-wrapper']}"><div class="span${requestScope.width}"></c:if>
				<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp" />
			<c:if test="${entity['content-wrapper']}"></div></c:if>
		</c:otherwise>
	</c:choose>
<c:if test="${entity['body-wrapper']}"></div></c:if>
