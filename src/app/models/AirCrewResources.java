package app.models;

public class AirCrewResources {
	public static Country[] countries;
	public static Airline[] airlines;
	
	public AirCrewResources(){
		
		// populate countries
		CountriesRequest countryRequest = new CountriesRequest(){
			public void afterSuccess(Country[] _countries){
				countries = _countries;
			}
		};
		countryRequest.getCountries();
		
		AirlinesRequest airlineRequest = new AirlinesRequest() {
			public void onSuccess(Airline[] _airlines) {
				airlines = _airlines;
			}
		};
		airlineRequest.getAirlines();
	}
}
