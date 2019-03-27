<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="row"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class'] : ''}"/>

<div class="${class}" <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
	<div class="col-12">
		<h4>${entity['title']}</h4>
		<div class="owl-carousel owl-theme">
			<c:forEach items="${entity['itens']}" var="i" varStatus="count">
				<div class="item">
					<a href="${i['link']}"><img src="${i['logo']}" class="client-logo" alt="" /></a>
				</div>
			</c:forEach>
		</div>
	</div>
</div>