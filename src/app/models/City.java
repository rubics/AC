package app.models;

public class City {
	private String city_id;
	private String city_name;
	
	public City(String _city_id, String _city_name){
		city_id = _city_id;
		city_name = _city_name;
	}

	public String getCity_id() {
		return city_id;
	}

	public String getCity_name() {
		return city_name;
	}
	
	public String toString(){
		return city_name;
	}
}
