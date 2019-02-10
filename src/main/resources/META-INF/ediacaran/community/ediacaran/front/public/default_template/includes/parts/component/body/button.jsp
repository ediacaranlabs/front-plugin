<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>

<%-- monta a tag class--%>
<c:set var="class"  value="${!empty entity['size']? 'btn-'.concat(entity['size']) : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['type']? class.concat(' ') : class}${!empty entity['type']? 'btn-'.concat(entity['type']) : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['block']? class.concat(' ') : class}${!empty entity['block']? 'btn-block-level' : ''}"/>
<c:set var="class"  value="${!empty class && entity['enabled'] == 'false'? class.concat(' ') : class}${entity['enabled'] == 'false'? 'disabled' : ''}"/>

<button type="${empty entity['action']? 'button' : entity['action'] }" class="btn ${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
${entity['label']}
</button>
