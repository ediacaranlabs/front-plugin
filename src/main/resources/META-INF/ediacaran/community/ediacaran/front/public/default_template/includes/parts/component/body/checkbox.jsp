<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class--%>
<c:set var="class"  value="checkbox"/>
<c:set var="class"  value="${!empty class && !empty entity['class']?               class.concat(' ') : class}${entity['enabled'] == 'false'?         'uneditable-input'            : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['class']?               class.concat(' ') : class}${entity['inline']?                     'inline'                      : ''}"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class'] : ''}"/>

<%-- atributos --%>
<c:set var="attr" value=                                                                       "type=\"checkbox\""/>
<c:set var="attr" value="${!empty attr && entity['enabled'] == 'false'? attr.concat(' ') : attr}${entity['enabled'] == 'false'? 'disabled' : ''}"/>
<c:set var="attr" value="${!empty attr && !empty entity['name']?        attr.concat(' ') : attr}${empty entity['name']?         ''         : 'name='.concat(quote).concat(entity['name']).concat(quote)  }"/>
<c:set var="attr" value="${!empty attr && !empty entity['value']?       attr.concat(' ') : attr}${empty entity['value']?        ''         : 'value='.concat(quote).concat(entity['value']).concat(quote)}"/>
<c:set var="attr" value="${!empty attr && entity['selected']?           attr.concat(' ') : attr}${empty entity['selected']?     ''         : 'checked'                                                   }"/>
<%--<c:set var="attr" value="${!empty attr && !empty class?                 attr.concat(' ') : attr}${empty class?                  ''         : 'class='.concat(quote).concat(class).concat(quote)          }"/>--%>

<c:if test="${requestScope.formControls}">
	<div class="control-group">
		<div class="controls">
</c:if>
<label class="${class}" <jsp:include page="/plugins/community/ediacaran/front/default_template/includes/parts/designer/properties.jsp" />>
  <input ${attr}>
  ${entity['label']}
</label>
<c:if test="${requestScope.formControls}">
		</div>
	</div>
</c:if>
