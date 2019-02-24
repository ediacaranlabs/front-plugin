<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page trimDirectiveWhitespaces="true" %>

<%-- define a entidade --%>
<c:set var="entity" value="${requestScope.entity}"/>

<section class="callaction">
	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="big-cta">${entity['content']}</div>
			</div>
		</div>
	</div>
</section>
