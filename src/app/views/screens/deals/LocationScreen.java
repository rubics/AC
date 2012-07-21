package app.views.screens.deals;

import net.rim.device.api.lbs.MapField;
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
	public LocationScreen(DealDetailsScreenManager _dealsInfo){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		dealsInfo = _dealsInfo;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		setTitle(new ScreenTitle("Location"));
		add(new LabelField("Location"));
		
		VerticalFieldManager vrm = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		MapField mapField = new MapField();
		vrm.add(mapField);
				
		add(vrm);
	}
	
	public boolean isDirty() {
	    return false;
	}
}
