<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>
<ec:setBundle var="messages" locale="${locale}"/>

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2><fmt:message key="title" bundle="${messages}"/></h2>
			</div>
		</ed:col>
		<ed:col size="8">
			<ec:breadcrumb title="Plugins">
				<ec:breadcrumb-path icon="home" text="" lnk="#" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<ed:row>
	<ed:col size="3">
		<ec:box>
			<ec:box-header><h3><fmt:message key="title_menu" bundle="${messages}"/></h3></ec:box-header>
			<ec:box-body>
				<ec:accordion>
					<c:forEach items="${Controller.groups}" var="group">
						<ec:accordion-item title="${group}">
							<ec:list>
								<c:forEach items="${Controller.getPlugins(group)}" var="plugin">
									<ec:list-item><a href="#!/plugins/ediacaran/front/admin/plugins/${plugin.metadata.code}" dest-content="plugin_body">${plugin.metadata.name}</a></ec:list-item>
								</c:forEach>
							</ec:list>
						</ec:accordion-item>	
					</c:forEach>
				</ec:accordion>
			</ec:box-body>
		</ec:box>
	</ed:col>
	<ed:col size="9">
		<ec:box>
			<ec:box-body id="plugin_body">
			
				<ec:form method="POST" update="result-install-file" 
					action="/plugins/ediacaran/front/admin/plugins/install-file" 
					enctype="multipart/form-data">
					<ed:row>
						<ed:col size="12">
							<h5><fmt:message key="package_install.title" bundle="${messages}"/></h5>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12">
							<ec:separator/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12">
							<fmt:message key="package_install.description" bundle="${messages}"/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="10" classStyle="file form-group has-feedback">
							<ec:filefield accept=".zip" classStyle="hide-file" id="file" name="file" required="true">
								<ec:field-validator>
									<fmt:message key="package_install.file.validator.notEmpty" bundle="${messages}" var="msg_tmp"/>
									<ec:field-validator-rule name="notEmpty" message="${msg_tmp}"/>
									<fmt:message key="package_install.file.validator.format" bundle="${messages}" var="msg_tmp"/>
									<ec:field-validator-rule name="file" message="${msg_tmp}">
										<ec:field-validator-param name="extension">zip</ec:field-validator-param>
										<ec:field-validator-param name="maxSize" raw="true">2048*1024*1024</ec:field-validator-param>
									</ec:field-validator-rule>
								</ec:field-validator>
							</ec:filefield>
						</ed:col>
						<ed:col size="2">
							<fmt:message key="package_install.button" bundle="${messages}" var="msg_tmp"/>
							<ec:button label="${msg_tmp}" actionType="submit" align="right" />
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12" id="result-install-file">
						</ed:col>
					</ed:row>
				</ec:form>
					
				<ec:form method="POST" update="result-install-url"  action="/plugins/ediacaran/front/admin/plugins/install-url">
					<ed:row>
						<ed:col size="12">
							<h5><fmt:message key="url_install.title" bundle="${messages}"/></h5>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12">
							<ec:separator/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12">
							<fmt:message key="url_install.description" bundle="${messages}"/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="10" classStyle=" form-group has-feedback">
							<fmt:message key="url_install.url.placeholder" bundle="${messages}" var="msg_tmp"/>
							<ec:urlfield name="url" classStyle="url" placeholder="${msg_tmp}">
								<ec:field-validator>
									<fmt:message key="url_install.url.validator.notEmpty" bundle="${messages}" var="msg_tmp"/>
									<ec:field-validator-rule name="notEmpty" message="${msg_tmp}"/>
								</ec:field-validator>
							</ec:urlfield>
						</ed:col>
						<ed:col size="2">
							<fmt:message key="url_install.button" bundle="${messages}" var="msg_tmp"/>
							<ec:button label="${msg_tmp}" actionType="submit" align="right" />
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12" id="result-install-url">
						</ed:col>
					</ed:row>
				</ec:form>
				
				
			</ec:box-body>
		</ec:box>
	</ed:col>
</ed:row>