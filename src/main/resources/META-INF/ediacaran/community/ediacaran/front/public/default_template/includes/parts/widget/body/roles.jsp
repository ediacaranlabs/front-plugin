<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
<img src="${pageContext.request.contextPath}${entity['image']}" alt=""
	class="img-polaroid" />
<div class="roles">
	<p class="lead">
		<strong>${entity['name']}</strong>
	</p>
	<p>${entity['role']}</p>
</div>
