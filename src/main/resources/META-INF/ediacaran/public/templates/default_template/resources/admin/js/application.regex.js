$.AppContext.regexUtil = {};

$.AppContext.regexUtil.constants = {
	
		SPANISH_CHARS: function(){ 
			return "aábcdeéfghijklmnñoópqrstuúüvwxyzAÁBCDEÉFGHIJKLMNÑOÓPQRSTUÚÜ‌​VWXYZ";
		},
		
		PORTUGUESE_CHARS: function(){ 
			return "éúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄ";
		},

		EASTERN_CHARS: function(){ 
			return "一-龠ぁ-ゔァ-ヴーＡ-Ｚ０-９々〆〤";
		},

		//ALL_CHARS: function(){ return this.SPANISH_CHARS() + this.PORTUGUESE_CHARS() + this.EASTERN_CHARS() + "0-9";},
		ALL_CHARS: function(){ 
			return "a-z0-9";
		},
		
		VALID_CHAR_PASSWORD: function(){ 
			return "[a-zA-Z0-9\p{Ll}\p{Lu}\!\@\#\$\%\&\?\*]";
		},
		
		WORD: function(){ 
			return "([" + 
				$.AppContext.regexUtil.constants.SPANISH_CHARS() + 
				$.AppContext.regexUtil.constants.PORTUGUESE_CHARS() + 
				$.AppContext.regexUtil.constants.EASTERN_CHARS() + 
				"]+\\.?)((\\'|\\-)?[" + 
				$.AppContext.regexUtil.constants.SPANISH_CHARS() + 
				$.AppContext.regexUtil.constants.PORTUGUESE_CHARS() + 
				$.AppContext.regexUtil.constants.EASTERN_CHARS() +
				"]+\\.?)*";
		},

		WORD_NUM: function(){ 
			return "([" + 
				$.AppContext.regexUtil.constants.SPANISH_CHARS() + 
				$.AppContext.regexUtil.constants.PORTUGUESE_CHARS() + 
				$.AppContext.regexUtil.constants.EASTERN_CHARS() + 
				"\\d]+\\.?)((\\'|\\-)?[" + 
				$.AppContext.regexUtil.constants.SPANISH_CHARS() + 
				$.AppContext.regexUtil.constants.PORTUGUESE_CHARS() + 
				$.AppContext.regexUtil.constants.EASTERN_CHARS() + 
				"\\d]+\\.?)*";
		},

		NAME_FORMAT: function(){ 
			return "^" + 
				$.AppContext.regexUtil.constants.WORD() + 
				"(\\,?\\s" + 
				$.AppContext.regexUtil.constants.WORD() + 
				")*$";
		},

		ADDRESS_FORMAT: function(){ 
			return "^" + 
				$.AppContext.regexUtil.constants.WORD_NUM() + 
				"(((\\,\\s?)|\\s)" + 
				$.AppContext.regexUtil.constants.WORD_NUM() + 
				")*$";
		},
		
		PASSWORD_FORMAT: function(){
			return "^" + 
				this.VALID_CHAR_PASSWORD() + 
				"{12,60}$";
		}
				
};

//expressões regulares da aplicação
$.AppContext.regexUtil.patterns = function(){

	return {
	
			CVV: /^[0-9]{3,4}$/,
			
			UUID: /[0-9a-f]{8}\-[0-9a-f]{4}\-[0-9a-f]{4}\-[0-9a-f]{4}\-[0-9a-f]{12}/,
			
			EMAIL: /^[a-z0-9!#$%&'*+\/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+\/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/,
	
			NAME_FORMAT: new RegExp($.AppContext.regexUtil.constants.NAME_FORMAT()),
	
			ADDRESS_FORMAT:	new RegExp($.AppContext.regexUtil.constants.ADDRESS_FORMAT()),
	
			PASSWORD: new RegExp($.AppContext.regexUtil.constants.PASSWORD_FORMAT()),
			
			WORD_NUM: new RegExp($.AppContext.regexUtil.constants.WORD_NUM())
	
	};
	
}


 