<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="${requestScope.ignore}"/>
<c:set var="quote" value="'"/>
<c:forEach var="item" items="${entity['properties']}">
<c:if test="${empty ignore || fn:indexOf(ignore, quote.concat(item.key).concat(quote)) == -1}">
${item.key}=${item.value}
</c:if>
</c:forEach>
