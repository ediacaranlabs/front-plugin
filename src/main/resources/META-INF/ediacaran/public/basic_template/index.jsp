<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="vars" value="${requestScope.vars}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>${vars['title']}</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta name="description" content="${vars['description']}" />
  <meta name="author" content="${vars['author']}" />

  <c:import context="/plugins/community/ediacaran/front" url="/basic_template/includes/head.jsp"/>

</head>

<body>
    <!-- start header -->
    <header>
      <div class="container ">
        <div class="row nomargin">
          <div class="col-12">
            <div class="headnav">
              <ul>
                <li><a href="${vars['signup']['link']}" data-toggle="modal">${vars['signup']['text']}</a></li>
                <li><a href="${vars['signin']['link']}" data-toggle="modal">${vars['signin']['text']}</a></li>
              </ul>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-4">
            <div class="logo">
              <a href="${vars['logo']['link']}"><img src="${vars['logo']['image']}" alt="" class="logo" /></a>
              <h1>${vars['logo']['text']}</h1>
            </div>
          </div>
          <div class="col-8">
            <div class="navbar navbar-static-top">
              <div class="navigation">
              
                <nav class="navbar navbar-expand-lg navbar-light">
                  <ul  class="navbar-nav mr-auto">

					<c:forEach items="${vars['menu']}" var="menu">
						<c:choose>
							<c:when test="${empty menu['link']}">
								<li class="nav-item dropdown">
									<a href="#" class="nav-link dropdown-toggle"  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${menu['name']}</a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown">
										<c:forEach items="${menu['itens']}" var="item">
											<a class="dropdown-item" href="${item['link']}">${item['name']}</a>
										</c:forEach>
									</div>
								</li>
							</c:when>
							<c:otherwise>
								<li class="nav-item"><a href="${menu['link']}" class="nav-link">${menu['name']}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
                  
					<c:forEach items="${menuBar.itens}" var="menu">
						<c:choose>
							<c:when test="${empty menu.resource}">
								<li class="nav-item dropdown">
									<a href="#" class="nav-link dropdown-toggle"  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${menu.fullName}</a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown">
										<c:forEach items="${menu.itens}" var="item">
											<a class="dropdown-item" href="${item.resource}">${item.fullName}</a>
										</c:forEach>
									</div>
								</li>
							</c:when>
							<c:otherwise>
								<li class="nav-item"><a class="dropdown-item" href="${menu.resource}">${menu.fullName}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
                  </ul>
                </nav>
              </div>
              <!-- end navigation -->
            </div>
          </div>
        </div>
      </div>
    </header>
    <!-- end header -->
    
    <c:set var="entity" value="${vars['header']}" scope="request"/>
    <c:import context="/plugins/community/ediacaran/front" url="/basic_template/includes/parts/${entity['template']}.jsp"/>
        
    <section id="content">
      <div class="container">
	    <c:set var="entity" value="${vars['body']}" scope="request"/>
		<c:import context="/plugins/community/ediacaran/front" url="/basic_template/includes/parts/${vars['body']['template']}.jsp"/>
      </div>
    </section>

    <footer>
      <div class="container">
        <div class="row">
			<c:set var="entity" value="${vars['footer']}" scope="request"/>
		    <c:import context="/plugins/community/ediacaran/front" url="/basic_template/includes/parts/${vars['footer']['template']}.jsp"/>
        </div>
      </div>
      
      <div id="sub-footer">
        <div class="container">
          <div class="row">
            <div class="col-lg-6">
              <div class="copyright">
                <p>
                  <span>${vars['copyright']}</span>
                </p>
              </div>
            </div>
            <div class="col-lg-6">
              <ul class="social-network">
			    <c:forEach items="${vars['socialNetwork']}" var="item">
			    <li><a href="${item['link']}" data-placement="bottom" title="${item['title']}">
					<span class="fa-stack fa-2x">
				    	<i class="fa ${item['icon']} fa-stack-1x fa-inverse"></i>
				    </span>			    
              	</c:forEach>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </footer>

  <a href="#" class="scrollup">
	<span class="fa-stack fa-1x">
  		<i class="fa fa-square fa-stack-2x active"></i>
    	<i class="fa fa-chevron-up fa-stack-1x fa-inverse"></i>
    </span>			    
  </a>
  
  <c:import context="/plugins/community/ediacaran/front" url="/basic_template/includes/footer.jsp"/>
    
</body>
</html>
