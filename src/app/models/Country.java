package app.models;

public class Country {
	private String country_code;
	private String country_name;
	
	public Country(String _country_code, String _country_name){
		country_code = _country_code;
		country_name = _country_name;
	}

	public String getCountry_code() {
		return country_code;
	}

	public String getCountry() {
		return country_name;
	}
	
	public String toString(){
		return country_name;
	}
	
}
