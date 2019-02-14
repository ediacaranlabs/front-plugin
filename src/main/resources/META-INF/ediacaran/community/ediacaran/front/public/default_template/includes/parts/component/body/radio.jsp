<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />

<%-- monta a tag class--%>
<c:set var="class"  value="${entity['enabled'] == 'false'? 'uneditable-input' : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['class']? class.concat(' ') : class}${entity['inline']? 'inline' : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['class']? class.concat(' ') : class}${!empty entity['class']? entity['class'] : ''}"/>

<label class="radio ${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
  <input type="radio" name="${entity['name']}" value="${entity['value']}" ${entity['enabled'] == 'false'? ' disabled' : '' }
  ${entity['selected']? ' checked' : '' }>
  ${entity['label']}
</label>