<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="row"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class']  : '' }"/>

<!-- divider -->
<div class="${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
  <div class="span${requestScope.width}">
    <div class="solidline">
    </div>
  </div>
</div>
<!-- end divider -->
