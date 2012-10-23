package app.models;

public class ChatHistory {
	public String id;
	public String date_time;
	public String type;
	public String text;
	public String token;
	public String user_id;
	public String user2_id;
	
	public ChatHistory(String id, String dateTime, String type, String text, String token, String user_id, String user2_id) {
		super();
		this.id = id;
		date_time = dateTime;
		this.type = type;
		this.text = text;
		this.token = token;
		this.user_id = user_id;
		this.user2_id = user2_id;
	}
	
	public String toString(){
		return "from: " + user_id + "\t" + "to: " + user2_id + "\ntype: " + type + "\nmessage: " + text;
	}
}
