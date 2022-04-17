<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"              prefix="fn"%>
<%@page trimDirectiveWhitespaces="true" %>
<ec:load-data file="sidebar.json" var="pageObjects" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<ec:include uri="/includes/head.jsp"/>
</head>

<body id="body" class="collapse show">

	<ec:include uri="/includes/header.jsp"/>
	
	<section class="inner-headline">
		<ed:container>
			<ed:row>
				<ed:col size="4">
					<div class="inner-heading">
						<h2>Sidebar</h2>
					</div>
				</ed:col>
				<ed:col size="8">
					<ec:breadcrumb title="Sidebar">
						<ec:breadcrumb-path icon="home" text="" lnk="#" />
						<ec:breadcrumb-path text="Features" lnk="#" />
					</ec:breadcrumb>
				</ed:col>
			</ed:row>
		</ed:container>
	</section>

	<aside class="sidebar">
    	<ec:menu-bar-brand>
    		<span>Menu</span>
    	</ec:menu-bar-brand>
    	<div class="sidebar-body">
    	
			<ec:menu-bar id="${pageObjects['leftMenu']['id']}" 
					classStyle="${pageObjects['leftMenu']['classStyle']}" 
					expand="${pageObjects['leftMenu']['expand']}" 
					style="${pageObjects['leftMenu']['style']}">
				<ed:container>
				<ec:menu-bar-brand>
					<ec:image 
						src="${pageObjects['leftMenu']['menu-bar-brand']['src']}" 
						classStyle="${pageObjects['leftMenu']['menu-bar-brand']['classStyle']}"/>
				</ec:menu-bar-brand>
				<ec:menu-body collapse="false">
					<ec:menu-itens align="right">
						<c:forEach items="${pageObjects['leftMenu']['itens']}" var="menu">
							<c:choose>
								<c:when test="${!empty menu['itens']}">
									<ec:menu>
										<ec:menu-label>${menu['name']}</ec:menu-label>
										<ec:menu-itens>
											<c:forEach items="${menu['itens']}" var="item">
												<ec:menu-item href="${item['link']}">${item['name']}</ec:menu-item>
											</c:forEach>
										</ec:menu-itens>
									</ec:menu>
								</c:when>
								<c:otherwise>
									<ec:menu-item href="${item['link']}">${menu['name']}</ec:menu-item>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ec:menu-itens>
				</ec:menu-body>
				</ed:container>
			</ec:menu-bar>
    	
		</div>
	</aside>
	
    <section id="content-body" class="content">
    	<ed:row>
    		<ed:col size="12">
   		    	${pageObjects['content']}
    		</ed:col>
    	</ed:row>
    </section>
	
        	 
	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>