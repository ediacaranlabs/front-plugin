<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
              <h5 class="widgetheading">${entity['title']}</h5>
				${entity['content']}