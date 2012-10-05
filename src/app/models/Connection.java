package app.models;

public class Connection implements Identity{
	private String id;
	private String user_id;
	private String user_name;
	private String user_email;
	private String about_me;
	private String airline;
	private String gender;
	private String designation;
	private String country;
	private String city_id;
	private String airline_name;
	private String type;
	private String desig;
	private String city_name;
	private String image_name;
	private String last_visit;
	private String visit_status;
	
	public Connection(String id, String userId, String userName,
			String userEmail, String aboutMe, String airline, String gender,
			String designation, String country, String cityId,
			String airlineName, String type, String desig, String cityName,
			String imageName, String lastVisit, String visitStatus) {
		super();
		this.id = id;
		user_id = userId;
		user_name = userName;
		user_email = userEmail;
		about_me = aboutMe;
		this.airline = airline;
		this.gender = gender;
		this.designation = designation;
		this.country = country;
		city_id = cityId;
		airline_name = airlineName;
		this.type = type;
		this.desig = desig;
		city_name = cityName;
		image_name = imageName;
		last_visit = lastVisit;
		visit_status = visitStatus;
	}
	
	public String getId() {
		return id;
	}
	public String getUser_id() {
		return user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public String getAbout_me() {
		return about_me;
	}
	public String getAirlineId() {
		return airline;
	}
	public String getGender() {
		return gender;
	}
	public String getDesig() {
		return designation;
	}
	public String getCountry() {
		return country;
	}
	public String getCity_id() {
		return city_id;
	}
	public String getAirline() {
		return airline_name;
	}
	public String getType() {
		return type;
	}
	public String getDesignation() {
		return desig;
	}
	public String getCity_name() {
		return city_name;
	}
	public String getImage_name() {
		return image_name;
	}
	public String getLast_visit() {
		return last_visit;
	}
	public String getVisit_status() {
		return visit_status;
	}
	
	public String toString(){
		return "User_name: " + user_name + ">> Image_name: " + image_name;
	}
	
	public String getLocation(){
		return getCity_name() + ", " + getCountry();
	}
}
