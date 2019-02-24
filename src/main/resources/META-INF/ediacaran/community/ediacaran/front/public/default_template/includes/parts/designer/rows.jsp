<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>
<c:forEach items="${entity['content']}" var="item" varStatus="count">
	<c:set var="entity" value="${item}" scope="request"/>
	<c:set var="oldWidth" value="${width}"/>
	<c:set var="width" value="${empty requestScope.width? '12' : requestScope.width}" scope="request"/>

	<c:if test="${requestScope.entity['row-wrapper']}">
	<div class="row">
	</c:if>
	<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp"/>
	<c:if test="${requestScope.entity['row-wrapper']}">
	</div>
	</c:if>
	<c:set var="width" value="${oldWidth}"/>
</c:forEach>
