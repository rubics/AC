package rubyx.custom_fields;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class CustomEditField extends VerticalFieldManager{
	
	private static final int H_OFFSET = 4;
	private static final int V_OFFSET = 3;
	
	private EditField field;
	private String string;
	private int width;
	private int height;
		
	private static final Font font = Font.getDefault().derive(Font.PLAIN, 5, Ui.UNITS_pt);	
	
	public CustomEditField(String _string, int _width, int _height){
		super(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		string = _string;
		width = _width;
		height = _height;
		field = new EditField(EditField.NO_LEARNING | EditField.STATUS_MOVE_FOCUS_VERTICALLY | EditField.NON_SPELLCHECKABLE);
		field.setLabel("");
		field.setText(string);
		field.setFont(font);
		Manager manager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | VERTICAL_SCROLLBAR){
			public void sublayout(int a, int b){
				layoutChild(getField(0), width  - 2*H_OFFSET, b);
				setPositionChild(getField(0),H_OFFSET, V_OFFSET);
				setExtent(width, height);
			}
		};
		manager.add(field);
		add(manager);
	}
	
	public void sublayout(int a, int b){
		layoutChild(getField(0), width - 2*H_OFFSET, height-2*V_OFFSET);
		setPositionChild(getField(0), 0,0);
		setExtent(width, height);				
	}
	
	public void paintBackground(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
		g.setColor(Color.BLACK);
		super.paint(g);
	}
	
	public String getText(){
		return field.getText();
	}
	
	public void setText(String text){
		field.setText(text);
	}
}
