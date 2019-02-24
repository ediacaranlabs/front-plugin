<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class--%>
<c:set var="class"  value="table"/>
<c:set var="class"  value="${!empty class && !empty entity['type']?                class.concat(' ') : class}${empty entity['type']?                 ''                              : 'table-'.concat(entity['type']) }"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class']   : ''                              }"/>

<table class="${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
	<c:if test="${!empty entity['cols']}">
	<%-- head --%>
	<thead>
		<tr>
			<c:forEach items="${entity['cols']}" var="col">
			<%-- col --%>
			<c:set var="entity" value="${col}" scope="request"/>
			<c:set var="ignore" value="['']" scope="request" />
			<c:choose>
				<c:when test="${col.getClass().simpleName == 'String'}">
					<th>${col}</th>
				</c:when>
				<c:otherwise>
					<%-- col sem template --%>
					<c:if test="${empty col['template']}">
					<th <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>${col['content']}</th>
					</c:if>
					<%-- /col sem template --%>

					<%-- col com template --%>
					<c:if test="${col['template']}">
					<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp" />
					</c:if>
					<%-- /col com template --%>
				</c:otherwise>
			</c:choose>
			<c:set var="entity" value="${entity}" scope="request"/>
			<%-- col --%>
			</c:forEach>
		</tr>
	</thead>
	<%-- /head --%>
	</c:if>
	<tbody>
	</tbody>
</table>