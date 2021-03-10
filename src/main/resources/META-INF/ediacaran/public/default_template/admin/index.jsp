<%@taglib uri="http://java.sun.com/jsp/jstl/core"                              prefix="c" %>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"                         prefix="fn"%>
<ec:setTemplatePackage name="admin"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<ec:include uri="/includes/head.jsp"/>
</head>

<body id="body" class="collapse show">
	<ec:menu-bar id="top_menu" classStyle="top-menu" expand="xl" style="light">
		<ec:menu-toggler menuID="body">
			<ec:icon icon="bars" size="1" />
		</ec:menu-toggler>
		<ec:menu-itens align="right">
			<ec:menu-item href="#">
				<ec:icon icon="comments" size="1"/>
				<span class="badge badge-danger navbar-badge">3</span>
			</ec:menu-item>
			<ec:menu-item href="#">
				<ec:icon icon="bell" size="1"/>
				<span class="badge badge-warning navbar-badge">15</span>
			</ec:menu-item>
			<ec:menu id="user_menu_top">
				<ec:menu-label><ec:icon icon="user" size="1"/></ec:menu-label>
				<ec:menu-itens resource="${plugins.ediacaran.front.admin_user_menu}" menuAlign="right">
				</ec:menu-itens>
			</ec:menu>
<%-- 			
			<ec:menu-item href="#">
				<ec:icon icon="user" size="1"/>
			</ec:menu-item>
--%>			
		</ec:menu-itens>
	</ec:menu-bar>

	<aside class="sidebar">
    	<ec:menu-bar-brand>
    		<ec:image src="/plugins/ediacaran/front/admin/img/Logo.png"/> 
    		<span>Ediacaran</span>
    	</ec:menu-bar-brand>
    	<div class="sidebar-body">
				<ec:menu-bar>
					<ec:menu-itens>
						<c:forEach items="${Controller.TopMenuBar}" var="menu">
							<c:choose>
								<c:when test="${fn:length(menu.itens) eq 0}">
									<ec:menu-item href="${menu.resource}">
										<ec:icon icon="${menu.icon}" size="1"/> 
										<p>${menu.FullName}</p>
										<c:if test="${not empty menu.badge}">
											<span class="badge badge-${empty item.badgeStyle? 'info' : item.badgeStyle} navbar-badge">${menu.badge}</span>
										</c:if>
									</ec:menu-item>
								</c:when>
								<c:when test="${fn:length(companies) gt 0}">
									<ec:menu>
										<ec:menu-label>${menu.FullName}</ec:menu-label>
									</ec:menu>
									<ec:menu-itens>
										<c:forEach items="${menu.itens}" var="item">
											<ec:menu-item href="${item.resource}">
												<ec:icon icon="${item.icon}" size="1"/> 
												<p>${item.FullName}</p>
												<c:if test="${not empty item.badge}">
													<span class="badge badge-${empty item.badgeStyle? 'info' : item.badgeStyle} navbar-badge">${item.badge}</span>
												</c:if>
											</ec:menu-item>
										</c:forEach>
									</ec:menu-itens>
								</c:when>
							</c:choose>
						</c:forEach>
					</ec:menu-itens>
				</ec:menu-bar>
		</div>
	</aside>
	
    <section id="content-body" class="content">
    </section>

	<ec:include uri="/includes/footer.jsp"/>
 </body>
</html>