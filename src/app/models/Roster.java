package app.models;

public class Roster {
	private String country;
	private String city;
	private String from_date;
	private String to_date;
	private String my_fav;
	
	public Roster(String country, String city, String fromDate,
			String toDate, String myFav) {
		super();
		this.country = country;
		this.city = city;
		from_date = fromDate;
		to_date = toDate;
		my_fav = myFav;
	}

	public String getCountry() {
		return country;
	}

	public String getCityCode() {
		return city;
	}

	public String getFrom_date() {
		return from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public String getMy_fav() {
		return my_fav;
	}
	
	public String toString(){
		return "Roster: ".concat(country).concat("., " + city).concat("., " + from_date).concat("., " + to_date);
	}
}
