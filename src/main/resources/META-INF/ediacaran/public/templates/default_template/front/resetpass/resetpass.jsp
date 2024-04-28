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
	
	<link rel="stylesheet" href="${plugins.ediacaran.front.web_path}/templates/default_template/front/resetpass/css/resetpass.css">
	
</head>

<body class="hold-transition resetpass-page">
<ec:box>
	<ec:box-header>
    <a href="#"><fmt:message key="box.title" bundle="${sys_messages}"/></a>
    </ec:box-header>
    <ec:box-body>
	    <p class="box-msg"><fmt:message key="box.message" bundle="${sys_messages}"/></p>
		<ec:form id="registerForm" action="${plugins.ediacaran.front.user_resetpass_page}" method="post"
		update="result">
			<input type="hidden" name="requestID" value="${requestID}">
			<input type="hidden" name="gen" value="${gen}">
			<ed:row>
				<ed:col classStyle="form-group has-feedback">
					<ec:passwordfield name="password" placeholder="#{box.password.placeholder}" bundle="${sys_messages}">
						<ec:field-validator>
							<ec:field-validator-rule name="notEmpty" message="#{box.password.validation.notEmpty}" bundle="${sys_messages}"/>
							<ec:field-validator-rule name="stringLength" message="#{box.password.validation.stringLength}" bundle="${sys_messages}">
									<ec:field-validator-param name="min">12</ec:field-validator-param>
									<ec:field-validator-param name="max">128</ec:field-validator-param>
							</ec:field-validator-rule>
						</ec:field-validator>
					</ec:passwordfield>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col classStyle="form-group has-feedback">
					<ec:passwordfield name="retypePassword" placeholder="#{box.retype_password.placeholder}" bundle="${sys_messages}">
						<ec:field-validator>
							<ec:field-validator-rule name="notEmpty" message="#{box.retype_password.validation.notEmpty}" bundle="${sys_messages}"/>
							<ec:field-validator-rule name="identical" message="#{box.retype_password.validation.identical}" bundle="${sys_messages}">
								<ec:field-validator-param name="field">password</ec:field-validator-param>
							</ec:field-validator-rule>
						</ec:field-validator>
					</ec:passwordfield>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col id="result" size="8" classStyle="form-group has-feedback">
				</ed:col>
				<ed:col size="4">
					<ec:button bundle="${sys_messages}" actionType="submit" label="#{box.reset_button}"/>
				</ed:col>
			</ed:row>
		
		</ec:form>
    </ec:box-body>
</ec:box>