package app.views.screens.deals;

import java.util.Vector;

import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.NullField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.ListItem;
import app.models.DealImage;
import app.models.Images;
import app.views.fields.ScreenTitle;
import app.views.fields.deals.CompositeDealLabel;
import app.views.fields.deals.DealImageField;
import app.views.managers.deals.DealDetailsScreenManager;

public class EmailScreen extends MainScreen{
	private DealDetailsScreenManager dealsInfo;
	
	private Manager manager;
	
	public EmailScreen(DealDetailsScreenManager _dealsInfo){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL);
		dealsInfo = _dealsInfo;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		setTitle(new ScreenTitle("Email"));
		manager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		add(manager);
		manager.add(new NullField());
		System.out.println(dealsInfo.deal.getDealDetails().getName());
		System.out.println(dealsInfo.deal.getDealDetails().getDescription());
		manager.add(new ListItem(dealsInfo.deal.getDealDetails().getName(), dealsInfo.deal.getDealDetails().getDescription(), dealsInfo.deal.getDealDetails().getLogo(), false));
		HorizontalFieldManager image_scroller = new HorizontalFieldManager(Manager.HORIZONTAL_SCROLL);
		// override focus movement with arrow keys
		Vector deal_images = dealsInfo.deal.getDealDetails().getImages();
		for(int i=0; i<deal_images.size(); i++){
			DealImage deal_image = (DealImage)deal_images.elementAt(i);
			DealImageField field = new DealImageField(Images.aircrew_small);
			deal_image.addUpdatableFields(field);
			image_scroller.add(field);
		}
		manager.add(image_scroller);
		manager.add(new CompositeDealLabel(dealsInfo.deal.getDealDetails()));
	}
	
	public boolean isDirty() {
	    return false;
	}
}
