package app.models;

public class AirCrewResources {
	public static Country[] countries;
	public static Airline[] airlines;
	public static Gender[] genders;
	public static Designation[] designations;
	
	
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
		
		DesignationRequest designationRequest = new DesignationRequest() {
			
			public void afterSuccess(Designation[] _designations) {
				designations = _designations;
			}
		};
		designationRequest.getDesignations();
		
		GenderRequest genderRequest = new GenderRequest() {
			
			public void afterSuccess(Gender[] _genders) {
				genders = _genders;
			}
		};
		genderRequest.getGenders();
	}
}
