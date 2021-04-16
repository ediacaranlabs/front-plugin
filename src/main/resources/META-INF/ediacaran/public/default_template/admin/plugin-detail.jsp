<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<ec:form id="config_fr">
	<c:forEach items="${pluginConfig.metadata.properties}" var="property">
		<ed:row style="form">
			<ed:col size="6">
				<c:choose>
					<c:when test="${property.type == 'TEXT'}">
						<ec:textfield label="${property.name}" name="${property.code}" value="${pluginConfig.getRawValue(property.code)}"/>
					</c:when>
					<c:when test="${property.type == 'SELECT'}">
						<ec:select name="${property.code}" label="${property.name}">
							<c:set var="opt_selected" value="${pluginConfig.getRawValue(property.code)}"/>
							<c:forEach items="${property.options}" var="opts">
								<ec:option label="${opts.description}" selected="${opts.value == opt_selected}" value="${opts.value}"/>
							</c:forEach>
						</ec:select>
					</c:when>
					<c:when test="${property.type == 'MULTISELECT'}">
						<ec:select name="${property.code}" label="${property.name}" multiple="true" sizeList="5">
							<c:set var="opt_selected" value="${pluginConfig.getRawValue(property.code)}"/>
							<c:forEach items="${property.options}" var="opts">
								<ec:option label="${opts.description}" selected="${opts.value == opt_selected}" value="${opts.value}"/>
							</c:forEach>
						</ec:select>
					</c:when>
					<c:when test="${property.type == 'SELECT_LIST'}">
						${property.name}<br>
						<c:set var="opt_selected" value="${pluginConfig.getRawValue(property.code)}"/>
						<c:forEach items="${property.options}" var="opts">
							<ec:radio inline="true" name="${property.code}" label="${opts.description}" value="${opts.value}" selected="${opts.value == opt_selected}"/>
						</c:forEach>
					</c:when>
					<c:when test="${property.type == 'MULTISELECT_LIST'}">
						${property.name}<br>
						<c:set var="opt_selected" value="${pluginConfig.getRawValue(property.code)}"/>
						<c:forEach items="${property.options}" var="opts">
							<ec:checkbox inline="true" name="${property.code}" label="${opts.description}" value="${opts.value}" selected="${opts.value == opt_selected}"/>
						</c:forEach>
					</c:when>
				</c:choose>
				<ec:field-validator form="config_fr" field="${property.code}">
					<c:if test="${!property.allowEmpty}">
					<ec:field-validator-rule name="notEmpty" message="You must agree before submitting."/>
					</c:if>
					
					<c:if test="${property.min > 0 || property.max > 0}">
					<ec:field-validator-rule name="stringLength"  message="Value is short or large">
						<c:if test="${property.min > 0}">
						<ec:field-validator-param name="min">${property.min}</ec:field-validator-param>
						</c:if>
						<c:if test="${property.max > 0}">
						<ec:field-validator-param name="max">${property.max}</ec:field-validator-param>
						</c:if>
					</ec:field-validator-rule>
					</c:if>
					
					<c:if test="${!empty property.regex}">
					<ec:field-validator-rule name="regexp"  message="Invalid value!">
						<ec:field-validator-param name="regexp" raw="true">/${property.regex}/</ec:field-validator-param>
					</ec:field-validator-rule>
					</c:if>
					
				</ec:field-validator>
			</ed:col>
			<ed:col size="6">
				<small>${property.description}</small>
			</ed:col>
		</ed:row>
		<ed:row style="form">
			<ed:col size="12">
				<hr>
			</ed:col>
		</ed:row>	
	</c:forEach>
</ec:form>