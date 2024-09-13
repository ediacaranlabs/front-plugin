<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                    prefix="fmt"%> 
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@page trimDirectiveWhitespaces="true" %>
<ec:setBundle var="messages" locale="${locale}"/>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<ec:include uri="/includes/head.jsp"/>
</head>

<body>

	<ec:include uri="/includes/header.jsp"/>
	
	<section class="inner-headline">
		<ed:container>
			<ed:row>
				<ed:col size="4">
					<div class="inner-heading">
						<h2><fmt:message key="header.title" bundle="${messages}"/></h2>
					</div>
				</ed:col>
				<ed:col size="8">
					<ec:breadcrumb title="#{header.title}" bundle="${messages}">
						<ec:breadcrumb-path 
							icon="home" 
							text="" 
							lnk="#" />
						<ec:breadcrumb-path 
							text="#{header.breadcrumb.parent}" 
							lnk="${plugins.ediacaran.marketplace.web_path}/"
							bundle="${messages}"
							/>
					</ec:breadcrumb>
				</ed:col>
			</ed:row>
		</ed:container>
	</section>
				    
    <section class="content">
		<ed:container>
			<ed:row>
				<ed:col size="12">
					<ec:data-table id="supplier_search_form" 
						action="${plugins.ediacaran.marketplace.web_path}${plugins.ediacaran.marketplace.panel_context}/suppliers/">
						<!-- start search form  -->
						<ed:row>
							<ed:col size="2">
				    			<ec:textfield 
				    				name="code" 
				    				placeholder="#{supplier_search_form.code.placeholder}"
				    				bundle="${messages}"/>
							</ed:col>
							<ed:col size="6">
				    			<ec:textfield 
				    				name="name" 
				    				placeholder="#{supplier_search_form.name.placeholder}"
				    				bundle="${messages}"/>
							</ed:col>
							<ed:col size="2">
				    			<ec:select name="status">
				    				<ec:option value=""><fmt:message key="supplier_search_form.status.placeholder"  bundle="${messages}"/></ec:option>
				    				<c:forEach items="${vars.statusList}" var="status" >
					    				<ec:option value="${status}">${status.getName(locale)}</ec:option>
				    				</c:forEach>
				    			</ec:select>
							</ed:col>
							<ed:col size="2">
				    			<ec:button 
				    				actionType="submit" 
				    				align="right" 
				    				label="#{supplier_search_form.search_button.label}"
				    				bundle="${messages}"/>
							</ed:col>
						</ed:row>
						<!-- end search form  -->
						<!-- start search result  -->
						<ec:data-result var="response">
							<ec:forEach items="!{response.data}" var="item">
								<ed:row>
									<ed:col size="2">
										!{item.code}
									</ed:col>
									<ed:col size="6">
										!{item.name}
									</ed:col>
									<ed:col size="2">
										<ec:center>!{item.status}</ec:center>
									</ed:col>
									<ed:col size="2">
										<ec:button 
											id="button_!{item.code}" 
											label="#{supplier_search_form.response.edit_button.label}"
											align="right"
											bundle="${messages}" >
											<ec:event type="click">
												location.href = '${plugins.ediacaran.marketplace.web_path}${plugins.ediacaran.marketplace.panel_context}/suppliers/edit/!{item.id}'; 
											</ec:event>
										</ec:button>
									</ed:col>
								</ed:row>
							</ec:forEach>
						</ec:data-result>
						<!-- end search result  -->
					</ec:data-table>
					
				</ed:col>
			</ed:row>
			
		</ed:container>
    </section>
    
	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>