package br.com.uoutec.community.ediacaran.front;

import java.util.Properties;

import org.brandao.brutos.BrutosException;
import org.brandao.brutos.ObjectFactory;

public class ReflectionObjectFactory implements ObjectFactory {

	@Override
	public Object getBean(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getBean(Class clazz) {
		try{
			return clazz.newInstance();
		}
		catch(Throwable e) {
			throw new BrutosException(e);
		}
	}

	@Override
	public void configure(Properties properties) {
	}

	@Override
	public void destroy() {
	}

}
