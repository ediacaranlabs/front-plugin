<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>
<c:forEach items="${entity['content']}" var="item" varStatus="count">
	<c:set var="entity" value="${item}" scope="request"/>

	<c:if test="${empty item['row-wrapper']? entity['wrapper'] : item['row-wrapper']}">
	<div class="row">
	</c:if>
	<jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp"/>
	<c:if test="${empty item['row-wrapper']? entity['wrapper'] : item['row-wrapper']}">
	</div>
	</c:if>
</c:forEach>
