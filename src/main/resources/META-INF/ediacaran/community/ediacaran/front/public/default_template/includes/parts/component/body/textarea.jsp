<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />

<%-- monta a tag class--%>
<c:set var="class"  value="${!empty entity['block']? 'input-block-level' : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['class']? class.concat(' ') : class}${!empty entity['class']? entity['class'] : ''}"/>

<c:choose>
	<%-- constrói o campo com label --%>
	<c:when test="${!empty entity['label']}">
	<div class="control-group">
		<label class="control-label" for="${entity['name']}">${entity['label']}</label>
		<div class="controls">
		<c:set var="ignore" value="['class']" scope="request" />
		<textarea name="${entity['name']}" rows="${empty entity['rows']? '3' : entity['rows']}" placeholder="${entity['placeholder']}" class="${class}" ${entity['enabled'] == 'false'? ' disabled' : '' } <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>${entity['value']}</textarea>
		</div>
	</div>
	</c:when>
	<c:otherwise>
		<%-- constrói somente o campo --%>
		<textarea name="${entity['name']}" rows="${empty entity['rows']? '3' : entity['rows']}" placeholder="${entity['placeholder']}" class="${class}" ${entity['enabled'] == 'false'? ' disabled' : '' } <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>${entity['value']}</textarea>
	</c:otherwise>
</c:choose>
