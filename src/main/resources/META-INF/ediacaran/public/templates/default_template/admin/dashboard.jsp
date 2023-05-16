<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                               prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core"                              prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"                         prefix="fn"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<ec:setBundle var="sys_messages" locale="${locale}"/>
<fmt:message key="dashboard.local" var="dashboard_local" bundle="${sys_messages}"/>

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2><fmt:message key="dashboard.title" bundle="${sys_messages}"/></h2>
			</div>
		</ed:col>
		<ed:col size="8">
			<ec:breadcrumb title="${dashboard_local}">
				<ec:breadcrumb-path icon="home" text="" lnk="#" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<div class="row">
<c:forEach begin="0" end="${fn:length(vars.widgets)}" varStatus="loop">
	<c:if test="${!empty vars.widgets[loop.index].resource}">
		<div class="col-sm-6 col-md-6 col-lg-6" id="widget_${loop.index}">
		</div>
	</c:if>
</c:forEach>
</div>

<script type="text/javascript">
	$.AppContext.onload(
		function (){
			<c:forEach begin="0" end="${fn:length(vars.widgets)}" varStatus="loop">
				<c:if test="${!empty vars.widgets[loop.index].resource}">
				$.AppContext.loadContent("widget_${loop.index}", "${vars.widgets[loop.index].resource}");		
				</c:if>
			</c:forEach>
		}
	);
</script>