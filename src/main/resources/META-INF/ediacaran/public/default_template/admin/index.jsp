<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"                              prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@page trimDirectiveWhitespaces="true" %>
<ec:setTemplatePackage name="adm"/>
<ec:setBundle basename="ediacaran/adm/default_template/admin/index" var="sys_messages" locale="${locale}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><fmt:message key="root.title" bundle="${sys_messages}" /></title>

  <ec:include uri="/includes/header.jsp" />
    
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

	<ec:menu-bar expand="sm" background="white" style="light">
		<ec:menu-toggler />
		<ec:menu-body>
			<ec:menu-itens>
				<ec:menu-item href="${plugins.ediacaran.front.admin_logout_page}">
					<fmt:message key="root.top_menu.sign_out" bundle="${sys_messages}" />
				</ec:menu-item>
			</ec:menu-itens>
		</ec:menu-body>
	</ec:menu-bar>

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
  
    <!-- Brand Logo -->
    <a href="index3.html" class="brand-link">
      <img src="dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">Ediacaran</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
		<ec:menu-bar>
			<ec:menu-body>
				<ec:menu-itens align="right">
					<c:forEach items="${Controller.menuBar.itens}" var="menu">
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
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <strong>Copyright &copy; 2014-2020 <a href="https://adminlte.io">AdminLTE.io</a>.</strong>
    All rights reserved.
    <div class="float-right d-none d-sm-inline-block">
      <b>Version</b> 3.1.0-rc
    </div>
  </footer>

</div>
<!-- ./wrapper -->

  <ec:include uri="/includes/footer.jsp" />

</body>
</html>
