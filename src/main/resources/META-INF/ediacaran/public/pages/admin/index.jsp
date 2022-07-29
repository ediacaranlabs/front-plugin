<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2>Páginas</h2>
			</div>
		</ed:col>
		<ed:col size="8">
			<ec:breadcrumb title="Páginas">
				<ec:breadcrumb-path icon="home" text="" lnk="#" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<ed:row>
	<ed:col size="12">
		<ec:box>
			<ec:box-body>
				<ec:form method="POST" update="#pages_body">
					<ed:row>
						<ed:col>
							<ec:textfield label="Nome" name="name" value="${name}"/>
						</ed:col>
						<ed:col size="3">
							<ec:select label="Idioma" name="locale">
								<ec:option value="" selected="true"></ec:option>
								<c:forEach items="${locales}" var="loc">
								<ec:option value="${loc.key}">${loc.value}</ec:option>
								</c:forEach>
							</ec:select>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="9">
						</ed:col>
						<ed:col size="2">
							<ec:button 
								actionType="submit" 
								action="${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/list" 
								label="Pesqusiar" 
								align="right"/>
						</ed:col>
						<ed:col size="1">
							<ec:button 
								actionType="button"
								label="Novo" 
								align="right">
								<ec:event type="click">
									$.AppContext.utils.loadResourceContent(
										null, 
										"#m${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/new");
								</ec:event>
							</ec:button>
						</ed:col>
					</ed:row>
				</ec:form>
			</ec:box-body>
		</ec:box>
	</ed:col>
</ed:row>
<ed:row>
	<ed:col size="12">
		<ec:box>
			<ec:box-body id="pages_body">
				<jsp:include page="/pages/admin/table.jsp"/>
			</ec:box-body>
		</ec:box>
	</ed:col>
</ed:row>