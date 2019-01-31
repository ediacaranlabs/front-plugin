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
        <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/page/${vars['body']['template']}.jsp"/>
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