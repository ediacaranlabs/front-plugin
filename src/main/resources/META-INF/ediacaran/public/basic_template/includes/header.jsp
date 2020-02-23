<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components.tld" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer.tld"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"                                  prefix="c"%>
    <!-- start header -->
    <header>
      <ed:container>
      	<ed:row classStyle="nomargin">
      		<ed:col>
	            <div class="headnav">
	            	<ec:list>
	            		<ec:list-item><a href="${vars['signup']['link']}" data-toggle="modal">${vars['signup']['text']}</a></ec:list-item>
	            		<ec:list-item><a href="${vars['signin']['link']}" data-toggle="modal">${vars['signin']['text']}</a></ec:list-item>
	            	</ec:list>
	            </div>
      		</ed:col>
      	</ed:row>
      	<ed:row>
      		<ed:col size="4">
	            <div class="logo">
	              <a href="${vars['logo']['link']}"><img src="${vars['logo']['image']}" alt="" class="logo" /></a>
	              <h1>${vars['logo']['text']}</h1>
	            </div>
      		</ed:col>
      		<ed:col size="8">
      			<ec:menu-bar expand="sm">
      				<!--<ec:menu-bar-brand href="/">UoU Technology</ec:menu-bar-brand>-->
      				<ec:menu-toggler><ec:icon icon="bars"/></ec:menu-toggler>
      				<ec:menu-body>
      					<ec:menu-itens>
							<c:forEach items="${vars['menu']}" var="menu">
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
      		</ed:col>
      	</ed:row>
      </ed:container>
    </header>
    <!-- end header -->