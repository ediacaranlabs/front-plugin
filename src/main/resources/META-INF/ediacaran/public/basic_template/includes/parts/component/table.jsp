<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity"     value="${requestScope.entity}"/>
<c:set var="ignore"     value="['class']" scope="request" />
<c:set var="quote"      value="\"" />
<c:set var="attNames"   value="align;bgcolor;border;cellpadding;cellspacing;frame;rules;summary;width" scope="request" />
<c:set var="attValues"  value="{v};{v};{v};{v};{v};{v};{v};{v};{v}" scope="request" />
<c:set var="attRenames" value="{n};{n};{n};{n};{n};{n};{n};{n};{n}" scope="request" />

<%-- atributo class--%>
<c:set var="class"  value="table"/>
<c:set var="class"  value="${!empty class && !empty entity['type']?                class.concat(' ') : class}${empty entity['type']?                 ''                              : 'table-'.concat(entity['type']) }"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class']   : ''                              }"/>

<table class="${class}" <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
	<%-- header --%>
	<c:if test="${!empty entity['header']}">
		
		<c:catch var="headerTemplateError">
			<c:set var="headerTemplate" value="${entity['header']['template']}"/>
		</c:catch>
	
		<%-- default --%>
		<c:if test="${empty headerTemplate}">
		<thead>
			<tr>
				<c:forEach items="${entity['header']}" var="col">
				<%-- col --%>
				<c:choose>
					<c:when test="${col.getClass().simpleName == 'String'}">
						<th>${col}</th>
					</c:when>
					<c:otherwise>
						<%-- col sem template --%>
						<c:if test="${empty col['template']}">
						<c:set var="entity"     value="${col}" scope="request"/>
						<c:set var="ignore"     value="['']" scope="request" />
						<c:set var="attNames"   value="abbr;align;axis;bgcolor;char;charoff;colspan;headers;height;nowrap;rowspan;scope;sorted;valign;width" scope="request" />
						<c:set var="attValues"  value="{v};{v};{v};{v};{v};{v};{v};{v};{v};{v};{v};{v};{v};{v};{v}" scope="request" />
						<c:set var="attRenames" value="{n};{n};{n};{n};{n};{n};{n};{n};{n};{n};{n};{n};{n};{n};{n}" scope="request" />
						<th <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>${col['content']}</th>
						</c:if>
						<%-- /col sem template --%>
	
						<%-- col com template --%>
						<c:if test="${col['template']}">
						<c:set var="entity" value="${col}" scope="request"/>
						<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
						</c:if>
						<%-- /col com template --%>
					</c:otherwise>
				</c:choose>
				<c:set var="entity" value="${entity}" scope="request"/>
				<%-- col --%>
				</c:forEach>
			</tr>
		</thead>
		</c:if>
		<%-- /default --%>
	
		<%-- template --%>
		<c:if test="${!empty headerTemplate}">
			<c:set var="entity" value="${entity['header']}" scope="request"/>
			<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
		</c:if>	
		<%-- /template --%>
	
	</c:if>
	<%-- /header --%>
	
	<%-- body --%>
	
	<%-- default body --%>
	
	<c:catch var="bodyTemplateError">
		<c:set var="bodyTemplate" value="${entity['rows']['template']}"/>
	</c:catch>
		
	<c:if test="${empty bodyTemplate}">
	<tbody>
		<%-- rows --%>
		<c:forEach items="${entity['rows']}" var="row">
		
		<c:set var="entity"     value="${row}" scope="request"/>
		<c:set var="attNames"   value="align;bgcolor;char;charoff;valign" scope="request" />
		<c:set var="attValues"  value="{v};{v};{v};{v};{v}" scope="request" />
		<c:set var="attRenames" value="{n};{n};{n};{n};{n}" scope="request" />
		
		<tr <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
		
			<c:forEach items="${row['cols']}" var="col">
			<%-- col --%>
			<c:set var="entity" value="${col}" scope="request"/>
			<c:set var="ignore" value="['']" scope="request" />
			<c:choose>
				<c:when test="${col.getClass().simpleName == 'String'}">
					<td>${col}</td>
				</c:when>
				<c:otherwise>
					<%-- col sem template --%>
					<c:if test="${empty col['template']}">
					<c:set var="entity"     value="${col}" scope="request"/>
					<c:set var="ignore"     value="['']" scope="request" />
					<c:set var="attNames"   value="abbr;align;axis;bgcolor;char;charoff;colspan;headers;height;nowrap;rowspan;scope;sorted;valign;width" scope="request" />
					<c:set var="attValues"  value="{v};{v};{v};{v};{v};{v};{v};{v};{v};{v};{v};{v};{v};{v};{v}" scope="request" />
					<c:set var="attRenames" value="{n};{n};{n};{n};{n};{n};{n};{n};{n};{n};{n};{n};{n};{n};{n}" scope="request" />
					<td <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>${col['content']}</td>
					</c:if>
					<%-- /col sem template --%>

					<%-- col com template --%>
					<c:if test="${col['template']}">
					<c:set var="entity" value="${col}" scope="request"/>
					<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
					</c:if>
					<%-- /col com template --%>
				</c:otherwise>
			</c:choose>
			<%-- col --%>
			</c:forEach>
			
		</tr>	
		</c:forEach>
		<%-- /rows --%>
	</tbody>
	</c:if>
	<%-- default body --%>

	<%-- template --%>
	<c:if test="${!empty bodyTemplate}">
		<c:set var="entity" value="${entity['rows']}" scope="request"/>
		<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
	</c:if>	
	<%-- /template --%>
	
	<%-- /body --%>
	
</table>