<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="[]" scope="request" />
<c:set var="quote" value="\"" />

<c:if test="${!empty entity['properties']}">
<div <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
</c:if>
	<c:if test="${!empty entity['title']}">
		<c:if test="${entity['title-wrapper']}"><div class="col-12"></c:if>
			<h4>${entity['title']}</h4>
		<c:if test="${entity['title-wrapper']}"></div><div class="clearfix"></div></c:if>
	</c:if>
	<c:choose>
		<c:when test="${entity['content'].getClass().simpleName == 'String'}">
			<c:if test="${entity['content-wrapper']}"><div class="col-12"></c:if>
			${entity['content']}
			<c:if test="${entity['content-wrapper']}"></div></c:if>
		</c:when>
		<c:otherwise>
			<c:set var="entity" value="${entity['content']}" scope="request" />
			<c:if test="${entity['content-wrapper']}"><div class="col-12"></c:if>
				<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
			<c:if test="${entity['content-wrapper']}"></div></c:if>
		</c:otherwise>
	</c:choose>
<c:if test="${!empty entity['properties']}">
</div>
</c:if>
