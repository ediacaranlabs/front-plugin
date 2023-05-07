<%@page trimDirectiveWhitespaces="true" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"              prefix="fn"%>
<ec:import-object id="menubar/front/default" var="defaultMenu"/>

    <!-- start header -->
   <header>
		<ec:menu-bar id="top_menu" 
				expand="xl" 
				style="light">
			<ed:container>
			<ec:menu-bar-brand>
				<ec:image src="/img/logo.png"/>
			</ec:menu-bar-brand>
			<ec:menu-toggler menuID="top_menu_body">
				<ec:icon icon="bars" />
			</ec:menu-toggler>
			<ec:menu-body collapse="true">
				<ec:menu-itens align="right">
					<c:forEach items="${defaultMenu.itens}" var="menu">
						<c:choose>
	
							<c:when test="${not empty menu.body}">
								<ec:menu>
									<ec:menu-label>
										<c:if test="${not empty menu.icon}">
											<ec:icon icon="${menu.icon}" size="1"/>
										</c:if>
										${menu.fullName}
										<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
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
										<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
									</ec:menu-label>
									<ec:menu-itens id="menu_itens_xx">
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
									${menu.fullName}
									<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
								</ec:menu-item>
							</c:otherwise>
							
						</c:choose>
					</c:forEach>
				</ec:menu-itens>
			</ec:menu-body>
			</ed:container>
		</ec:menu-bar>
	</header>
    <!-- end header -->