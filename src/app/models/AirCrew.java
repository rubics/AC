package app.models;

import net.rim.device.api.system.Bitmap;

public class AirCrew {
	public static final String url = "http://www.4aircrew.com/dev";
	
	public static final String signin = url + "/index.php/iphone_4aircrew/login_phone";
	public static final String signup = url + "/index.php/iphone_4aircrew/i_register";
	public static final String signout = url + "/index.php/iphone_4aircrew/logout_phone";
	public static final String all_deals = url + "/index.php/iphone_4aircrew/all_deals";
	public static final String deal_detials = url + "/index.php/iphone_4aircrew/deals_details/";
	public static final String image_medium = url + "/assets/uploads/gallery/medium/";
	public static final String categories = url + "/index.php/iphone_4aircrew/i_category";
	public static final String countries = url + "/index.php/iphone_4aircrew/i_country";
	public static final String cities = url + "/index.php/iphone_4aircrew/i_city/";
	public static final String filter_deals = url + "/index.php/iphone_4aircrew/deals_filter/";
	public static final String deal_by_category = url + "/index.php/iphone_4aircrew/deals_by_cat/";
	public static final String bookmarks = url + "/index.php/iphone_4aircrew/bookmark/";
	public static final String gallery = url + "/index.php/iphone_4aircrew/get_gallery/";
	public static final String user_images = url + "/assets/uploads/app/user_images/";
	public static final String my_profile = url + "/index.php/iphone_4aircrew/i_edit_profile/";
	public static final String airlines = url + "/index.php/iphone_4aircrew/i_airline";
	public static final String update_profile = url + "/index.php/iphone_4aircrew/i_edit_profile_update/";
	public static final String my_roster = url + "/index.php/iphone_4aircrew/my_roster/";
	public static final String update_roster = url + "/index.php/iphone_4aircrew/my_roster_update/";
	public static final String delete_my_roster = url + "/index.php/iphone_4aircrew/delete_my_roster/";
	public static final String gender = url + "/index.php/iphone_4aircrew/i_gender";
	public static final String designation = url + "/index.php/iphone_4aircrew/i_designation";
	public static final String set_main_image = url + "/index.php/iphone_4aircrew/set_img_default/";
	public static final String delete_image = url + "/index.php/iphone_4aircrew/remove_gallery_img/";
	public static final String favorites = url + "/index.php/iphone_4aircrew/my_connection/";
	public static final String insert_to_gallery = url + "/index.php/iphone_4aircrew/insert_gallery_img/";
	public static final String my_visitors = url + "/index.php/iphone_4aircrew/my_visitors/";
	public static final String remove_from_favorites = url + "/index.php/iphone_4aircrew/remove_myconnection/";
	public static final String forgot_password = url + "/index.php/iphone_4aircrew/iphone_reset_pwd/";
	public static final String clear_chat_history = url + "/index.php/iphone_4aircrew/clear_chat_history/";
	public static final String clear_visitor_history = url + "/index.php/iphone_4aircrew/clear_visitor_list/";
	public static final String[] toolbox_url = {url + "/index.php/iphone_4aircrew/i_cc",
												"http://www.weather.com",
												"http://aviationweather.gov/obs/sat/intl",
												"http://aviationweather.gov/adds/metars",
												url + "/index.php/iphone_4aircrew/crew_discussion/574",
												url + "/index.php/iphone_4aircrew/recommend_aircrew"};
	
	public static final Bitmap[] toolbox = {Bitmap.getBitmapResource("images/listing/iconCurrency.png"),
		Bitmap.getBitmapResource("images/listing/iconWeather.png"),
		Bitmap.getBitmapResource("images/listing/iconSatelite.png"),
		Bitmap.getBitmapResource("images/listing/iconTaf.png"),
		Bitmap.getBitmapResource("images/listing/iconCrew.png"),
		Bitmap.getBitmapResource("images/listing/iconRecommendation.png")};
	public static final String[] toolbox_names ={"Currency Converter", "Weather Forecast", "Satellite Map", "METAR & TAF", "Crew Discussion", "Recommend 4Aircrew"};
}