<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class'] : ''}"/>

<c:if test="${!empty class || !empty entity['properties']}">
<div class="${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
</c:if>
		<c:if test="${!empty entity['filters']}">
			<ul class="portfolio-categ filter">
				<c:forEach items="${entity['filters']}" var="i" varStatus="count">
					<li class="${i['code']}${ count.index == 0? ' active' : ''}"><a
						href="#">${i['name']}</a></li>
				</c:forEach>
			</ul>
			<div class="clearfix"></div>
		</c:if>

	<div class="row">
		<ul id="thumbs" class="portfolio">
			<!-- Item Project and Filter Name -->
			<c:set var="cols" value="${12/entity['cols']}" />
			<c:forEach items="${entity['content']}" var="i" varStatus="count">
				<li class="item-thumbs span${fn:substringBefore(cols,'.')} design"
					data-id="id-${count.index}" data-type="${i['type']}">
					<!-- Fancybox - Gallery Enabled - Title - Full Image --> <a
					class="hover-wrap fancybox" data-fancybox-group="${i['group']}"
					title="${i['title']}" href="${i['image']}"> <span
						class="overlay-img"></span> <span
						class="overlay-img-thumb font-icon-plus"></span>
				</a> <!-- Thumb Image and Description --> <img src="${i['icon']}"
					alt="${i['content']}">
				</li>
			</c:forEach>
			<!-- End Item Project -->
		</ul>
	</div>
<c:if test="${!empty class || !empty entity['properties']}">
</div>
</c:if>