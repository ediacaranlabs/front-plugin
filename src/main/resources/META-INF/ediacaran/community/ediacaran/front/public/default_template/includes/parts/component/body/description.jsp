<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="class"  value="${!empty entity['properties']['class']? ' '.concat(entity['properties']['class']) : ''}"/>
<dl ${!empty entity['type']? " class=\"".concat(entity['type']).concat(class).concat("\"")  : ''} <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
	<c:forEach var="group" items="${entity['content']}">
	<dt>${group['title']}</dt>
	<c:forEach var="item" items="${group['content']}">
	<dd>${item}</dd>
	</c:forEach>
	</c:forEach>
</dl>
