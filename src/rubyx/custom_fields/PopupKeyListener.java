package rubyx.custom_fields;

import net.rim.device.api.system.Characters;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;

public class PopupKeyListener implements KeyListener {
	
	Screen screen;
	public PopupKeyListener(Screen screen){
		this.screen = screen;
	}
    public boolean keyChar( char key, int status, int time ) 
    {
        if ( key == Characters.ESCAPE ) 
        {
        	UiApplication.getUiApplication().popScreen(screen);
        }
        return false;
    }

	public boolean keyDown(int keycode, int time) {
		return false;
	}

	public boolean keyRepeat(int keycode, int time) {
		return false;
	}

	public boolean keyStatus(int keycode, int time) {
		return false;
	}

	public boolean keyUp(int keycode, int time) {
		return false;
	}
}
