package app.models;

public class User {
	private String user_name;
	private String user_id;
	private String session_id;
	private String password;

	public User(String _user_name, String _user_id, String _session_id, String _password){
		user_name = _user_name;
		user_id = _user_id;
		session_id = _session_id;
		password = _password;
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

	public String getPassword() {
		return password;
	}
	
//	public String getEmail(){
//		return "pratuat@gmail.com";
//	}
}