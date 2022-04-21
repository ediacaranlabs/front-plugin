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

<body id="body">

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

	<ec:sidebar-group>
		<ec:sidebar>
			<ed:container>
				<ec:menu-bar id="${pageObjects['leftMenu']['id']}" 
						classStyle="${pageObjects['leftMenu']['classStyle']}" 
						expand="${pageObjects['leftMenu']['expand']}" 
						style="${pageObjects['leftMenu']['style']}">
		 				<ec:menu-toggler menuID="leftMenu_body">
							<ec:icon icon="bars" size="1" />
						</ec:menu-toggler>
						<ec:menu-body collapse="true">
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
				</ec:menu-bar>
			</ed:container>
		</ec:sidebar>
		<ec:body id="content-body">
	    	<ed:row>
	    		<ed:col size="12">
	    			<ed:container>
	 					${pageObjects['content']}
	    			</ed:container>
	    		</ed:col>
	    	</ed:row>
		</ec:body>
	</ec:sidebar-group>
        	 
	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>