<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>

<ul class="link-list">
	<c:forEach items="${entity['content']}" var="i">
		<li><a href="${i['link']}">${i['text']}</a></li>
	</c:forEach>
</ul>