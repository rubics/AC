package app.models;

public class AirCrewResources {
	public static Airline[] airlines;
	
	public AirCrewResources(){
		AirlinesRequest airlineRequest = new AirlinesRequest() {
			public void onSuccess(Airline[] _airlines) {
				airlines = _airlines;
			}
		};
		airlineRequest.getAirlines();
	}
}
