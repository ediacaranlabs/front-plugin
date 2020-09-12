<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />


<%-- atributo class --%>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class']   : ''    }"/>

<c:if test="${requestScope.formControls}">
	<div class="control-group ${class}" <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
		<div class="controls">
</c:if>

<c:if test="${!empty entity['label']}">
<label for="input_${entity['name']}">${entity['label']}</label>
</c:if>
<c:if test="${empty entity['label']}">
<label class="sr-only" for="input_${entity['name']}">${entity['label']}</label>
</c:if>
	
<div class="input-group ${class}" <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
	<c:if test="${!empty entity['prepend']}">
		<div class="input-group-prepend">
		<c:choose>
			<c:when test="${entity['prepend'].getClass().simpleName == 'String'}">
				<div class="input-group-text">${entity['prepend']}</div>
			</c:when>
			<c:otherwise>
				<c:set var="entity" value="${entity['prepend']}" scope="request" />
				<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
			</c:otherwise>
		</c:choose>
		</div>
	</c:if>
	
	<c:forEach items="${entity['content']}" var="item">
		<c:set var="entity" value="${item}" scope="request" />
		<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
	</c:forEach>
	
	<c:if test="${!empty entity['append']}">
		<div class="input-group-append">
			<c:choose>
				<c:when test="${entity['append'].getClass().simpleName == 'String'}">
					<div class="input-group-text">${entity['append']}</div>
				</c:when>
				<c:otherwise>
					<c:set var="entity" value="${entity['append']}" scope="request" />
					<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>		
</div>

<c:if test="${requestScope.formControls}">
		</div>
	</div>
</c:if>