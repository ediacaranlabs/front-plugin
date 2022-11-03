package br.com.uoutec.community.ediacaran.front.page;

import java.util.List;
import java.util.Map;

public interface ObjectTemplateManager {

	void registerTemplate(String type, String id, String name, String formPath, String template) throws PageTemplateManagerException;

	void unregisterTemplate(String type, String id);
	
	ObjectTemplate getTemplate(String type, String id);
	
	List<ObjectTemplate> getTemplates(String type);

	Map<String,ObjectTemplate> getTemplatesIdMap(String type);
	
	public static class PageTemplateManagerException extends RuntimeException{

		private static final long serialVersionUID = 1231376961383126039L;

		public PageTemplateManagerException() {
			super();
		}

		public PageTemplateManagerException(String message, Throwable cause) {
			super(message, cause);
		}

		public PageTemplateManagerException(String message) {
			super(message);
		}

		public PageTemplateManagerException(Throwable cause) {
			super(cause);
		}
		
	}
	
	public static class ObjectTemplate {
		
		private String id;
		
		private String name;
		
		private String formPath;
		
		private String template;

		public ObjectTemplate(String id, String name, String formPath, String template) {
			this.id = id;
			this.name = name;
			this.formPath = formPath;
			this.template = template;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getFormPath() {
			return formPath;
		}

		public String getTemplate() {
			return template;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
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
			ObjectTemplate other = (ObjectTemplate) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
		
	}
	
}
