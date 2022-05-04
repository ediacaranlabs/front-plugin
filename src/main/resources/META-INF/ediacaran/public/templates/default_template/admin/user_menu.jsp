<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<link rel="stylesheet" href="/plugins/ediacaran/front/default_template/admin/css/user_menu.css">
<ec:setBundle var="sys_messages" locale="${locale}"/>
<ed:row style="form">
	<ed:col size="12" classStyle="text-center">
		<ec:image align="center" style="circle" src="${empty sessionScope.system_user.image? '/plugins/ediacaran/front/admin/img/user.png' : sessionScope.system_user.image}"/>
	</ed:col>
</ed:row>
<ed:row style="form">
	<ed:col classStyle="text-center" size="12">
		<c:choose>
			<c:when test="${empty sessionScope.system_user.firstName}">
				User Name
			</c:when>
			<c:otherwise>
				${sessionScope.system_user.firstName} ${sessionScope.system_user.lastName}
			</c:otherwise>
		</c:choose>
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
