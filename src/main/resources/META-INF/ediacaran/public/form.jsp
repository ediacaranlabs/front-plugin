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
				
					<ec:form>
						<ed:row style="form">
							<ed:col size="6">
								<ec:textfield name="email" label="Email" placeholder="Email"/>
							</ed:col>
							<ed:col size="6">
								<ec:passwordfield name="password" label="Password" placeholder="Password"/>
							</ed:col>
						</ed:row>
					
					  	<ec:textfield name="address" label="Address" placeholder="1234 Main St"/>
					  	<ec:textfield name="address2" label="Address 2" placeholder="Apartment, studio, or floor"/>
					
						<ed:row style="form">
							<ed:col size="6">
								<ec:textfield name="city" label="City"/>
							</ed:col>
							<ed:col size="4">
								<ec:select name="state" label="State">
									<ec:option selected="true">Choose...</ec:option>
								</ec:select>
							</ed:col>
							<ed:col size="2">
								<ec:textfield name="zip" label="Zip"/>
							</ed:col>
						</ed:row>
					  	
					  	<ec:checkbox name="check_out_me" label="Check me out"/>
					  	<ec:button label="Sign in"/>
					  	
					</ec:form>				
				
				
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><form>
	<row style="form">
		<col size="6">
			<textfield name="email" label="Email" placeholder="Email"/>
		</col>
		<col size="6">
			<passwordfield name="password" label="Password" placeholder="Password"/>
		</col>
	</row>

  	<textfield name="address" label="Address" placeholder="1234 Main St"/>
  	<textfield name="address2" label="Address 2" placeholder="Apartment, studio, or floor"/>

	<row style="form">
		<col size="6">
			<textfield name="city" label="City"/>
		</col>
		<col size="4">
			<select name="state" label="State">
				<option selected="true">Choose...</option>
			</select>
		</col>
		<col size="2">
			<textfield name="zip" label="Zip"/>
		</col>
	</row>
  	
  	<checkbox name="check_out_me" label="Check me out"/>
  	<button label="Sign in"/>
  	
</form></ec:prettify>
				</ed:col>
			</ed:row>
			

			<ed:row>
				<ed:col size="12">
		            <h4>Horizontal form</h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
					<ec:form>
						<ed:row>
							<ed:col size="2"><ec:label style="col-form">Email</ec:label></ed:col>
							<ed:col size="10">
								<ec:textfield name="email" placeholder="Email"/>
							</ed:col>
						</ed:row>
						<ed:row>
							<ed:col size="2"><ec:label style="col-form">Password</ec:label></ed:col>
							<ed:col size="10">
								<ec:passwordfield name="password" placeholder="Password"/>
							</ed:col>
						</ed:row>
						<ed:row>
							<ed:col size="2"><ec:label style="col-form">Radios</ec:label></ed:col>
							<ed:col size="10">
								<ec:radio label="First radio" name="gridRadios" value="option1" selected="true"/>
								<ec:radio label="Second radio" name="gridRadios" value="option2"/>
								<ec:radio label="Third disabled radio" name="gridRadios" value="option3" enabled="false"/>
							</ed:col>
						</ed:row>
					
						<ed:row>
							<ed:col size="2">
								<ec:label style="col-form">Checkbox</ec:label>
							</ed:col>
							<ed:col size="10">
								<ec:checkbox label="Example checkbox" name="gridCheck1"/>
							</ed:col>
						</ed:row>
					
						<ec:button label="Sign in"/>	
					</ec:form>				
					
					</ed:col>
					<ed:col size="6">
						<ec:prettify linenums="true"><form>
	<row>
		<col size="2"><label style="col-form">Email</label></col>
		<col size="10">
			<textfield name="email" placeholder="Email"/>
		</col>
	</row>
	<row>
		<col size="2"><label style="col-form">Password</label></col>
		<col size="10">
			<passwordfield name="password" placeholder="Password"/>
		</col>
	</row>
	<row>
		<col size="2"><label style="col-form">Radios</label></col>
		<col size="10">
			<radio label="First radio" name="gridRadios" value="option1" selected="true"/>
			<radio label="Second radio" name="gridRadios" value="option2"/>
			<radio label="Third disabled radio" name="gridRadios" value="option3" enabled="false"/>
		</col>
	</row>

	<row>
		<col size="2">
			<label style="col-form">Checkbox</label>
		</col>
		<col size="10">
			<checkbox label="Example checkbox" name="gridCheck1"/>
		</col>
	</row>

	<button label="Sign in"/>	
