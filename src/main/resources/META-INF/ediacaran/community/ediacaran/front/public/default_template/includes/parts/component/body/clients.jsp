<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="class"  value="${!empty entity['properties']['class']? ' '.concat(entity['properties']['class']) : ''}"/>
<c:set var="entity" value="${requestScope.entity}"/>
        <div class="row${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
          <div class="span12">
            <h4>${entity['title']}</h4>
            <ul id="mycarousel" class="jcarousel-skin-tango recent-jcarousel clients">
              <c:forEach items="${entity['itens']}" var="i" varStatus="count">
              <li>
                <a href="${i['link']}">
					<img src="${i['logo']}" class="client-logo" alt="" />
					</a>
              </li>
              </c:forEach>            
            </ul>
          </div>
        </div>
