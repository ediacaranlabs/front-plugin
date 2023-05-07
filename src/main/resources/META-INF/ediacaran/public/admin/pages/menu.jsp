<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<ec:accordion>
	<ed:row>
		<ed:col size="3" classStyle="form-group has-feedback">
			<ec:textfield name="icon">
				<ec:autocomplete resource="/plugins/ediacaran/front/admin/menus/search-icons" />
			</ec:textfield>
		</ed:col>
		<ed:col size="9" classStyle="form-group has-feedback">
			<ec:textfield name="name"/>
		</ed:col>
	</ed:row>
	<ed:row>
		<ed:col size="12" classStyle="form-group has-feedback">
			<ec:textfield name="resource">
				<ec:autocomplete resource="/plugins/ediacaran/front/admin/menus/search-resource" />
			</ec:textfield>
		</ed:col>
	</ed:row>
</ec:accordion>