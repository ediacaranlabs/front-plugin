<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2>Editar p�gina</h2>
			</div>
		</ed:col>
		<ed:col size="8">
			<ec:breadcrumb title="Editar">
				<ec:breadcrumb-path icon="home" text="" lnk="#" />
				<ec:breadcrumb-path text="p�ginas" lnk="#!/plugins/ediacaran/front${plugins.ediacaran.front.admin_context}/pages" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<ec:box>
	<ec:box-body>	
		<ec:form id="page_edit_form" method="POST" update="#result_page_edit_form">
			<input type="hidden" value="${metadata.hashCode()}" name="gid">
			<input type="hidden" value="${metadata.path}" name="path">
			<input type="hidden" value="${metadata.id}" name="name">
			<input type="hidden" value="${metadata.locale}" name="locale">
			<input type="hidden" value="${metadata.template}" name="template">
			<ed:row>
				<ed:col size="12" id="result_page_edit_form">
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="5" classStyle="form-group has-feedback">
					<ec:textfield name="path" label="Path" value="${metadata.path}"  enabled="${empty metadata}">
						<ec:field-validator>
							<ec:field-validator-rule name="notEmpty" message="Please inform the Path!"/>
							<ec:field-validator-rule name="regexp" message="Invalid path!">
								<ec:field-validator-param name="regexp" raw="true">/^(\/|(\/[a-z][a-z0-9]+(_[a-z0-9]+)*)*)$/</ec:field-validator-param>
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
						<ec:option value="${template.id}" selected="${page.template == template.id}">${template.name}</ec:option>
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