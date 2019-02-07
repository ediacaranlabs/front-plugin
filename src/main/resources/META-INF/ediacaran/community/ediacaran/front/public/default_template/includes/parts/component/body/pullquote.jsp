<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true"%>
<c:set var="entity" value="${requestScope.entity}" />
<span class="pullquote-${entity['type']}">
${entity['content']}
</span>