<%@taglib uri="http://java.sun.com/jsp/jstl/core"                 prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer"   prefix="ed"%>
<%@page trimDirectiveWhitespaces="true" %>
<ec:load-data file="index.json" var="pageObjects" />
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
						<h2>Form</h2>
					</div>
				</ed:col>
				<ed:col size="8">
					<ec:breadcrumb title="Form">
						<ec:breadcrumb-path icon="home" text="" lnk="#" />
						<ec:breadcrumb-path text="Features" lnk="#" />
					</ec:breadcrumb>
				</ed:col>
			</ed:row>
		</ed:container>
	</section>
	
    <section class="content">
		<ed:container>
			<ed:row>
				<ed:col size="12">
		            <h4>Example <strong>form</strong></h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
					<ec:form>
						<ec:textfield name="email" label="Email address" placeholder="Enter email"/>
						<ec:passwordfield name="senha" label="Password" placeholder="Password"/>
						<ec:checkbox name="check" label="Check me out"/>
						<ec:button label="Submit" actionType="submit"/>
					</ec:form>				
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><form>
	<textfield name="email" label="Email address" placeholder="Enter email"/>
	<passwordfield name="senha" label="Password" placeholder="Password"/>
	<checkbox name="chk" label="Chk me out"/>
	<button label="Submit" actionType="submit"/>
</form></ec:prettify>
				</ed:col>
			</ed:row>
			
			
			<ed:row>
				<ed:col size="12">
		            <h4></h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
					<ec:form>
						<ec:textfield name="email" label="Email address" placeholder="name@example.com"/>
						<ec:select name="select" label="Example select">
							<ec:option>1</ec:option>
							<ec:option>2</ec:option>
							<ec:option>3</ec:option>
							<ec:option>4</ec:option>
							<ec:option>5</ec:option>
						</ec:select>
						<ec:select name="select2" multiple="true" label="Example multiple select">
							<ec:option>1</ec:option>
							<ec:option>2</ec:option>
							<ec:option>3</ec:option>
							<ec:option>4</ec:option>
							<ec:option>5</ec:option>
						</ec:select>
						<ec:textarea name="textarea" label="Example textarea" rows="3"></ec:textarea>
					</ec:form>
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><form>
	<textfield name="email" label="Email address" placeholder="name@example.com"/>
	<select name="select" label="Example select">
		<option>1</option>
		<option>2</option>
		<option>3</option>
		<option>4</option>
		<option>5</option>
	</select>
	<select name="select2" multiple="true" label="Example multiple select">
		<option>1</option>
		<option>2</option>
		<option>3</option>
		<option>4</option>
		<option>5</option>
	</select>
	<textarea name="textarea" label="Example textarea" rows="3"></textarea>
</form></ec:prettify>
				</ed:col>
			</ed:row>


			<ed:row>
				<ed:col size="12">
		            <h4></h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true">
					</ec:prettify>
				</ed:col>
			</ed:row>


			<ed:row>
				<ed:col size="12">
		            <h4></h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true">
					</ec:prettify>
				</ed:col>
			</ed:row>


			<ed:row>
				<ed:col size="12">
		            <h4></h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true">
					</ec:prettify>
				</ed:col>
			</ed:row>


			<ed:row>
				<ed:col size="12">
		            <h4></h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true">
					</ec:prettify>
				</ed:col>
			</ed:row>
			
		</ed:container>    
    </section>
        	 
	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>