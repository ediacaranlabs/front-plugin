<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="[]" scope="request" />
<c:set var="quote" value="\"" />

<c:if test="${!empty entity['properties']}">
<div <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
</c:if>

<c:if test="${empty entity['type'] || entity['type'] == 'left'}">
<div class="col-lg-5 col-sm-12">
	<hr class="section-heading-spacer">
	<div class="clearfix"></div>
	<h2 class="section-heading">${entity['title']}</h2>
	<p class="lead">${entity['text']}</p>
</div>
<div class="col-lg-5 offset-lg-2 col-sm-12 text-center">
	<img src="${entity['image']}" class="mx-auto d-block">
</div>
</c:if>

<c:if test="${entity['type'] == 'right'}">
<div class="col-lg-5 col-sm-12 text-center">
	<img src="${entity['image']}" class="mx-auto d-block">
</div>
<div class="col-lg-5 offset-lg-2 col-sm-12">
	<hr class="section-heading-spacer">
	<div class="clearfix"></div>
	<h2 class="section-heading">${entity['title']}</h2>
	<p class="lead">${entity['text']}</p>
</div>
</c:if>

<c:if test="${!empty entity['properties']}">
</div>
</c:if>