<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2>Editar página</h2>
			</div>
		</ed:col>
		<ed:col size="8">
			<ec:breadcrumb title="Editar">
				<ec:breadcrumb-path icon="home" text="" lnk="#" />
				<ec:breadcrumb-path text="páginas" lnk="#!/plugins/ediacaran/front${plugins.ediacaran.front.admin_context}/pages" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<ec:box>
	<ec:box-body>	
	
		<ec:form id="page_edit_form" method="POST" enctype="multipart/form-data" update="result_page_edit_form">
			<input type="hidden" value="${template}" name="template">
			<c:if test="${!empty metadata}">
				<input type="hidden" value="${metadata.hashCode()}" name="gid">
				<input type="hidden" value="${metadata.path}" name="path">
				<input type="hidden" value="${metadata.id}" name="name">
				<input type="hidden" value="${metadata.locale}" name="locale">
			</c:if>
			<ed:row>
				<ed:col size="12" id="result_page_edit_form">
				</ed:col>
			</ed:row>
	        <ec:tabs>
        	<ec:tabs-item active="true" title="Miniatura">
        		<ed:row>
        			<ed:col size="12">
						<ed:row style="form">
							<ed:col size="8" align="center" classStyle="form-group has-feedback">
								<ec:imagefield name="thumbnail"	width="560" height="292" 
								button="Select image" border="squad" src="${page.publicThumbnailPath}" />
							</ed:col>
						</ed:row>
		        		<ed:row>
		        			<ed:col classStyle="form-group has-feedback">
								<ec:textfield name="titleThumbnail" placeholder="Title" align="center" value="${page.thumbnailTitle}"/>
		        			</ed:col>
		        		</ed:row>
		        		<ed:row style="form">
		        			<ed:col classStyle="form-group has-feedback">
								<ec:textfield name="keywords" placeholder="keywords" align="center" value="${page.keywords}">
									<ec:field-validator>
										<ec:field-validator-rule name="regexp" message="Invalid keywords!">
											<ec:field-validator-param name="regexp" raw="true">/^([^,.]+(\,[^,.]+)*)$/</ec:field-validator-param>
										</ec:field-validator-rule>
									</ec:field-validator>								
								</ec:textfield>
		        			</ed:col>
		        		</ed:row>
		        		<ed:row style="form">
		        			<ed:col classStyle="form-group has-feedback">
								<ec:textarea rows="6" name="shortDescription" align="center">${page.thumbnailDescription}</ec:textarea>
								<ec:field-validator form="page_edit_form" field="shortDescription">
									<ec:field-validator-rule name="stringLength" message="It must be less than 255 characters">
										<ec:field-validator-param name="max" raw="true">255</ec:field-validator-param>
									</ec:field-validator-rule>
								</ec:field-validator>								

		        			</ed:col>
		        		</ed:row>
        			</ed:col>
        		</ed:row>
				</ec:tabs-item>
	        	<ec:tabs-item title="Conteúdo da Página">
					<ed:row>
						<ed:col size="5" classStyle="form-group has-feedback">
							<ec:textfield name="path" label="Path" value="${metadata.path}"  enabled="${empty metadata}">
								<ec:field-validator>
									<ec:field-validator-rule name="notEmpty" message="Please inform the Path!"/>
									<ec:field-validator-rule name="regexp" message="Invalid path!">
										<ec:field-validator-param name="regexp" raw="true">/^(\/[a-z][a-z0-9]+(_[a-z0-9]+)*)*$/</ec:field-validator-param>
									</ec:field-validator-rule>
								</ec:field-validator>
							</ec:textfield>
						</ed:col>
						<ed:col size="4">
							<ec:textfield name="name" label="Name" value="${metadata.id}" enabled="false"/>
						</ed:col>
						<ed:col size="3" classStyle="form-group has-feedback">
							<ec:select label="Language" name="locale" enabled="${empty metadata}">
								<ec:option value=""></ec:option>
								<c:forEach items="${locales}" var="loc">
								<ec:option value="${loc.key}" selected="${metadata.locale == loc.key}">${loc.value}</ec:option>
								</c:forEach>
							</ec:select>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="8" classStyle="form-group has-feedback">
							<ec:textfield name="title" label="Title" value="${page.title}">
								<ec:field-validator>
									<ec:field-validator-rule name="notEmpty" message="Please inform the title!"/>
								</ec:field-validator>
							</ec:textfield>
						</ed:col>
						<ed:col size="4">
							<ec:select label="Template" name="template" enabled="false">
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
	       	</ec:tabs-item>
        	</ec:tabs>
			
			<ed:row>
				<ed:col size="12">
					<ec:button 
						actionType="submit" 
						action="${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/editor/default/save" 
						label="Salvar" 
						align="right"/>
				</ed:col>
			</ed:row>
			
		</ec:form>
	</ec:box-body>
</ec:box>