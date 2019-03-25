<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class','style']" scope="request" />
<c:set var="quote" value="\"" scope="request" />

<%-- monta o atributo class --%>
<c:set var="class"  value="btn-group"/>
<c:set var="class"  value="${!empty class && !empty entity['type']?      class.concat(' ') : class}${!empty entity['type']? 'btn-'.concat(entity['type']) : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['drop-type']? class.concat(' ') : class}${!empty entity['drop-type']? 'drop'.concat(entity['drop-type']) : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['class']?     class.concat(' ') : class}${!empty entity['class']? entity['class'] : ''}"/>

<%-- monta o atributo class do button --%>
<c:set var="buttonClass"  value="${!empty entity['button-type']? 'btn-'.concat(entity['button-type']) : ''}"/>
<c:set var="buttonClass"  value="${!empty buttonClass && !empty entity['button-size']? buttonClass.concat(' ') : buttonClass}${!empty entity['button-size']? 'btn-'.concat(entity['button-size']) : ''}"/>

<div class="${class}" <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
	<c:choose>
		<%-- label --%>
		<c:when test="${entity['label'].getClass().simpleName == 'String'}">
		<%-- split-button --%>
		<c:if test="${entity['split-button']}">
		<a class="btn ${buttonClass}" href="#">
			<c:if test="${!empty entity['icon']}">
			<i class="icon-${entity['icon']}"></i>
			</c:if>
			${entity['label']}
		</a>
		<a class="dropdown-toggle btn ${buttonClass}" data-toggle="dropdown" href="#">
			<span class="caret"></span>
		</a>
		</c:if>
		<%-- /split-button --%>
		<%-- not split-button --%>
		<c:if test="${!entity['split-button']}">
		<a class="dropdown-toggle btn ${buttonClass}" data-toggle="dropdown" href="#">
			<c:if test="${!empty entity['icon']}">
			<i class="icon-${entity['icon']}"></i>
			</c:if>
			${entity['label']}
			<i class="caret" style="margin-left: 25px"></i>
		</a>
		</c:if>
		<%-- /not split-button --%>
		</c:when>
		<c:otherwise>
			<c:set var="entity" value="${entity['label']}" scope="request" />
			<jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
		</c:otherwise>
	</c:choose>
	<%-- /label --%>
	<%-- menu --%>
	<ul class="dropdown-menu">
		<c:forEach items="${entity['itens']}" var="item">
			<c:choose>
			<c:when test="${item['divider']}">
			<li class="divider"></li>
			</c:when>
			<c:otherwise>
			<li${!item['enabled']? ' class='.concat(quote).concat('disabled').concat(quote) : ''}>
				<a href="${!item['enabled']? item['link'] : '#'}">
					<c:if test="${!empty item['icon']}">
					<i class="icon-${item['icon']}"></i>
					</c:if>
					${item['label']}
				</a>
			</li>
			</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
	<%-- /menu --%>
</div>