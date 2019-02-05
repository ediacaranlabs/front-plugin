<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:forEach items="${entity['content']}" var="item" varStatus="count">

	<c:if test="${entity['separator'] == 'true' && count.index > 0}">
		<!-- divider -->
		<div class="row">
		  <div class="span12">
		    <div class="solidline">
		    </div>
		  </div>
		</div>
		<!-- end divider -->
	</c:if>
	
	<c:set var="entity" value="${item}" scope="request"/>
	<c:set var="width" value="12" scope="request"/>
	<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp"/>
	
</c:forEach>
