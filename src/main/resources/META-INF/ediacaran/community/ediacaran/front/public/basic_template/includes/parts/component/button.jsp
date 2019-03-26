<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="btn"/>
<c:set var="class"  value="${!empty class && !empty entity['size']?                class.concat(' ') : class}${!empty entity['size']?                'btn-'.concat(entity['size']) : ''}"/>
<c:set var="class"  value="${!empty class                         ?                class.concat(' ') : class}${!empty entity['type']?                'btn-'.concat(entity['type']) : 'btn-primary'}"/>
<c:set var="class"  value="${!empty class && !empty entity['block']?               class.concat(' ') : class}${!empty entity['block']?               'btn-block-level'             : ''}"/>
<c:set var="class"  value="${!empty class && entity['enabled'] == 'false'?         class.concat(' ') : class}${entity['enabled'] == 'false'?         'disabled'                    : ''}"/>

<%-- atributos opcionais--%>
<c:set var="attr" value="${!empty attr && !empty class? attr.concat(' ') : attr}${!empty entity['name']? 'name='.concat(quote).concat(entity['name']).concat(quote) : ''}"/>

<c:set var="attr" value="${!empty attr?                 attr.concat(' ') : attr}${'type='.concat(quote).concat((empty entity['action']? 'button' : entity['action'])).concat(quote)}"/>

<c:if test="${requestScope.formControls}">
	<div class="control-group ${entity['properties']['class']}">
		<div class="controls">
</c:if>
<button ${attr} class="${class}" <c:if test="${empty requestScope.formControls}">class="${class}" <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" /></c:if>>
${entity['label']}
</button>
<c:if test="${requestScope.formControls}">
		</div>
	</div>
</c:if>