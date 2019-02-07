<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true"%>
<c:set var="entity" value="${requestScope.entity}" />
<blockquote>
	<i class="icon-quote-left icon-2x"></i>
	${entity['content']}
	<c:if test="${!empty entity['cite']}"> 
	<cite>${entity['cite']}</cite>
	</c:if>
</blockquote>