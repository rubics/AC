package app.models;

// {"bookmark":[{"bookmark_id":"9","b_id":"4","u_id":"364","b_name":null,"b_desc":"","logo":null}]}

public class Bookmark {
	
	private String bookmark_id;
	private String b_id;
	private String u_id;
	private String b_name;
	private String b_desc;
	private String logo;
	private DealDetails dealDetails = null;

	public Bookmark(String _bookmark_id, String _b_id, String _u_id, String _b_name,
			String _b_desc, String _logo) {
		super();
		bookmark_id = _bookmark_id;
		b_id = _b_id;
		u_id = _u_id;
		b_name = _b_name;
		b_desc = _b_desc;
		logo = _logo;
	}
	
	public void setDealDetails(DealDetails dealDetails) {
		this.dealDetails = dealDetails;
	}
	
	public String getBookmark_id() {
		return bookmark_id;
	}
	public String getB_id() {
		return b_id;
	}
	public String getU_id() {
		return u_id;
	}
	public String getB_name() {
		return b_name;
	}
	public String getB_desc() {
		return b_desc;
	}
	public String getLogo() {
		return logo;
	}
	
	public DealDetails getDealDetails(){
		return dealDetails;
	}
	public String toString(){
		return ("Bookmark_id: " + bookmark_id + "\nBusiness_id: " + b_id + "\nBusiness_name: " + b_name);
	}
	

}
