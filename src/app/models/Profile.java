package app.models;

public class Profile {
	private String user_id;
	private String user_name;
	private String user_email;
	private String airline;
	private String gender;
	private String about_me;
	private String designation;
	private String country;
	private String city_id;
	private String airline_name;
	private String airline_id;
	private String type;
	private String desig;
	private String city_name;
	private String country_code;
	
	public Profile(String userId, String userName, String userEmail,
			String airline, String gender, String aboutMe, String designation,
			String country, String city_id, String airlineName, String airlineId,
			String type, String desig, String cityName, String countryCode) {
		super();
		user_id = userId;
		user_name = userName;
		user_email = userEmail;
		this.airline = airline;
		this.gender = gender;
		about_me = aboutMe;
		this.designation = designation;
		this.country = country;
		this.city_id = city_id;
		airline_name = airlineName;
		airline_id = airlineId;
		this.type = type;
		this.desig = desig;
		city_name = cityName;
		country_code = countryCode;
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

	public String getAirline() {
		return airline;
	}

	public String getGender() {
		return gender;
	}

	public String getAbout_me() {
		return about_me;
	}

	public String getDesignation() {
		return designation;
	}

	public String getCountry() {
		return country;
	}

	public String getCityId() {
		return city_id;
	}

	public String getAirline_name() {
		return airline_name;
	}

	public String getAirline_id() {
		return airline_id;
	}

	public String getType() {
		return type;
	}

	public String getDesig() {
		return desig;
	}

	public String getCity_name() {
		return city_name;
	}

	public String getCountry_code() {
		return country_code;
	}
	
	
}
