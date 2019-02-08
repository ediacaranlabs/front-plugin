<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>
<c:if test="${!empty entity['properties']}"><div <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp"/>></c:if>
<img src="${pageContext.request.contextPath}${entity['image']}" alt=""
	class="img-polaroid" />
<div class="roles">
	<p class="lead">
		<strong>${entity['name']}</strong>
	</p>
	<p>${entity['role']}</p>
</div>
<c:if test="${!empty entity['properties']}"></div></c:if>
