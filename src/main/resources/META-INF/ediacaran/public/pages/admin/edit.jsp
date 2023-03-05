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
	
		<ec:form id="page_edit_form" method="POST" enctype="multipart/form-data" update="#result_page_edit_form">
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
        			<ed:col size="12" align="center">
						<ed:row style="form">
							<ed:col size="8" classStyle="form-group has-feedback mx-auto d-block">
							    <link rel="Stylesheet" type="text/css" href="/plugins/ediacaran/front/templates/default_template/front/plugins/imageField/css/croppie.css" />
								<script src="/plugins/ediacaran/front/templates/default_template/front/plugins/imageField/js/croppie.js"></script>
								<script type="text/javascript">
									$.AppContext.imageField = {};
									
									$.AppContext.imageField.apply = function ($root, $fieldName, $width, $height, $type, $default){
										
										var $croppie   = $root.find(".croppie-image");
										var $button    = $root.find("button");
										var $fileField = $root.find("input[type='file']");
										//var $fieldName = $fileField.attr('name');
										
										$root.width($width);
										$root.height($height);
										
										var $croppieObj = $($croppie).croppie({
											viewport: {
												width: $width,
												height: $height,
												type: $type
											},
											boundary: {
												width: $width,
												height: $height
											},
											showZoomer: false
										});
		
										$croppieObj.croppie(
												'bind',{
													url: $default
										});
										
										$croppieObj.on('update.croppie', function(ev, cropData) {
											
											var $x1 = $($root).find("input[name='" + $fieldName + ".x1']");
											var $x2 = $($root).find("input[name='" + $fieldName + ".x2']");
											var $y1 = $($root).find("input[name='" + $fieldName + ".y1']");
											var $y2 = $($root).find("input[name='" + $fieldName + ".y2']");
			
											$x1.val(cropData.points[0]);
											$y1.val(cropData.points[1]);
											
											$x2.val(cropData.points[2]);
											$y2.val(cropData.points[3]);
											
											console.log(
													 "x1=" + cropData.points[0] +
													",y1=" + cropData.points[1] +
													",x2=" + cropData.points[2] +
													",y2=" + cropData.points[3]
											);
											
											//console.log(JSON.stringify(cropData));
										});
			
										$($fileField).on('change', function(event){
											var $url = URL.createObjectURL(event.target.files[0]);
											
											$croppieObj.croppie('bind', {
												url: $url,
											});
											
										});
										
										$button.click(function(){
											$fileField.click();
										});
									};
			
									$.AppContext.onload(function(){
										
										$.AppContext.imageField.apply(
												$('#thumbnail'), 
												'thumbnail', 
												560, 
												292, 
												'squad', 
												'${empty page.publicThumbnailPath? '/plugins/ediacaran/front/templates/default_template/front/plugins/imageField/img/default.png' : page.publicThumbnailPath}'
										);
										
									});
									
								</script>
								<div id="thumbnail" class=" mx-auto d-block">
									<div class="croppie-image" style="border: 1px solid #ced4da;"></div>
									<br>
									<ec:button actionType="button"  label="Select Image" align="right"/>
									<input name="thumbnail.x1" type="hidden" value="0">
									<input name="thumbnail.y1" type="hidden" value="0">
									<input name="thumbnail.x2" type="hidden" value="0">
									<input name="thumbnail.y2" type="hidden" value="0">
									<input name="thumbnail.file" type="file" style="display: none">
		
								</div>					
							</ed:col>
						</ed:row>
		        		<ed:row style="form">
		        			<ed:col>
								<ec:textfield name="titleThumbnail" align="center" value="${page.thumbnailTitle}"/>
		        			</ed:col>
		        		</ed:row>
		        		<ed:row style="form">
		        			<ed:col>
								<ec:textarea rows="6" name="shortDescription" align="center">${page.thumbnailDescription}</ec:textarea>
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