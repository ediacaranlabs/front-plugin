<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />

<%-- monta a tag class--%>
<c:set var="class"  value="${!empty entity['size']? 'input-'.concat(entity['size']) : ''}"/>
<c:set var="class"  value="${!empty class && entity['search']? class.concat(' ') : class}${entity['search']? 'search-query' : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['block']? class.concat(' ') : class}${!empty entity['block']? 'input-block-level' : ''}"/>
<c:set var="class"  value="${!empty class && entity['enabled'] == 'false'? class.concat(' ') : class}${entity['enabled'] == 'false'? 'uneditable-input' : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['class']? class.concat(' ') : class}${!empty entity['class']? entity['class'] : ''}"/>

<%-- inicia o div para agrupar prepend e append--%>
<c:if test="${!('hidden' eq entity['type']) && (!empty entity['prepend'] || !empty entity['append'])}">
<c:set var="append"  value="${!empty entity['prepend']? 'input-prepend' : ''}"/>
<c:set var="append"  value="${!empty append && !empty entity['append']? append.concat(' ') : append}${!empty entity['append']? 'input-append' : ''}"/>
	<c:if test="${!empty append}">
	<div class="${append}">
	</c:if>
</c:if>

<%-- conteudo do prepend --%>
<c:if test="${!('hidden' eq entity['type']) && !empty entity['prepend']}">
	<c:choose>
		<c:when test="${entity['prepend'].getClass().simpleName == 'String'}">
			<span class="add-on">${entity['prepend']}</span>
		</c:when>
		<c:otherwise>
			<c:set var="entity" value="${entity['prepend']}" scope="request" />
			<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp" />
		</c:otherwise>
	</c:choose>
</c:if>

<c:choose>
	<%-- constrói o campo com label --%>
	<c:when test="${!('hidden' eq entity['type']) && !empty entity['label']}">
	<div class="control-group">
		<label class="control-label" for="${entity['name']}">${entity['label']}</label>
		<div class="controls">
		<c:set var="ignore" value="['class']" scope="request" />
		<input type="text" name="${entity['name']}" placeholder="${entity['placeholder']}" class="${class}" value="${entity['value']}" ${entity['enabled'] == 'false'? ' disabled' : '' } <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
		</div>
	</div>
	</c:when>
	<c:otherwise>
		<%-- constrói somente o campo --%>
		<input type="${entity['type']}" name="${entity['name']}" placeholder="${entity['placeholder']}" class="${class}" value="${entity['value']}" ${entity['enabled'] == 'false'? ' disabled' : '' } <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
	</c:otherwise>
</c:choose>

<%-- conteudo do append --%>
<c:if test="${!('hidden' eq entity['type']) && !empty entity['append']}">
	<c:choose>
		<c:when test="${entity['append'].getClass().simpleName == 'String'}">
			<span class="add-on">${entity['append']}</span>
		</c:when>
		<c:otherwise>
			<c:set var="entity" value="${entity['append']}" scope="request" />
			<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp" />
		</c:otherwise>
	</c:choose>
</c:if>

<%-- encerra o div para agrupar prepend e append--%>
<c:if test="${!('hidden' eq entity['type']) && (!empty entity['prepend'] || !empty entity['append'])}">
	<c:if test="${!empty append}">
	</div>
	</c:if>
</c:if>
