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
			<input type="hidden" value="${template}" name="template">
			<input type="hidden" value="${empty metadata? '' : metadata.hashCode()}" name="gid">
			<ed:row>
				<ed:col size="12" id="result_page_edit_form">
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="12">
					<h5>Conteúdo</h5>
					<ec:separator/>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="5" classStyle="form-group has-feedback">
					<ec:textfield name="path" label="Caminho" value="${metadata.path}" readonly="${!empty metadata}">
						<ec:field-validator>
							<ec:field-validator-rule name="notEmpty" message="Informe um caminho! ( ex: /caminho )"/>
							<ec:field-validator-rule name="regexp" message="Caminho inválido! ( ex: /caminho )">
								<ec:field-validator-param name="regexp" raw="true">/^(\/[a-z][a-z0-9]+(_[a-z0-9]+)*)*$/</ec:field-validator-param>
							</ec:field-validator-rule>
						</ec:field-validator>
					</ec:textfield>
				</ed:col>
				<ed:col size="4" classStyle="form-group has-feedback">
					<ec:textfield name="id" label="Identificação" value="${metadata.id}" readonly="${!empty metadata}">
						<ec:field-validator>
							<ec:field-validator-rule name="regexp" message="Identificação inválida! ( ex: pagina-teste )">
								<ec:field-validator-param name="regexp" raw="true">/^[a-z0-9][a-z0-9]+([_-][a-z0-9]+)*$/</ec:field-validator-param>
							</ec:field-validator-rule>
						</ec:field-validator>
					</ec:textfield>
				</ed:col>
				<ed:col size="3" classStyle="form-group has-feedback">
					<ec:select label="Idioma" name="locale" readonly="${!empty metadata}">
						<ec:option value=""></ec:option>
						<c:forEach items="${locales}" var="loc">
						<ec:option value="${loc.key}" selected="${metadata.locale == loc.key}">${loc.value}</ec:option>
						</c:forEach>
					</ec:select>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="8" classStyle="form-group has-feedback">
					<ec:textfield name="title" label="Título" value="${page.title}">
						<ec:field-validator>
							<ec:field-validator-rule name="notEmpty" message="Informe um título!"/>
						</ec:field-validator>
					</ec:textfield>
				</ed:col>
				<ed:col size="4">
					<ec:select label="Template" name="Modelo" enabled="false">
						<c:forEach items="${templates}" var="template">
						<ec:option value="${template.key}" selected="${page.template == template.key}">${template.value.name}</ec:option>
						</c:forEach>
					</ec:select>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="12">
					<ec:textarea name="content" classStyle="tinymce">
						${page.write(pageContext.out)}
					</ec:textarea>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="12">
					<h5>Minuatura</h5>
					<ec:separator/>
				</ed:col>
			</ed:row>
       		<ed:row>
       			<ed:col size="12">
					<ed:row style="form">
						<ed:col size="8" align="center" classStyle="form-group has-feedback">
							<ec:imagefield name="thumbnail"	width="560" height="292" 
							button="Selecione uma imagem" border="squad" src="${page.publicThumbnailPath}" />
						</ed:col>
					</ed:row>
	        		<ed:row>
	        			<ed:col classStyle="form-group has-feedback">
							<ec:textfield name="titleThumbnail" label="Título" align="center" value="${page.thumbnailTitle}">
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
							<ec:textfield name="keywords" label="Palavra chave" align="center" value="${page.keywords}">
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
							<ec:textarea rows="6" name="shortDescription" label="Descrição" align="center">${page.thumbnailDescription}</ec:textarea>
							<ec:field-validator form="page_edit_form" field="shortDescription">
								<ec:field-validator-rule name="stringLength" message="Tem que possuir no máximo 255 caracteres!">
									<ec:field-validator-param name="max" raw="true">255</ec:field-validator-param>
								</ec:field-validator-rule>
							</ec:field-validator>								

	        			</ed:col>
	        		</ed:row>
       			</ed:col>
       		</ed:row>
			<ed:row>
				<ed:col size="12">
					<ec:button 
						actionType="submit" 
						action="#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/editor/default/save" 
						label="Salvar" 
						align="right"/>
				</ed:col>
			</ed:row>
			
		</ec:form>
	</ec:box-body>
</ec:box>