package br.com.uoutec.community.ediacaran.front.pub;

import javax.annotation.Generated;

import br.com.uoutec.community.ediacaran.system.repository.ObjectMetadata;
import br.com.uoutec.community.ediacaran.system.repository.ObjectValue;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsManagerDriver.ObjectsManagerDriverListener;
import br.com.uoutec.community.ediacaran.system.repository.PathMetadata;

public class InstallMenuListener 
	implements ObjectsManagerDriverListener {

	private final Object globalID;
	
	private final String path;
	
	private final String id;
	
	private IMenusParams menus;

	@Generated("SparkTools")
	private InstallMenuListener(Builder builder) {
		this.path = builder.path;
		this.id = builder.id;
		this.menus = builder.menus;
		this.globalID = builder.globalID;
	}
	
	public InstallMenuListener(Object globalID, String path, String id, IMenusParams menus) {
		super();
		this.path = path;
		this.id = id;
		this.menus = menus;
		this.globalID = globalID;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((globalID == null) ? 0 : globalID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstallMenuListener other = (InstallMenuListener) obj;
		if (globalID == null) {
			if (other.globalID != null)
				return false;
		} else if (!globalID.equals(other.globalID))
			return false;
		return true;
	}

	public interface IMenusParams {
		void set(MenuBar value);
	}
	
	@Generated("SparkTools")
	public static IGlobalIDStage builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public interface IGlobalIDStage {
		public IPathStage withGlobalID(Object globalID);
	}
	
	@Generated("SparkTools")
	public interface IPathStage extends IBuildStage {
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
	public static final class Builder implements IPathStage, IGlobalIDStage, IMenusStage, IIdStage, IBuildStage {
		private String path;
		private String id;
		private Object globalID;
		private IMenusParams menus;

		private Builder() {
		}

		public IPathStage withGlobalID(Object globalID) {
			this.globalID = globalID;
			return this;
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
