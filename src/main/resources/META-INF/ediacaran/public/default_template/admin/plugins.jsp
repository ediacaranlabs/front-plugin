<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<style>
	.file {
		/*text-align: center;*/
	}
	.file .label {
		border: 1px dashed #cacaca;
		position: relative;
		width: 100%;
		padding-top: 20px;
		padding-bottom: 20px;
		cursor: pointer;
	}
	.file .label i{
		color: #cacaca;
	}
	.file .hide-file {
		/*display:none;*/
	}
	.url {
		width: 100%;
	}
</style>

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2>Plugins</h2>
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
			<ec:box-header><h3>Menu</h3></ec:box-header>
			<ec:box-body>
				<ec:accordion>
					<c:forEach items="${Controller.groups}" var="group">
						<ec:accordion-item title="${group}">
							<ec:list>
								<c:forEach items="${Controller.getPlugins(group)}" var="plugin">
									<ec:list-item><a href="#!/plugins/ediacaran/front/admin/plugins/${plugin.metadata.code}" dest-content="#plugin_body">${plugin.metadata.name}</a></ec:list-item>
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
			
				<ec:form method="POST" update="#result-install-file" 
					action="/plugins/ediacaran/front/admin/plugins/install-file" 
					enctype="multipart/form-data">
					<ed:row>
						<ed:col size="12">
							<h5>Instalar plugin a partir de um arquivo</h5>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12">
							<ec:separator/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12">
							Clique no campo abaixo e informe o arquivo de instalação. Depois aperte o botão instalar para instalar o plugin.
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="10" classStyle="file form-group has-feedback">
							<ec:filefield extAttrs="accept=\".zip\"" classStyle="hide-file" id="file" name="file" required="true">
<%--							
								<ec:field-validator>
									<ec:field-validator-rule name="notEmpty" message="The file must be informed"/>
									<ec:field-validator-rule name="file" message="The selected file is not valid">
										<ec:field-validator-param name="extension">zip</ec:field-validator-param>
										<ec:field-validator-param name="maxSize" raw="true">2048*1024*1024</ec:field-validator-param>
									</ec:field-validator-rule>
								</ec:field-validator>
--%>								
							</ec:filefield>
						</ed:col>
						<ed:col size="2">
							<ec:button label="Instalar" actionType="submit" align="right" />
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12" id="result-install-file">
						</ed:col>
					</ed:row>
				</ec:form>
					
				<ec:form method="POST" update="#result-install-url"  action="/plugins/ediacaran/front/admin/plugins/install-url">
					<ed:row>
						<ed:col size="12">
							<h5>Instalar plugin a partir de um URL</h5>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12">
							<ec:separator/>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12">
							Informe a URL do arquivo de instalação no campo abaixo e aperte o botão instalar para instalar o plugin.
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="10" classStyle=" form-group has-feedback">
							<ec:urlfield name="url" classStyle="url" placeholder="https://www.site.com/uri/package">
								<ec:field-validator>
									<ec:field-validator-rule name="notEmpty" message="The URL must be informed!"/>
								</ec:field-validator>
							</ec:urlfield>
						</ed:col>
						<ed:col size="2">
							<ec:button label="Instalar" align="right" />
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