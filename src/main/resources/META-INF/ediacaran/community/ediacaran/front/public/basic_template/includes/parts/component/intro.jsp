<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="ignore" value="['style']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class'] : ''}"/>

<%-- atributo style --%>
<c:set var="style"  value="${!empty style && !empty entity['background-image']   ? style.concat(' ') : style}${!empty entity['background-image']   ? 'background: url('.concat(pageContext.request.contextPath).concat(entity['background-image']).concat(') no-repeat center center; background-size: cover;') : ''}"/>
<c:set var="style"  value="${!empty style && !empty entity['properties']['style']? style.concat(' ') : style}${!empty entity['properties']['style']? entity['properties']['style'] : ''}"/>

<div style="${style}" class="intro-header${class}" <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
	<div class="container">
			<div class="intro-message">
				<div class="row">
				<c:choose>
					<c:when test="${entity['content'].getClass().simpleName == 'String'}">
						<c:if test="${entity['content-wrapper']}"><div class="span${requestScope.width}"></c:if>
						${entity['content']}
						<c:if test="${entity['content-wrapper']}"></div></c:if>
					</c:when>
					<c:otherwise>
						<c:set var="entity" value="${entity['content']}" scope="request" />
						<c:if test="${entity['content-wrapper']}"><div class="span${requestScope.width}"></c:if>
							<jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
						<c:if test="${entity['content-wrapper']}"></div></c:if>
					</c:otherwise>
				</c:choose>
				</div>
			</div>
	</div>
	<!-- /.container -->

</div>

