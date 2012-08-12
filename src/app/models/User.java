package app.models;

public class User {
	private String user_name;
	private String user_id = "574";
	private String session_id;

	public User(String _user_name, String _user_id, String _session_id){
		user_name = _user_name;
		user_id = _user_id;
		session_id = _session_id;
	}

	public String getUserName(){
		return user_name;
	}

	public String getUserId(){
		return user_id;
	}
	
	public String getSessionId(){
		return session_id;
	}

}