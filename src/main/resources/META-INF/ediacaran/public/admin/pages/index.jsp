<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                    prefix="fmt"%> 
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
				<ec:data-table id="page_form_search" action="${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/list">
					<!-- menubar-form -->
					<ed:row>
						<ed:col size="8">
							<ec:textfield name="path" placeholder="Caminho"/>
						</ed:col>
						<ed:col size="4" align="right">
							<ec:select name="locale">
								<ec:option value="" selected="true">Selecione o idioma</ec:option>
								<c:forEach items="${locales}" var="loc">
								<ec:option value="${loc.key}">${loc.value}</ec:option>
								</c:forEach>
							</ec:select>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12">
							<ec:button
			    				classStyle="last-item" 
			    				align="right" 
								actionType="submit" 
								style="primary"
								label="Pesqusiar"/>
							<ec:dropdown label="Novo" style="primary" align="right" bundle="${messages}">
								<c:forEach items="${templates}" var="template">
									<ec:dropdown-item src="#!${template.editTemplate}">${template.name}</ec:dropdown-item>
								</c:forEach>
							</ec:dropdown>
						</ed:col>
					</ed:row>
					<!-- /menubar-form -->
					<ec:data-result var="response">
						<ec:forEach items="!{response.data}" var="item">
							<ec:separator/>
							<ed:row>
								<ed:col size="6">
									Path
								</ed:col>
								<ed:col size="2" align="right">
									<ec:center>Idioma</ec:center>
								</ed:col>
								<ed:col size="4">
									<ec:center>Actions</ec:center>
								</ed:col>
							</ed:row>
							<ed:row>
								<ed:col size="6">
									!{item.name}
								</ed:col>
								<ed:col size="2" align="right">
									<ec:center>!{item.locale.name}</ec:center>
								</ed:col>
								<ed:col size="4">
									<ec:button
										actionType="button" 
					    				classStyle="last-item" 
					    				align="right" 
										label="Editar">
										<ec:event type="click">
											var link = '#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/edit?';
											link += 'gid=' + encodeURI('!{item.gid}');
											link += '&path=' + encodeURI('!{item.path}');
											link += '&id=' + encodeURI('!{item.id}');
											link += '&locale=' + encodeURI('!{item.locale.id}');
											$.AppContext.utils.updateContent(link);
										</ec:event>
									</ec:button>
									<ec:button
										style="danger" 
										actionType="submit" 
					    				classStyle="last-item" 
					    				align="right" 
										label="Apagar">
										<ec:event type="click">
											var link = '#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/confirm-delete?';
											link += 'gid=' + encodeURI('!{item.gid}');
											link += '&path=' + encodeURI('!{item.path}');
											link += '&id=' + encodeURI('!{item.id}');
											link += '&locale=' + encodeURI('!{item.locale.id}');
											$.AppContext.utils.updateContent(link);
										</ec:event>
									</ec:button>
								</ed:col>
							</ed:row>
						</ec:forEach>
					</ec:data-result>
				</ec:data-table>
			</ec:box-body>
		</ec:box>
	</ed:col>
</ed:row>