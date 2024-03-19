package br.com.uoutec.community.ediacaran.front.pub;

import javax.annotation.Generated;

import br.com.uoutec.community.ediacaran.system.repository.ObjectMetadata;
import br.com.uoutec.community.ediacaran.system.repository.ObjectValue;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsManagerDriver.ObjectsManagerDriverListener;
import br.com.uoutec.community.ediacaran.system.repository.PathMetadata;

public class InstallMenuListener 
	implements ObjectsManagerDriverListener {

	private final String path;
	
	private final String id;
	
	private IMenusParams menus;

	@Generated("SparkTools")
	private InstallMenuListener(Builder builder) {
		this.path = builder.path;
		this.id = builder.id;
		this.menus = builder.menus;
	}
	
	public InstallMenuListener(String path, String id, IMenusParams menus) {
		super();
		this.path = path;
		this.id = id;
		this.menus = menus;
	}

	public void afterLoad(ObjectMetadata omd, ObjectValue obj) {
		
		if(obj == null) {
			return;
		}

		PathMetadata pmd = omd.getPathMetadata();
		
		if(obj.getObject() instanceof MenuBar && pmd.getPath().equals(path)) {
			
			if(pmd.getId().equals(id)) {
				MenuBar menuBar = (MenuBar)obj.getObject();
				menus.set(menuBar);
			}
		}
		
	}

	public interface IMenusParams {
		
		void set(MenuBar value);
		
	}
	
	@Generated("SparkTools")
	public static IPathStage builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public interface IPathStage {
		public IIdStage withPath(String path);
	}

	@Generated("SparkTools")
	public interface IIdStage {
		public IMenusStage withId(String id);
	}

	@Generated("SparkTools")
	public interface IMenusStage {
		public IBuildStage withMenu(IMenusParams menus);
	}
	
	@Generated("SparkTools")
	public interface IBuildStage {
		public InstallMenuListener build();
		
	}

	@Generated("SparkTools")
	public static final class Builder implements IPathStage, IMenusStage, IIdStage, IBuildStage {
		private String path;
		private String id;
		private IMenusParams menus;

		private Builder() {
		}

		@Override
		public IIdStage withPath(String path) {
			this.path = path;
			return this;
		}

		@Override
		public IMenusStage withId(String id) {
			this.id = id;
			return this;
		}

		@Override
		public IBuildStage withMenu(IMenusParams menus) {
			this.menus = menus;
			return this;
		}
		
		@Override
		public InstallMenuListener build() {
			return new InstallMenuListener(this);
		}
	}
	
}
