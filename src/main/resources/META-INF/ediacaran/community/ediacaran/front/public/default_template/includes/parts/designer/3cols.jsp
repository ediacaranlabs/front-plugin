<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
<div class="span4">
<c:set var="entity" value="${entity['content'][0]}" scope="request"/>
<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp"/>
</div>
<div class="span4">
<c:set var="entity" value="${entity['content'][1]}" scope="request"/>
<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp"/>
</div>
<div class="span4">
<c:set var="entity" value="${entity['content'][2]}" scope="request"/>
<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${requestScope.entity['template']}.jsp"/>
</div>
