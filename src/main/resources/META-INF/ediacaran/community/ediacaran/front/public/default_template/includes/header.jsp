<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components.tld" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer.tld"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"                                  prefix="c"%>
    <!-- start header -->
    <header>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white">
      <a class="my-0 mr-md-auto font-weight-normal" href="${vars['logo']['link']}"><img src="${vars['logo']['image']}" alt="" class="logo" /></a>
      			<ec:menu-bar>
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
    </div>
    </header>
    <!-- end header -->