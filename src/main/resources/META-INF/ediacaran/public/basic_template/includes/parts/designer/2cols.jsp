<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>
<c:forEach var="item" items="${entity['content']}">
<div class="col-sm-12 col-md-12 col-lg-6 col-xl-6">
	<c:set var="entity" value="${item}" scope="request"/>
	<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp"/>
</div>
</c:forEach>
