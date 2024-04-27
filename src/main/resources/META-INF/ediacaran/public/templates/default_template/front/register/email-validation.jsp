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
	<link rel="stylesheet" href="${plugins.ediacaran.front.web_path}/templates/default_template/front/register/css/register.css">
</head>

<body class="hold-transition register-page">
<ec:box>
	<ec:box-header>
    <a href="#"><fmt:message key="box.title" bundle="${sys_messages}"/></a>
    </ec:box-header>
    <ec:box-body>
	    <p id="msg_result" success="${empty exception}" class="box-msg">
	    	<c:if test="${empty exception}">
	    		<fmt:message key="box.success_msg" bundle="${sys_messages}"/>
	    	</c:if>
	    	<c:if test="${!empty exception}">
	    		${exception.message}
	    	</c:if>
    	</p>
    </ec:box-body>
</ec:box>