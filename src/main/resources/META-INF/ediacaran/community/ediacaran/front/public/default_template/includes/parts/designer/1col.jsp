<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${entity}"/>
<div class="span12">
<c:set var="entity" value="${entity['content']}" scope="request"/>
<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${entity['content']['template']}.jsp"/>
</div>