</form></ec:prettify>
					</ed:col>
				</ed:row>
			<ed:row>
				<ed:col size="12">
		            <h4>Horizontal form label sizing</h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
					<ec:form>
						<ed:row>
							<ed:col size="2"><ec:label style="col-form" size="sm">Email</ec:label></ed:col>
							<ed:col size="10">
								<ec:textfield name="email" size="sm" placeholder="Email"/>
							</ed:col>
						</ed:row>
						<ed:row>
							<ed:col size="2"><ec:label style="col-form">Email</ec:label></ed:col>
							<ed:col size="10">
								<ec:textfield name="email" placeholder="Email"/>
							</ed:col>
						</ed:row>
						<ed:row>
							<ed:col size="2"><ec:label style="col-form" size="lg">Email</ec:label></ed:col>
							<ed:col size="10">
								<ec:textfield name="email" size="lg" placeholder="Email"/>
							</ed:col>
						</ed:row>
					</ec:form>			
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><form>
	<row>
		<col size="2"><label style="col-form" size="sm">Email</label></col>
		<col size="10">
			<textfield name="email" size="sm" placeholder="Email"/>
		</col>
	</row>
	<row>
		<col size="2"><label style="col-form">Email</label></col>
		<col size="10">
			<textfield name="email" placeholder="Email"/>
		</col>
	</row>
	<row>
		<col size="2"><label style="col-form" size="lg">Email</label></col>
		<col size="10">
			<textfield name="email" size="lg" placeholder="Email"/>
		</col>
	</row>
</form></ec:prettify>
				</ed:col>
			</ed:row>

			<ed:row>
				<ed:col size="12">
		            <h4>Inline form</h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
					<ec:form style="inline">
						<ec:textfield name="name" size="sm" placeholder="Jane Doe"/>
						<ec:field-group size="sm">
							<ec:prepend-field>
								<ec:prepend-field-item>@</ec:prepend-field-item>
							</ec:prepend-field>
							<ec:textfield name="username" placeholder="Username"/>
						</ec:field-group>
						<ec:button label="Submit" size="sm" actionType="submit"/>
					</ec:form>				
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><form style="inline">
	<textfield name="name" size="sm" placeholder="Jane Doe"/>
	<field-group size="sm">
		<prepend-field>
			<prepend-field-item>@</prepend-field-item>
		</prepend-field>
		<textfield name="username" placeholder="Username"/>
	</field-group>
	<button label="Submit" size="sm" actionType="submit"/>
</form></ec:prettify>
				</ed:col>
			</ed:row>

			<ed:row>
				<ed:col size="12">
		            <h4>Validation</h4>
				</ed:col>
			</ed:row>
			<ed:row>
				<ed:col size="6">
				
