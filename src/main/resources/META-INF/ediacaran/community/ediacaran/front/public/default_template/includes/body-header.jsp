<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <c:if test="${!empty vars['top']}">
    <!-- toggle top area -->
    <div class="hidden-top">
      <div class="hidden-top-inner container">
        <div class="row">
          <div class="span12">
          	${vars['top']['content']}
          </div>
        </div>
      </div>
    </div>
    <!-- end toggle top area -->
    </c:if>
    <!-- start header -->
    <header>
      <div class="container ">
    	<c:if test="${!empty vars['top']}">
        <!-- hidden top area toggle link -->
        <div id="header-hidden-link">
          <a href="#" class="toggle-link" title="${vars['top']['linkTitle']}" data-target=".hidden-top"><i></i>${vars['top']['linkText']}</a>
        </div>
        <!-- end toggle link -->
	    </c:if>
        <div class="row nomargin">
          <div class="span12">
            <div class="headnav">
              <ul>
                <li><a href="${vars['signup']['link']}" data-toggle="modal">${vars['signup']['text']}</a></li>
                <li><a href="${vars['signin']['link']}" data-toggle="modal">${vars['signin']['text']}</a></li>
              </ul>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="span4">
            <div class="logo">
              <a href="${vars['logo']['link']}"><img src="${vars['logo']['image']}" alt="" class="logo" /></a>
              <h1>${vars['logo']['text']}</h1>
            </div>
          </div>
          <div class="span8">
            <div class="navbar navbar-static-top">
              <div class="navigation">
                <nav>
                  <ul class="nav topnav">
                  
					<c:forEach items="${vars['menu']}" var="menu">
						<c:choose>
							<c:when test="${empty menu['link']}">
								<li class="dropdown">
									<a href="#">${menu['name']} <i class="icon-angle-down"></i></a>
									<ul class="dropdown-menu">
										<c:forEach items="${menu['itens']}" var="item">
											<li><a href="${item['link']}">${item['name']}</a></li>
										</c:forEach>
									</ul>
								</li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}${menu['link']}">${menu['name']}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
                  
					<c:forEach items="${menuBar.itens}" var="menu">
						<c:choose>
							<c:when test="${empty menu.resource}">
								<li class="dropdown">
									<a href="#">${menu.fullName} <i class="icon-angle-down"></i></a>
									<ul class="dropdown-menu">
										<c:forEach items="${menu.itens}" var="item">
											<li><a href="${item.resource}">${item.fullName}</a></li>
										</c:forEach>
									</ul>
								</li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}${menu.resource}">${menu.fullName}</a></li>
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