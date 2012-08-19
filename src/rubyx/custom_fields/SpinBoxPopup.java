package rubyx.custom_fields;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.TextSpinBoxField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.SpinBoxFieldManager;

public class SpinBoxPopup extends PopupScreen{

	Object[] choices;
	TextSpinBoxField spinBox;
	SpinBoxFieldManager spinBoxFieldManager;
	
	public  SpinBoxPopup(Object[] choices){
		super(new HorizontalFieldManager(Field.FIELD_HCENTER));
		this.choices = choices;
		
		
		spinBox = new TextSpinBoxField(choices);
		spinBox.setEditable(false);
		spinBoxFieldManager = new SpinBoxFieldManager();
		spinBoxFieldManager.setClickToLock(true);
		spinBoxFieldManager.add(spinBox);
		add(spinBoxFieldManager);
	}
}
