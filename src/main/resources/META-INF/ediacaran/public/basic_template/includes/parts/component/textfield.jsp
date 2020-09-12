<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />


<%-- atributo class --%>
<c:set var="class"  value="${!empty class && !empty entity['size']?                class.concat(' ') : class}${!empty entity['size']?                'col-'.concat(entity['size']) : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class']   : ''    }"/>
<c:set var="class"  value="${!empty class && entity['enabled'] == 'false'?         class.concat(' ') : class}${entity['enabled'] == 'false'?         'uneditable-input'              : ''    }"/>

<%-- atributos - input --%>
<c:set var="attr" value="${!empty attr && entity['enabled'] == 'false'? attr.concat(' ') : attr}${entity['enabled'] == 'false'? 'disabled' : '' }"/>
<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'type='.concat(quote).concat(entity['type']).concat(quote) }"/>
<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'name='.concat(quote).concat(entity['name']).concat(quote) }"/>
<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'value='.concat(quote).concat(entity['value']).concat(quote) }"/>
<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'placeholder='.concat(quote).concat(entity['placeholder']).concat(quote) }"/>


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
<input ${attr} class="form-control ${class}" id="input_${entity['name']}" <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>

<c:if test="${requestScope.formControls}">
		</div>
	</div>
</c:if>