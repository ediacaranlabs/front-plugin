<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                    prefix="fmt"%> 
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"              prefix="fn"%>
<%@page trimDirectiveWhitespaces="true" %>
<ec:setBundle var="messages" locale="${locale}"/>
<ec:import-object var="adminmenubar" id="menubar/admin/adminmenubar"/>
<ec:import-object var="admintopmenubar" id="menubar/admin/admintopmenubar"/>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<ec:include uri="/includes/head.jsp"/>
<style type="text/css">
#content-body{
	padding: 10px 10px;
	min-height: 600px;
}

.navbar-dark.navbar {
	padding: 0px 0px;
} 

.navbar-dark .navbar-nav{
	margin: 0px 0px;
}

.navbar-dark .navbar-nav .nav-link{
	margin: 0px 0px;
}

/* ===================================
Inner - Section: page headline
==================================== */


#content-body .inner-headline {
  background: transparent;
  position: relative;
  /*padding: 15px .5rem;*/  
  margin: 0;
  color: #212529;
}

#content-body .inner-headline .row, .row-fluid {
	padding-bottom: 10px;
}


#content-body .inner-headline .inner-heading h2 {
  color: #212529;
  margin: 20px 0 0 0;
}

/* --- breadcrumbs --- */

#content-body .breadcrumb > li {
  display: inline-block;
  *display: inline;
  text-shadow: none;
  font-size: 12px;
  *zoom: 1;
  margin:0;
}

#content-body .breadcrumb > .active {
  color: #6c757d;;
}

#content-body .inner-headline ul.breadcrumb {
	padding: 0 0;
	margin: 0;
	list-style: none;
	background: none;
	-webkit-border-radius: 0;
	-moz-border-radius: 0;
	border-radius: 0;
}

#content-body .inner-headline ul.breadcrumb li {
  margin-bottom: 0;
  padding-bottom: 0;
}

#content-body .inner-headline ul.breadcrumb li {
  font-size: 16px;
  color: #988f8f;
}

#content-body .inner-headline ul.breadcrumb li i {
  color: #dedede;
}

#content-body .inner-headline ul.breadcrumb li a {
  color: #212529;
}

#content-body ul.breadcrumb li a:hover {
  text-decoration: none;
}

#content-body .inner-headline ul.breadcrumb li [class^="fa"] {
    display: inline-block;
    width: 1.25em;
    text-align: center;
    color: #212529;
}

@media screen and (min-width: 1200px){

	#content-body .inner-headline ul.breadcrumb {
	  	padding:10px 0 10px 0;
		margin: 20px 0 0;
		float: right;
	}
	
}

@media screen and (min-width: 992px){
	
	#content-body .inner-headline ul.breadcrumb {
	  	padding:10px 0 10px 0;
		margin: 20px 0 0;
		float: right;
	}
	
}

@media screen and (min-width: 768px){

	#content-body .inner-headline ul.breadcrumb {
		/*padding: 0 0;*/
	}
	
}

/* panel nav */

.navbar-panel .dropdown-menu {
	border: 0px;
	font-size: unset;
	padding: 0 0;
}

.navbar-panel .dropdown-item {
	padding: 0.5rem 0.5rem;	
    background: #252525;	
} 

.navbar-panel .nav-item:hover{
	color: #ffffff;
}

.navbar-panel .dropdown-item:hover{
	color: rgba(0, 0, 0, 0.9);
}

.navbar-panel .nav-link i:first-child {
    margin-right: 5px;
}

.navbar-panel .dropdown-item  i:first-child {
    margin-right: 5px;
}

.navbar-panel .navbar-nav .nav-link{
	text-transform: uppercase;
}

.navbar-panel .nav-item a{
	color: #656565;
	text-transform: uppercase;
}

.navbar-panel .nav-item a:hover{
	color: #ffffff;
}

.navbar-panel .dropdown-menu {
	background: #222;
	color: #ddd;
	border-radius: 0;
	border: 0px;
	font-size: inherit;
}

.navbar-panel .dropdown-item:hover{
	background: #F03C02;
    color: #919191;
}
 
