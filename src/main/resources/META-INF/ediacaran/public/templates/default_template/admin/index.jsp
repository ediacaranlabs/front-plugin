<%@taglib uri="http://java.sun.com/jsp/jstl/core"                              prefix="c" %>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"                         prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>
<ec:setBundle var="messages" locale="${locale}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<ec:include uri="/includes/head.jsp"/>
</head>

<body>
<ec:import-object var="adminmenubar" id="/admin/menus/adminmenubar"/>
<ec:import-object var="admintopmenubar" id="/admin/menus/admintopmenubar"/>
<ec:sidebar-group id="pageBody" show="true">

	<ec:sidebar>
		<ec:menu-bar style="adm-left">
			<ec:menu-bar-brand>
				<ec:image src="/img/admin/Logo.png" />
				<span>Ediacaran</span>
			</ec:menu-bar-brand>
				<ec:menu-itens>
					<c:forEach items="${adminmenubar.itens}" var="menu">
						<c:choose>
	
							<c:when test="${not empty menu.body}">
								<ec:menu>
									<ec:menu-label>
										<c:if test="${not empty menu.icon}">
											<ec:icon icon="${menu.icon}" size="1"/>
										</c:if>
										${menu.fullName}
										<c:if test="${not empty menu.badge}">
											<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
										</c:if>
									</ec:menu-label>
									<ec:menu-itens resource="${menu.body}"/>
								</ec:menu>
							</c:when>
	
							<c:when test="${fn:length(menu.itens) != 0}">
								<ec:menu>
									<ec:menu-label>
										<c:if test="${not empty menu.icon}">
											<ec:icon icon="${menu.icon}" size="1"/>
										</c:if>
										${menu.fullName}
										<c:if test="${not empty menu.badge}">
											<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
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
													<ec:badge id="${item.id}_badge" style="${empty item.badgeStyle? 'info' : item.badgeStyle}" type="navbar">${item.badge}</ec:badge>
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
										<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
									</c:if>
								</ec:menu-item>
							</c:otherwise>
	
						</c:choose>
					</c:forEach>
				</ec:menu-itens>
		</ec:menu-bar>		
	</ec:sidebar>

	<div class="sidebar-content">
		<ec:menu-bar id="top_menu" classStyle="top-menu" expand="xl" style="light">
			<ec:menu-toggler menuID="pageBody">
				<ec:icon icon="bars" size="1" />
			</ec:menu-toggler>
			<ec:menu-bar-brand>
			</ec:menu-bar-brand>
				<ec:menu-itens align="right">
				<c:forEach items="${admintopmenubar.itens}" var="menu">
					<c:choose>
						<c:when test="${not empty menu.body}">
							<ec:menu>
								<ec:menu-label>
									<c:if test="${not empty menu.icon}">
										<ec:icon icon="${menu.icon}" size="1"/>
									</c:if>
									<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
								</ec:menu-label>
								<ec:menu-itens resource="${menu.body}"/>
							</ec:menu>
						</c:when>
						<c:when test="${fn:length(menu.itens) gt 0}">
							<ec:menu>
								<ec:menu-label>
									<c:if test="${not empty menu.icon}">
										<ec:icon icon="${menu.icon}" size="1"/>
									</c:if>
									<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
								</ec:menu-label>
								<ec:menu-itens>
									<c:forEach items="${menu.itens}" var="item">
										<ec:menu-item href="${item.resource}">
											<c:if test="${not empty item.icon}">
												<ec:icon icon="${item.icon}" size="1"/>
											</c:if>
											${item.fullName}
											<ec:badge id="${item.id}_badge" style="${empty item.badgeStyle? 'info' : item.badgeStyle}" type="navbar">${item.badge}</ec:badge>
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
								${item.fullName}
								<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
							</ec:menu-item>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ec:menu-itens>
		</ec:menu-bar>
		<ec:body id="content-body">
		</ec:body>
		<ec:include uri="/includes/footer.jsp"/>
	</div>

	
</ec:sidebar-group>

<div id="wait-modal" class="modal hide fade in" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body" style="padding-top: 3rem; padding-bottom: 3rem;">
				<ed:row>
					<ed:col size="4">
						<div class="spinner-border spinner-border-sm text-primary float-right" style="width: 2rem; height: 2rem;" role="status">
						  <span class="sr-only"><fmt:message key="root.loading" bundle="${messages}"/></span>
						</div>
					</ed:col>
					<ed:col size="8">
						<h3><fmt:message key="root.loading" bundle="${messages}"/></h3>
					</ed:col>
				</ed:row>
			</div>
		</div>
	</div>
</div>

<%--
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
								<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
							</ec:menu-label>
							<ec:menu-itens resource="${menu.body}"/>
						</ec:menu>
					</c:when>
					<c:when test="${fn:length(menu.itens) gt 0}">
						<ec:menu>
							<ec:menu-label>
								<c:if test="${not empty menu.icon}">
									<ec:icon icon="${menu.icon}" size="1"/>
								</c:if>
								<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
							</ec:menu-label>
							<ec:menu-itens>
								<c:forEach items="${menu.itens}" var="item">
									<ec:menu-item href="${item.resource}">
										<c:if test="${not empty item.icon}">
											<ec:icon icon="${item.icon}" size="1"/>
										</c:if>
										${item.fullName}
										<ec:badge id="${item.id}_badge" style="${empty item.badgeStyle? 'info' : item.badgeStyle}" type="navbar">${item.badge}</ec:badge>
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
							${item.fullName}
							<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
						</ec:menu-item>
					</c:otherwise>
				</c:choose>
			</c:forEach>
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
												<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
											</c:if>
										</ec:menu-label>
										<ec:menu-itens resource="${menu.body}"/>
									</ec:menu>
								</c:when>

								<c:when test="${fn:length(menu.itens) != 0}">
									<ec:menu>
										<ec:menu-label>
											<c:if test="${not empty menu.icon}">
												<ec:icon icon="${menu.icon}" size="1"/>
											</c:if>
											${menu.fullName}
											<c:if test="${not empty menu.badge}">
												<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
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
														<ec:badge id="${item.id}_badge" style="${empty item.badgeStyle? 'info' : item.badgeStyle}" type="navbar">${item.badge}</ec:badge>
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
											<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
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
				<div class="modal-body" style="padding-top: 3rem; padding-bottom: 3rem;">
					<ed:row>
						<ed:col size="4">
							<div class="spinner-border spinner-border-sm text-primary float-right" style="width: 2rem; height: 2rem;" role="status">
							  <span class="sr-only"><fmt:message key="root.loading" bundle="${messages}"/></span>
							</div>
						</ed:col>
						<ed:col size="8">
							<h3><fmt:message key="root.loading" bundle="${messages}"/></h3>
						</ed:col>
					</ed:row>
				</div>
			</div>
		</div>
	</div>
 
	<ec:include uri="/includes/footer.jsp"/>
--%>
	
 </body>
</html>