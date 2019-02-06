<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
<ul ${!empty entity['type']? " class=\"".concat(entity['type']).concat("\"")  : ''}>
	<c:forEach var="item" items="${entity['content']}">
		<li>
		<c:choose>
			<c:when test="${item.getClass().simpleName == 'String'}">
		     		${item}
			</c:when>
			<c:otherwise>
				<c:set var="entity" value="${item}" scope="request" />
				<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp" />
			</c:otherwise>
		</c:choose>
		</li>
	</c:forEach>
</ul>
