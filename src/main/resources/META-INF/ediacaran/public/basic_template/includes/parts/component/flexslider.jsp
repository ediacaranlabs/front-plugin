<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="flexslider"/>
<c:set var="class"  value="${!empty class && !empty entity['size']?                class.concat(' ') : class}${!empty entity['size']?                'btn-'.concat(entity['size']) : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['type']?                class.concat(' ') : class}${!empty entity['type']?                'btn-'.concat(entity['type']) : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['block']?               class.concat(' ') : class}${!empty entity['block']?               'btn-block-level'             : ''}"/>
<c:set var="class"  value="${!empty class && entity['enabled'] == 'false'?         class.concat(' ') : class}${entity['enabled'] == 'false'?         'disabled'                    : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class'] : ''}"/>

<div class="${class}" <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
	<ul class="slides">
		<c:forEach var="item" items="${entity['content']}">
		<li><img src="${item}" alt="" /></li>
		</c:forEach>
	</ul>
</div>
