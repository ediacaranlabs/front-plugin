<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="class"  value="${!empty entity['properties']['class']? ' '.concat(entity['properties']['class']) : ''}"/>
<ol ${!empty entity['type']? " class=\"".concat(entity['type']).concat(class).concat("\"")  : ''} <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
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
</ol>