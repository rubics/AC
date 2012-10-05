package app.models;

public class Visitor implements Identity {
	private String id;
	private String user_id;
	private String user_name;
	private String country;
	private String gender;
	private String image_name;
	private String city;
	private String airline_name;
	private String desig;
	private String visit_status;
	private String block_status;
	private String favorite_status;
	public Visitor(String id, String userId, String userName, String country,
			String gender, String imageName, String city, String airlineName,
			String desig, String visitStatus, String blockStatus,
			String favoriteStatus) {
		super();
		this.id = id;
		user_id = userId;
		user_name = userName;
		this.country = country;
		this.gender = gender;
		image_name = imageName;
		this.city = city;
		airline_name = airlineName;
		this.desig = desig;
		visit_status = visitStatus;
		block_status = blockStatus;
		favorite_status = favoriteStatus;
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
	public String getCountry() {
		return country;
	}
	public String getGender() {
		return gender;
	}
	public String getImage_name() {
		return image_name;
	}
	public String getCity() {
		return city;
	}
	public String getAirline_name() {
		return airline_name;
	}
	public String getDesig() {
		return desig;
	}
	public String getVisit_status() {
		return visit_status;
	}
	public String getBlock_status() {
		return block_status;
	}
	public String getFavorite_status() {
		return favorite_status;
	}
	
	public String getLocation(){
		return getCity() + ", " + getCountry();
	}
	
	public String getAirline() {
		return getAirline_name();
	}
	
	public String getAbout_me() {
		// TODO Auto-generated method stub
		return "";
	}
}
