<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	    <c:forEach items="${item.itens}" var="item" varStatus="_count">
	    
	    	<c:if test="${_count.index > 0}">
	        <!-- divider -->
	        <div class="row">
	          <div class="span12">
	            <div class="solidline">
	            </div>
	          </div>
	        </div>
	        <!-- end divider -->
	    	</c:if>
	    	
    		<c:set var="item" value="${item}" scope="request"/>
			<jsp:include page="/plugins/community/ediacaran/front/${plugins.front.template}/includes/index/widget/body/${item['template']}.jsp"/>
	    	
	    </c:forEach>
