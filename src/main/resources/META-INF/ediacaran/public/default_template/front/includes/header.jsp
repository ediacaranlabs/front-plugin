<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"                                  prefix="c"%>
<ec:load-data file="/header.json" var="pageObjects" />
    <!-- start header -->
   <header>
		<ec:menu-bar id="${pageObjects['menu']['id']}" classStyle="t${pageObjects['menu']['classStyle']}" expand="${pageObjects['menu']['expand']}" style="${pageObjects['menu']['Style']}">
			<ed:container>
			<ec:menu-bar-brand>
				<ec:image 
					src="${pageObjects['menu']['menu-bar-brand']['src']}" 
					classStyle="${pageObjects['menu']['menu-bar-brand']['classStyle']}"/>
			</ec:menu-bar-brand>
			<ec:menu-toggler>
				<ec:icon icon="bars" />
			</ec:menu-toggler>
			<ec:menu-body>
				<ec:menu-itens align="right">
					<c:forEach items="${pageObjects['menu']['itens']}" var="menu">
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
	</header>
    <!-- end header -->