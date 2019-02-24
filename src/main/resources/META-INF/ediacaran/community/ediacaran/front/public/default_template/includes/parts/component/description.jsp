<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value=""/>
<c:set var="class"  value="${!empty class && !empty entity['type']?                class.concat(' ') : class}${!empty entity['type']?                entity['type'] : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class'] : ''}"/>

<%-- atributos opcionais --%>
<c:set var="attr" value="${!empty attr && !empty class? attr.concat(' ') : attr}${empty class? '' : 'class='.concat(quote).concat(class).concat(quote)}"/>

<dl ${attr} <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
	<c:forEach var="group" items="${entity['content']}">
	<dt>${group['title']}</dt>
	<c:forEach var="item" items="${group['content']}">
	<dd>${item}</dd>
	</c:forEach>
	</c:forEach>
</dl>
