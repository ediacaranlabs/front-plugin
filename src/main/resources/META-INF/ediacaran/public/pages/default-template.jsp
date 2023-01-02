<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"              prefix = "fn"%>
<%@page trimDirectiveWhitespaces="true" %>
<ec:load-data file="index" locale="${locale}" var="pageObjects" />
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<c:forEach items="${page.header}" var="item">
<meta name="${item.key}" content="${item.value}" />
</c:forEach>
<title>${page.title}</title>
<ec:include uri="/includes/head.jsp"/>
</head>

<body>

	<ec:include uri="/includes/header.jsp"/>
	
	<section class="inner-headline">
		<ed:container>
			<ed:row>
				<ed:col size="4">
					<div class="inner-heading">
						<h2>${page.title}</h2>
					</div>
				</ed:col>
				<ed:col size="8">
					<ec:breadcrumb title="${fn:length(page.title) > 30? fn:substring(page.title, 0, 15).concat(' ... ').concat(fn:substring(page.title, fn:length(page.title) - 10, fn:length(page.title))) : page.title }">
						<c:forEach items="${page.breadcrumb}" var="item">
							<ec:breadcrumb-path icon="${item.icon}" text="${item.name}" lnk="${item.link}" />
						</c:forEach>
					</ec:breadcrumb>
				</ed:col>
			</ed:row>
		</ed:container>
	</section>
		
	<section class="content">
		<ed:container>
			${page.write(pageContext.out)}
		</ed:container>
	</section>
	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>