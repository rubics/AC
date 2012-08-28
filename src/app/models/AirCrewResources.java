package app.models;

public class AirCrewResources {
	public static Country[] countries;// = {new Country("", "Select a country")};
	public static Airline[] airlines;// = {new Airline("", "Select Airline", "")};
	public static Gender[] genders;// = {new Gender("", "Select Gender")};
	public static Designation[] designations;// = {new Designation("", "Select Designation")};
	
	
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
	
	public static int getCountryIndex(String id){
		for(int i=0; i < countries.length; i++){
			if(countries[i].getCountry_code().equals(id))
				return i;
		}
		return 0;
	}
	
	public static int getGenderIndex(String id){
		for(int i=0; i < genders.length; i++){
			if(genders[i].getId().equals(id))
				return i;
		}
		return 0;
	}
	
	public static int getDestinationIndex(String id){
		for(int i=0; i < designations.length; i++){
			if(designations[i].getId().equals(id))
				return i;
		}
		return 0;
	}
	
	public static int getAirlineIndex(String id){
		System.out.println(id);
		for(int i=0; i < airlines.length; i++){
			System.out.println(">> " + airlines[i].getAirline_id());
			if(airlines[i].getAirline_id().equals(id)){
				System.out.println(">> Return: " + String.valueOf(i));
				return i;
			}
		}
		return 0;
	}
}
