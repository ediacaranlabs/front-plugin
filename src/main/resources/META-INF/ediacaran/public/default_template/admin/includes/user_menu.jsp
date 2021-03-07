<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<ec:setTemplatePackage name="admin"/>
<ed:row>
	<ed:col size="12" classStyle="text-center">
		<ec:icon icon="user" bg="circle" size="3" />
	</ed:col>
</ed:row>
<ed:row>
	<ed:col classStyle="text-center" size="12">
		User name
	</ed:col>
</ed:row>
<ed:row>
	<ed:col size="6">
		<ec:button block="true" label="Perfil"/>
	</ed:col>
	<ed:col size="6" >
		<ec:button block="true" label="log out"/>
	</ed:col>
</ed:row>
