package br.com.uoutec.community.ediacaran.front;

import org.brandao.brutos.ResultAction;
import org.brandao.brutos.web.WebMvcRequest;

import br.com.uoutec.application.security.PrivilegedActionParams;

public interface SectionView extends PrivilegedActionParams<ResultAction>{

	ResultAction getView(WebMvcRequest request);
	
}
