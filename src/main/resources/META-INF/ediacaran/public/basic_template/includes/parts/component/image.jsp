<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- monta o atributo class --%>
<c:set var="class"  value=                                                                                  "${!empty entity['type']               ? entity['type']                            : ''}"/>
<c:set var="class"  value="${!empty class && entity['responsive']                ? class.concat(' ') : class}${entity['responsive']                ? 'img-fluid'                               : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? ' '.concat(entity['properties']['class']) : ''}"/>

<%-- monta o atributo style --%>
<c:set var="style"  value=                                                                                     "${!empty entity['width'] ? 'width: '.concat(entity['width']).concat(';')   : ''}"/>
<c:set var="style"  value="${!empty style && !empty entity['height']? style.concat(' ')                 : style}${!empty entity['height']? 'height: '.concat(entity['height']).concat(';') : ''}"/>

<img src="${entity['src']}" class="${class}" style="${style}" <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>