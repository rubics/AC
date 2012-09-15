package app.views.screens.deals;

import net.rim.device.api.lbs.maps.MapDimensions;
import net.rim.device.api.lbs.maps.MapFactory;
import net.rim.device.api.lbs.maps.model.MapDataModel;
import net.rim.device.api.lbs.maps.model.MapLocation;
import net.rim.device.api.lbs.maps.model.MapPoint;
import net.rim.device.api.lbs.maps.model.Mappable;
import net.rim.device.api.lbs.maps.ui.RichMapField;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.ScreenBannar;
import rubyx.custom_fields.SpaceField;
import rubyx.tabbedUI.TabbedButton;
import rubyx.tabbedUI.TabbedButtonManager;
import app.AirCrewApp;
import app.controllers.DealController;
import app.models.AirCrew;
import app.models.Category;
import app.models.Deal;
import app.models.Images;
import app.views.fields.listings.ListingField;

public class SearchResultScreen extends MainScreen{
	
	private DealController dealController;
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private int manager_index;
	private final MainScreen current_screen;
	private VerticalFieldManager vrm;
	private VerticalFieldManager lastManager;
		
	public SearchResultScreen(DealController _dealController){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL);
		current_screen = this;
		dealController = _dealController;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		setTitle(new ScreenBannar("Deals", 40, backButton, homeButton));
		
		vrm = new VerticalFieldManager(Manager.USE_ALL_HEIGHT);
		
		add(new SpaceField(5));
		TabbedButtonManager statusButtonGroup = new TabbedButtonManager(480, 42, true, 2);
		Field listViewButton = new TabbedButton("List View",6);
		Field mapViewButton = new TabbedButton("Map View",6);
		listViewButton.setChangeListener(tabbedViewListener);
		mapViewButton.setChangeListener(tabbedViewListener);
		statusButtonGroup.add(listViewButton);
		statusButtonGroup.add(mapViewButton);
		add(statusButtonGroup);
		add(new SpaceField(3));
		
		vrm = new VerticalFieldManager(Manager.USE_ALL_HEIGHT);
		add(vrm);
	}
	
	public void updateScreen(){
		UiApplication.getUiApplication().invokeLater(new Runnable() {					
			public void run() {
				lastManager = null;
				current_screen.delete(vrm);
				vrm = new VerticalFieldManager(Manager.USE_ALL_HEIGHT);
				VerticalFieldManager listManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
				for(int i=0; i < dealController.getDeals().length; i++){
					Deal deal = dealController.getDeals()[i];
					System.out.println(deal.toString());
					Field listItem = new ListingField(AirCrew.image_medium + deal.getLogo(), deal.getName(), deal.getCategory_name(),8);
					listItem.setChangeListener(dealController.dealDetailsListener);
					listManager.add(listItem);
				}
				vrm.add(listManager);
				add(vrm);
				vrm.invalidate();
			}
		});
	}
	
	public void drawCategories(){
		UiApplication.getUiApplication().invokeLater(new Runnable() {					
			public void run() {
				lastManager = null;
				current_screen.delete(vrm);
				vrm = new VerticalFieldManager(Manager.USE_ALL_HEIGHT);
				VerticalFieldManager listManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
				Category allcategories = new Category("0", "All Categories");
				Field firstListItem = new ListingField(AirCrew.image_medium + "", allcategories.getCategory_name(), "",8);
				firstListItem.setChangeListener(dealController.allDeals);
				listManager.add(firstListItem);
				for(int i=0; i < dealController.getCategories().length; i++){
					Category category = dealController.getCategories()[i];
					Field listItem = new ListingField(AirCrew.image_medium + "", category.getCategory_name(), "",8);
					listItem.setChangeListener(dealController.categorizedDeals);
					listManager.add(listItem);
				}
				vrm.add(listManager);
				add(vrm);
				vrm.invalidate();
			}
		});
	}
	
	public void drawLastManager(){
		
		UiApplication.getUiApplication().invokeLater(new Runnable() {					
			public void run() {
				try{
					current_screen.delete(vrm);
					vrm = lastManager;
					add(vrm);
					vrm.invalidate();
				} catch (Exception e){
					System.out.println(">> Exception: " + e.getClass().getName());
					System.out.println();
				}
			}
		});
	}
		
	public void drawMap(){
		
		UiApplication.getUiApplication().invokeLater(new Runnable() {					
			public void run() {
				lastManager = vrm;
				current_screen.delete(vrm);
				vrm = new VerticalFieldManager(Manager.USE_ALL_HEIGHT);
				VerticalFieldManager listManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR | Manager.USE_ALL_WIDTH);
				
				RichMapField mapField = MapFactory.getInstance().generateRichMapField();
				MapDataModel mapDataModel = mapField.getModel();
				MapDimensions mapDimensions = new MapDimensions(Display.getWidth(), 220);
				mapDimensions.setZoom(3);
				mapDimensions.setCentre(new MapPoint(Double.parseDouble(dealController.getDeals()[0].getX_code()), Double.parseDouble(dealController.getDeals()[0].getY_code())));
				mapField.getMapField().setDimensions(mapDimensions);
				
				MapDataModel dataModel = mapField.getModel();
				for(int i=0; i < dealController.getDeals().length; i++){
					Deal deal = dealController.getDeals()[i];
					dataModel.add((Mappable) new MapLocation(Double.parseDouble(deal.getX_code()), Double.parseDouble(deal.getY_code()), deal.getName(), deal.getDescription()), deal.getDescription(), true);
				}
								
				listManager.add(mapField);
				vrm.add(listManager);
				add(vrm);
				vrm.invalidate();
			}
		});
	}
	
	public boolean isDirty() {
	    return false;
	}
	
	private FieldChangeListener tabbedViewListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			switch (field.getIndex()){
			case 0:
				drawLastManager();
				break;
			case 1:
			default:
				drawMap();
				break;
			}
		}
	};
}