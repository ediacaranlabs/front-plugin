<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<ec:load-data context="/" file="/objects/menus/footer-menu" var="footerMenu" />
<ec:load-data context="/" file="/objects/menus/footer-menu2" var="footerMenu2" />
<ec:load-data context="/" file="/objects/address" var="address" />
<ec:load-data context="/" file="/objects/social-links" var="socialLinks" />
<%--<ec:load-data file="footer.json" var="objects" />--%>
<footer>
	<ed:container>
		<ed:row>
			<ed:col>
				<c:if test="${!empty socialLinks}">
					<ec:list style="inline">
						<c:forEach items="${socialLinks}" var="social_link">
							<ec:list-item>
								<a href="${social_link.value['src']}">
									<ec:icon icon="${social_link.value['icon']}" bg="square" bgSize="2" size="2" />
								</a>
							</ec:list-item>
						</c:forEach>
					</ec:list>
				</c:if>
			</ed:col>
			<ed:col classStyle="widget">
				<c:if test="${!empty footerMenu2}">
				
					<ec:menu-bar id="frontFooterMenu2" 
							expand="xl" 
							style="footer">
						<ed:container>
						<ec:menu-bar-brand>
							<h5>${footerMenu2['title']}</h5>
						</ec:menu-bar-brand>
						<ec:menu-body collapse="false">
							<ec:menu-itens>
								<c:forEach items="${footerMenu2['itens']}" var="menu">
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
				</c:if>
			</ed:col>
			<ed:col classStyle="widget">
				<c:if test="${!empty footerMenu}">
				
					<ec:menu-bar id="frontFooterMenu" 
							expand="xl" 
							style="footer">
						<ed:container>
						<ec:menu-bar-brand>
							<h5>${footerMenu['title']}</h5>
						</ec:menu-bar-brand>
						<ec:menu-body collapse="false">
							<ec:menu-itens>
								<c:forEach items="${footerMenu['itens']}" var="menu">
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
				</c:if>
			</ed:col>
			<ed:col classStyle="widget">
				<c:if test="${!empty address}">
					<h5>${address['title']}</h5>
					<address>${address['address']}</address>
					<p>
					<c:if test="${!empty address['phone']}">
						<ec:icon icon="phone" size="1"/> ${address['phone']}<br>
					</c:if>
					<c:if test="${!empty address['email']}">
						<ec:icon icon="envelope" size="1"/> ${address['email']}<br>
					</c:if>
					</p>
				</c:if>			
			</ed:col>
		</ed:row>
	</ed:container>
	<div class="sub-footer">
		<ed:container>
			<ed:row>
				<ed:col size="12">
					<span>UoUTec - All right reserved.</span>
				</ed:col>
			</ed:row>
		</ed:container>
	</div>
    </footer>
    
  