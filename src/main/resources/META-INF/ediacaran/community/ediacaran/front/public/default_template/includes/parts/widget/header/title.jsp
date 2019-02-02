<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${entity}"/>
<section id="inner-headline">
	<div class="container">
		<div class="row">
			<div class="span4">
				<div class="inner-heading">
					<h2>${vars['title']}</h2>
				</div>
			</div>
			<div class="span8">
				<ul class="breadcrumb">
	    			<c:forEach items="${entity['breadcrumb']}" var="item">
					</c:forEach>
					<li>
						<a href="${item['link']}">
						<c:if test="${!empty item['icon']}">
							<i class="${item['icon']}"></i>
						</c:if>
						${item['text']}
						</a>
						<i class="icon-angle-right"></i>
					</li>
					
					<li class="active">${vars['title']}</li>
				</ul>
			</div>
		</div>
	</div>
</section>