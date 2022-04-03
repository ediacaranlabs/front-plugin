<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>

<ec:load-data file="footer.json" var="objects" />
<footer>
	<ed:container>
		<ed:row>
			<ed:col>
				<c:if test="${!empty objects['social-links']}">
					<ec:list style="inline">
						<c:forEach items="${objects['social-links']}" var="social_link">
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
				<c:if test="${!empty objects['frontFooterMenu2']}">
				
					<ec:menu-bar id="${objects['frontFooterMenu2']['id']}" 
							classStyle="${objects['frontFooterMenu2']['classStyle']}" 
							expand="${objects['frontFooterMenu2']['expand']}" 
							style="${objects['frontFooterMenu2']['style']}">
						<ed:container>
						<ec:menu-bar-brand>
							<h5>${objects['frontFooterMenu2']['title']}</h5>
						</ec:menu-bar-brand>
						<ec:menu-body collapse="false">
							<ec:menu-itens>
								<c:forEach items="${objects['frontFooterMenu2']['itens']}" var="menu">
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
				<c:if test="${!empty objects['frontFooterMenu1']}">
				
					<ec:menu-bar id="${objects['frontFooterMenu1']['id']}" 
							classStyle="${objects['frontFooterMenu1']['classStyle']}" 
							expand="${objects['frontFooterMenu1']['expand']}" 
							style="${objects['frontFooterMenu1']['style']}">
						<ed:container>
						<ec:menu-bar-brand>
							<h5>${objects['frontFooterMenu1']['title']}</h5>
						</ec:menu-bar-brand>
						<ec:menu-body collapse="false">
							<ec:menu-itens>
								<c:forEach items="${objects['frontFooterMenu1']['itens']}" var="menu">
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
				<c:if test="${!empty objects['address']}">
					<h5>${objects['address']['title']}</h5>
					<address>${objects['address']['address']}</address>
					<p>
					<c:if test="${!empty objects['address']['phone']}">
						<ec:icon icon="phone" size="1"/> ${objects['address']['phone']}<br>
					</c:if>
					<c:if test="${!empty objects['address']['email']}">
						<ec:icon icon="envelope" size="1"/> ${objects['address']['email']}<br>
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
    
  