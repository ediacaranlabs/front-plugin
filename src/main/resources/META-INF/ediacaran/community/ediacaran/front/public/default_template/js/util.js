/**
 * Utilitário.
 * @author Brandao
 */
Util = {
};

/**
 * Verifica se um documento do tipo CPF ou CNPJ é válido.
 * @param doc Documento.
 * @returns Verdadeiro se o documento é válido. Caso contrário,
 * falso.
 */
Util.isValidDocument = function(doc){
	
	if(doc == null || (doc.length != 11 && doc.length != 14)){
		return false;
	}
	
	var chars  = doc.split('');
	var dv1;
	var dv2;
	var i;
	var r = false;
	var tmpChar;
	
	tmpChar = chars[0];
	for(i=0;i<chars.length;i++){
		if(tmpChar != chars[i]){
			r = true;
			break;
		}
	}
	
	if(!r){
		return false;
	}
	
	if(doc.length == 11){
		dv1 = this.calcDV(doc, 0, 9, 10);
		dv2 = this.calcDV(doc, 0, 10, 11);
	}
	else
	if(doc.length == 14){
		dv1 = this.calcDV(doc, 0, 12, 5);
		dv2 = this.calcDV(doc, 0, 13, 6);
	}
	else{
		return false;
	}
	
	r = 
		parseInt(chars[doc.length - 2]) == dv1 && 
		parseInt(chars[doc.length - 1]) == dv2;
	return r;
	
};

/**
 * Calcula o digito verificador de um documento
 * do tipo CPF e CNPJ.
 * @param doc Documento.
 * @param start Posição inicial do calculo.
 * @param len Quantidade de posições que serão calculadas.
 * @param p peso usado no calculo.
 * @returns Digito verificador do intervalo calculado.
 */
Util.calcDV = function(doc, start, len, p){
	
	if(doc == null){
		return false;
	}
	
	var chars  = doc.split('');
	var length = start + len;
	var soma   = 0;
	var dv;
	var i;
	
	for(i=start;i<length;i++){
		soma += chars[i]*p;
		p--;
		
		if(p < 2){
			p = 9;
		}
		
	}
	
	dv = soma % 11;
	dv = dv <2? 0 : 11 - dv;
	return dv;
};
