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

<body class="hold-transition register-page">
<ec:box>
	<ec:box-header>
    <a href="#"><fmt:message key="box.title" bundle="${sys_messages}"/></a>
    </ec:box-header>
    <ec:box-body>
	    <p class="box-msg"><fmt:message key="box.message" bundle="${sys_messages}"/></p>
		<ec:form id="registerForm" action="${plugins.ediacaran.front.user_register_page}" method="post"
		update="result">
			<input type="hidden" name="redirectTo" value="${plugins.ediacaran.user.admin_context}">
			<%--
			<ed:row>
				<ed:col classStyle="form-group has-feedback">
					<ec:field-group>
						<ec:textfield bundle="${sys_messages}" 
							name="firstName" placeholder="#{box.first_name.placeholder}">
							<ec:field-validator>
								<ec:field-validator-rule name="notEmpty" message="#{box.first_name.validation.notEmpty}" bundle="${sys_messages}" />
								<ec:field-validator-rule name="regexp" message="#{box.first_name.validation.regexp}" bundle="${sys_messages}">
									<ec:field-validator-param name="regexp" raw="true">$.AppContext.regexUtil.patterns().NAME_FORMAT</ec:field-validator-param>
								</ec:field-validator-rule>
								<ec:field-validator-rule name="stringLength" message="#{box.first_name.validation.stringLength}" bundle="${sys_messages}">
										<ec:field-validator-param name="min">3</ec:field-validator-param>
										<ec:field-validator-param name="max">60</ec:field-validator-param>
								</ec:field-validator-rule>
							</ec:field-validator>
						</ec:textfield>
						<ec:textfield bundle="${sys_messages}" 
							name="lastName" placeholder="#{box.last_name.placeholder}">
							<ec:field-validator>
								<ec:field-validator-rule name="notEmpty" message="#{box.last_name.validation.notEmpty}" bundle="${sys_messages}" />
								<ec:field-validator-rule name="regexp" message="#{box.last_name.validation.regexp}" bundle="${sys_messages}">
									<ec:field-validator-param name="regexp" raw="true">$.AppContext.regexUtil.patterns().NAME_FORMAT</ec:field-validator-param>
								</ec:field-validator-rule>
								<ec:field-validator-rule name="stringLength" message="#{box.last_name.validation.stringLength}" bundle="${sys_messages}">
										<ec:field-validator-param name="min">3</ec:field-validator-param>
										<ec:field-validator-param name="max">60</ec:field-validator-param>
								</ec:field-validator-rule>
							</ec:field-validator>
						</ec:textfield>
					</ec:field-group>
				</ed:col>
			</ed:row>
			--%>
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
			<%--
			<ed:row>
				<ed:col classStyle="form-group has-feedback">
					<ec:select name="country">
						<ec:field-validator>
							<ec:field-validator-rule name="notEmpty" message="#{box.country.validation.notEmpty}" bundle="${sys_messages}"/>
							<ec:field-validator-rule name="stringLength" message="invalid">
									<ec:field-validator-param name="min">3</ec:field-validator-param>
									<ec:field-validator-param name="max">3</ec:field-validator-param>
							</ec:field-validator-rule>
						</ec:field-validator>
						<ec:option value=""><fmt:message key="box.country" bundle="${sys_messages}"/></ec:option>
						<c:forEach items="${Controller.getCountries(locale)}" var="country">
							<ec:option value="${country.isoAlpha3}">${country.name}</ec:option>
						</c:forEach>
					</ec:select>
				</ed:col>
			</ed:row>
			--%>
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
				<ed:col size="8" classStyle="form-group has-feedback">
					<c:set var="terms" value="${plugins.ediacaran.user.terms}"/>
					<c:if test="${!empty terms && terms != ''}">
						<fmt:message var="terms_label" key="box.terms" bundle="${sys_messages}">
						 	<fmt:param value="${'#m'.concat(terms)}" />
						</fmt:message>
						<ec:checkbox label="${terms_label}" name="terms">
							<ec:field-validator>
								<ec:field-validator-rule name="notEmpty" message="Obrigatório"/>
							</ec:field-validator>
						</ec:checkbox>
					</c:if>
				</ed:col>
				<ed:col size="4">
					<ec:button bundle="${sys_messages}" actionType="submit" label="#{box.register_button}"/>
				</ed:col>
			</ed:row>
		
		</ec:form>
    </ec:box-body>
</ec:box>