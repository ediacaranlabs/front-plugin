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
			<ec:menu-item href="#">
				<ec:icon icon="user" size="1"/>
			</ec:menu-item>
		</ec:menu-itens>
	</ec:menu-bar>

	<aside class="sidebar">
    	<ec:menu-bar-brand>
    		Ediacaran
    	</ec:menu-bar-brand>
    	<div class="sidebar-body">
				<ec:menu-bar>
					<ec:menu-itens>
						<ec:menu-item href="#"><ec:icon icon="tachometer" size="1"/> Dashboard</ec:menu-item>
						<ec:menu>
							<ec:menu-label><ec:icon icon="copy" size="1"/> Menu 1</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 2</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 3</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 4</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 5</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 6</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 7</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 8</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 9</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 10</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 11</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 12</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 13</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
						<ec:menu>
							<ec:menu-label><ec:icon icon="table" size="1"/> Menu 14</ec:menu-label>
							<ec:menu-itens>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 1</ec:menu-item>
								<ec:menu-item href="#"><ec:icon icon="circle" size="1"/> item 2</ec:menu-item>
							</ec:menu-itens>
						</ec:menu>
					</ec:menu-itens>
				</ec:menu-bar>
		</div>
	</aside>
	
    <section class="content">
		<ed:container>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		</ed:container>    
    </section>

	<ec:include uri="/includes/footer.jsp"/>
 </body>
</html>