<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@page trimDirectiveWhitespaces="true" %>
<ec:setBundle var="sys_messages" locale="${locale}"/>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<ec:include uri="/includes/head.jsp"/>
<style type="text/css">
.box {
    width: 360px;
    margin: 12% auto;
}

.box-header {
    font-size: 35px;
    text-align: center;
    margin-bottom: 25px;
    font-weight: 300;
    border-bottom: 0px !important;
}

.box-header a {
    color: #444;
}

/*
.box-header {
    font-size: 35px;
    text-align: center;
    margin-bottom: 25px;
    font-weight: 300;
    border-bottom: 0px !important;
}

.box-header a {
    color: #444;
}

.box-footer{
	border-top: 0px !important;
}

.box-body{
	background-color: #fff;	
}

.box-body .box-msg {
    margin: 0;
    text-align: center;
    padding: 0 20px 20px 20px;
}
*/
</style>
</head>

<body>

	<ec:include uri="/includes/header.jsp"/>
	
	
    <section class="content">
		<ed:container>
			
						
			<ec:box>
				<%--
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
			    --%>
			    <ec:box-body>
				    <p class="box-msg"><fmt:message key="box.message" bundle="${sys_messages}"/></p>
					<ec:form id="registerForm" action="${plugins.ediacaran.front.user_register_page}" method="post"
					update="result">
						<input type="hidden" name="redirectTo" value="${param.redirectTo}">
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
						<ed:row style="form">
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
						<ed:row style="form">
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
						<ed:row style="form">
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
						<ed:row style="form">
							<ed:col size="12" id="result">
							</ed:col>
						</ed:row>
						<ed:row style="form">
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
					
					<c:if test="${!empty plugins.ediacaran.front.login_page}">
				    	<a href="${plugins.ediacaran.front.login_page}${empty param.redirectTo? '' : '?redirectTo='.concat(param.redirectTo)}" class="text-center">Fazer login</a>
					</c:if>
					
					<div id="success_result" style="display:none">
				    	<ed:row style="form">
				    		<ed:col size="12">
				    			<p class="box-msg">
				    				<fmt:message key="box.success_msg" bundle="${sys_messages}"/>
				    			</p>
					    		<ec:alert style="success"><fmt:message key="box.success_msg_alert" bundle="${sys_messages}"/></ec:alert>
				    		</ed:col>
				    	</ed:row>
				    	<ed:row style="form">
				    		<ed:col size="8"></ed:col>
				    		<ed:col size="4">
				    			<ec:button align="right" bundle="${sys_messages}" label="#{box.back_button}">
				    				<ec:event type="click">
				    					$.AppContext.openLink("${plugins.ediacaran.user.success_actions_uri}");
				    				</ec:event>
				    			</ec:button>
				   			</ed:col>
				    	</ed:row>
					</div>
			    </ec:box-body>
			</ec:box>
			
			
		</ed:container>    
    </section>
	 
	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>