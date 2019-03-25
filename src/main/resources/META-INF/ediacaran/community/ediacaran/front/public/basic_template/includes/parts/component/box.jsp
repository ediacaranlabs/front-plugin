<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="box text-center"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? ' '.concat(entity['properties']['class']) : ''}"/>

<div class="${class}" <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
  <div class="text-center">
  	<span class="fa-stack fa-2x">
  		<i class="fa fa-circle fa-stack-2x"></i>
    	<i class="fa ${entity['icon']} fa-stack-1x fa-inverse"></i>
    </span>
  </div>
  <div class="text">
    <h6>${entity['title']}</h6>
    <p>
      ${entity['content']}
    </p>
    <a href="${entity['link']}">Ler mais</a>
  </div>
</div>
