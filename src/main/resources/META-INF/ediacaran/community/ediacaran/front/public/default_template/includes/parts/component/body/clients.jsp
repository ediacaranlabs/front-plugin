<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="row"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class'] : ''}"/>

<div class="${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
	<div class="span12">
		<h4>${entity['title']}</h4>
		<ul id="mycarousel"
			class="jcarousel-skin-tango recent-jcarousel clients">
			<c:forEach items="${entity['itens']}" var="i" varStatus="count">
				<li><a href="${i['link']}"> <img src="${i['logo']}"
						class="client-logo" alt="" />
				</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
