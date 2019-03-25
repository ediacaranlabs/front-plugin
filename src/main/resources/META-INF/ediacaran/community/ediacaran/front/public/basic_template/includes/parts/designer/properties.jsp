<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:if test="${requestScope.entity.getClass().simpleName != 'String'}">
	<c:set var="entity"     value="${requestScope.entity}"/>
	<c:set var="ignore"     value="${requestScope.ignore}"/>
	<c:set var="quote"      value="'"/>
	<c:set var="attNames"   value="${fn:split(requestScope.attNames,';')}"/>
	<c:set var="attValues"  value="${fn:split(requestScope.attValues,';')}"/>
	<c:set var="attRenames" value="${fn:split(requestScope.attRenames,';')}"/>
	
	<c:forEach var="item" items="${attNames}" varStatus="count">
	<c:if test="${!empty entity[item]}">${fn:replace(attRenames[count.index], '{n}', item)}="${fn:replace(attValues[count.index], '{v}', entity[item])}" </c:if>
	</c:forEach>
	
	<c:forEach var="item" items="${entity['properties']}">
	<c:if test="${empty ignore || fn:indexOf(ignore, quote.concat(item.key).concat(quote)) == -1}">
	${item.key}="${item.value}"
	</c:if>
	</c:forEach>
</c:if>
