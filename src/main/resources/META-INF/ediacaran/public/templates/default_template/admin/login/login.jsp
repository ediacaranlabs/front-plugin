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
			    <a href="#"><fmt:message key="box.title" bundle="${sys_messages}"/></a>
			    </ec:box-header>
			    --%>
			    <ec:box-body>
				    <p class="box-msg"><fmt:message key="box.message" bundle="${sys_messages}"/></p>
				<ec:form id="authForm" action="${plugins.ediacaran.front.login_page}" method="POST" update="result" acceptCharset="UTF-8">
				      <input type="hidden" name="redirectTo" value="${param.redirectTo}">
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
					
					<c:if test="${!empty plugins.ediacaran.front.user_forgot_page}">
				    <a href="${plugins.ediacaran.front.user_forgot_page}"><fmt:message key="box.forgot_pass" bundle="${sys_messages}"/></a><br>
					</c:if>
					<c:if test="${!empty plugins.ediacaran.front.user_register_page}">
				    <a href="${plugins.ediacaran.front.user_register_page}${empty param.redirectTo? '' : '?redirectTo='.concat(param.redirectTo)}" class="text-center"><fmt:message key="box.register" bundle="${sys_messages}"/></a>
					</c:if>
					
			    </ec:box-body>
			</ec:box>
						
			
			
		</ed:container>    
    </section>
	 
	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>