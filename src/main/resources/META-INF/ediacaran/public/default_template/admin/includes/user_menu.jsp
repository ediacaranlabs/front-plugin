<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<link rel="stylesheet" href="/plugins/ediacaran/front/default_template/admin/css/user_menu.css">
<ec:setBundle var="sys_messages" locale="${locale}"/>
<ed:row style="form">
	<ed:col size="12" classStyle="text-center">
		<ec:icon icon="user" bg="circle" size="3" />
	</ed:col>
</ed:row>
<ed:row style="form">
	<ed:col classStyle="text-center" size="12">
		${sessionScope.system_user.firstName} ${sessionScope.system_user.lastName}
	</ed:col>
</ed:row>
<ed:row style="form">
	<ed:col size="6">
		<a href="#!${plugins.ediacaran.front.admin_perfil_page}"><fmt:message key="perfil_button" bundle="${sys_messages}"/></a>
	</ed:col>
	<ed:col size="6" >
		<a href="${plugins.ediacaran.front.admin_logout_page}"><fmt:message key="logout_button" bundle="${sys_messages}"/></a>
	</ed:col>
</ed:row>
