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
<ec:include uri="/includes/head.jsp"/>
</head>

<body>

	<ec:include uri="/includes/header.jsp"/>
	
	<ec:nivo-slider>
		<c:forEach items="${pageObjects.slider}" var="item">
			<ec:nivo-slider-item link="${item.src}" image="${item.img}" title="${item.title}">
				${item.desc}		
			</ec:nivo-slider-item>
		</c:forEach>
	</ec:nivo-slider>

	<section class="content">
		<ed:container>
			<div class="cta-text">
				<h3>${pageObjects.topMessage}</h3>
			</div>
		</ed:container>
	</section>
	<section class="content">
		<ed:container>
		<ed:row>
			<c:forEach items="${pageObjects.box}" var="item">
				<ed:col size="3">
					<ec:box>
						<ec:box-header>
							<ec:icon align="center" icon="${item.icon}" bg="circle" size="3" />
							<h6>${item.title}</h6>
						</ec:box-header>
						<ec:box-body>
							<p>${item.desc}</p>
						</ec:box-body>
						<ec:box-footer>
							<a href="${item.src}">Mais</a>
						</ec:box-footer>
					</ec:box>
				</ed:col>
			</c:forEach>
		</ed:row>
		</ed:container>
	</section>
	<section class="content">
		<ed:container>
			<ec:separator />
		</ed:container>
	</section>
	<section class="content">
		<ed:container>
		<ed:row>
			<ed:col size="12">
				<h4 class="heading">Gallery</h4>
				<ec:gallery cols="4">
					<ec:gallery-images>
						<c:forEach items="${pageObjects.gallery}" var="item">
							<ec:gallery-image filter="all" icon="${item.icon}" text="${item.desc}" src="${item.image}" title="${item.title}"/>
						</c:forEach>
					</ec:gallery-images>
				</ec:gallery>
			</ed:col>
		</ed:row>
		</ed:container>	
	</section>
	<section class="content">
		<ed:container>
		<ed:row>
			<ed:col size="12">
				<h4 class="heading">Carousel</h4>

				<ec:carousel>
					<ec:carousel-item>
						<a href="#"><ec:image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-01.jpg"/></a>
					</ec:carousel-item> 
					<ec:carousel-item>
						<a href="#"><ec:image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-02.jpg"/></a>
					</ec:carousel-item> 
					<ec:carousel-item>
						<a href="#"><ec:image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-03.jpg"/></a>
					</ec:carousel-item> 
					<ec:carousel-item>
						<a href="#"><ec:image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-04.jpg"/></a>
					</ec:carousel-item> 
					<ec:carousel-item>
						<a href="#"><ec:image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-05.jpg"/></a>
					</ec:carousel-item> 
					<ec:carousel-item>
						<a href="#"><ec:image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-06.jpg"/></a>
					</ec:carousel-item> 
					<ec:carousel-item>
						<a href="#"><ec:image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-07.jpg"/></a>
					</ec:carousel-item> 
					<ec:carousel-item>
						<a href="#"><ec:image src="/plugins/ediacaran/front/templates/default_template/img/gallery/thumbs/image-08.jpg"/></a>
					</ec:carousel-item> 
				</ec:carousel>

			</ed:col>
		</ed:row>
		</ed:container>	
	</section>

	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>