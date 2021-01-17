//constantes usadas na montagem das expressões regulares da aplicação 
regexPatternConstants = {
		
		SPANISH_CHARS: function(){ return "aábcdeéfghijklmnñoópqrstuúüvwxyzAÁBCDEÉFGHIJKLMNÑOÓPQRSTUÚÜ‌​VWXYZ";},
		
		PORTUGUESE_CHARS: function(){ return "éúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄ";},

		EASTERN_CHARS: function(){ return "一-龠ぁ-ゔァ-ヴーＡ-Ｚ０-９々〆〤";},

		//ALL_CHARS: function(){ return this.SPANISH_CHARS() + this.PORTUGUESE_CHARS() + this.EASTERN_CHARS() + "0-9";},
		ALL_CHARS: function(){ return "a-z0-9";},
		
		VALID_CHAR_PASSWORD: function(){ return "[a-zA-Z0-9\p{Ll}\p{Lu}\!\@\#\$\%\&\?\*]";},
		
		WORD: function(){ return "([" + this.SPANISH_CHARS() + this.PORTUGUESE_CHARS() + this.EASTERN_CHARS() + "]+\\.?)((\\'|\\-)?[" + this.SPANISH_CHARS() + this.PORTUGUESE_CHARS() + this.EASTERN_CHARS() + "]+\\.?)*";},

		WORD_NUM: function(){ return "([" + this.SPANISH_CHARS() + this.PORTUGUESE_CHARS() + this.EASTERN_CHARS() + "\\d]+\\.?)((\\'|\\-)?[" + this.SPANISH_CHARS() + this.PORTUGUESE_CHARS() + this.EASTERN_CHARS() + "\\d]+\\.?)*";},

		NAME_FORMAT: function(){ return "^" + this.WORD() + "(\\,?\\s" + this.WORD() + ")*$";},

		ADDRESS_FORMAT: function(){ return "^" + this.WORD_NUM() + "(((\\,\\s?)|\\s)" + this.WORD_NUM() + ")*$";},
		
		PASSWORD_FORMAT: function(){
			return "^" + this.VALID_CHAR_PASSWORD() + "{12,60}$";
			/*
			return "^(" +
			"([\p{Ll}0-9\!\@\#\$\%\&]" + this.VALID_CHAR_PASSWORD() + "[\p{Lu}]" + this.VALID_CHAR_PASSWORD() + "[0-9]" + this.VALID_CHAR_PASSWORD() + ")|" +
			"([\p{Ll}0-9\!\@\#\$\%\&]" + this.VALID_CHAR_PASSWORD() + "[0-9]" + this.VALID_CHAR_PASSWORD() + "[\p{Lu}]" + this.VALID_CHAR_PASSWORD() + ")|" +

			"([0-9]" + this.VALID_CHAR_PASSWORD() + "[\p{Lu}]" + this.VALID_CHAR_PASSWORD() + "[\p{Ll}0-9\!\@\#\$\%\&]" + this.VALID_CHAR_PASSWORD() + ")|" +
			"([0-9]" + this.VALID_CHAR_PASSWORD() + "[\p{Ll}0-9\!\@\#\$\%\&]" + this.VALID_CHAR_PASSWORD() + "[A-Z]" + this.VALID_CHAR_PASSWORD() + ")|" +
			
			"([\p{Lu}]" + this.VALID_CHAR_PASSWORD() + "[0-9]" + this.VALID_CHAR_PASSWORD() + "[\p{Ll}0-9\!\@\#\$\%\&]" + this.VALID_CHAR_PASSWORD() + ")|" +
			"([\p{Lu}]" + this.VALID_CHAR_PASSWORD() + "[\p{Ll}0-9\!\@\#\$\%\&]" + this.VALID_CHAR_PASSWORD() + "[0-9]" + this.VALID_CHAR_PASSWORD() + ")" +
			")$";
			*/
		}		
		
};

//expressões regulares da aplicação
regexPatterns = {

		CVV: /^[0-9]{3,4}$/,
		
		UUID: /[0-9a-f]{8}\-[0-9a-f]{4}\-[0-9a-f]{4}\-[0-9a-f]{4}\-[0-9a-f]{12}/,
		
		EMAIL: /^[a-z0-9!#$%&'*+\/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+\/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/,

		NAME_FORMAT: new RegExp(regexPatternConstants.NAME_FORMAT()),

		ADDRESS_FORMAT:	new RegExp(regexPatternConstants.ADDRESS_FORMAT()),

		PASSWORD: new RegExp(regexPatternConstants.PASSWORD_FORMAT())

};
