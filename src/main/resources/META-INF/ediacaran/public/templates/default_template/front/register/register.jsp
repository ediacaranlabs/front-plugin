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
<div class="register-box">
  <div class="register-logo">
    <a href="#"><fmt:message key="box.title" bundle="${sys_messages}"/></a>
  </div>
  <!-- /.login-logo -->
  <div class="register-box-body">
    <p class="login-box-msg"><fmt:message key="box.message" bundle="${sys_messages}"/></p>

	<form id="registerForm" action="${plugins.ediacaran.front.user_register_page}" method="post"
		dest-content="#result" accept-charset="UTF-8">
		<input type="hidden" name="redirectTo" value="${plugins.ediacaran.user.admin_context}">
		<div class="input-group">
		<div class="form-group has-feedback">
			<input name="firstName" type="text" class="form-control" placeholder="<fmt:message key="box.first_name.placeholder" bundle="${sys_messages}"/>">
		</div>
		<div class="form-group has-feedback">
			<input name="lastName" type="text" class="form-control" placeholder="<fmt:message key="box.last_name.placeholder" bundle="${sys_messages}"/>">
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		</div>
		</div>
		<div class="form-group has-feedback">
			<input name="email" type="email" class="form-control" placeholder="<fmt:message key="box.email.placeholder" bundle="${sys_messages}"/>">
			<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
		</div>
		<div class="form-group has-feedback">
			<select name="country" class="form-control">
				<option value="">
					<fmt:message key="box.country" bundle="${sys_messages}" />
				</option>
				<c:forEach items="${Controller.getCountries(locale)}" var="country">
					<option value="${country.isoAlpha3}">${country.name}</option>
				</c:forEach>
				<option value="XXX">
					<fmt:message key="box.country.default" bundle="${sys_messages}" />
				</option>
			</select>
		</div>
		<div class="form-group has-feedback">
			<input name="password" type="password" class="form-control" placeholder="<fmt:message key="box.password.placeholder" bundle="${sys_messages}"/>">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		</div>
		<div class="form-group has-feedback">
			<input name="retypePassword" type="password" class="form-control"
				placeholder="<fmt:message key="box.retype_password.placeholder" bundle="${sys_messages}"/>"> 
			<span class="glyphicon glyphicon-log-in form-control-feedback"></span>
		</div>
	      <div class="row">
	        <div class="col-xs-12">
		        <div id="result" class="result-check"></div>
	        </div>
	        <!-- /.col -->
	      </div>
		<div class="row">
			<div class="col-xs-8">
				<c:set var="terms" value="${plugins.ediacaran.user.terms}"/>
				<c:if test="${!empty terms && terms != ''}">
				<div class="checkbox icheck">
					<label> 
						<input name="terms" type="checkbox" class="icheckbox_square-blue">
							<fmt:message key="box.terms" bundle="${sys_messages}">
							 	<fmt:param value="${'#m'.concat(terms)}" />
							</fmt:message>
					</label>
				</div>
				</c:if>
			</div>
			<!-- /.col -->
			<div class="col-xs-4">
				<button type="submit" class="btn btn-primary btn-block btn-flat"><fmt:message key="box.register_button" bundle="${sys_messages}"/></button>
			</div>
			<!-- /.col -->
		</div>
	</form>

    <a href="${plugins.ediacaran.front.admin_login_page}"><fmt:message key="box.login" bundle="${sys_messages}"/></a><br>

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
 
	<ec:include uri="/includes/footer.jsp"/>

	<script type="text/javascript" src="${plugins.ediacaran.front.web_path}/templates/default_template/front/register/js/register.js"                            charset="utf-8"></script>

	
</body>
</html>
