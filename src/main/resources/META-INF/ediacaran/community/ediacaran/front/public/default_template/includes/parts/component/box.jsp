<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="box aligncenter"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? ' '.concat(entity['properties']['class']) : ''}"/>

<div class="${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
  <div class="aligncenter icon">
    <i class="${entity['icon']} icon-circled icon-64 active"></i>
  </div>
  <div class="text">
    <h6>${entity['title']}</h6>
    <p>
      ${entity['content']}
    </p>
    <a href="${entity['link']}">Learn more</a>
  </div>
</div>
