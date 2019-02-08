<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="class"  value="${!empty entity['properties']['class']? ' '.concat(entity['properties']['class']) : ''}"/>
<!-- divider -->
<div class="row${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
  <div class="span12">
    <div class="solidline">
    </div>
  </div>
</div>
<!-- end divider -->
