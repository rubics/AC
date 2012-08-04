package rubyx.tabbedUI;

import app.models.Business;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class TabbedScreenManager {
	
	UiApplication app = UiApplication.getUiApplication();
	
	private MainScreen[] tabbedScreens;
	private Field[] tabbedFields;
	private Manager tabbedFieldManager;
	
	private int currentScreenIndex;
	
	public FieldChangeListener tabbedFieldListener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			int button_index = field.getIndex();			
			switchScreen(button_index);
		}
	};
	
	public TabbedScreenManager(MainScreen[] screens, Manager manager){
		tabbedScreens = screens;
		tabbedFields = new Field[manager.getFieldCount()];
		tabbedFieldManager = manager;
		
		for(int i=0; i<tabbedFieldManager.getFieldCount(); i++){
			tabbedFields[i] = manager.getField(i);
			tabbedFields[i].setChangeListener(tabbedFieldListener);
		}		
		currentScreenIndex = 0;
		tabbedScreens[currentScreenIndex].setStatus(tabbedFieldManager);
	}
	
	public void pushScreen(){
		app.pushScreen(tabbedScreens[currentScreenIndex]);
		try {
			if(tabbedScreens[currentScreenIndex].getFieldCount()>0)
				tabbedScreens[currentScreenIndex].getField(0).setFocus();
			else
				tabbedFields[currentScreenIndex].setFocus();
		} catch (Exception e) {
			System.out.println("# # # #  Exception caught  # # # #");
			System.out.println(e.getClass());
			e.printStackTrace();
		}
	}

	public void popScreen(){
		app.popScreen(tabbedScreens[currentScreenIndex]);
	}
	
	public void switchScreen(final int index){
		if(currentScreenIndex != index){
			UiApplication.getUiApplication().invokeAndWait(new Runnable(){
				public void run() {
					tabbedScreens[currentScreenIndex].setStatus(null);
					popScreen();
					currentScreenIndex = index;
					tabbedScreens[currentScreenIndex].setStatus(tabbedFieldManager);
					tabbedFieldManager.getField(currentScreenIndex).setFocus();
					pushScreen();
				}
			});
		}
	}
}
