<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
              <h5 class="widgetheading">${entity['title']}</h5>
              <ul class="link-list">
	    		<c:forEach items="${entity['content']}" var="i">
                <li><a href="${i['link']}">${i['text']}</a></li>
	    		</c:forEach>
              </ul>
