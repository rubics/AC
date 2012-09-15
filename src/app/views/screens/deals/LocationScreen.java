package app.views.screens.deals;

import net.rim.device.api.lbs.maps.MapDimensions;
import net.rim.device.api.lbs.maps.MapFactory;
import net.rim.device.api.lbs.maps.model.MapDataModel;
import net.rim.device.api.lbs.maps.model.MapLocation;
import net.rim.device.api.lbs.maps.model.MapPoint;
import net.rim.device.api.lbs.maps.model.Mappable;
import net.rim.device.api.lbs.maps.ui.RichMapField;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import app.models.Images;
import app.views.fields.ScreenTitle;
import app.views.managers.deals.DealDetailsScreenManager;

public class LocationScreen extends MainScreen{
	private DealDetailsScreenManager dealsInfo;
	RichMapField mapField;
	public LocationScreen(DealDetailsScreenManager _dealsInfo){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		dealsInfo = _dealsInfo;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		setTitle(new ScreenTitle("Location"));
		
		VerticalFieldManager vrm = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		
		mapField = MapFactory.getInstance().generateRichMapField();
		MapDataModel mapDataModel = mapField.getModel();
		MapDimensions mapDimensions = new MapDimensions(Display.getWidth(), 270);
		mapDimensions.setZoom(3);
		mapDimensions.setCentre(new MapPoint(Double.parseDouble(dealsInfo.deal.getX_code()), Double.parseDouble(dealsInfo.deal.getY_code())));
		mapField.getMapField().setDimensions(mapDimensions);
		
		MapLocation location1 = new MapLocation(Double.parseDouble(dealsInfo.deal.getX_code()), Double.parseDouble(dealsInfo.deal.getY_code()), dealsInfo.deal.getName(), dealsInfo.deal.getDescription());
		int index = mapDataModel.add((Mappable)location1, dealsInfo.deal.getName());
		mapDataModel.tag(index, dealsInfo.deal.getName());
		
		mapDataModel.setVisibleNone();
		mapDataModel.setVisible(index);

		vrm.add(mapField);
				
		add(vrm);
	}
	
	public boolean isDirty() {
	    return false;
	}
	
	protected void onUiEngineAttached(boolean attached) {
		// TODO Auto-generated method stub
		super.onUiEngineAttached(attached);
		if(!attached){
			try{
				mapField.close();
			}catch(Exception e){
				
			}
			
		}
	}
}
