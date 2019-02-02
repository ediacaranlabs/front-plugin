<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${entity}"/>
<div class="span2">
<c:set var="entity" value="${entity['content'][0]}" scope="request"/>
<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${entity['content'][0]['template']}.jsp"/>
</div>
<div class="span2">
<c:set var="entity" value="${entity['content'][1]}" scope="request"/>
<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${entity['content'][1]['template']}.jsp"/>
</div>
<div class="span2">
<c:set var="entity" value="${entity['content'][2]}" scope="request"/>
<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${entity['content'][2]['template']}.jsp"/>
</div>
<div class="span2">
<c:set var="entity" value="${entity['content'][3]}" scope="request"/>
<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${entity['content'][3]['template']}.jsp"/>
</div>
<div class="span2">
<c:set var="entity" value="${entity['content'][4]}" scope="request"/>
<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${entity['content'][4]['template']}.jsp"/>
</div>
<div class="span2">
<c:set var="entity" value="${entity['content'][5]}" scope="request"/>
<jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/${entity['content'][5]['template']}.jsp"/>
</div>
