package app.models;

public class Category {
	
	private String category_id;
	private String category_name;
	
	public Category(String _category_id, String _category_name){
		category_id = _category_id;
		category_name = _category_name;
	}

	public String getCategory_id() {
		return category_id;
	}

	public String getCategory_name() {
		return category_name;
	}
	
	public String toString(){
		return (category_name);
	}
}
