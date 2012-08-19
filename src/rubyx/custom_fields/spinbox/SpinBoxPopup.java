package rubyx.custom_fields.spinbox;

import rubyx.custom_fields.PopupKeyListener;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.TextSpinBoxField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.SpinBoxFieldManager;

public class SpinBoxPopup extends PopupScreen{

	Object[] choices;
	TextSpinBoxField spinBox;
	SpinBoxFieldManager spinBoxFieldManager;
	SpinBoxListener spinBoxListener;
	
	public  SpinBoxPopup(Object[] _choices, SpinBoxListener listener){
		super(new HorizontalFieldManager(Field.FIELD_HCENTER));
		this.choices = _choices;
		spinBoxListener = listener;
		addKeyListener(new PopupKeyListener(this));
		
		spinBox = new TextSpinBoxField(choices);
		spinBox.setEditable(false);
		spinBox.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {
				spinBoxListener.selectionChanged(choices[((TextSpinBoxField)field).getSelectedIndex()]);
			}
		});
		spinBoxFieldManager = new SpinBoxFieldManager();
		spinBoxFieldManager.setClickToLock(true);
		spinBoxFieldManager.setVisibleRows(5);
		spinBoxFieldManager.add(spinBox);
		add(spinBoxFieldManager);
	}
	
	public interface SpinBoxListener{
		public void selectionChanged(Object selected);
	}
}

