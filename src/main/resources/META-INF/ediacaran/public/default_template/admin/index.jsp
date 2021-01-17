<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<ec:setTemplatePackage name="admin"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
		</ec:menu-itens>
	</ec:menu-bar>

	<aside class="sidebar">
	 sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar  
	 sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar  
	 sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar  
	 sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar  
	 sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar  
	 sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar sidebar  
	</aside>
	
	<section class="content">
		<ed:container>
		content content content content content content content content content content content content content content content content content content  
		content content content content content content content content content content content content content content content content content content  
		content content content content content content content content content content content content content content content content content content  
		content content content content content content content content content content content content content content content content content content  
		content content content content content content content content content content content content content content content content content content  
		content content content content content content content content content content content content content content content content content content  
		content content content content content content content content content content content content content content content content content content  
		</ed:container>	
	</section>

	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>