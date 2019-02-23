<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\""/>

<%-- monta a tag class--%>
<c:set var="class"  value="${!empty class && entity['enabled'] == 'false'? class.concat(' ') : class}${entity['enabled'] == 'false'? 'uneditable-input' : ''}"/>
<c:set var="class"  value="${!empty class && entity['inline']?             class.concat(' ') : class}${entity['inline']?             'inline'           : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['class']?       class.concat(' ') : class}${!empty entity['class']?       entity['class']    : ''}"/>

<%-- atributos --%>
<c:set var="attr" value="${!empty attr? attr.concat(' ') : attr}${'name='.concat(quote).concat(entity['name']).concat(quote) }"/>

<c:set var="attr" value="${!empty attr && !entity['multiple']?  attr : attr.concat(' ')}${!entity['multiple']?  '' : 'multiple='.concat(quote).concat(multiple).concat(quote)   }"/>
<c:set var="attr" value="${!empty attr && empty entity['rows']? attr : attr.concat(' ')}${empty entity['rows']? '' : fn:substringBefore('size='.concat(quote).concat(entity['rows']),'.').concat(quote) }"/>

<%-- label --%>
<c:if test="${!empty entity['label']}">
<div class="control-group ${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp"/>>
	<label class="control-label" for="${entity['name']}">${entity['label']}</label>
	<div class="controls">
</c:if>
<%-- /label --%>

	<select ${attr} <c:if test="${empty entity['label']}">class="${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp"/></c:if>>
	<%-- values --%>
		<c:forEach items="${entity['options']}" var="option">
		<%-- options agrupados --%>
		<c:if test="${!empty option['group']}">
		<optgroup label="${option['group']}">
		<c:forEach items="${option['options']}" var="groupOption">
			<option value="${groupOption['value']}"${groupOption['value'] == entity['value']? ' selected' : ''}>${groupOption['label']}</option>
		</c:forEach>
		</optgroup>
		</c:if>
		<%-- /options agrupados --%>
		<%-- options --%>
		<c:if test="${empty option['group']}">
		<option value="${option['value']}"${option['value'] == entity['value']? ' selected' : ''}>${option['label']}</option>
		</c:if>
		<%-- /options --%>
		</c:forEach>
	<%-- /values --%>
	</select>
		
<%-- label --%>
<c:if test="${!empty entity['label']}">
	</div>
</div>
</c:if>
<%-- /label --%>
