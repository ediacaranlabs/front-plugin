<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${entity}"/>
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
	<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${item['template']}.jsp"/>
	
</c:forEach>
