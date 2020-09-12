<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>
<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
<c:set var="entity" value="${requestScope.entity}"/><c:set var="entity" value="${entity['content'][0]}" scope="request"/>
<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp"/>
</div>
