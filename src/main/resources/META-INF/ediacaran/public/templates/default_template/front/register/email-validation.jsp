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
    <a href="#">
		<c:if test="${empty plugins.ediacaran.front.image_logo}">
			 <a href="#">${plugins.ediacaran.front.text_logo}</a>
		</c:if>
		<c:if test="${!empty plugins.ediacaran.front.image_logo}">
			<ec:image src="${plugins.ediacaran.front.image_logo}"/>
		</c:if>
    </a>
    </ec:box-header>
    <ec:box-body>
    	<ed:row>
    		<ed:col size="12">
			    <p class="box-msg"><fmt:message key="box.message" bundle="${sys_messages}"/></p>
			    <p id="msg_result" class="box-msg">
			    	<div style="display:none">>@web-result=${empty exception}</div>
			    	<c:if test="${empty exception}">
			    		<ec:alert style="success"><fmt:message key="box.success_msg" bundle="${sys_messages}"/></ec:alert>
			    	</c:if>
			    	<c:if test="${!empty exception}">
			    		<ec:alert style="danger">${exception.message}</ec:alert>
			    	</c:if>
		    	</p>
    		</ed:col>
    	</ed:row>
    	<ed:row>
    		<ed:col size="8"></ed:col>
    		<ed:col size="4">
    			<ec:button align="right" bundle="${sys_messages}" label="#{box.back_button}">
    				<ec:event type="click">
    					$.AppContext.openLink("${plugins.ediacaran.user.success_actions_uri}");
    				</ec:event>
    			</ec:button>
   			</ed:col>
    	</ed:row>
    </ec:box-body>
</ec:box>