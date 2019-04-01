<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="${!empty class && !empty entity['size']?                class.concat(' ') : class}${!empty entity['size']?                'col-'.concat(entity['size']) : ''}"/>
<c:set var="class"  value="${!empty class && entity['search']?                     class.concat(' ') : class}${entity['search']?                     'search-query'                  : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['block']?               class.concat(' ') : class}${!empty entity['block']?               'input-block-level'             : ''}"/>
<c:set var="class"  value="${!empty class && entity['enabled'] == 'false'?         class.concat(' ') : class}${entity['enabled'] == 'false'?         'uneditable-input'              : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class']   : ''}"/>

<%-- atributos --%>
<c:set var="attr" value="${!empty attr && entity['enabled'] == 'false'? attr.concat(' ') : attr}${entity['enabled'] == 'false'? 'disabled' : '' }"/>

<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'type='.concat(quote).concat(entity['type']).concat(quote) }"/>
<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'name='.concat(quote).concat(entity['name']).concat(quote) }"/>
<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'value='.concat(quote).concat(entity['value']).concat(quote) }"/>
<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'placeholder='.concat(quote).concat(entity['placeholder']).concat(quote) }"/>

<c:if test="${!empty entity['label']}">
<label for="input_${entity['name']}">${entity['label']}</label>
</c:if>
<c:if test="${empty entity['label']}">
<label class="sr-only" for="input_${entity['name']}">${entity['label']}</label>
</c:if>

<c:if test="${!(entity['type'] eq 'hidden') && (!empty entity['prepend'] || !empty entity['append']) }">
	
	<div class="input-group">
	
		<c:if test="${!empty entity['prepend']}">
		<div class="input-group-prepend">
			<c:choose>
				<c:when test="${entity['prepend'].getClass().simpleName == 'String'}">
					<div class="input-group-text">${entity['prepend']}</div>
				</c:when>
				<c:otherwise>
					<c:set var="entity" value="${entity['prepend']}" scope="request" />
					<jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
		</c:if>
		
		<input ${attr} class="form-control" id="input_${entity['name']}">
		
		<c:if test="${!empty entity['append']}">
		<div class="input-group-append">
			<c:choose>
				<c:when test="${entity['append'].getClass().simpleName == 'String'}">
					<div class="input-group-text">${entity['append']}</div>
				</c:when>
				<c:otherwise>
					<c:set var="entity" value="${entity['append']}" scope="request" />
					<jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
		</c:if>		
	</div>
	
</c:if>

<c:if test="${(entity['type'] eq 'hidden') || (empty entity['prepend'] && empty entity['append']) }">
	<input ${attr} class="form-control" id="input_${entity['name']}">
</c:if>