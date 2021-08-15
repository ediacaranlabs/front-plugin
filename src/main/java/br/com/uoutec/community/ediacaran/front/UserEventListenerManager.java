package br.com.uoutec.community.ediacaran.front;

import java.io.Serializable;

public interface UserEventListenerManager {

	void asyncFireEvent(UserEvent e);

	UserEvent[] getEvents();
	
	public static class UserEvent implements Serializable{
		
		private static final long serialVersionUID = -8891288318423166567L;

		private String sourceID;
		
		private String type;
		
		private Object data;

		public UserEvent(String sourceID, String type, Object data) {
			this.sourceID = sourceID;
			this.type = type;
			this.data = data;
		}

		public String getSourceID() {
			return sourceID;
		}

		public void setSourceID(String sourceID) {
			this.sourceID = sourceID;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}
		
	}
}
