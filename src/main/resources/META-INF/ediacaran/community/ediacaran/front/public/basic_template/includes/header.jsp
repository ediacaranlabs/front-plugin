<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib uri="META-INF/tags/bootstrap4/components.tld" prefix="ec"%>
<%@taglib uri="META-INF/tags/bootstrap4/designer.tld"   prefix="ed"%>
    <!-- start header -->
    <header>
      <ed:container>
      	<ec:row classStyle="nomargin">
      		<ec:col>
	            <div class="headnav">
	            	<ec:list>
	            		<ec:list-item><a href="${vars['signup']['link']}" data-toggle="modal">${vars['signup']['text']}</a></ec:list-item>
	            		<ec:list-item><a href="${vars['signin']['link']}" data-toggle="modal">${vars['signin']['text']}</a></ec:list-item>
	            	</ec:list>
	            </div>
      		</ec:col>
      	</ec:row>
      	<ec:row>
      		<ec:col size="4">
	            <div class="logo">
	              <a href="${vars['logo']['link']}"><img src="${vars['logo']['image']}" alt="" class="logo" /></a>
	              <h1>${vars['logo']['text']}</h1>
	            </div>
      		</ec:col>
      		<ec:col size="8">
      			<ec:menu-bar>
					<c:forEach items="${vars['menu']}" var="menu">
						<c:choose>
							<c:when test="${empty menu['link']}">
								<ec:menu name="${menu['name']}">
									<c:forEach items="${menu['itens']}" var="item">
										<ec:menu-item link="${item['link']}">${item['name']}</ec:menu-item>
									</c:forEach>
								</ec:menu>
							</c:when>
							<c:otherwise>
								<ec:menu-item link="${menu['link']}">${menu['name']}</ec:menu-item>
							</c:otherwise>
						</c:choose>
					</c:forEach>
      			</ec:menu-bar>
      		</ec:col>
      	</ec:row>
      </ed:container>
    </header>
    <!-- end header -->