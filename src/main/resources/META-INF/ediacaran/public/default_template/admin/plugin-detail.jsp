<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<ec:form method="POST" id="status_config_fr" update="#enable_plugin_result" action="/plugins/ediacaran/front/adm/plugins/${vars.config.metadata.code}/status">
<ec:event type="form:after">
	var $form = new $.AppContext.types.Form('status_config_fr');
	$statusField = $form.getField('status');
	$statusField.setValue(e.response.status);
</ec:event>
<ed:row style="form">
	<ed:col size="8">
		<h3>${vars.config.metadata.name}</h3>
	</ed:col>
	<ed:col size="4">
		<div class="float-right">
			<ec:radio name="status" inline="true" label="Enabled" value="true" selected="${vars.status == 'RUNNING' || vars.status == 'STOPPED_ERROR'}">
				<ec:event type="click">
				
					var $form = new $.AppContext.types.Form('status_config_fr');
					var $statusField = $form.getField('status');
					$.AppContext.utils.execute('/plugins/ediacaran/front/adm/plugins/${vars.config.metadata.code}/status', 'POST', {status:$status.getValue()}, function(e){
						$statusField.setValue(e.response.status);
					});
					
					var $form = new $.AppContext.types.Form('status_config_fr');
					$form.submit();
				</ec:event>
			</ec:radio>
			<ec:radio name="status" inline="true" label="Disabled" value="false"  selected="${!(vars.status == 'RUNNING' || vars.status == 'STOPPED_ERROR')}">
				<ec:event type="click">
					var $form = new $.AppContext.types.Form('status_config_fr');
					$form.submit();
				</ec:event>
			</ec:radio>
		</div>
	</ed:col>
</ed:row>
<ed:row style="form">
	<ed:col id="enable_plugin_result" size="12">
	</ed:col>
</ed:row>
</ec:form>	
<ec:form method="POST" id="config_fr" update="#update_plugin_result" action="/plugins/ediacaran/front/adm/plugins/${vars.config.metadata.code}">
<ec:table style="striped">
	<ec:table-body>
	<c:forEach items="${vars.config.metadata.properties}" var="property">
		<ec:table-row>
			 <ec:table-col classStyle="form-group has-feedback">
				<c:choose>
					<c:when test="${property.type == 'TEXT'}">
						<ec:textfield label="${property.name}" name="config.${property.code}" value="${vars.config.getRawValue(property.code)}"/>
					</c:when>
					<c:when test="${property.type == 'SELECT'}">
						<ec:select name="config.${property.code}" label="${property.name}">
							<c:set var="opt_selected" value="${vars.config.getRawValue(property.code)}"/>
							<c:forEach items="${property.options}" var="opts">
								<ec:option label="${opts.description}" selected="${opts.value == opt_selected}" value="${opts.value}"/>
							</c:forEach>
						</ec:select>
					</c:when>
					<c:when test="${property.type == 'MULTISELECT'}">
						<ec:select name="config.${property.code}" label="${property.name}" multiple="true" sizeList="5">
							<c:set var="opt_selected" value="${vars.config.getRawValue(property.code)}"/>
							<c:forEach items="${property.options}" var="opts">
								<ec:option label="${opts.description}" selected="${opts.value == opt_selected}" value="${opts.value}"/>
							</c:forEach>
						</ec:select>
					</c:when>
					<c:when test="${property.type == 'SELECT_LIST'}">
						<ec:label>${property.name}</ec:label><br>
						<c:set var="opt_selected" value="${vars.config.getRawValue(property.code)}"/>
						<c:forEach items="${property.options}" var="opts">
							<ec:radio inline="true" name="config.${property.code}" label="${opts.description}" value="${opts.value}" selected="${opts.value == opt_selected}"/>
						</c:forEach>
					</c:when>
					<c:when test="${property.type == 'MULTISELECT_LIST'}">
						<ec:label>${property.name}</ec:label><br>
						<c:set var="opt_selected" value="${vars.config.getRawValue(property.code)}"/>
						<c:forEach items="${property.options}" var="opts">
							<ec:checkbox inline="true" name="config.${property.code}" label="${opts.description}" value="${opts.value}" selected="${opts.value == opt_selected}"/>
						</c:forEach>
					</c:when>
				</c:choose>
				<ec:field-validator form="config_fr" field="config.${property.code}">
				
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
					<ec:field-validator-rule name="regexp"  message="Invalid format!">
						<ec:field-validator-param name="regexp" raw="true">/${property.regex}/</ec:field-validator-param>
					</ec:field-validator-rule>
					</c:if>
					
				</ec:field-validator>
			</ec:table-col>
			<ec:table-col>
				<small>${property.description}</small>
			</ec:table-col>
		</ec:table-row>
	</c:forEach>
	</ec:table-body>
