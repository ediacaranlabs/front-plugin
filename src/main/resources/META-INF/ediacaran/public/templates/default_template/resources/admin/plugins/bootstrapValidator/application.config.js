$.fn.bootstrapValidator.DEFAULT_OPTIONS.excluded = [function($field, $validator) {
	//$field = $('#' + $field.attr("id"));
	//console.log($field.attr("id") + " " + document.body.contains($field[0]));
    //return !document.body.contains($field[0]);//!$field.length;
    if(!document.body.contains($field[0])){
    	console.log($field.attr("id") + ":" + $field.attr("name") + " " + document.body.contains($field[0]));
		try{
			this.removeField($field[0]);
    	}
		catch($ex){
		}
    }
    return !document.body.contains($field[0]);
}];

$.AppContext.validator = {
		
		isConfigured: function (formID){
			var $bv = $('#' + formID).data('bootstrapValidator');
			return typeof $bv !== "undefined";
		},
		
		configureForm: function(formID){
			$('#' + formID)
			.bootstrapValidator({
				feedbackIcons : {
					valid : '',
					invalid : '',
					validating : ''
					/*	
					valid : ' icon-ok',
					invalid : ' icon-remove',
					validating : ' icon-refresh'
					*/
				}
			});
		},
		
		addRules: function (rules){
			
			let $fields = $.AppContext.utils.getListById(rules.field);

			for(let i=0;i<$fields.length;i++){
				this.addRulesField($fields[i], rules);
			}
			
		},

		addRulesField: function ($field, rules){
			
			if($field == null){
				console.log("field not found: " + rules.field);
				return;
			}
			
			if(rules.form == null || rules.form == ""){
				let $form = $field.getForm();
				
				if($form == null){
					console.log("form not exist for field " + rules.field);
					return;
				}
				
				rules.form = $form.getAttribute("id");
				 
			}
			
			if(!this.isConfigured(rules.form)){
				this.configureForm(rules.form);
			}
			
			var validator = this.createValidatorField(rules);
			
			try{
				$('#' + rules.form).bootstrapValidator('removeField', $field.getAdvise());
			}
			catch($ex){
			}
			
			$('#' + rules.form).bootstrapValidator('addField', $field.getAdvise(), validator);
		},
		
		createValidatorField: function(rules){
			
			var validator = {
					validators: {}
			};
			
			for (r of rules.rules) {
				validator.validators[r.rule] = this.getValidatorRule(r);
			}
			//console.log(rules.form + "-> " + rules.field + ": " + JSON.stringify(validator));
			return validator;
		},
		
		getValidatorRule: function (rule){
			
			var r = {
					"message": rule.message
			};
			
			for (p of rule.params) {
				r[p.name] = p.value
			}
			return r;
		}


}