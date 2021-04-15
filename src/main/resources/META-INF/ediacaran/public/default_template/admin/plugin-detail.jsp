<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<c:forEach items="${pluginConfig.metadata.properties}" var="property">
	<ed:row style="form">
		<ed:col size="12">
			<c:choose>
				<c:when test="${property.type == 'TEXT'}">
					<ec:textfield label="${property.name}" name="${property.code}" value="${pluginConfig.getString(property.code)}"/>
				</c:when>
				<c:when test="${property.type == 'SELECT'}">
					<ec:select name="${property.code}" label="${property.name}">
						<c:set var="opt_selected" value="${pluginConfig.getString(property.code)}"/>
						<c:forEach items="${property.options}" var="opts">
							<ec:option label="${opts.description}" selected="${opts.value == opt_selected}" value="${opts.value}"/>
						</c:forEach>
					</ec:select>
					<small>${property.description}</small>
				</c:when>
				<c:when test="${property.type == 'MULTISELECT'}">
					<ec:select name="${property.code}" label="${property.name}" multiple="true" sizeList="5">
						<c:set var="opt_selected" value="${pluginConfig.getString(property.code)}"/>
						<c:forEach items="${property.options}" var="opts">
							<ec:option label="${opts.description}" selected="${opts.value == opt_selected}" value="${opts.value}"/>
						</c:forEach>
					</ec:select>
					<small>${property.description}</small>
				</c:when>
				<c:when test="${property.type == 'SELECT_LIST'}">
					${property.name}<br>
					<c:set var="opt_selected" value="${pluginConfig.getString(property.code)}"/>
					<c:forEach items="${property.options}" var="opts">
						<ec:radio inline="true" name="${property.code}" label="${opts.description}" value="${opts.value}" selected="${opts.value == opt_selected}"/>
					</c:forEach>
					<br>
					<small>${property.description}</small>
				</c:when>
				<c:when test="${property.type == 'MULTISELECT_LIST'}">
					${property.name}<br>
					<c:set var="opt_selected" value="${pluginConfig.getString(property.code)}"/>
					<c:forEach items="${property.options}" var="opts">
						<ec:checkbox inline="true" name="${property.code}" label="${opts.description}" value="${opts.value}" selected="${opts.value == opt_selected}"/>
					</c:forEach><br>
					<small>${property.description}</small>
				</c:when>
			</c:choose>
		</ed:col>
	</ed:row>
</c:forEach>
