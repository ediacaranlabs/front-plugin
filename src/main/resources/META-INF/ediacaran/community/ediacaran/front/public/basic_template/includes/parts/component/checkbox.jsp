<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class--%>
<c:set var="class"  value=""/>
<c:set var="class"  value="${!empty class && entity['enabled'] == 'false'? class.concat(' ') : class}${entity['enabled'] == 'false'? 'uneditable-input'  : ''}"/>
<c:set var="class"  value="${!empty class && entity['inline']?             class.concat(' ') : class}${entity['inline']?             'form-check-inline' : ''}"/>

<%-- atributos --%>
<c:set var="attr" value=                                                                       "type=\"checkbox\""/>
<c:set var="attr" value="${!empty attr && entity['enabled'] == 'false'? attr.concat(' ') : attr}${entity['enabled'] == 'false'? 'disabled' : ''}"/>
<c:set var="attr" value="${!empty attr && !empty entity['name']?        attr.concat(' ') : attr}${!empty entity['name']?        'name='.concat(quote).concat(entity['name']).concat(quote)   : ''}"/>
<c:set var="attr" value="${!empty attr && !empty entity['value']?       attr.concat(' ') : attr}${!empty entity['value']?       'value='.concat(quote).concat(entity['value']).concat(quote) : ''}"/>
<c:set var="attr" value="${!empty attr && entity['selected']?           attr.concat(' ') : attr}${entity['selected']?           'checked'                                                    : ''}"/>
<%--<c:set var="attr" value="${!empty attr && !empty class?                 attr.concat(' ') : attr}${empty class?                  ''         : 'class='.concat(quote).concat(class).concat(quote)          }"/>--%>


<div class="form-check ${empty requestScope.formControls? class : ''}" <c:if test="${empty requestScope.formControls}"><jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" /></c:if>>
  <input class="form-check-input" ${attr}>
  <label class="form-check-label">
    ${entity['label']}
  </label>
</div>