<ec:form>
	<ed:row style="form">
		<ed:col size="4" classStyle="form-group has-feedback">
			<ec:textfield name="first_name" label="First Name">
				<ec:field-validator>
					<ec:field-validator-rule name="notEmpty" message="Please provide a valid First Name"/>
				</ec:field-validator>
			</ec:textfield>
		</ed:col>
		<ed:col size="4" classStyle="form-group has-feedback">
			<ec:textfield name="last_name" label="Last name">
				<ec:field-validator>
					<ec:field-validator-rule name="notEmpty" message="Please provide a valid Last Name"/>
				</ec:field-validator>
			</ec:textfield>
		</ed:col>
		<ed:col size="4" classStyle="form-group has-feedback">
			<ec:label>Username</ec:label>
			<ec:field-group>
				<ec:prepend-field>
					<ec:prepend-field-item>@</ec:prepend-field-item>
				</ec:prepend-field>
				<ec:textfield name="username" placeholder="Username">
					<ec:field-validator>
						<ec:field-validator-rule name="notEmpty" message="Please choose a username."/>
					</ec:field-validator>
				</ec:textfield>
			</ec:field-group>
		</ed:col>
	</ed:row>
	<ed:row style="form">
		<ed:col size="6" classStyle="form-group has-feedback">
			<ec:textfield name="city" label="City">
				<ec:field-validator>
					<ec:field-validator-rule name="notEmpty" message="Please provide a valid city."/>
				</ec:field-validator>
			</ec:textfield>
		</ed:col>
		<ed:col size="3" classStyle="form-group has-feedback">
			<ec:textfield name="State" label="State">
				<ec:field-validator>
					<ec:field-validator-rule name="notEmpty" message="Please provide a valid state."/>
				</ec:field-validator>
			</ec:textfield>
		</ed:col>
		<ed:col size="3" classStyle="form-group has-feedback">
			<ec:textfield name="zip" label="Zip">
				<ec:field-validator>
					<ec:field-validator-rule name="notEmpty" message="Please provide a valid zip."/>
				</ec:field-validator>
			</ec:textfield>
		</ed:col>
	</ed:row>
	<ed:row style="form">
		<ed:col size="12" classStyle="form-group has-feedback">
			<ec:checkbox name="checkbox" label="Agree to terms and conditions">
				<ec:field-validator>
					<ec:field-validator-rule name="notEmpty" message="You must agree before submitting."/>
				</ec:field-validator>
			</ec:checkbox>
		</ed:col>
	</ed:row>

	<ed:row style="form">
		<ed:col size="12">
			<ec:button label="Submit form"/>
		</ed:col>
	</ed:row>

</ec:form>
				</ed:col>
				<ed:col size="6">
					<ec:prettify linenums="true"><form>
	<row style="form">
		<col size="4" classStyle="form-group has-feedback">
			<textfield name="first_name" label="First Name">
				<field-validator>
					<field-validator-rule name="notEmpty" message="Looks good!"/>
				</field-validator>
			</textfield>
		</col>
		<col size="4" classStyle="form-group has-feedback">
			<textfield name="last_name" label="Last name">
				<field-validator>
					<field-validator-rule name="notEmpty" message="Looks good!"/>
				</field-validator>
			</textfield>
		</col>
		<col size="4" classStyle="form-group has-feedback">
			<label>Username</label>
			<field-group>
				<prepend-field>
					<prepend-field-item>@</prepend-field-item>
				</prepend-field>
				<textfield name="username" placeholder="Username">
					<field-validator>
						<field-validator-rule name="notEmpty" message="Please choose a username."/>
					</field-validator>
				</textfield>
			</field-group>
		</col>
	</row>
	<row style="form">
		<col size="6" classStyle="form-group has-feedback">
			<textfield name="city" label="City">
				<field-validator>
					<field-validator-rule name="notEmpty" message="Please provide a valid city."/>
				</field-validator>
			</textfield>
		</col>
		<col size="3" classStyle="form-group has-feedback">
			<textfield name="State" label="State">
				<field-validator>
					<field-validator-rule name="notEmpty" message="Please provide a valid state."/>
				</field-validator>
			</textfield>
		</col>
		<col size="3" classStyle="form-group has-feedback">
			<textfield name="zip" label="Zip">
				<field-validator>
					<field-validator-rule name="notEmpty" message="Please provide a valid zip."/>
				</field-validator>
			</textfield>
		</col>
	</row>
	<row style="form">
		<col size="12" classStyle="form-group has-feedback">
			<checkbox name="checkbox" label="Agree to terms and conditions">
				<field-validator>
					<field-validator-rule name="notEmpty" message="You must agree before submitting."/>
				</field-validator>
			</checkbox>
		</col>
	</row>
	<row style="form">
		<col size="12">
			<button label="Submit form"/>
		</col>
	</row>
</form></ec:prettify>
				</ed:col>
			</ed:row>

		</ed:container>    
    </section>
        	 
	<ec:include uri="/includes/footer.jsp"/>
 
</body>
</html>