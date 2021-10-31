<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<style>
	.file {
		text-align: center;
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
		display:none
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
			
				<ec:form method="POST" action="/plugins/ediacaran/front/admin/plugins/install-file" enctype="multipart/form-data">
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
						<ed:col size="12" classStyle="file">
							<label class="label" for="file">
								<ec:icon icon="file" />
								<ec:filefield extAttrs="accept=\".zip\"" classStyle="hide-file" id="file" name="file"/>
							</label>
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12" >
							<ec:button label="Instalar" align="right" />
						</ed:col>
					</ed:row>
				</ec:form>
					
				<ec:form method="POST" action="/plugins/ediacaran/front/admin/plugins/install-url" enctype="multipart/form-data">
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
						<ed:col size="12" classStyle="url">
							<ec:textfield name="url" classStyle="url" placeholder="https://www.site.com/uri/package" />
						</ed:col>
					</ed:row>
					<ed:row>
						<ed:col size="12" >
							<ec:button label="Instalar" align="right" />
						</ed:col>
					</ed:row>
				</ec:form>					
				
				
			</ec:box-body>
		</ec:box>
	</ed:col>
</ed:row>