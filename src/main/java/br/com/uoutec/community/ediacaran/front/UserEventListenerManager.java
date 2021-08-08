package br.com.uoutec.community.ediacaran.front;

public interface UserEventListenerManager {

	void asyncFireEvent(UserEvent e);

	UserEvent[] getEvents();
	
	public static class UserEvent{
		
		private String sourceID;
		
		private String type;
		
		private Object data;

		public UserEvent(String sourceID, String type, Object data) {
			this.sourceID = sourceID;
			this.type = type;
			this.data = data;
		}

		public void setSourceID(String sourceID) {
			this.sourceID = sourceID;
		}

		public void setType(String type) {
			this.type = type;
		}

		public void setData(Object data) {
			this.data = data;
		}
		
	}
}
