<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<ec:setTemplatePackage name="admin"/>
<ec:setBundle var="messages" locale="${locale}"/>

<ec:form method="POST" id="status_config_fr" update="enable_plugin_result" 
action="/plugins/ediacaran/front/admin/plugins/${vars.config.metadata.code}/status" acceptCharset="UTF-8">
	<ed:row style="form">
		<ed:col size="8">
			<h3>${vars.config.metadata.name}</h3>
		</ed:col>
		<ed:col size="4">
			<div class="float-right">
				<fmt:message key="menu.button.enabled" bundle="${messages}" var="msg_tmp"/>
				<ec:radio name="status" inline="true" label="${msg_tmp}" value="true"
					enabled="${vars.status == 'RUNNING' || vars.status == 'STOPPED'}" 
					selected="${vars.status == 'RUNNING'}">
					<ec:event type="click">
						var $form = $.AppContext.utils.getById('status_config_fr');
						var $statusField = $form.getField('status');
						$form.submit();
						$statusField.setProperty('disabled', true);
					</ec:event>
				</ec:radio>
				<fmt:message key="menu.button.disabled" bundle="${messages}" var="msg_tmp"/>
				<ec:radio name="status" inline="true" label="${msg_tmp}" value="false"
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
		<fmt:message key="tabs.configuration.title" bundle="${messages}" var="msg_tmp"/>
		<ec:tabs-item active="true" icon="cog" title="${msg_tmp}">
			<ec:form method="POST" id="config_fr" update="update_plugin_result" action="/plugins/ediacaran/front/admin/plugins/${vars.config.metadata.code}">
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
										<ec:select name="config.${property.code}" label="${property.name}" multiple="true" rows="5">
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
				<ed:row style="form">
					<ed:col id="update_plugin_result" size="12">
					</ed:col>
				</ed:row>
				<ed:row style="form">
					<ed:col id="update_plugin_result" size="12">
						<fmt:message key="tabs.configuration.button_save" bundle="${messages}" var="msg_tmp"/>
						<ec:button style="dark" align="right" label="${msg_tmp}" actionType="submit"/>
					</ed:col>
				</ed:row>
			</ec:form>
		</ec:tabs-item>
		<fmt:message key="tabs.security.title" bundle="${messages}" var="msg_tmp"/>
		<ec:tabs-item icon="life-saver" title="${msg_tmp}">
			<ec:form method="POST" id="config_fr" update="update_plugin_result" action="/plugins/ediacaran/front/admin/plugins/${vars.config.metadata.code}">
				<ec:table style="striped">
					<ec:table-body>
						<c:set var="count" value="0"/>
						<ec:table-header>
							<ec:table-col><fmt:message key="tabs.security.security_tab.type_col" bundle="${messages}"/></ec:table-col>
							<ec:table-col><fmt:message key="tabs.security.security_tab.value_col" bundle="${messages}"/></ec:table-col>
							<ec:table-col><fmt:message key="tabs.security.security_tab.actions_col" bundle="${messages}"/></ec:table-col>
							<ec:table-col><fmt:message key="tabs.security.security_tab.status_col" bundle="${messages}"/></ec:table-col>
						</ec:table-header>
						<c:forEach items="${vars.security}" var="per">
							<ec:table-row>
								<ec:table-col classStyle="form-group has-feedback">
									<input type="hidden" name="security[${count}].permission" value="${per.request.permission}">
									<%--<ec:textfield name="security[${count}].permission" readonly="true" value="${per.request.permission}"></ec:textfield>--%>
									
									<ec:select name="security[${count}].permission" enabled="false">
										<fmt:message key="tabs.security.security_tab.type.file" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="FILE" selected="${per.request.permission == 'FILE'}"/>
										
										<fmt:message key="tabs.security.security_tab.type.auth" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="AUTH" selected="${per.request.permission == 'AUTH'}"/>
										
										<fmt:message key="tabs.security.security_tab.type.property" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="PROPERTY" selected="${per.request.permission == 'PROPERTY'}"/>
										
										<fmt:message key="tabs.security.security_tab.type.runtime" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="RUNTIME" selected="${per.request.permission == 'RUNTIME'}"/>

										<fmt:message key="tabs.security.security_tab.type.reflection" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="REFLECTION" selected="${per.request.permission == 'REFLECTION'}"/>
										
										<fmt:message key="tabs.security.security_tab.type.all" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="ALL" selected="${per.request.permission == 'ALL'}"/>
									</ec:select>
									
								</ec:table-col>
								<ec:table-col classStyle="form-group has-feedback">
									<ec:textfield name="security[${count}].name" readonly="true" value="${per.request.name}"></ec:textfield>
								</ec:table-col>
								<ec:table-col classStyle="form-group has-feedback">
									<ec:textfield name="security[${count}].actions" readonly="true" value="${per.request.actions}"></ec:textfield>
								</ec:table-col>
								<ec:table-col classStyle="form-group has-feedback">
									<ec:select name="security[${count}].enabled">
										<fmt:message key="tabs.security.security_tab.status.enabled" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="true" selected="${per.enabled}" />
										<fmt:message key="tabs.security.security_tab.status.disabled" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="false" selected="${!per.enabled}" />
										<fmt:message key="tabs.security.security_tab.status.deleted" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" />
										<ec:field-validator>
											<fmt:message key="tabs.security.security_tab.status.validator.notEmpty" bundle="${messages}" var="msg_tmp"/>
											<ec:field-validator-rule name="choice"  message="${msg_tmp}">
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
										
										<fmt:message key="tabs.security.security_tab.type.file" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="FILE"/>
										
										<fmt:message key="tabs.security.security_tab.type.auth" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="AUTH"/>
										
										<fmt:message key="tabs.security.security_tab.type.property" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="PROPERTY"/>
										
										<fmt:message key="tabs.security.security_tab.type.runtime" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="RUNTIME"/>
										
										<fmt:message key="tabs.security.security_tab.type.reflection" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="REFLECTION"/>
										
										<fmt:message key="tabs.security.security_tab.type.all" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="ALL"/>
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
										
										<fmt:message key="tabs.security.security_tab.status.enabled" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="true"/>
										
										<fmt:message key="tabs.security.security_tab.status.disabled" bundle="${messages}" var="msg_tmp"/>
										<ec:option label="${msg_tmp}" value="false"/>
									</ec:select>
								</ec:table-col>
							</ec:table-row>
							<c:set var="count" value="${count + 1}"/>
						</c:forEach>
					</ec:table-body>
				</ec:table>		
				<ed:row style="form">
					<ed:col id="update_plugin_result" size="12">
					</ed:col>
				</ed:row>
				<ed:row style="form">
					<ed:col id="update_plugin_result" size="12">
						<fmt:message key="tabs.security.button_save" bundle="${messages}" var="msg_tmp"/>
						<ec:button style="dark" align="right" label="${msg_tmp}" actionType="submit"/>
					</ed:col>
				</ed:row>
			</ec:form>
		</ec:tabs-item>
		<fmt:message key="tabs.uninstall.title" bundle="${messages}" var="msg_tmp"/>
		<ec:tabs-item icon="warning" title="${msg_tmp}">
			<ec:form method="post" update="update_plugin_result" action="/plugins/ediacaran/front/admin/plugins/${vars.config.metadata.code}/uninstall">
				<ed:row>
					<ed:col size="12">
						<fmt:message key="tabs.uninstall.description" bundle="${messages}">
							<fmt:param><ec:badge style="danger">${vars.config.metadata.code}</ec:badge></fmt:param>
						</fmt:message>
					</ed:col>
				</ed:row>
				<ed:row>
					<ed:col size="6" classStyle="form-group has-feedback">
						<ec:textfield name="uninstall_code">
							<ec:field-validator>
								<fmt:message key="tabs.uninstall.uninstall_code.validator.notEmpty" bundle="${messages}" var="msg_tmp"/>
								<ec:field-validator-rule name="notEmpty" message="${msg_tmp}"/>
								<fmt:message key="tabs.uninstall.uninstall_code.validator.regexp" bundle="${messages}" var="msg_tmp"/>
								<ec:field-validator-rule name="regexp"  message="${msg_tmp}">
										<ec:field-validator-param name="regexp" raw="true">/^${vars.config.metadata.code}$/i</ec:field-validator-param>
								</ec:field-validator-rule>
							</ec:field-validator>
						</ec:textfield>
					</ed:col>
					<ed:col size="6">
						<fmt:message key="tabs.uninstall.button" bundle="${messages}" var="msg_tmp"/>
						<ec:button label="${msg_tmp}" style="danger" align="right" actionType="submit"/>
					</ed:col>
				</ed:row>
				<ed:row>
					<ed:col size="12">
						<b><fmt:message key="tabs.uninstall.msg_warning" bundle="${messages}"/></b>
					</ed:col>
				</ed:row>				
			</ec:form>
		</ec:tabs-item>
		<ec:tabs-item title="Error Message">
			<ed:row>
				<ed:col size="12">
					<ec:textarea rows="30" enabled="false">${vars.error}</ec:textarea>
				</ed:col>
			</ed:row>
		</ec:tabs-item>
	</ec:tabs>

<script type="text/javascript" src="/plugins/ediacaran/front/default_template/admin/js/pages/update-status-plugin-detail.js" charset="utf-8"></script>