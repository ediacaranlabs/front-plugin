<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class']  : '' }"/>

<%-- atributos --%>
<c:set var="attr" value="${!empty attr && !empty entity['accept-charset']? attr.concat(' ') : attr}${!empty entity['accept-charset']? 'accept-charset='.concat(quote).concat(entity['accept-charset']).concat(quote) : '' }"/>
<c:set var="attr" value="${!empty attr && entity['enabled'] == 'false'?    attr.concat(' ') : attr}${entity['enabled'] == 'false'?    'disabled'                                                                     : '' }"/>

<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'name='.concat(quote).concat(entity['name']).concat(quote) }"/>
<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'rows='.concat(quote).concat(empty entity['rows']? 3 : entity['rows']).concat(quote) }"/>
<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'placeholder='.concat(quote).concat(entity['placeholder']).concat(quote) }"/>
<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'class='.concat(quote).concat(class).concat(quote) }"/>

<c:choose>
	<c:when test="${!empty entity['label']}">
	<%-- constrói o campo com label --%>
	<div class="control-group ${class}" <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp"/>>
		<label class="control-label" for="${entity['name']}">${entity['label']}</label>
		<div class="controls">
		<textarea ${attr}>${entity['value']}</textarea>
		</div>
	</div>
	<%-- /constrói o campo com label --%>
	</c:when>
	<c:otherwise>
		<%-- constrói somente o campo --%>
		<textarea ${attr} class="${class}" <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>${entity['value']}</textarea>
		<%-- /constrói somente o campo --%>
	</c:otherwise>
</c:choose>
