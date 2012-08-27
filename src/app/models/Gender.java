package app.models;

public class Gender {
	private String id;
	private String type;
	public Gender(String id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	
	public String toString(){
		return type;
	}
}
