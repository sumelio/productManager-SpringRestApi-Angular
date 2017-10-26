package co.beitech.productManager.domain;

public class Response {
	private String description;
	private int code;
	
	
	
	public Response(String description, int code) {
		super();
		this.description = description;
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	

}
