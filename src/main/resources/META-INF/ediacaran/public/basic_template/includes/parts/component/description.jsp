<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>
<c:set var="ignore" value="['class']" scope="request" />
<c:set var="quote" value="\"" />

<%-- atributo class --%>
<c:set var="class"  value=""/>
<c:set var="class"  value="${!empty class && !empty entity['properties']['class']? class.concat(' ') : class}${!empty entity['properties']['class']? entity['properties']['class'] : ''}"/>

<%-- dt class --%>
<c:set var="dtClass"  value="${!empty dtClass && !empty entity['left-size']? dtClass.concat(' ') : dtClass}${!empty entity['left-size']?                'col-'.concat(entity['left-size']) : ''}"/>

<%-- dd class --%>
<c:set var="ddClass"  value="${!empty ddClass && !empty entity['right-size']? ddClass.concat(' ') : ddClass}${!empty entity['right-size']?                'col-'.concat(entity['right-size']) : ''}"/>

<c:if test="${entity['type'] == 'horizontal'}">
<c:set var="dtClass" value="${!empty dtClass && empty entity['left-size'] ? dtClass.concat(' ') : dtClass}${empty entity['left-size']?                'col-sm-3' : ''}"/>
<c:set var="ddClass" value="${!empty ddClass && empty entity['right-size']? ddClass.concat(' ') : ddClass}${empty entity['right-size']?                'col-sm-9' : ''}"/>
<c:set var="class"   value="${!empty class                                ? class.concat(' ')   : class  }row"/>
</c:if>

<%-- atributos opcionais --%>
<c:set var="attr" value="${!empty attr && !empty class? attr.concat(' ') : attr}${empty class? '' : 'class='.concat(quote).concat(class).concat(quote)}"/>

<dl ${attr} <jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/designer/properties.jsp" />>
	<c:forEach var="group" items="${entity['content']}">
	
	<c:set var="groupDtClass"  value="${dtClass}${!empty dtClass && group['text-truncate']? ' ' : ''}${group['text-truncate']? 'text-truncate' : ''}"/>
	<dt class="${groupDtClass}">${group['title']}</dt>

	<dd class="${ddClass}">
	<c:forEach var="item" items="${group['content']}">
		<p>${item}</p>
	</c:forEach>
	</dd>
	
	</c:forEach>
</dl>
