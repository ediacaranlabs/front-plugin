<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:forEach var="item" items="${entity['content']}">
<c:set var="width" value="6" scope="request"/>
<div class="span${requestScope.width}">
	<c:set var="entity" value="${item}" scope="request"/>
	<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp"/>
</div>
</c:forEach>
