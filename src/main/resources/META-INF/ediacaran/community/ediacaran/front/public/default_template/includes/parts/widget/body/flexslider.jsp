<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
<div class="flexslider">
	<ul class="slides">
		<c:forEach var="item" items="${entity['content']}">
		<li><img src="${pageContext.request.contextPath}${item}" alt="" /></li>
		</c:forEach>
	</ul>
</div>
