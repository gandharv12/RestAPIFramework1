package Resources;
//enum is special class in Java which is collection of constant or methods

public enum Apiresource {
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resource;

	Apiresource(String resource) {
		
		this.resource=resource;
		// TODO Auto-generated constructor stub
	} 
	public String getResource() {
		return resource;
	}

}
