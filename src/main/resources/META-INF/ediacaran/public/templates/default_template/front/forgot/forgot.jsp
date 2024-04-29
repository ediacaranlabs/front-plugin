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
	
	<link rel="stylesheet" href="${plugins.ediacaran.front.web_path}/templates/default_template/front/forgot/css/forgot.css">

	<style type="text/css">
		.has-feedback .form-control-feedback {
		    position: absolute;
		    top: 0;
		    right: 0;
		    z-index: 2;
		    display: block;
		    width: 34px;
		    height: 34px;
		    line-height: 34px;
		    text-align: center;
		    pointer-events: none;
		}
		
		.input-group{
			display: flex;
		}
		
		.input-group>.form-control {
		    position: relative;
			flex-grow: 1;
		    margin-bottom: 0;
	    }
	    
		.icheck {
			padding-left: 0px
		}
		
	</style>

</head>

<body class="forgot-page">
	<ec:box>
		<ec:box-header>
			<c:if test="${empty plugins.ediacaran.front.image_logo}">
				 <a href="#">${plugins.ediacaran.front.text_logo}</a>
			</c:if>
			<c:if test="${!empty plugins.ediacaran.front.image_logo}">
				<ec:image src="${plugins.ediacaran.front.image_logo}"/>
			</c:if>
	    </ec:box-header>
	    <ec:box-body>
		    <p class="box-msg"><fmt:message key="box.message" bundle="${sys_messages}"/></p>
			<ec:form id="forgotForm" action="${plugins.ediacaran.front.user_register_page}" method="post"
			update="result">
				<ed:row>
					<ed:col classStyle="form-group has-feedback">
						<ec:textfield bundle="${sys_messages}" 
								name="email" placeholder="#{box.email.placeholder}">
							<ec:field-validator>
								<ec:field-validator-rule name="notEmpty" message="#{box.email.validation.notEmpty}" bundle="${sys_messages}"/>
								<ec:field-validator-rule name="regexp" message="#{box.email.validation.regexp}" bundle="${sys_messages}">
									<ec:field-validator-param name="regexp" raw="true">$.AppContext.regexUtil.patterns().EMAIL</ec:field-validator-param>
								</ec:field-validator-rule>
							</ec:field-validator>
						</ec:textfield>
					</ed:col>
				</ed:row>
				<ed:row>
					<ed:col size="12" id="result">
					</ed:col>
				</ed:row>
				<ed:row>
					<ed:col size="8">
					</ed:col>
					<ed:col size="4">
						<ec:button bundle="${sys_messages}" actionType="submit" label="#{box.reset_button}"/>
					</ed:col>
				</ed:row>
			
			</ec:form>
	    </ec:box-body>
	</ec:box>


</body>
</html>
