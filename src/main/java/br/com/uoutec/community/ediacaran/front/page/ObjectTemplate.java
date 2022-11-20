package br.com.uoutec.community.ediacaran.front.page;

import br.com.uoutec.community.ediacaran.VarParser;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;

public class ObjectTemplate {
		
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
			VarParser varParser = EntityContextPlugin.getEntity(VarParser.class);
			return varParser.getValue(formPath);
		}

		public String getTemplate() {
			VarParser varParser = EntityContextPlugin.getEntity(VarParser.class);
			return varParser.getValue(template);
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
