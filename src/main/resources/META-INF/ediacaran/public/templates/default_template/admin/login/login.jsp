<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<ec:setTemplatePackage name="admin"/>
<ec:setBundle var="sys_messages" locale="${locale}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta property="og:title" content="<fmt:message key="header.title" bundle="${sys_messages}"/>">
	<meta name="description" property="og:description" content="<fmt:message key="header.description" bundle="${sys_messages}"/>">
	<meta name="keywords" content="<fmt:message key="header.keywords" bundle="${sys_messages}"/>">
	<meta name="author" content="<fmt:message key="header.author" bundle="${sys_messages}"/>">
	<meta name="robots" content="noimageindex" />

    <title><fmt:message key="title" bundle="${sys_messages}"/></title>

	<ec:include uri="/includes/head.jsp"/>
	
	<link rel="stylesheet" href="/plugins/ediacaran/front/templates/default_template/admin/login/css/login.css">

</head>

<body class="hold-transition login-page">
<ec:box>
	<ec:box-header>
    <a href="#"><fmt:message key="box.title" bundle="${sys_messages}"/></a>
    </ec:box-header>
    <ec:box-body>
	    <p class="box-msg"><fmt:message key="box.message" bundle="${sys_messages}"/></p>
<%--	<ec:form action="${plugins.ediacaran.front.admin_login_page}" method="POST" update="#result" acceptCharset="UTF-8"> --%>
	<ec:form action="${plugins.ediacaran.front.admin_login_page}" method="POST" update="#result" acceptCharset="UTF-8">
	      <input type="hidden" name="redirectTo" value="${plugins.ediacaran.front.landing_page}">
	      <ed:row>
      		<ed:col>
		      <ec:textfield name="username" placeholder="Email"/>
      		</ed:col>
	      </ed:row>
	      <ed:row>
      		<ed:col>
		      <ec:passwordfield name="password" placeholder="Password"/>
      		</ed:col>
	      </ed:row>
	      <ed:row style="form">
	      	<ed:col>
				<div id="result" class="result-check"></div>
	      	</ed:col>
	      </ed:row>
	      <ed:row style="form">
      		<ed:col size="8">
      		</ed:col>
      		<ed:col size="4">
      			<fmt:message key="box.sign_in_button" bundle="${sys_messages}" var="label"/>
      			<ec:button block="true" label="${label}" style="primary" actionType="submit"/>
      		</ed:col>
	      </ed:row>
		</ec:form>
		
	    <a href="${plugins.ediacaran.front.user_forgot_page}"><fmt:message key="box.forgot_pass" bundle="${sys_messages}"/></a><br>
	    <a href="${plugins.ediacaran.front.user_register_page}" class="text-center"><fmt:message key="box.register" bundle="${sys_messages}"/></a>
		
    </ec:box-body>
</ec:box>

<script type="text/javascript" src="/plugins/ediacaran/front/default_template/admin/login/js/language/${locale}/login.js"></script>
<script type="text/javascript" src="/plugins/ediacaran/front/default_template/admin/login/js/login.js"></script>

</body>
</html>
