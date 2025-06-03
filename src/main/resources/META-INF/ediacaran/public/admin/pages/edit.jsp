<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2>Página</h2>
			</div>
		</ed:col>
		<ed:col size="8">
			<ec:breadcrumb title="Editar">
				<ec:breadcrumb-path icon="home" text="" lnk="#" />
				<ec:breadcrumb-path text="Pages" lnk="#!/plugins/ediacaran/front${plugins.ediacaran.front.admin_context}/pages" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<ec:box>
	<ec:box-body>	
	
		<ec:form id="page_edit_form" method="POST" enctype="multipart/form-data" update="result_page_edit_form">
			<input type="hidden" value="${empty vars.id? '' : vars.id.toInt()}" name="gid">
			<ed:row>
				<ed:col size="12" id="result_page_edit_form">
				</ed:col>
			</ed:row>
			<ec:tabs>
				<ec:tabs-item title="Conteúdo" active="true">
				
					<ed:row>
						<ed:col size="5" classStyle="form-group has-feedback">
							<ec:textfield name="path" label="Caminho" value="${vars.id.path}" readonly="${!empty vars.id}">
								<ec:field-validator>
									<ec:field-validator-rule name="notEmpty" message="Informe um caminho! ( ex: /caminho )"/>
									<ec:field-validator-rule name="regexp" message="Caminho inválido! ( ex: /caminho )">
										<ec:field-validator-param name="regexp" raw="true">/^(\/[a-z][a-z0-9]+(_[a-z0-9]+)*)*$/</ec:field-validator-param>
									</ec:field-validator-rule>
								</ec:field-validator>
							</ec:textfield>
						</ed:col>
						<ed:col size="4" classStyle="form-group has-feedback">
							<ec:textfield name="id" label="Identificação" value="${vars.id.id}" readonly="${!empty vars.id}">
								<ec:field-validator>
									<ec:field-validator-rule name="regexp" message="Identificação inválida! ( ex: pagina-teste )">
										<ec:field-validator-param name="regexp" raw="true">/^[a-z0-9][a-z0-9]+([_-][a-z0-9]+)*$/</ec:field-validator-param>
									</ec:field-validator-rule>
								</ec:field-validator>
							</ec:textfield>
						</ed:col>
						<ed:col size="3" classStyle="form-group has-feedback">
							<ec:select label="Idioma" name="locale" readonly="${!empty vars.id}">
								<ec:option value=""></ec:option>
								<c:forEach items="${locales}" var="loc">
								<ec:option value="${loc.key}" selected="${vars.id.locale == loc.key}">${loc.value}</ec:option>
								</c:forEach>
							</ec:select>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="8" classStyle="form-group has-feedback">
							<ec:textfield name="title" label="Título" value="${vars.page.title}">
								<ec:field-validator>
									<ec:field-validator-rule name="notEmpty" message="Informe um título!"/>
								</ec:field-validator>
							</ec:textfield>
						</ed:col>
						<ed:col size="4">
							<ec:select label="Template" name="template" readonly="true">
								<c:forEach items="${vars.templates}" var="template">
								<ec:option value="${template.id}" selected="${vars.page.template == template.id}">${template.id} - ${template.name}</ec:option>
								</c:forEach>
							</ec:select>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12" classStyle="form-group has-feedback">
							<ec:tinymce name="content">
								${vars.page.write(pageContext.out)}
							</ec:tinymce>
						</ed:col>
					</ed:row>
				
				
				</ec:tabs-item>
				<ec:tabs-item title="Miniatura">
				
					<ed:row style="form" classStyle="form-group has-feedback">
						<ed:col size="8" align="center" classStyle="form-group has-feedback">
							<ec:imagefield name="thumbnail"	width="560" height="292" 
							button="Selecione uma imagem" border="squad" src="${vars.page.publicThumbnailPath}" >
							</ec:imagefield>
						</ed:col>
					</ed:row>
	        		<ed:row>
	        			<ed:col classStyle="form-group has-feedback">
							<ec:textfield name="titleThumbnail" label="Título" align="center" value="${vars.page.thumbnailTitle}">
								<ec:field-validator>
									<ec:field-validator-rule name="stringLength" message="Tem que possuir no máximo 255 caracteres!">
										<ec:field-validator-param name="max" raw="true">255</ec:field-validator-param>
									</ec:field-validator-rule>
								</ec:field-validator>								
							</ec:textfield>
	        			</ed:col>
	        		</ed:row>
	        		<ed:row style="form">
	        			<ed:col classStyle="form-group has-feedback">
							<ec:textfield name="keywords" label="Palavra chave" align="center" value="${vars.page.keywords}">
								<ec:field-validator>
									<ec:field-validator-rule name="regexp" message="Informe novamente a palavra chave!">
										<ec:field-validator-param name="regexp" raw="true">/^([^,.]+(\,[^,.]+)*)$/</ec:field-validator-param>
									</ec:field-validator-rule>
								</ec:field-validator>								
							</ec:textfield>
	        			</ed:col>
	        		</ed:row>
	        		<ed:row style="form">
	        			<ed:col classStyle="form-group has-feedback">
							<ec:textarea rows="6" name="shortDescription" label="Descrição" align="center">${vars.page.thumbnailDescription}</ec:textarea>
							<ec:field-validator form="page_edit_form" field="shortDescription">
								<ec:field-validator-rule name="stringLength" message="Tem que possuir no máximo 255 caracteres!">
									<ec:field-validator-param name="max" raw="true">255</ec:field-validator-param>
								</ec:field-validator-rule>
							</ec:field-validator>								

	        			</ed:col>
	        		</ed:row>
	        		
	        						
				</ec:tabs-item>
			</ec:tabs>
			<ed:row>
				<ed:col size="12">
					<ec:button 
						actionType="submit" 
						action="#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/default/save" 
						label="Salvar" 
						align="right"/>
				</ed:col>
			</ed:row>
			
		</ec:form>
	</ec:box-body>
</ec:box>