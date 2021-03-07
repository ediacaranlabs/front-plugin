<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<ec:setTemplatePackage name="admin"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<ec:include uri="/includes/head.jsp"/>
</head>

<body id="body" class="collapse show">
	<ec:menu-bar id="top_menu" classStyle="top-menu" expand="xl" style="light">
		<ec:menu-toggler menuID="body">
			<ec:icon icon="bars" size="1" />
		</ec:menu-toggler>
		<ec:menu-itens align="right">
			<ec:menu-item href="#">
				<ec:icon icon="comments" size="1"/>
				<span class="badge badge-danger navbar-badge">3</span>
			</ec:menu-item>
			<ec:menu-item href="#">
				<ec:icon icon="bell" size="1"/>
				<span class="badge badge-warning navbar-badge">15</span>
			</ec:menu-item>
			<ec:menu id="user_menu_top">
				<ec:menu-label><ec:icon icon="user" size="1"/></ec:menu-label>
				<ec:menu-itens resource="${plugins.ediacaran.front.admin_user_menu}" menuAlign="right">
				</ec:menu-itens>
			</ec:menu>
<%-- 			
			<ec:menu-item href="#">
				<ec:icon icon="user" size="1"/>
			</ec:menu-item>
--%>			
		</ec:menu-itens>
	</ec:menu-bar>

	<aside class="sidebar">
    	<ec:menu-bar-brand>
    		<ec:image src="/plugins/ediacaran/front/admin/img/Logo.png"/> 
    		<span>Ediacaran</span>
    	</ec:menu-bar-brand>
    	<div class="sidebar-body">
				<ec:menu-bar>
					<ec:menu-itens>
						<ec:menu-item href="#"><ec:icon icon="tachometer" size="1"/> <p>Dashboard</p></ec:menu-item>
						<ec:menu-item href="#!/plugins/ediacaran/front/admin/components.jsp"><ec:icon icon="tree" size="1"/> <p>Components</p></ec:menu-item>
						<ec:menu-item href="#!/plugins/ediacaran/front/admin/form.jsp"><ec:icon icon="edit" size="1"/> <p>Forms</p></ec:menu-item>
						<ec:menu-item href="#!/plugins/ediacaran/front/admin/typography.jsp"><ec:icon icon="pencil" size="1"/> <p>Typography</p></ec:menu-item>
						<ec:menu-item href="#!/plugins/ediacaran/front/admin/table.jsp"><ec:icon icon="table" size="1"/> <p>Tables</p></ec:menu-item>
						<ec:menu-item href="#!/plugins/ediacaran/front/admin/pricingbox.jsp"><ec:icon icon="money" size="1"/> <p>Pricing boxes</p></ec:menu-item>
						<ec:menu-item href="#!/plugins/ediacaran/front/admin/flotcharts.jsp"><ec:icon icon="pie-chart" size="1"/> <p>Flot Charts</p></ec:menu-item>
					</ec:menu-itens>
				</ec:menu-bar>
		</div>
	</aside>
	
    <section id="content-body" class="content">
    </section>

	<ec:include uri="/includes/footer.jsp"/>
 </body>
</html>