@media (min-width: 1200px){
	.navbar-panel.navbar{
		padding: 0 0;
	}

	.navbar-panel .navbar-nav{
		margin: 0px 0px;
	}
	
	.navbar-panel.nav-item a{
		padding: 10px 15px 10px 15px;
		color: #656565;
		text-transform: uppercase;
	}
	
	.navbar-panel .nav-item a:hover{
		color: #ffffff;
	}
	
	.navbar-panel .dropdown-menu {
		padding: 0px 0px;
		background: #222;
		color: #ddd;
		border-radius: 0;
		margin: 0 0;
		border: 0px;
		font-size: inherit;
	}
	
	.navbar-panel .dropdown-item:hover{
		background: #F03C02;
	    color: #919191;
	}
}

</style>
</head>

<body>

	<ec:include uri="/includes/header.jsp"/>
	
	<section class="inner-headline">
		<ed:container>
			<ed:row>
				<ed:col size="3">
					<div class="inner-heading">
						<h2><fmt:message key="header.title" bundle="${messages}"/></h2>
					</div>
				</ed:col>
				<ed:col size="9">
					<ec:breadcrumb title="#{header.title}" bundle="${messages}">
						<ec:breadcrumb-path 
							icon="home" 
							text="" 
							lnk="#" />
					</ec:breadcrumb>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col>
					<ec:menu-bar 
						id="panel_menu" 
						expand="xl" 
						style="panel">
						<ec:menu-toggler menuID="panel_menu_body">
							<ec:icon icon="bars" />
						</ec:menu-toggler>
						<ec:menu-body collapse="true">
							<ec:menu-itens>
								<c:forEach items="${adminmenubar.itens}" var="menu">
									<c:choose>
				
										<c:when test="${not empty menu.body}">
											<ec:menu>
												<ec:menu-label>
													<c:if test="${not empty menu.icon}">
														<ec:icon icon="${menu.icon}" size="1"/>
													</c:if>
													${menu.fullName}
													<c:if test="${not empty menu.badge}">
														<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
													</c:if>
												</ec:menu-label>
												<ec:menu-itens resource="${menu.body}"/>
											</ec:menu>
										</c:when>
				
										<c:when test="${fn:length(menu.itens) != 0}">
											<ec:menu>
												<ec:menu-label>
													<c:if test="${not empty menu.icon}">
														<ec:icon icon="${menu.icon}" size="1"/>
													</c:if>
													${menu.fullName}
													<c:if test="${not empty menu.badge}">
														<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
													</c:if>
												</ec:menu-label>
												<ec:menu-itens id="menu_itens_xx">
													<c:forEach items="${menu.itens}" var="item">
														<ec:menu-item href="${item.resource}">
															<c:if test="${not empty item.icon}">
																<ec:icon icon="${item.icon}" size="1"/>
															</c:if>
															${item.fullName}
															<c:if test="${not empty item.badge}">
																<ec:badge id="${item.id}_badge" style="${empty item.badgeStyle? 'info' : item.badgeStyle}" type="navbar">${item.badge}</ec:badge>
															</c:if>
														</ec:menu-item>
													</c:forEach>
												</ec:menu-itens>
											</ec:menu>
										</c:when>
				
										<c:otherwise>
											<ec:menu-item href="${menu.resource}">
												<c:if test="${not empty menu.icon}">
													<ec:icon icon="${menu.icon}" size="1"/>
												</c:if>
												${menu.fullName}
												<c:if test="${not empty menu.badge}">
													<ec:badge id="${menu.id}_badge" style="${empty menu.badgeStyle? 'info' : menu.badgeStyle}" type="navbar">${menu.badge}</ec:badge>
												</c:if>
											</ec:menu-item>
										</c:otherwise>
				
									</c:choose>
								</c:forEach>
							</ec:menu-itens>
						</ec:menu-body>
					</ec:menu-bar>		
				</ed:col>
			</ed:row>
		</ed:container>
	</section>
	
	<ed:container>
		<div id="content-body"></div>
	</ed:container>

	<ec:include uri="/includes/footer.jsp"/>
	
	<script type="text/javascript">
		$.AppContext.onload(function(){			
			$.AppContext.utils.loadResourceContent($.AppContext.vars.painel, '/plugins/ediacaran/front${plugins.ediacaran.front.admin_context}/dashboard')
		});
	</script>
 
</body>
</html>