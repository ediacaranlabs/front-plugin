<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="accordionID" value="${empty requestScope.accordionID? 1 : requestScope.accordionID + 1}" scope="request"/>
<div class="accordion" id="accordion${requestScope.accordionID}">
<c:forEach var="item" items="${entity['content']}" varStatus="count">
  <div class="accordion-group">
    <div class="accordion-heading">
      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion${requestScope.accordionID}" href="#collapse${requestScope.accordionID}_${count.index}">${item['title']}</a>
    </div>
    <div id="collapse${requestScope.accordionID}_${count.index}" class="accordion-body collapse${count.index == 0? ' in' : ''}">
      <div class="accordion-inner">
        <c:choose>
        	<c:when test="${item['content'].getClass().simpleName == 'String'}">
        		${item['content']}
       		</c:when>
        	<c:otherwise>
        		<c:set var="entity" value="${item['content']}" scope="request"/>
        		<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp"/>
        	</c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>
</c:forEach>
</div>
