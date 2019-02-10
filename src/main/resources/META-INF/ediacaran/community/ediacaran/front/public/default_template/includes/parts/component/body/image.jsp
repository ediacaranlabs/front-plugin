<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class','style']" scope="request" />

<%-- monta o atributo class --%>
<c:set var="class"  value="${!empty entity['type']? 'img-'.concat(entity['type']) : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['class']? class.concat(' ') : class}${!empty entity['class']? entity['class'] : ''}"/>

<%-- monta o atributo style --%>
<c:set var="style"  value="${!empty entity['width']? 'width: '.concat(entity['width']) : ''}"/>
<c:set var="style"  value="${!empty style && !empty entity['height']? style.concat(' ') : style}${!empty entity['height']? 'height: '.concat(entity['height']) : ''}"/>
<c:set var="style"  value="${!empty style && !empty entity['style']? class.concat(' ') : style}${!empty entity['style']? entity['style'] : ''}"/>

<img src="${entity['src']}" class="${class}" style="${style}"  <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>