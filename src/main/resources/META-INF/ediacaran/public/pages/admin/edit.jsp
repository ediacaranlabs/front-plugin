<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>
<ec:setBundle var="messages" locale="${locale}"/>

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2>P�ginas</h2>
			</div>
		</ed:col>
		<ed:col size="8">
			<ec:breadcrumb title="Plugins">
				<ec:breadcrumb-path icon="home" text="" lnk="#" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<ed:row>
	<ed:col size="12">
	</ed:col>
</ed:row>