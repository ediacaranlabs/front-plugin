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
				<ec:breadcrumb-path text="páginas" lnk="#" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<ed:row>
	<ed:col size="5">
		<ec:textfield name="path" label="path" value="${metadata.path}" enabled="${empty metadata}"/>
	</ed:col>
	<ed:col size="4">
		<ec:textfield name="name" label="name" value="${metadata.id}" enabled="${empty metadata}"/>
	</ed:col>
	<ed:col size="3">
		<ec:select label="Idioma" name="locale" enabled="${empty metadata}">
			<ec:option value="" ></ec:option>
			<c:forEach items="${locales}" var="loc">
			<ec:option value="${loc.key}" selected="${metadata.locale == loc.key}">${loc.value}</ec:option>
			</c:forEach>
		</ec:select>
	</ed:col>
</ed:row>
<ed:row>
	<ed:col size="8">
		<ec:textfield name="title" label="Title" value="${page.title}"/>
	</ed:col>
	<ed:col size="4">
		<ec:select label="Template" name="template">
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