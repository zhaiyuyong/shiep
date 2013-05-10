package cn.edu.shiep.model;

public class RequestModel extends BaseModel{

	
	private static final long serialVersionUID = 1L;
	private String head;
	private String body;
	
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public RequestModel(String head, String body) {
		this.head = head;
		this.body = body;
	}
	public RequestModel() {
		super();
	}
	
	
}
