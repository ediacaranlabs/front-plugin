<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="[]" scope="request" />
<c:set var="quote" value="\"" />

<div <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
	<c:choose>
		<c:when test="${entity['content'].getClass().simpleName == 'String'}">
			${entity['content']}
		</c:when>
		<c:otherwise>
			<c:set var="entity" value="${entity['content']}" scope="request" />
				<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
		</c:otherwise>
	</c:choose>
</div>