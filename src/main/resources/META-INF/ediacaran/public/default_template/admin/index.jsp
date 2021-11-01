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
			<c:forEach items="${Controller.topMenuBar.itens}" var="menu">
				<c:choose>
					<c:when test="${not empty menu.body}">
						<ec:menu>
							<ec:menu-label>
								<c:if test="${not empty menu.icon}">
									<ec:icon icon="${menu.icon}" size="1"/>
								</c:if>
								<span id="${menu.id}_badge" class="badge badge-${empty menu.badgeStyle? 'info' : menu.badgeStyle} navbar-badge">${menu.badge}</span>
							</ec:menu-label>
						</ec:menu>
						<ec:menu-itens resource="${menu.body}"/>
					</c:when>
					<c:when test="${fn:length(menu.itens) gt 0}">
						<ec:menu>
							<ec:menu-label>
								<c:if test="${not empty menu.icon}">
									<ec:icon icon="${menu.icon}" size="1"/>
								</c:if>
								<span id="${menu.id}_badge" class="badge badge-${empty menu.badgeStyle? 'info' : menu.badgeStyle} navbar-badge">${menu.badge}</span>
							</ec:menu-label>
						</ec:menu>
						<ec:menu-itens>
							<c:forEach items="${menu.itens}" var="item">
								<ec:menu-item href="${item.resource}">
									<c:if test="${not empty item.icon}">
										<ec:icon icon="${item.icon}" size="1"/>
									</c:if>
									<span id="${item.id}_badge" class="badge badge-${empty item.badgeStyle? 'info' : item.badgeStyle} navbar-badge">${item.badge}</span>
								</ec:menu-item>
							</c:forEach>
						</ec:menu-itens>
					</c:when>
					<c:otherwise>
						<ec:menu-item href="${menu.resource}">
							<c:if test="${not empty menu.icon}">
								<ec:icon icon="${menu.icon}" size="1"/>
							</c:if>
							<span id="${menu.id}_badge" class="badge badge-${empty menu.badgeStyle? 'info' : menu.badgeStyle} navbar-badge">${menu.badge}</span>
						</ec:menu-item>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!--
			<ec:menu id="user_menu_top">
				<ec:menu-label><ec:icon icon="user" size="1"/></ec:menu-label>
				<ec:menu-itens resource="${plugins.ediacaran.front.admin_user_menu}" menuAlign="right">
				</ec:menu-itens>
			</ec:menu>
			-->
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
						<c:forEach items="${Controller.menuBar.itens}" var="menu">
							<c:choose>

								<c:when test="${not empty menu.body}">
									<ec:menu>
										<ec:menu-label>
											<c:if test="${not empty menu.icon}">
												<ec:icon icon="${menu.icon}" size="1"/>
											</c:if>
											${menu.fullName}
											<c:if test="${not empty menu.badge}">
												<span class="badge badge-${empty menu.badgeStyle? 'info' : menu.badgeStyle} navbar-badge">${menu.badge}</span>
											</c:if>
										</ec:menu-label>
									</ec:menu>
									<ec:menu-itens resource="${menu.body}"/>
								</c:when>

								<c:when test="${fn:length(menu.itens) != 0}">
									<ec:menu>
										<ec:menu-label>
											<c:if test="${not empty menu.icon}">
												<ec:icon icon="${menu.icon}" size="1"/>
											</c:if>
											${menu.fullName}
											<c:if test="${not empty menu.badge}">
												<span class="badge badge-${empty menu.badgeStyle? 'info' : menu.badgeStyle} navbar-badge">${menu.badge}</span>
											</c:if>
										</ec:menu-label>
										<ec:menu-itens id="menu_itens_xx">
											<c:forEach items="${menu.itens}" var="item">
												<ec:menu-item href="${item.resource}">
													<c:if test="${not empty item.icon}">
														<ec:icon icon="${item.icon}" size="1"/>
													</c:if>
													${item.fullName}
													<c:if test="${not empty item.badge}">
														<span class="badge badge-${empty item.badgeStyle? 'info' : item.badgeStyle} navbar-badge">${item.badge}</span>
													</c:if>
												</ec:menu-item>
											</c:forEach>
										</ec:menu-itens>
									</ec:menu>
								</c:when>

								<c:otherwise>
									<ec:menu-item href="${menu.resource}">
										<c:if test="${not empty menu.icon}">
											<ec:icon icon="${menu.icon}" size="1"/>
										</c:if>
										${menu.fullName}
										<c:if test="${not empty menu.badge}">
											<span class="badge badge-${empty menu.badgeStyle? 'info' : menu.badgeStyle} navbar-badge">${menu.badge}</span>
										</c:if>
									</ec:menu-item>
								</c:otherwise>

							</c:choose>
						</c:forEach>
					</ec:menu-itens>
				</ec:menu-bar>
		</div>
	</aside>
	
    <section id="content-body" class="content">
    </section>

	<div id="wait-modal" class="modal hide fade in" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body text-center" style="padding-top: 3rem; padding-bottom: 3rem;">
					<h3>Loading...</h3>
					<div class="spinner-border spinner-border-sm text-primary" style="width: 3rem; height: 3rem;" role="status">
					  <span class="sr-only">Loading...</span>
					</div>
				</div>
			</div>
		</div>
	
	</div>
 
	<ec:include uri="/includes/footer.jsp"/>
 </body>
</html>