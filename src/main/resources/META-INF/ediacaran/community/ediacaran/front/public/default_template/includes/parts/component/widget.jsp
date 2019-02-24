<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>

<h5 class="widgetheading">${entity['title']}</h5>
<c:choose>
	<c:when test="${entity['content'].getClass().simpleName == 'String'}">
		${entity['content']}
	</c:when>
	<c:otherwise>
		<c:set var="entity" value="${entity['content']}" scope="request" />
		<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp" />
	</c:otherwise>
</c:choose>