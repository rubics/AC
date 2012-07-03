package app.models;

public class User {
	private String user_name;
	private String user_id;

	public User(String name, String id){
		user_name = name;
		user_id = id;
	}

	public String getUserName(){
		return user_name;
	}

	public String getUserId(){
		return user_id;
	}

}