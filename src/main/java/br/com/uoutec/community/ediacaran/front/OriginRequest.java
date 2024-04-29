package br.com.uoutec.community.ediacaran.front;

public class OriginRequest {

	private String ip;
	
	private String userAgent;

	public OriginRequest(String ip, String userAgent) {
		this.ip = ip;
		this.userAgent = userAgent;
	}
	
	public String toText() {
		return "IP: " + ip + ", userAgent: " + userAgent;
	}
	
	public String toString() {
		return "<b>IP</b>: " + ip + "<br> <b>User-Agent</b>: " + userAgent;
		
	}
}
