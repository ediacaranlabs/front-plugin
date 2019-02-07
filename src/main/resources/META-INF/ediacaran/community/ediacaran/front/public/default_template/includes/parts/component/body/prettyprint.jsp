<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page trimDirectiveWhitespaces="true"%>
<c:set var="entity" value="${requestScope.entity}" />
<pre class="prettyprint">
<c:out value="${entity['content']}" escapeXml="true"/>
</pre>
