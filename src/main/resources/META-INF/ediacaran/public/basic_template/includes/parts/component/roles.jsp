<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<c:if test="${!empty entity['properties']}"><div <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp"/>></c:if>
<img src="${entity['image']}" alt="" class="img-thumbnail" />
<div class="roles">
	<p class="lead">
		<strong>${entity['name']}</strong>
	</p>
	<p>${entity['role']}</p>
</div>
<c:if test="${!empty entity['properties']}"></div></c:if>
