<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['']" scope="request" />
<c:set var="quote" value="\"" />

<c:set var="width"       value="${empty requestScope.width? 12 : requestScope.width}" />
<c:set var="barWidth"     value="${width*0.3333}"/>
<c:set var="barWidth"     value="${barWidth < 1? 1 : barWidth}"/>
<c:set var="contentWidth" value="${width - barWidth < 1? 1 : width - barWidth}"/>

<c:if test="${entity['type'] == 'left'}">
<c:set var="entity"     value="${entity['bar']}" scope="request"/>
<c:set var="ignore"     value="['class']" scope="request" />
<c:set var="attNames"   value="" scope="request" />
<c:set var="attValues"  value="" scope="request" />
<c:set var="attRenames" value="" scope="request" />
<c:set var="barClass"  value="${fn:substringBefore('span'.concat(barWidth + 0.5),'.')}"/>

<c:if test="${requestScope.entity.getClass().simpleName != 'String'}">
<c:set var="barClass"  value="${!empty barClass && !empty requestScope.entity['properties']['class']? barClass.concat(' ') : barClass}${!empty requestScope.entity['properties']['class']? requestScope.entity['properties']['class']   : '' }"/>
</c:if>

<div class="${barClass}" <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
	<aside class="left-sidebar">
		<c:choose>
			<c:when test="${requestScope.entity.getClass().simpleName == 'String'}">
		     		${requestScope.entity}
			</c:when>
			<c:otherwise>
				<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
			</c:otherwise>
		</c:choose>
	</aside>
</div>
</c:if>

<c:set var="entity"     value="${entity['content']}" scope="request"/>
<c:set var="ignore"     value="['class']" scope="request" />
<c:set var="attNames"   value="" scope="request" />
<c:set var="attValues"  value="" scope="request" />
<c:set var="attRenames" value="" scope="request" />
<c:set var="contentClass"  value="${fn:substringBefore('span'.concat(contentWidth + 0.5),'.')}"/>

<c:if test="${requestScope.entity.getClass().simpleName != 'String'}">
<c:set var="contentClass"  value="${!empty contentClass && !empty requestScope.entity['properties']['class']? contentClass.concat(' ') : contentClass}${!empty requestScope.entity['properties']['class']? requestScope.entity['properties']['class']   : '' }"/>
</c:if>

<div class="${contentClass}" <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
	<article>
		<c:choose>
			<c:when test="${requestScope.entity.getClass().simpleName == 'String'}">
		     		${requestScope.entity}
			</c:when>
			<c:otherwise>
				<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
			</c:otherwise>
		</c:choose>
	</article>
</div>

<c:if test="${entity['type'] == 'right'}">
<c:set var="entity"     value="${entity['bar']}" scope="request"/>
<c:set var="ignore"     value="['class']" scope="request" />
<c:set var="attNames"   value="" scope="request" />
<c:set var="attValues"  value="" scope="request" />
<c:set var="attRenames" value="" scope="request" />
<c:set var="barClass"  value="${fn:substringBefore('span'.concat(barWidth + 0.5),'.')}"/>

<c:if test="${requestScope.entity.getClass().simpleName != 'String'}">
<c:set var="barClass"  value="${!empty barClass && !empty requestScope.entity['properties']['class']? barClass.concat(' ') : barClass}${!empty requestScope.entity['properties']['class']? requestScope.entity['properties']['class']   : '' }"/>
</c:if>

<div class="${barClass}" <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
	<aside class="right-sidebar">
		<c:choose>
			<c:when test="${requestScope.entity.getClass().simpleName == 'String'}">
		     		${requestScope.entity}
			</c:when>
			<c:otherwise>
				<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
			</c:otherwise>
		</c:choose>
	</aside>
</div>
</c:if>