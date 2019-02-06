<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>
<dl${!empty entity['type']? " class=\"".concat(entity['type']).concat("\"")  : ''}>
	<c:forEach var="group" items="${entity['content']}">
	<dt>${group['title']}</dt>
	<c:forEach var="item" items="${group['content']}">
	<dd>${item}</dd>
	</c:forEach>
	</c:forEach>
</dl>
