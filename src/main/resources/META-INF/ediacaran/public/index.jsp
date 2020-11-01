<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@page trimDirectiveWhitespaces="true" %>
<ec:load-data file="index.json" var="pageObjects" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<ec:include uri="/includes/head.jsp"/>
</head>

<body>

	<ec:include uri="/includes/header.jsp"/>
	
	<ec:nivo-slider>
		<c:forEach items="${pageObjects.slider}" var="item">
			<ec:nivo-slider-tem link="${item.src}" image="${item.img}" title="${item.title}">
				${item.desc}		
			</ec:nivo-slider-tem>
		</c:forEach>
	</ec:nivo-slider>

	<section class="content">
		<ed:container>
			<div class="cta-text">
				<h3>Registre um domínio <b>ods.in.net</b> gratuitamente e use nosso serviço de <b>DNS dinâmico</b>!</h3>
			</div>
		</ed:container>
	</section>
	<section class="content">
		<ed:container>
		<ed:row>
			<ed:col size="3">
				<ec:box>
					<ec:box-header>
						<ec:icon icon="briefcase" bg="circle" size="3" />
						<h6>Corporate business</h6>
					</ec:box-header>
					<ec:box-body>
						<p>Lorem ipsum dolor sit amet, has ei ipsum scaevola deseruisse 
						am sea facilisis.</p>
					</ec:box-body>
					<ec:box-footer>
						<a href="#">Learn more</a>
					</ec:box-footer>
				</ec:box>
			</ed:col>
			<ed:col size="3">
				<ec:box>
					<ec:box-header>
						<ec:icon icon="desktop" bg="circle" size="3" />
						<h6>Responsive theme</h6>
					</ec:box-header>
					<ec:box-body>
						<p>Lorem ipsum dolor sit amet, has ei ipsum scaevola deseruisse am sea facilisis.</p>
					</ec:box-body>
					<ec:box-footer>
						<a href="#">Learn more</a>
					</ec:box-footer>
				</ec:box>
			</ed:col>
			<ed:col size="3">
				<ec:box>
					<ec:box-header>
						<ec:icon icon="flask" bg="circle" size="3" />
						<h6>Coded carefully</h6>
					</ec:box-header>
					<ec:box-body>
						<p>Lorem ipsum dolor sit amet, has ei ipsum scaevola deseruisse am sea facilisis.</p>
					</ec:box-body>
					<ec:box-footer>
						<a href="#">Learn more</a>
					</ec:box-footer>
				</ec:box>
			</ed:col>
			<ed:col size="3">
				<ec:box>
					<ec:box-header>
						<ec:icon icon="coffee" bg="circle" size="3" />
						<h6>Sit and enjoy</h6>
					</ec:box-header>
					<ec:box-body>
						<p>Lorem ipsum dolor sit amet, has ei ipsum scaevola deseruisse am sea facilisis.</p>
					</ec:box-body>
					<ec:box-footer>
						<a href="#">Learn more</a>
					</ec:box-footer>
				</ec:box>
			</ed:col>
		</ed:row>
		</ed:container>
	</section>

	<section class="content">
		<ed:container>
		<ed:row>
			<ed:col size="6">
				<ec:box>
					<ec:box-header>
						<ec:image src="/plugins/ediacaran/front/templates/default_template/img/calypte.png" align="center" />
						<h6>Calypte Cache</h6>
					</ec:box-header>
					<ec:box-body>
						Calypte é um sistema de cache de propósito geral com suporte transacional. 
						Permite o armazenamento de dados na forma de chave-valor em memoria e disco. 
						É extremamente rápido, tanto para escrita como para leitura, podendo chegar a 
						mais de 600.000 operações por segundo. Não é necessária grandes quantidades de 
						memória para seu funcionamento. Ele trabalha de forma eficiente com pouca memória.
					</ec:box-body>
					<ec:box-footer>
						<ec:button type="dark" label="Mais"/>
					</ec:box-footer>
				</ec:box>
			</ed:col>
			<ed:col size="6">
				<ec:box>
					<ec:box-header>
						<ec:image src="/plugins/ediacaran/front/templates/default_template/img/brutos.png" align="center" />
						<h6>Brutos Framework</h6>
					</ec:box-header>
					<ec:box-body>
						O Brutos application framework é um controlador MVC desenvolvido em Java. 
						Projetado para reduzir a complexidade do desenvolvimento web com mapeamento 
						configurável, resolução de vista, suporte ao upload e download de arquivos. 
						Podendo ser configurado usando XML, anotações e suas convenções de configuração.
					</ec:box-body>
					<ec:box-footer>
						<ec:button label="Mais"/>
					</ec:box-footer>
				</ec:box>
			</ed:col>
		</ed:row>
		</ed:container>	</section>

	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>