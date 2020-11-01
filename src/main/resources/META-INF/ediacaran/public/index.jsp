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
			<ed:col size="12">
				<h4 class="heading">Some of recent <strong>works</strong></h4>
				<ec:gallery cols="4">
					<ec:gallery-images>
						<ec:gallery-image filter="all" icon="/plugins/ediacaran/front/templates/default_template/img/works/thumbs/image-01.jpg" 
							text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
							src="/plugins/ediacaran/front/templates/default_template/img/works/full/image-01-full.jpg" 
							title="The City"/>
						<ec:gallery-image filter="all" icon="/plugins/ediacaran/front/templates/default_template/img/works/thumbs/image-02.jpg" 
							text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
							src="/plugins/ediacaran/front/templates/default_template/img/works/full/image-02-full.jpg" 
							title="The City"/>
						<ec:gallery-image filter="all" icon="/plugins/ediacaran/front/templates/default_template/img/works/thumbs/image-03.jpg" 
							text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
							src="/plugins/ediacaran/front/templates/default_template/img/works/full/image-03-full.jpg" 
							title="The City"/>
						<ec:gallery-image filter="all" icon="/plugins/ediacaran/front/templates/default_template/img/works/thumbs/image-04.jpg" 
							text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
							src="/plugins/ediacaran/front/templates/default_template/img/works/full/image-04-full.jpg" 
							title="The City"/>
						<ec:gallery-image filter="all" icon="/plugins/ediacaran/front/templates/default_template/img/works/thumbs/image-05.jpg" 
							text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
							src="/plugins/ediacaran/front/templates/default_template/img/works/full/image-05-full.jpg" 
							title="The City"/>
						<ec:gallery-image filter="all" icon="/plugins/ediacaran/front/templates/default_template/img/works/thumbs/image-06.jpg" 
							text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
							src="/plugins/ediacaran/front/templates/default_template/img/works/full/image-06-full.jpg" 
							title="The City"/>
						<ec:gallery-image filter="all" icon="/plugins/ediacaran/front/templates/default_template/img/works/thumbs/image-07.jpg" 
							text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
							src="/plugins/ediacaran/front/templates/default_template/img/works/full/image-07-full.jpg" 
							title="The City"/>
						<ec:gallery-image filter="all" icon="/plugins/ediacaran/front/templates/default_template/img/works/thumbs/image-08.jpg" 
							text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis elementum odio. Curabitur pellentesque, dolor vel pharetra mollis." 
							src="/plugins/ediacaran/front/templates/default_template/img/works/full/image-08-full.jpg" 
							title="The City"/>
					</ec:gallery-images>
				</ec:gallery>
			</ed:col>
		</ed:row>
		</ed:container>	</section>

	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>