package app.models;

public class Deal {
	
	private String id;
	private String name;
	private String description;
	private String category_id;
	private String category_name;
	private String logo;
	private String city;
	private String x_code;
	private String y_code;
	private String phone;
	private String email;
	private DealDetails dealDetails = null;
	
	public Deal(String _id,
			String _name,
			String _description,
			String _category_id,
			String _category_name,
			String _logo,
			String _city,
			String _x_code,
			String _y_code,
			String _phone,
			String _email){
		id = _id;
		name = _name;
		description = _description;
		category_id = _category_id;
		category_name = _category_name;
		logo = _logo;
		city = _city;
		x_code = _x_code;
		y_code = _y_code;
		phone = _phone;
		email = _email;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory_id() {
		return category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public String getLogo() {
		return logo;
	}

	public String getCity() {
		return city;
	}

	public String getX_code() {
		return x_code;
	}

	public String getY_code() {
		return y_code;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String toString(){
		return "\n" + id + "\n" + name + "\n" + description + "\n" + category_id + "\n" + category_name + "\n" + logo + "\n" + city + "\n" + x_code + "\n" + y_code + "\n" + phone + "\n" + email;
	}
	
	public void setDealDetails(DealDetails _dealDetails){
		dealDetails = _dealDetails;
	}
	
	public DealDetails getDealDetails(){
		return dealDetails;
	}
}
