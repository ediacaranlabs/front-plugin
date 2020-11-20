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
		            <h4>Form controls</h4>
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
						<ec:filefield name="file" label="Example file input" />
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
	<filefield name="file" label="Example file input" />
</form></ec:prettify>
				</ed:col>
			</ed:row>

			<ed:row>
				<ed:col size="12">
		            <h4>Sizing</h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
					<ec:textfield name="text1" size="lg" placeholder="lg" />
					<ec:textfield name="text1" placeholder="Default input" />
					<ec:textfield name="text1" size="sm" placeholder="sm" />
					<ec:select name="select" size="lg">
						<ec:option>Large select</ec:option>
					</ec:select>
					<ec:select name="select">
						<ec:option>Default select</ec:option>
					</ec:select>
					<ec:select name="select" size="sm">
						<ec:option>Small select</ec:option>
					</ec:select>
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><textfield name="text1" size="lg" placeholder="lg" />
<textfield name="text1" placeholder="Default input" />
<textfield name="text1" size="sm" placeholder="sm" />
<select name="select" size="lg">
	<option>Large select</option>
</select>
<select name="select">
	<option>Default select</option>
</select>
<select name="select" size="sm">
	<option>Small select</option>
</select></ec:prettify>
				</ed:col>
			</ed:row>

			<ed:row>
				<ed:col size="12">
		            <h4>Readonly</h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
					<ec:textfield name="text" readonly="true" placeholder="Readonly input here..." />
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><textfield name="text" readonly="true" placeholder="Readonly input here..." /></ec:prettify>
				</ed:col>
			</ed:row>


			<ed:row>
				<ed:col size="12">
		            <h4>Checkboxes and radios</h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
					<ec:checkbox name="checkbox" label="Default checkbox"/>
					<ec:checkbox name="checkbox2" label="Disabled checkbox" enabled="false" />
					<ec:radio name="exampleRadios" label="Default radio" selected="true"/>
					<ec:radio name="exampleRadios" label="Second default radio"/>
					<ec:radio name="exampleRadios" label="Disabled radio" enabled="false"/>
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><checkbox name="checkbox" label="Default checkbox"/>
<checkbox name="checkbox2" label="Disabled checkbox" enabled="false" />
<radio name="exampleRadios" label="Default radio" selected="true"/>
<radio name="exampleRadios" label="Second default radio"/>
<radio name="exampleRadios" label="Disabled radio" enabled="false"/></ec:prettify>
				</ed:col>
			</ed:row>


			<ed:row>
				<ed:col size="12">
		            <h4>Inline</h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
					<ec:checkbox name="checkbox1" label="1" inline="true" />
					<ec:checkbox name="checkbox2" label="2" inline="true" />
					<ec:checkbox name="checkbox3" label="3" enabled="false" inline="true"/>
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><checkbox name="checkbox1" label="1" inline="true" />
<checkbox name="checkbox2" label="2" inline="true" />
<checkbox name="checkbox3" label="3" enabled="false" inline="true"/></ec:prettify>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
					<ec:radio name="radioExample" label="1" selected="true" inline="true" />
					<ec:radio name="radioExample" label="2" inline="true" />
					<ec:radio name="radioExample" label="3" enabled="false" inline="true"/>
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><radio name="radioExample" label="1" selected="true" inline="true" />
<radio name="radioExample" label="2" inline="true" />
<radio name="radioExample" label="3" enabled="false" inline="true"/></ec:prettify>
				</ed:col>
			</ed:row>
		
		
			<ed:row>
				<ed:col size="12">
		            <h4>Form grid</h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
					<ec:form>
						<ed:row>
							<ed:col size="6">
								<ec:textfield name="first_name" placeholder="First name"/>
							</ed:col>
							<ed:col size="6">
								<ec:textfield name="first_name" placeholder="Last name"/>
							</ed:col>
						</ed:row>
					</ec:form>
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><form>
	<row>
		<col size="6">
			<textfield name="first_name" placeholder="First name">
		</col>
		<col size="6">
			<textfield name="first_name" placeholder="Last name">
		</col>
	</row>
</form></ec:prettify>
				</ed:col>
			</ed:row>

			<ed:row>
				<ed:col size="12">
		            <h4>Form row</h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
					<ec:form>
						<ed:row style="form">
							<ed:col size="6">
								<ec:textfield name="first_name" placeholder="First name"/>
							</ed:col>
							<ed:col size="6">
								<ec:textfield name="first_name" placeholder="Last name"/>
							</ed:col>
						</ed:row>
					</ec:form>
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><form>
	<row style="form">
		<col size="6">
			<textfield name="first_name" placeholder="First name">
		</col>
		<col size="6">
			<textfield name="first_name" placeholder="Last name">
		</col>
	</row>
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
					<ec:prettify linenums="true"></ec:prettify>
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
					<ec:prettify linenums="true"></ec:prettify>
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
					<ec:prettify linenums="true"></ec:prettify>
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
					<ec:prettify linenums="true"></ec:prettify>
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
					<ec:prettify linenums="true"></ec:prettify>
				</ed:col>
			</ed:row>

		</ed:container>    
    </section>
        	 
	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>