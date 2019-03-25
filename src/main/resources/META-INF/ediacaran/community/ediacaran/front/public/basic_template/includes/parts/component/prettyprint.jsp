<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page trimDirectiveWhitespaces="true"%>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="prettyprint"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class'] : ''}"/>

<pre class="${class}" <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp"/>>
<c:out value="${entity['content']}" escapeXml="true"/>
</pre>
