<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
           <c:choose>
           	<c:when test="${entity['content'].getClass().simpleName == 'String'}">
           	<div class="span12">
           		${entity['content']}
        	</div>
           	</c:when>
           	<c:otherwise>
           	<c:set var="entity" value="${entity['content']}" scope="request"/>
           	<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp"/>
           	</c:otherwise>
           </c:choose>
