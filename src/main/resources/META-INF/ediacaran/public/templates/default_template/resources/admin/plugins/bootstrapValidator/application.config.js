$.fn.bootstrapValidator.DEFAULT_OPTIONS.excluded = [function($field, $validator) {
	$field = $('#' + $field.attr("id"));
    return !$field.length;
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
			
			if(!this.isConfigured(rules.form)){
				this.configureForm(rules.form);
			}
			
			var validator = this.createValidatorField(rules);
			var $f = $("#" + rules.field);
			
			//try{
				//$('#' + rules.form).bootstrapValidator('removeField', rules.field);
			//}
			//catch($ex){
			//}

			try{
				$('#' + rules.form).bootstrapValidator('removeField', $f);
				console.log("remove " + rules.field);
			}
			catch($ex){
			}

			try{
				$('#' + rules.form).bootstrapValidator('removeField', $f.attr("name"));
				console.log("remove " + $f.attr("name"));
			}
			catch($ex){
			}

			$('#' + rules.form).bootstrapValidator('addField', $f, validator);	
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