<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class--%>
<c:set var="class"  value=                                                                                  "${empty entity['type']               ? '' : 'form-'.concat(entity['type']) }"/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${empty entity['properties']['class']? '' : entity['properties']['class']  }"/>

<%-- atributos --%>
<c:set var="attr" value=                                                                          "${empty entity['accept']?         '' : 'accept='.concat(quote).concat(entity['accept']).concat(quote)                 }"/>
<c:set var="attr" value="${!empty attr && !empty entity['accept-charset']? attr.concat(' ') : attr}${empty entity['accept-charset']? '' : 'accept-charset='.concat(quote).concat(entity['accept-charset']).concat(quote) }"/>
<c:set var="attr" value="${!empty attr && !empty entity['action']?         attr.concat(' ') : attr}${empty entity['action']?         '' : 'action='.concat(quote).concat(entity['action']).concat(quote)                 }"/>
<c:set var="attr" value="${!empty attr && !empty entity['autocomplete']?   attr.concat(' ') : attr}${empty entity['autocomplete']?   '' : 'autocomplete='.concat(quote).concat(entity['autocomplete']).concat(quote)     }"/>
<c:set var="attr" value="${!empty attr && !empty entity['enctype']?        attr.concat(' ') : attr}${empty entity['enctype']?        '' : 'enctype='.concat(quote).concat(entity['enctype']).concat(quote)               }"/>
<c:set var="attr" value="${!empty attr && !empty entity['method']?         attr.concat(' ') : attr}${empty entity['method']?         '' : 'method='.concat(quote).concat(entity['method']).concat(quote)                 }"/>
<c:set var="attr" value="${!empty attr && !empty entity['name']?           attr.concat(' ') : attr}${empty entity['name']?           '' : 'name='.concat(quote).concat(entity['name']).concat(quote)                     }"/>
<c:set var="attr" value="${!empty attr && !empty entity['novalidate']?     attr.concat(' ') : attr}${empty entity['novalidate']?     '' : 'novalidate='.concat(quote).concat(entity['novalidate']).concat(quote)         }"/>
<c:set var="attr" value="${!empty attr && !empty entity['target']?         attr.concat(' ') : attr}${empty entity['target']?         '' : 'target='.concat(quote).concat(entity['target']).concat(quote)                 }"/>


<c:set var="formControls" value="${!('inline' eq entity['type'])}" scope="request"/>

<form ${attr} class="${class}" <jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>

	<%-- corpo do formulário --%>
	<c:choose>
		<c:when test="${entity['content'].getClass().simpleName == 'String'}">
			${entity['content']}
		</c:when>
		<c:otherwise>
			<c:set var="entity" value="${entity['content']}" scope="request" />
			<jsp:include page="/plugins/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
		</c:otherwise>
	</c:choose>
	<%-- /corpo do formulário --%>
	
</form>