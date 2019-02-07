<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>
<c:forEach items="${entity['content']}" var="item" varStatus="count">
	<c:set var="entity" value="${item}" scope="request"/>
	<c:set var="width" value="12" scope="request"/>
	<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp"/>
</c:forEach>
