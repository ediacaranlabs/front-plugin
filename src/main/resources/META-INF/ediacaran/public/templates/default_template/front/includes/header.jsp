<%@page trimDirectiveWhitespaces="true" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"              prefix="fn"%>
<ec:load-data context="/" file="/objects/menus/default-menu" var="defaultMenu" />
<%--<ec:load-data file="header.json" var="pageObjects" />--%>
    <!-- start header -->
   <header>
		<ec:menu-bar id="top_menu" 
				expand="xl" 
				style="light">
			<ed:container>
			<ec:menu-bar-brand>
				<ec:image src="${defaultMenu['menu-bar-brand']['src']}"/>
			</ec:menu-bar-brand>
			<ec:menu-toggler menuID="top_menu_body">
				<ec:icon icon="bars" />
			</ec:menu-toggler>
			<ec:menu-body collapse="true">
				<ec:menu-itens align="right">
					<c:forEach items="${defaultMenu['itens']}" var="menu">
						<c:choose>
							<c:when test="${!empty menu.value['itens']}">
								<ec:menu>
									<ec:menu-label>${menu.value['name']}</ec:menu-label>
									<ec:menu-itens>
										<c:forEach items="${menu.value['itens']}" var="item">
											<ec:menu-item href="${item.value['link']}">${item.value['name']}</ec:menu-item>
										</c:forEach>
									</ec:menu-itens>
								</ec:menu>
							</c:when>
							<c:otherwise>
								<ec:menu-item href="${menu.value['link']}">${menu.value['name']}</ec:menu-item>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ec:menu-itens>
			</ec:menu-body>
			</ed:container>
		</ec:menu-bar>
	</header>
    <!-- end header -->