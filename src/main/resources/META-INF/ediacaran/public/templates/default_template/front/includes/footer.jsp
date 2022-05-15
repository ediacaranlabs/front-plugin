<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>

<%--<ec:load-data file="footer.json" var="objects" />--%>
<footer>
	<ed:container>
		<ed:row>
			<ed:col>
				<c:if test="${!empty pageObjects['social-links']}">
					<ec:list style="inline">
						<c:forEach items="${pageObjects['social-links']}" var="social_link">
							<ec:list-item>
								<a href="${social_link['src']}">
									<ec:icon icon="${social_link['icon']}" bg="square" bgSize="2" size="2" />
								</a>
							</ec:list-item>
						</c:forEach>
					</ec:list>
				</c:if>
			</ed:col>
			<ed:col classStyle="widget">
				<c:if test="${!empty pageObjects['frontFooterMenu2']}">
				
					<ec:menu-bar id="${pageObjects['frontFooterMenu2']['id']}" 
							classStyle="${pageObjects['frontFooterMenu2']['classStyle']}" 
							expand="${pageObjects['frontFooterMenu2']['expand']}" 
							style="${pageObjects['frontFooterMenu2']['style']}">
						<ed:container>
						<ec:menu-bar-brand>
							<h5>${pageObjects['frontFooterMenu2']['title']}</h5>
						</ec:menu-bar-brand>
						<ec:menu-body collapse="false">
							<ec:menu-itens>
								<c:forEach items="${pageObjects['frontFooterMenu2']['itens']}" var="menu">
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
				</c:if>
			</ed:col>
			<ed:col classStyle="widget">
				<c:if test="${!empty pageObjects['frontFooterMenu1']}">
				
					<ec:menu-bar id="${pageObjects['frontFooterMenu1']['id']}" 
							classStyle="${pageObjects['frontFooterMenu1']['classStyle']}" 
							expand="${pageObjects['frontFooterMenu1']['expand']}" 
							style="${pageObjects['frontFooterMenu1']['style']}">
						<ed:container>
						<ec:menu-bar-brand>
							<h5>${pageObjects['frontFooterMenu1']['title']}</h5>
						</ec:menu-bar-brand>
						<ec:menu-body collapse="false">
							<ec:menu-itens>
								<c:forEach items="${pageObjects['frontFooterMenu1']['itens']}" var="menu">
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
				</c:if>
			</ed:col>
			<ed:col classStyle="widget">
				<c:if test="${!empty pageObjects['address']}">
					<h5>${pageObjects['address']['title']}</h5>
					<address>${pageObjects['address']['address']}</address>
					<p>
					<c:if test="${!empty pageObjects['address']['phone']}">
						<ec:icon icon="phone" size="1"/> ${pageObjects['address']['phone']}<br>
					</c:if>
					<c:if test="${!empty pageObjects['address']['email']}">
						<ec:icon icon="envelope" size="1"/> ${pageObjects['address']['email']}<br>
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
    
  