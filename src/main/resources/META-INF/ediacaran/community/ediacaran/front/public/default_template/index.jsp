<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>${vars['title']}</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta name="description" content="${vars['description']}" />
  <meta name="author" content="${vars['author']}" />

  <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/header.jsp"/>

  <!-- =======================================================
    Theme Name: Flattern
    Theme URL: https://bootstrapmade.com/flattern-multipurpose-bootstrap-template/
    Author: BootstrapMade.com
    Author URL: https://bootstrapmade.com
  ======================================================= -->
</head>

<body>
  <div id="wrapper">
  
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
    <!-- end header -->
        
    <c:set var="entity" value="${vars['header']}" scope="request"/>
    <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${entity['template']}.jsp"/>
        
    <section id="content">
      <div class="container">
	    <c:set var="entity" value="${vars['body']}" scope="request"/>
		<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${vars['body']['template']}.jsp"/>
      </div>
    </section>
    
    <section id="bottom">
      <div class="container">
        <div class="row">
          <div class="span12">
            <div class="aligncenter">
              <div id="twitter-wrapper">
                <div id="twitter">
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    <footer>
      <div class="container">
        <div class="row">
			<c:set var="entity" value="${vars['footer']}" scope="request"/>
		    <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${vars['footer']['template']}.jsp"/>
        </div>
      </div>
      
      <div id="sub-footer">
        <div class="container">
          <div class="row">
            <div class="span6">
              <div class="copyright">
                <p>
                  <span>${vars['copyright']}</span>
                </p>
                <div class="credits">
                  <!--
                    All the links in the footer should remain intact.
                    You can delete the links only if you purchased the pro version.
                    Licensing information: https://bootstrapmade.com/license/
                    Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Flattern
                  -->
                  Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
                </div>
              </div>
            </div>
            <div class="span6">
              <ul class="social-network">
			    <c:forEach items="${vars['socialNetwork']}" var="item">
			    <li><a href="${item['link']}" data-placement="bottom" title="${item['title']}"><i class="${item['icon']} icon-square"></i></a></li>
              	</c:forEach>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </footer>    
    
  </div>
  
  <a href="#" class="scrollup"><i class="icon-chevron-up icon-square icon-32 active"></i></a>
  
  <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/footer.jsp"/>
  
</body>
</html>
