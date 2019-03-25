<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />
<c:set var="accordionID" value="${empty requestScope.accordionID? 1 : requestScope.accordionID + 1}" scope="request"/>

<%-- monta o atributo class--%>
<c:set var="class"  value="accordion"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class'] : ''}"/>

<%-- atributos --%>
<c:set var="attr" value="${!empty attr && !empty entity['name']?        attr.concat(' ') : attr}${!empty entity['name']?  'name='.concat(quote).concat(entity['name']).concat(quote)   : ''}"/>
<c:set var="attr" value="${!empty attr && !empty entity['value']?       attr.concat(' ') : attr}${!empty entity['value']? 'value='.concat(quote).concat(entity['value']).concat(quote) : ''}"/>
<c:set var="attr" value="${!empty attr && entity['selected']?           attr.concat(' ') : attr}${entity['selected']?     'checked'                                                    : ''}"/>
<c:set var="attr" value="${!empty attr && !empty class?                 attr.concat(' ') : attr}${!empty class?           'class='.concat(quote).concat(class).concat(quote)           : ''}"/>

<div ${attr} id="accordion${requestScope.accordionID}" <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
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
        		<jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp"/>
        	</c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>
</c:forEach>
</div>
