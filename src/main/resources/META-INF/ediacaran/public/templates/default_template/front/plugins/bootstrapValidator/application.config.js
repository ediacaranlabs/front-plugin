$.fn.bootstrapValidator.DEFAULT_OPTIONS.excluded = [];

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
			
			$('#' + rules.form).bootstrapValidator('addField', rules.field, validator);	
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