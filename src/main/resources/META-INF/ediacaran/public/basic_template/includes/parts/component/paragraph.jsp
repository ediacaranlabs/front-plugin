<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class']  : '' }"/>

<p class="${class}" <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
<c:forEach items="${entity['content']}" var="item" varStatus="count">

	<c:choose>
		<c:when test="${item.getClass().simpleName == 'String'}">
			${item}
		</c:when>
		<c:otherwise>
			<c:set var="entity" value="${entity['content']}" scope="request" />
			<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
		</c:otherwise>
	</c:choose>

</c:forEach>
</p>