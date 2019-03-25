<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class'] : ''}"/>

<c:if test="${entity['wrapper']}"><div class="row${class}" <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />></c:if>
<c:if test="${empty entity['wrapper'] && !empty entity['properties']}"><div class="${class}" <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp"/>></c:if>
	<c:if test="${!empty entity['title']}">
		<c:if test="${entity['title-wrapper']}"><div class="span${requestScope.width}"></c:if>
			<h4>${entity['title']}</h4>
		<c:if test="${entity['title-wrapper']}"></div><div class="clearfix"></div></c:if>
	</c:if>
	<c:choose>
		<c:when test="${entity['content'].getClass().simpleName == 'String'}">
			<c:if test="${entity['content-wrapper']}"><div class="span${requestScope.width}"></c:if>
			${entity['content']}
			<c:if test="${entity['content-wrapper']}"></div></c:if>
		</c:when>
		<c:otherwise>
			<c:set var="entity" value="${entity['content']}" scope="request" />
			<c:if test="${entity['content-wrapper']}"><div class="span${requestScope.width}"></c:if>
				<jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
			<c:if test="${entity['content-wrapper']}"></div></c:if>
		</c:otherwise>
	</c:choose>
<c:if test="${empty entity['wrapper'] && !empty entity['properties']}"></div></c:if>
<c:if test="${entity['wrapper']}"></div></c:if>
