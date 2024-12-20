$.fn.bootstrapValidator.DEFAULT_OPTIONS.excluded = [function($field, $validator) {
	//$field = $('#' + $field.attr("id"));
	//console.log($field.attr("id") + " " + document.body.contains($field[0]));
    //return !document.body.contains($field[0]);//!$field.length;
    if(!document.body.contains($field[0])){
    	console.log($field.attr("name") + " " + document.body.contains($field[0]));
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
			}
			catch($ex){
				//console.log(rules.field + " " + $ex);
			}

			try{
				$('#' + rules.form).bootstrapValidator('removeField', $f.attr("name"));
			}
			catch($ex){
				//console.log(rules.field + " " + $ex);
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