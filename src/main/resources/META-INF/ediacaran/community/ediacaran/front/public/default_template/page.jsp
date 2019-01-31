<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>${vars['title']}</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta name="description" content="${vars['description']}" />
  <meta name="author" content="${vars['author']}" />

  <!-- css -->
  <link href="https://fonts.googleapis.com/css?family=Noto+Serif:400,400italic,700|Open+Sans:300,400,600,700" rel="stylesheet">
  <link href="/plugins/community/ediacaran/front/default_template/css/bootstrap.css" rel="stylesheet" />
  <link href="/plugins/community/ediacaran/front/default_template/css/bootstrap-responsive.css" rel="stylesheet" />
  <link href="/plugins/community/ediacaran/front/default_template/css/fancybox/jquery.fancybox.css" rel="stylesheet">
  <link href="/plugins/community/ediacaran/front/default_template/css/jcarousel.css" rel="stylesheet" />
  <link href="/plugins/community/ediacaran/front/default_template/css/flexslider.css" rel="stylesheet" />
  <link href="/plugins/community/ediacaran/front/default_template/css/style.css" rel="stylesheet" />
  
  <!-- Theme skin -->
  <link href="/plugins/community/ediacaran/front/default_template/skins/default.css" rel="stylesheet" />
  
  <!-- boxed bg -->
  <link id="bodybg" href="/plugins/community/ediacaran/front/default_template/bodybg/bg1.css" rel="stylesheet" type="text/css" />
  
  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="default_template/ico/apple-touch-icon-144-precomposed.png" />
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="default_template/ico/apple-touch-icon-114-precomposed.png" />
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="default_template/ico/apple-touch-icon-72-precomposed.png" />
  <link rel="apple-touch-icon-precomposed" href="default_template/ico/apple-touch-icon-57-precomposed.png" />
  <link rel="shortcut icon" href="default_template/ico/favicon.png" />

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

    <section id="inner-headline">
      <div class="container">
        <div class="row">
          <div class="span4">
            <div class="inner-heading">
              <h2>${vars['title']}</h2>
            </div>
          </div>
          <div class="span8">
            <ul class="breadcrumb">
              <c:forEach items="${vars['breadcrumb']}" var="item">
              <li><a href="${item.link}"><c:if test="${!empty item.icon}"><i class="${item.icon}"></i></c:if>${item.text}</a><i class="icon-angle-right"></i></li>
              </c:forEach>
              <li class="active">${vars['title']}</li>
            </ul>
          </div>
        </div>
      </div>
    </section>
    
    <section id="content">
      <div class="container">
        <jsp:include page="/plugins/community/ediacaran/front/${plugins.front.template}/includes/page/${vars['body']['template']}.jsp"/>
      </div>
    </section>
    
        <footer>
      <div class="container">
        <div class="row">
	    <c:forEach items="${vars['footer']['itens']}" var="item" varStatus="_count">
          <div class="span3">
            <div class="widget">
    		<c:set var="item" value="${item}" scope="request"/>
			<jsp:include page="/plugins/community/ediacaran/front/${plugins.front.template}/includes/index/widget/footer/${item['template']}.jsp"/>
            </div>
          </div>
	    </c:forEach>
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
  <!-- javascript
    ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
  <script src="/plugins/community/ediacaran/front/default_template/js/jquery.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/jquery.easing.1.3.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/bootstrap.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/jcarousel/jquery.jcarousel.min.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/jquery.fancybox.pack.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/jquery.fancybox-media.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/google-code-prettify/prettify.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/portfolio/jquery.quicksand.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/portfolio/setting.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/jquery.flexslider.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/jquery.nivo.slider.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/modernizr.custom.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/jquery.ba-cond.min.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/jquery.slitslider.js"></script>
  <script src="/plugins/community/ediacaran/front/default_template/js/animate.js"></script>

  <!-- Template Custom JavaScript File -->
  <script src="/plugins/community/ediacaran/front/default_template/js/custom.js"></script>

</body>
</html>