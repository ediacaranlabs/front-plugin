<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<ec:setBundle var="sys_messages" locale="${locale}"/>
<section class="content-header">
	<h1>	
		<fmt:message key="dashboard.title" bundle="${sys_messages}"/><small><fmt:message key="dashboard.sub_title" bundle="${sys_messages}"/></small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> <fmt:message key="dashboard.parent" bundle="${sys_messages}"/></a></li>
		<li class="active"> <fmt:message key="dashboard.local" bundle="${sys_messages}"/></li>
	</ol>
</section>
<section class="content">
<div class="row">
<c:forEach begin="0" end="${fn:length(vars.widgets)}" varStatus="loop">
	<c:if test="${!empty vars.widgets[loop.index].resource}">
		<div class="col-sm-6 col-md-6 col-lg-6" id="widget_${loop.index}">
		</div>
	</c:if>
</c:forEach>
</div>
</section>
<script type="text/javascript">
	$.AppContext.onload(
		function (){
			<c:forEach begin="0" end="${fn:length(vars.widgets)}" varStatus="loop">
				<c:if test="${!empty vars.widgets[loop.index].resource}">
				$.AppContext.loadContent("#widget_${loop.index}", "${vars.widgets[loop.index].resource}");		
				</c:if>
			</c:forEach>
		}
	);
</script>