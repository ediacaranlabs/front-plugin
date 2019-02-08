<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true"%>
<c:set var="entity" value="${requestScope.entity}" />
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="class"  value="${!empty entity['properties']['class']? ' '.concat(entity['properties']['class']) : ''}"/>
<span class="pullquote-${entity['type']}${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp"/>>
${entity['content']}
</span>