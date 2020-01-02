<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>
<c:set var="entity" value="${requestScope.entity}"/>

<c:forEach var="item" items="${entity['content']}">
<c:set var="custom_size" value=               "${empty item['col-sm-size']? 'col-sm-12' : 'col-sm-'.concat(item['col-sm-size'])}"/>
<c:set var="custom_size" value="${custom_size} ${empty item['col-md-size']? 'col-md-12' : 'col-md-'.concat(item['col-md-size'])}"/>
<c:set var="custom_size" value="${custom_size} ${empty item['col-lg-size']? 'col-lg-12' : 'col-lg-'.concat(item['col-lg-size'])}"/>
<c:set var="custom_size" value="${custom_size} ${empty item['col-xl-size']? 'col-xl-12' : 'col-xl-'.concat(item['col-xl-size'])}"/>
<c:set var="col_size" value="${empty item['col-size']? custom_size : 'col-sm-12 col-md-12 col-lg-'.concat(item['col-size']).concat(' col-xl-').concat(item['col-size']) }"/>
<div class="${col_size}">
	<c:choose>
		<c:when test="${item.getClass().simpleName == 'String'}">
			${item}
		</c:when>
		<c:otherwise>
			<c:set var="entity" value="${item}" scope="request" />
			<jsp:include page="/plugins/community/ediacaran/front/basic_template/includes/parts/${requestScope.entity['template']}.jsp" />
		</c:otherwise>
	</c:choose>
	</div>
</c:forEach>
