<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value=                                                                                  "${!empty entity['type']               ? 'list-'.concat(entity['type'])            : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? ' '.concat(entity['properties']['class']) : ''}"/>

<ul class="${class}" <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
	<c:forEach var="item" items="${entity['content']}">
		<li ${entity['type'] == 'inline'? 'class='.concat(quote).concat('list-inline-item').concat(quote) : ''}>
		<c:choose>
			<c:when test="${item.getClass().simpleName == 'String'}">
		     		${item}
			</c:when>
			<c:otherwise>
				<c:set var="entity" value="${item}" scope="request" />
				<jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
			</c:otherwise>
		</c:choose>
		</li>
	</c:forEach>
</ul>