</ec:table>
<ed:row style="form">
	<ed:col size="12">
		<hr>
	</ed:col>
</ed:row>	
<ed:row style="form">
	<ed:col id="update_plugin_result" size="12">
	</ed:col>
</ed:row>
<ed:row style="form">
	<ed:col id="update_plugin_result" size="12">
		<ec:button type="dark" classStyle="float-right" label="Save" actionType="submit"/>
	</ed:col>
</ed:row>
<%--
	<c:forEach items="${vars.config.metadata.properties}" var="property">
		<ed:row style="form"> 
			<ed:col size="8" classStyle="form-group has-feedback">
				<c:choose>
					<c:when test="${property.type == 'TEXT'}">
						<ec:textfield label="${property.name}" name="config.${property.code}" value="${vars.config.getRawValue(property.code)}"/>
					</c:when>
					<c:when test="${property.type == 'SELECT'}">
						<ec:select name="config.${property.code}" label="${property.name}">
							<c:set var="opt_selected" value="${vars.config.getRawValue(property.code)}"/>
							<c:forEach items="${property.options}" var="opts">
								<ec:option label="${opts.description}" selected="${opts.value == opt_selected}" value="${opts.value}"/>
							</c:forEach>
						</ec:select>
					</c:when>
					<c:when test="${property.type == 'MULTISELECT'}">
						<ec:select name="config.${property.code}" label="${property.name}" multiple="true" sizeList="5">
							<c:set var="opt_selected" value="${vars.config.getRawValue(property.code)}"/>
							<c:forEach items="${property.options}" var="opts">
								<ec:option label="${opts.description}" selected="${opts.value == opt_selected}" value="${opts.value}"/>
							</c:forEach>
						</ec:select>
					</c:when>
					<c:when test="${property.type == 'SELECT_LIST'}">
						${property.name}<br>
						<c:set var="opt_selected" value="${vars.config.getRawValue(property.code)}"/>
						<c:forEach items="${property.options}" var="opts">
							<ec:radio inline="true" name="config.${property.code}" label="${opts.description}" value="${opts.value}" selected="${opts.value == opt_selected}"/>
						</c:forEach>
					</c:when>
					<c:when test="${property.type == 'MULTISELECT_LIST'}">
						${property.name}<br>
						<c:set var="opt_selected" value="${vars.config.getRawValue(property.code)}"/>
						<c:forEach items="${property.options}" var="opts">
							<ec:checkbox inline="true" name="config.${property.code}" label="${opts.description}" value="${opts.value}" selected="${opts.value == opt_selected}"/>
						</c:forEach>
					</c:when>
				</c:choose>
				<ec:field-validator form="config_fr" field="config.${property.code}">
				
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
					<ec:field-validator-rule name="regexp"  message="Invalid format!">
						<ec:field-validator-param name="regexp" raw="true">/${property.regex}/</ec:field-validator-param>
					</ec:field-validator-rule>
					</c:if>
					
				</ec:field-validator>
			</ed:col>
			<ed:col size="4">
				<small>${property.description}</small>
			</ed:col>
		</ed:row>
	</c:forEach>
	<ed:row style="form">
		<ed:col id="update_plugin_result" size="9">
		</ed:col>
		<ed:col id="update_plugin_result" size="3">
			<ec:button type="dark" classStyle="float-right" label="Save" actionType="submit"/>
		</ed:col>
	</ed:row>
--%>		
</ec:form>

<script type="text/javascript" src="/plugins/ediacaran/front/default_template/admin/js/pages/update-status-plugin-detail.js" charset="utf-8"></script>