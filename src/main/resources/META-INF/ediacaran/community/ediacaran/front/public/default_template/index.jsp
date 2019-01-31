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
    <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/body-header.jsp"/>
    
    <!-- end header -->
    <c:if test="${!empty vars['slider']}">
    <c:set var="item" value="${vars['slider']}" scope="request"/>
    <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/index/slider/${vars['slider']['template']}.jsp"/>
    </c:if>

    <c:if test="${!empty vars['cta']}">
    <c:set var="item" value="${vars['cta']}" scope="request"/>
	<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/index/cta/${vars['cta']['template']}.jsp"/>
    </c:if>
    
    <section id="content">
      <div class="container">
	    <c:set var="item" value="${vars['body']}" scope="request"/>
		<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/index/${vars['body']['template']}.jsp"/>
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
      <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/body-footer.jsp"/>
    </footer>
    
  </div>
  <a href="#" class="scrollup"><i class="icon-chevron-up icon-square icon-32 active"></i></a>
  
  <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/footer.jsp"/>

</body>
</html>
