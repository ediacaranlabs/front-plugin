package br.com.uoutec.community.ediacaran.front.pub;

import java.util.Map;

import org.brandao.brutos.ClassUtil;
import org.brandao.brutos.annotation.Transient;

import br.com.uoutec.community.ediacaran.system.entity.EntityInheritanceManager;
import br.com.uoutec.ediacaran.core.plugins.EntityContextPlugin;
import br.com.uoutec.pub.entity.AbstractPubEntity;
import br.com.uoutec.pub.entity.InvalidRequestException;

public abstract class GenericPubEntity<T> extends AbstractPubEntity<T>{

	private static final long serialVersionUID = -6586803845766008609L;

	private Map<String,Object> data;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public T rebuild(T instance, boolean reload, boolean override, 
			boolean validate, boolean direct) throws Throwable {
		
		preRebuild(instance, reload, override, validate);
		
		if(validate){
			validate(reload, override);
		}
		
		instance = direct? 
					super.createInstance(instance, reload, override, validate) : 
					createInstance(instance, reload, override, validate);
		
		postRebuild(instance, reload, override, validate);
		
		return instance;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected T createInstance(T instance, boolean reload, 
			boolean override, boolean validate) throws Throwable {

		String type = getCodeType();
		
		EntityInheritanceManager entityInheritanceUtil = 
				EntityContextPlugin.getEntity(EntityInheritanceManager.class);
		
		Map<String, Class<?>> clazzMap = 
				entityInheritanceUtil.getMap(getGenericType());
		
		Class<? extends GenericPubEntity> ptype;
		
		ptype = (Class<? extends GenericPubEntity>)(
			clazzMap == null || type == null? 
				getGenericType() : 
				clazzMap.containsKey(type)? clazzMap.get(type) : getGenericType()
		);

		GenericPubEntity p;
		
		try {
			p = ClassUtil.getInstance(ptype);
		}
		catch(Throwable e) {
			throw new InvalidRequestException("invalid type: " + ptype, e);
		}
		
		p.setData(data);
		p.loadProperties(this);
		return (T) p.rebuild(instance, reload, override, validate, true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transient
	public <X extends GenericPubEntity<?>> X getType() {

		String type = getCodeType();
		
		EntityInheritanceManager entityInheritanceUtil = 
				EntityContextPlugin.getEntity(EntityInheritanceManager.class);
		
		Map<String, Class<?>> clazzMap = 
				entityInheritanceUtil.getMap(getGenericType());
		
		Class<? extends GenericPubEntity> ptype;
		
		ptype = (Class<? extends GenericPubEntity>)(
			clazzMap == null || type == null? 
				null : 
				clazzMap.containsKey(type)? clazzMap.get(type) : null
		);

		if(ptype == null) {
			return (X) this;
		}
		
		GenericPubEntity p;
		
		try {
			p = ClassUtil.getInstance(ptype);
		}
		catch(Throwable e) {
			throw new InvalidRequestException("invalid type: " + ptype, e);
		}
		
		p.setData(data);
		p.loadProperties(this);
		return (X)p;
	}
	
	protected abstract String getCodeType();
	
	protected abstract Class<?> getGenericType();
	
	protected abstract void loadProperties(GenericPubEntity<T> e);
	
}
