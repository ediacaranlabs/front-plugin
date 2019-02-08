<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="class"  value="${!empty entity['properties']['class']? ' '.concat(entity['properties']['class']) : ''}"/>
<div class="pricing-box-alt${entity['special']? ' special' : '' }${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
  <div class="pricing-heading">
    <h3>${entity['title']}</h3>
  </div>
  <div class="pricing-terms">
    <h6>${entity['terms']}</h6>
  </div>
  <div class="pricing-content">
    <ul>
		<c:forEach items="${entity['itens']}" var="item" varStatus="count">
	    <li><c:if test="${item['checked']}"><i class="icon-ok"></i></c:if> ${item['text']}</li>
		</c:forEach>
    </ul>
  </div>
  <div class="pricing-action">
    <a href="${entity['link']}" class="btn btn-medium btn-theme"><i class="icon-bolt"></i> ${entity['button']}</a>
  </div>
</div>