package app.models;

public class Airline {
	
	private String id;
	private String name;
	private String airline_id;
	
	public Airline(String id, String name, String airlineId) {
		this.id = id;
		this.name = name;
		airline_id = airlineId;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAirline_id() {
		return airline_id;
	}
	
	public String toString(){
		return name;
	}
}