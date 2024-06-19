<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@page trimDirectiveWhitespaces="true" %>
<ec:setBundle var="sys_messages" locale="${locale}"/>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<ec:include uri="/includes/head.jsp"/>
<style type="text/css">
.box {
    width: 360px;
    margin: 12% auto;
}

.box-header {
    font-size: 35px;
    text-align: center;
    margin-bottom: 25px;
    font-weight: 300;
    border-bottom: 0px !important;
}

.box-header a {
    color: #444;
}

/*
.box-header {
    font-size: 35px;
    text-align: center;
    margin-bottom: 25px;
    font-weight: 300;
    border-bottom: 0px !important;
}

.box-header a {
    color: #444;
}

.box-footer{
	border-top: 0px !important;
}

.box-body{
	background-color: #fff;	
}

.box-body .box-msg {
    margin: 0;
    text-align: center;
    padding: 0 20px 20px 20px;
}
*/
</style>
</head>

<body>

	<ec:include uri="/includes/header.jsp"/>
	
	
    <section class="content">
		<ed:container>
			
			<ec:box>
				<%--
				<ec:box-header>
			    <a href="#">
					<c:if test="${empty plugins.ediacaran.front.image_logo}">
						 <a href="#">${plugins.ediacaran.front.text_logo}</a>
					</c:if>
					<c:if test="${!empty plugins.ediacaran.front.image_logo}">
						<ec:image src="${plugins.ediacaran.front.image_logo}"/>
					</c:if>
			    </a>
			    </ec:box-header>
			    --%>
			    <ec:box-body>
			    	<ed:row>
			    		<ed:col size="12">
						    <p class="box-msg"><fmt:message key="box.message" bundle="${sys_messages}"/></p>
						    <p id="msg_result" class="box-msg">
						    	<c:if test="${empty exception}">
						    		<ec:alert style="success">
						    			<fmt:message key="box.success_msg" bundle="${sys_messages}"/>
						    		</ec:alert>
						    	</c:if>
						    	<c:if test="${!empty exception}">
						    		<ec:alert style="danger"><div id="exceptionMessage">${exception.message}</div></ec:alert>
						    	</c:if>
					    	</p>
			    		</ed:col>
			    	</ed:row>
			    	<ed:row>
			    		<ed:col size="8"></ed:col>
			    		<ed:col size="4">
			    			<ec:button align="right" bundle="${sys_messages}" label="#{box.back_button}">
			    				<ec:event type="click">
			    					$.AppContext.openLink("${plugins.ediacaran.user.success_actions_uri}");
			    				</ec:event>
			    			</ec:button>
			   			</ed:col>
			    	</ed:row>
			    </ec:box-body>
			</ec:box>			
			
		</ed:container>    
    </section>
	 
	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>