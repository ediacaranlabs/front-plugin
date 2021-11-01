<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<ec:form method="POST" id="status_config_fr" update="#enable_plugin_result" 
action="/plugins/ediacaran/front/admin/plugins/${vars.config.metadata.code}/status" acceptCharset="UTF-8">
	<ed:row style="form">
		<ed:col size="8">
			<h3>${vars.config.metadata.name}</h3>
		</ed:col>
		<ed:col size="4">
			<div class="float-right">
				<ec:radio name="status" inline="true" label="Enabled" value="true"
					enabled="${vars.status == 'RUNNING' || vars.status == 'STOPPED'}" 
					selected="${vars.status == 'RUNNING'}">
					<ec:event type="click">
						var $form = $.AppContext.utils.getById('status_config_fr');
						var $statusField = $form.getField('status');
						$form.submit();
						$statusField.setProperty('disabled', true);
					</ec:event>
				</ec:radio>
				<ec:radio name="status" inline="true" label="Disabled" value="false"
					enabled="${vars.status == 'RUNNING' || vars.status == 'STOPPED'}"   
					selected="${vars.status == 'STOPPED'}">
					<ec:event type="click">
						var $form = $.AppContext.utils.getById('status_config_fr');
						var $statusField = $form.getField('status');
						$form.submit();
						$statusField.setProperty('disabled', true);
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
	
	<ec:tabs>
		<ec:tabs-item active="true" icon="cog" title="Configuration">
			<ec:form method="POST" id="config_fr" update="#update_plugin_result" action="/plugins/ediacaran/front/admin/plugins/${vars.config.metadata.code}">
				<ec:table style="striped">
					<ec:table-body>
					<c:forEach items="${vars.config.metadata.properties}" var="property">
						<ec:table-row>
							 <ec:table-col classStyle="form-group has-feedback">
								<c:choose>
									<c:when test="${property.type == 'TEXT'}">
										<c:if test="${property.protectedValue}">
											<ec:passwordfield label="${property.name}" name="config.${property.code}" value="************"/>
										</c:if>
										<c:if test="${!property.protectedValue}">
											<ec:textfield label="${property.name}" name="config.${property.code}" value="${vars.config.getRawValue(property.code)}"/>
										</c:if>
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
											<c:forEach items="${property.options}" var="opts">
												<ec:option label="${opts.description}" 
													selected="${vars.config.containsRawValue(property.code, opts.value)}" value="${opts.value}"/>
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
										<c:forEach items="${property.options}" var="opts">
											<ec:checkbox inline="true" name="config.${property.code}" label="${opts.description}" value="${opts.value}" 
												selected="${vars.config.containsRawValue(property.code, opts.value)}"/>
										</c:forEach>
									</c:when>
								</c:choose>
								<ec:field-validator form="config_fr" field="config.${property.code}">
								
									<c:if test="${!property.allowEmpty}">
									<ec:field-validator-rule name="notEmpty" message="The ${property.name} is required"/>
									</c:if>
				
									<c:if test="${(property.type == 'MULTISELECT' || property.type == 'MULTISELECT_LIST') && (property.min > 0 || property.max > 0)}">
									<ec:field-validator-rule name="choice"  message="Please choose ${property.min} - ${property.max}!">
										<c:if test="${property.min > 0}">
										<ec:field-validator-param name="min">${property.min}</ec:field-validator-param>
										</c:if>
										<c:if test="${property.max > 0}">
										<ec:field-validator-param name="max">${property.max}</ec:field-validator-param>
										</c:if>
									</ec:field-validator-rule>
									</c:if>
														
									<c:if test="${!(property.type == 'MULTISELECT' || property.type == 'MULTISELECT_LIST') && (property.min > 0 || property.max > 0)}">
									<ec:field-validator-rule name="stringLength"  message="The ${property.name} is short or large!">
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
			</ec:form>
		</ec:tabs-item>
		<ec:tabs-item icon="life-saver" title="Security">
			<ec:form method="POST" id="config_fr" update="#update_plugin_result" action="/plugins/ediacaran/front/admin/plugins/${vars.config.metadata.code}">
				<ec:table style="striped">
					<ec:table-body>
						<c:set var="count" value="0"/>
						<ec:table-header>
							<ec:table-col>Type</ec:table-col>
							<ec:table-col>Value</ec:table-col>
							<ec:table-col>Actions</ec:table-col>
							<ec:table-col>Status</ec:table-col>
						</ec:table-header>
						<c:forEach items="${vars.security}" var="per">
							<ec:table-row>
								<ec:table-col classStyle="form-group has-feedback">
									<ec:textfield name="security[${count}].permission" readonly="true" value="${per.request.permission}"></ec:textfield>
								</ec:table-col>
								<ec:table-col classStyle="form-group has-feedback">
									<ec:textfield name="security[${count}].name" readonly="true" value="${per.request.name}"></ec:textfield>
								</ec:table-col>
								<ec:table-col classStyle="form-group has-feedback">
									<ec:textfield name="security[${count}].actions" readonly="true" value="${per.request.actions}"></ec:textfield>
								</ec:table-col>
								<ec:table-col classStyle="form-group has-feedback">
									<ec:select name="security[${count}].enabled">
										<ec:option label="Enabled" value="true" selected="${per.enabled}" />
										<ec:option label="Disabled" value="false" selected="${!per.enabled}" />
										<ec:option label="Deleted" />
										<ec:field-validator>
											<ec:field-validator-rule name="choice"  message="Please choose!">
												<ec:field-validator-param name="min">1</ec:field-validator-param>
											</ec:field-validator-rule>
										</ec:field-validator>
									</ec:select>
								</ec:table-col>
							</ec:table-row>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
						<c:forEach begin="1" end="3">
							<ec:table-row>
								<ec:table-col>
									<ec:select name="security[${count}].permission">
										<ec:option label="" value="" selected="true"/>
										<ec:option label="File" value="FILE"/>
										<ec:option label="Auth" value="AUTH"/>
										<ec:option label="Property" value="PROPERTY"/>
										<ec:option label="Runtime" value="RUNTIME"/>
										<ec:option label="All" value="ALL"/>
									</ec:select>
								</ec:table-col>
								<ec:table-col>
									<ec:textfield name="security[${count}].name" value=""></ec:textfield>
								</ec:table-col>
								<ec:table-col>
									<ec:textfield name="security[${count}].actions" value=""></ec:textfield>
								</ec:table-col>
								<ec:table-col>
									<ec:select name="security[${count}].enabled">
										<ec:option label="" value="" selected="true"/>
										<ec:option label="Enabled" value="true"/>
										<ec:option label="Disabled" value="false"/>
									</ec:select>
								</ec:table-col>
							</ec:table-row>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
					</ec:table-body>
				</ec:table>		
			</ec:form>
		</ec:tabs-item>
		<ec:tabs-item icon="warning" title="Uninstall">
			<ec:form method="post" update="#update_plugin_result" action="/plugins/ediacaran/front/admin/plugins/${vars.config.metadata.code}/uninstall">
				<ed:row>
					<ed:col size="12">
						Para desinstalar o plugin aperte o botão desinstalar. Todos os dados associados ao plugin serão removidos permanentemente.
					</ed:col>
				</ed:row>
				<ed:row>
					<ed:col size="12">
						<ec:button label="Uninstall" type="danger" align="right" actionType="submit"/>
					</ed:col>
				</ed:row>
			</ec:form>
		</ec:tabs-item>
	</ec:tabs>
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

<script type="text/javascript" src="/plugins/ediacaran/front/default_template/admin/js/pages/update-status-plugin-detail.js" charset="utf-8"></script>