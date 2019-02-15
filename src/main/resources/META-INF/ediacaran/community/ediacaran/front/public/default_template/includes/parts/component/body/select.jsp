<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\""/>

<%-- monta a tag class--%>
<c:set var="class"  value="${entity['enabled'] == 'false'? 'uneditable-input' : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['class']? class.concat(' ') : class}${entity['inline']? 'inline' : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['class']? class.concat(' ') : class}${!empty entity['class']? entity['class'] : ''}"/>

<%-- label --%>
<c:if test="${!empty entity['label']}">
<div class="control-group">
	<label class="control-label" for="${entity['name']}">${entity['label']}</label>
	<div class="controls">
</c:if>
<%-- /label --%>

	<select name="${entity['name']}" ${entity['multiple']? ' multiple='.concat(quote).concat('multiple').concat(quote) : ''} class="${class}" ${!empty entity['rows']? ' size='.concat(quote).concat(entity['rows']).concat(quote) : ''} <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
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
