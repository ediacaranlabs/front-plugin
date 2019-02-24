<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- monta o atributo class --%>
<c:set var="class"  value="${!empty entity['type']?                                'icon-'.concat(entity['type']) : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['size']?                class.concat(' ')              : class}${!empty entity['size']?                'icon-'.concat(entity['size'])            : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['class']?               class.concat(' ')              : class}${!empty entity['class']?               entity['class']                           : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ')              : class}${!empty entity['properties']['class']? ' '.concat(entity['properties']['class']) : ''}"/>

<i class="${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />></i>