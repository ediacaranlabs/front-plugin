package br.com.uoutec.community.ediacaran.front.pub;

import java.util.Map;

import org.brandao.brutos.ClassUtil;

import br.com.uoutec.community.ediacaran.core.system.util.EntityInheritanceUtil;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.pub.entity.AbstractPubEntity;
import br.com.uoutec.pub.entity.InvalidRequestException;

public abstract class GenericPubEntity<T> extends AbstractPubEntity<T>{

	private static final long serialVersionUID = -6586803845766008609L;

	private Map<String,String> data;

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	protected abstract String getGenericType();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected T createInstance(T instance, boolean reload, boolean override, boolean validate) throws Throwable {

		String type = getGenericType();
		
		EntityInheritanceUtil entityInheritanceUtil = 
				EntityContextPlugin.getEntity(EntityInheritanceUtil.class);
		Map<String, Class<?>> clazzMap = entityInheritanceUtil.getMap(getClass());
		Class<? extends GenericPubEntity> ptype = (Class<? extends GenericPubEntity>) clazzMap.get(type);

		GenericPubEntity p;
		
		try {
			p = ClassUtil.getInstance(ptype);
		}
		catch(Throwable e) {
			throw new InvalidRequestException("invalid type: " + ptype, e);
		}
		
		p.setData(data);
		p.loadProperties(this);
		
		return (T) p.rebuild(instance, reload, override, validate);
		
	}

	protected abstract void loadProperties(GenericPubEntity<T> e);
	
}
