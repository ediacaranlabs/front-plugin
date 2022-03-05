<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@page trimDirectiveWhitespaces="true" %>
<ec:load-data file="index.json" var="pageObjects" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<jsp:include
	page="/plugins/ediacaran/front/default_template/includes/head.jsp" />
</head>

<body>
	<jsp:include
		page="/plugins/ediacaran/front/default_template/includes/header.jsp" />

	<ec:nivo-slider>
		<c:forEach items="${pageObjects.slider}" var="item">
		${item}
			<ec:nivo-slider-tem link="${item.src}" image="${item.img}" title="${item.title}">
				${item.desc}		
			</ec:nivo-slider-tem>
		</c:forEach>
	</ec:nivo-slider>
	
	<section class="content">
		<ed:container>
			<div class="cta-text">
				<h3>Registre um dom�nio <b>ods.in.net</b> gratuitamente e use nosso servi�o de <b>DNS din�mico</b>!</h3>
			</div>
		</ed:container>
	</section>
	
	<section class="content bg2">
		<ed:container>
		<ed:row>
			<ed:col size="4">
				<ec:price-box>
					<ec:price-box-header>
						<h3>DNS din�mico <strong>B�sico Anual</strong></h3>
					</ec:price-box-header>
					<ec:price-box-terms>
						<h6>Gratuito</h6>
					</ec:price-box-terms>
					<ec:price-box-body>
						<ec:price-box-item checked="true" >Permitido 5 hosts.</ec:price-box-item>
						<ec:price-box-item checked="true" >Registros b�sicos.</ec:price-box-item>
						<ec:price-box-item checked="false">Registros avan�ados.</ec:price-box-item>
						<ec:price-box-item checked="true" >Permite usar dom�nio pr�prio.</ec:price-box-item>
						<ec:price-box-item checked="true" >Permite usar dom�nio gratuito.</ec:price-box-item>
						<ec:price-box-item checked="false">Garantia de uptime.</ec:price-box-item>
					</ec:price-box-body>
				</ec:price-box>
			</ed:col>
			<ed:col size="4">
				<ec:price-box attractiveness="special">
					<ec:price-box-header>
						<h3>DNS din�mico <strong>B�sico Anual</strong></h3>
					</ec:price-box-header>
					<ec:price-box-terms>
						<h6>BRL 23,00 /ano</h6>
					</ec:price-box-terms>
					<ec:price-box-body>
						<ec:price-box-item checked="true" >Permitido 5 hosts.</ec:price-box-item>
						<ec:price-box-item checked="true" >Registros b�sicos.</ec:price-box-item>
						<ec:price-box-item checked="false">Registros avan�ados.</ec:price-box-item>
						<ec:price-box-item checked="true" >Permite usar dom�nio pr�prio.</ec:price-box-item>
						<ec:price-box-item checked="true" >Permite usar dom�nio gratuito.</ec:price-box-item>
						<ec:price-box-item checked="false">Garantia de uptime.</ec:price-box-item>
					</ec:price-box-body>
				</ec:price-box>
			</ed:col>
			<ed:col size="4">
				<ec:price-box>
					<ec:price-box-header>
						<h3>DNS din�mico <strong>avan�ado Anual</strong></h3>
					</ec:price-box-header>
					<ec:price-box-terms>
						<h6>BRL 65,00 /ano</h6>
					</ec:price-box-terms>
					<ec:price-box-body>
						<ec:price-box-item checked="true" >Permitido 5 hosts.</ec:price-box-item>
						<ec:price-box-item checked="true" >Registros b�sicos.</ec:price-box-item>
						<ec:price-box-item checked="false">Registros avan�ados.</ec:price-box-item>
						<ec:price-box-item checked="true" >Permite usar dom�nio pr�prio.</ec:price-box-item>
						<ec:price-box-item checked="true" >Permite usar dom�nio gratuito.</ec:price-box-item>
						<ec:price-box-item checked="false">Garantia de uptime.</ec:price-box-item>
					</ec:price-box-body>
				</ec:price-box>
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
						<ec:image src="/templates/default_template/img/calypte.png" align="center" />
						<h6>Calypte Cache</h6>
					</ec:box-header>
					<ec:box-body>
						Calypte � um sistema de cache de prop�sito geral com suporte transacional. 
						Permite o armazenamento de dados na forma de chave-valor em memoria e disco. 
						� extremamente r�pido, tanto para escrita como para leitura, podendo chegar a 
						mais de 600.000 opera��es por segundo. N�o � necess�ria grandes quantidades de 
						mem�ria para seu funcionamento. Ele trabalha de forma eficiente com pouca mem�ria.
					</ec:box-body>
					<ec:box-footer>
						<ec:button type="dark" label="Mais"/>
					</ec:box-footer>
				</ec:box>
			</ed:col>
			<ed:col size="6">
				<ec:box>
					<ec:box-header>
						<ec:image src="/templates/default_template/img/brutos.png" align="center" />
						<h6>Brutos Framework</h6>
					</ec:box-header>
					<ec:box-body>
						O Brutos application framework � um controlador MVC desenvolvido em Java. 
						Projetado para reduzir a complexidade do desenvolvimento web com mapeamento 
						configur�vel, resolu��o de vista, suporte ao upload e download de arquivos. 
						Podendo ser configurado usando XML, anota��es e suas conven��es de configura��o.
					</ec:box-body>
					<ec:box-footer>
						<ec:button label="Mais"/>
					</ec:box-footer>
				</ec:box>
			</ed:col>
		</ed:row>
		</ed:container>	</section>

	<jsp:include
		page="/plugins/ediacaran/front/default_template/includes/footer.jsp" />

</body>
</html>