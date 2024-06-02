<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                    prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2>Menu</h2>
			</div>
		</ed:col>
		<ed:col size="8">
			<ec:breadcrumb title="Menu">
				<ec:breadcrumb-path icon="home" text="" lnk="#" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<ed:row>
	<ed:col size="12">
		<ec:box>
			<ec:box-body>
				<ec:data-table id="menubar_form_search" action="${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/list">
					<!-- menubar-form -->
					<ed:row>
						<ed:col size="8">
							<ec:textfield name="path" placeholder="Caminho"/>
						</ed:col>
						<ed:col size="2" align="right">
							<ec:select name="locale">
								<ec:option value="" selected="true">Selecione o idioma</ec:option>
								<c:forEach items="${locales}" var="loc">
								<ec:option value="${loc.key}">${loc.value}</ec:option>
								</c:forEach>
							</ec:select>
						</ed:col>
						<ed:col size="2">
							<ec:button
			    				form="menubar_form_search" 
			    				classStyle="last-item" 
			    				align="right" 
								actionType="submit" 
			    				size="sm"
								label="Pesqusiar"/>
							<ec:button 
			    				classStyle="last-item" 
			    				align="right" 
								label="Novo"
			    				size="sm"
								actionType="button">
								<ec:event type="click">
			    					$.AppContext.utils.updateContentByID("#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/new")
								</ec:event>
							</ec:button>
						</ed:col>
					</ed:row>
					<!-- /menubar-form -->
					<ec:data-result var="response">
						<ec:forEach items="!{response.data}" var="item">
							<ec:separator/>
							<ed:row>
								<ed:col size="8">
									<ec:textfield value="!{item.name}" enabled="false"/>
								</ed:col>
								<ed:col size="2" align="right">
									<ec:textfield value="!{item.locale.name}" enabled="false"/>
								</ed:col>
								<ed:col size="2">
									<ec:button id="menubar_form_search_edit_button_!{item.index}"
										actionType="button" 
					    				classStyle="last-item" 
					    				align="right" 
					    				size="sm"
										label="Editar">
										<ec:event type="click">
											var $fr = $.AppContext.utils.getById('form_menu_item');
											var $path = $fr.getField('path');
											var $id = $fr.getField('id');
											var $locale = $fr.getField('locale');
											
											$path.setValue('!{item.path}');
											$id.setValue('!{item.id}');
											$locale.setValue('!{item.locale.id}');
											$fr.submit(false, '#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/edit');
										</ec:event>
									</ec:button>
									<ec:button
										id="menubar_form_search_edit_delete_!{item.index}"
										style="danger" 
										actionType="submit" 
					    				classStyle="last-item" 
					    				align="right" 
					    				size="sm"
										label="Apagar">
										<ec:event type="click">
											var $fr = $.AppContext.utils.getById('form_menu_item');
											var $path = $fr.getField('path');
											var $id = $fr.getField('id');
											var $locale = $fr.getField('locale');
											
											$path.setValue('!{item.path}');
											$id.setValue('!{item.id}');
											$locale.setValue('!{item.locale.id}');
											$fr.submit(false, '#m${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/confirm-delete');
										</ec:event>
									</ec:button>
								</ed:col>
							</ed:row>
						</ec:forEach>
					</ec:data-result>
				</ec:data-table>
			</ec:box-body>
		</ec:box>
		<ec:form id="form_menu_item" method="POST">
			<input type="hidden" name="path" value="">
			<input type="hidden" name="id" value="">
			<input type="hidden" name="locale" value="">
		</ec:form>
	</ed:col>
</ed:row>