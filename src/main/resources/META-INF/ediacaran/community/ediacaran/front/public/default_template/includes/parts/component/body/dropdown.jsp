<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class','style']" scope="request" />

<%-- monta o atributo class --%>
<c:set var="class"  value="${!empty entity['type']? 'img-'.concat(entity['type']) : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['class']? class.concat(' ') : class}${!empty entity['class']? entity['class'] : ''}"/>

<div class="btn-group ${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
	<c:choose>
		<c:when test="${entity['label'].getClass().simpleName == 'String'}">
		<a class="btn ${!empty entity['button-type']? ' btn-'.concat(entity['button-type']) : '' }" href="#">
			<c:if test="${!empty entity['icon']}">
			<i class="icon-${entity['icon']}"></i>
			</c:if>
			${entity['label']}
		</a>
		<a class="dropdown-toggle btn${!empty entity['button-type']? ' btn-'.concat(entity['button-type']) : '' }" data-toggle="dropdown" href="#">
			<span class="caret"></span>
		</a>
		</c:when>
		<c:otherwise>
			<c:set var="entity" value="${entity['label']}" scope="request" />
			<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp" />
		</c:otherwise>
	</c:choose>
	<ul class="dropdown-menu">
		<c:forEach items="${entity['itens']}" var="item">
			<c:choose>
			<c:when test="${item['divider']}">
			<li class="divider"></li>
			</c:when>
			<c:otherwise>
			<li>
				<a href="${item['link']}">
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
</div>