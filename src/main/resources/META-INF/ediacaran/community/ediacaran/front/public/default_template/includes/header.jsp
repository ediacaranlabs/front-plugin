<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components.tld" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer.tld"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"                                  prefix="c"%>
    <!-- start header -->
   <header>
   
<%--   
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#">Start Bootstrap</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="#">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Services</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Contact</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>   
  --%>
    
	<ec:menu-bar>
		<ed:container>
		<ec:menu-bar-brand><img src="${vars['logo']['image']}" alt="" class="logo" /></ec:menu-bar-brand>
		<ec:menu-toggler>
			<ec:icon icon="bars" />
		</ec:menu-toggler>
		<ec:menu-body>
			<ec:menu-itens align="right">
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
		</ed:container>
	</ec:menu-bar>
 
</header>
    <!-- end header -->