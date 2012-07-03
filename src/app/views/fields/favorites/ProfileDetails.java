package app.views.fields.favorites;

import java.util.Vector;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class ProfileDetails extends Field{
	
	private final int width = Display.getWidth();
	private int height;
	private int label_height;
	private static final int h_offset = 5;
	private static final int v_correction = 20;
	
	String name;
	String status;
	String location;
	String designation;
	String airlines;
	String aboutMe;
	private Vector lines;
	
	String[] labels = {"Name", "Status", "Location", "Designation", "Airlines", "About Me"};
	String [] values = {"Kristine Jacob", "Online", "Dubai, UAE", "Cabin Crew", "Emirates",""};
	String descp = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium,";
	private static final Font font = Font.getDefault().derive(Font.PLAIN, 20, Ui.UNITS_px);
	
	public ProfileDetails(){
		super();
		setFont(font);
		lines = wrap(descp, width);
		label_height = labels.length * font.getHeight();
		height = label_height + lines.size() * font.getHeight() + v_correction;
	}

	protected void paint(Graphics graphics) {
		graphics.setFont(font);
		for(int i=0; i<labels.length; i++){
			graphics.setColor(0xF57000);
			graphics.drawText(labels[i], h_offset, font.getHeight()*i);
			graphics.setColor(Color.GRAY);
			graphics.drawText("\t" + values[i], font.getAdvance(labels[i]), font.getHeight()*i, DrawStyle.ELLIPSIS);
		}
		for (int i = 0; i < lines.size(); i++) {
			graphics.drawText((String)lines.elementAt(i),h_offset, label_height + i*font.getHeight() + 5,DrawStyle.HCENTER |DrawStyle.ELLIPSIS);
		}
	}


	protected void layout(int _width, int _height) {
		setExtent(width, height);
	}
	
	private Vector wrap (String text, int width) 
	{
	    Vector result = new Vector ();
	    if (text ==null)
	       return result;
	 
	    boolean hasMore = true;
	 
	    // The current index of the cursor
	    int current = 0;
	 
	    // The next line break index
	    int lineBreak = -1;
	 
	    // The space after line break
	    int nextSpace = -1;
	 
	    while (hasMore) 
	    {
	       //Find the line break
	       while (true) 
	       {
	           lineBreak = nextSpace;
	           if (lineBreak == text.length() - 1) 
	           {
	               // We have reached the last line
	               hasMore = false;
	               break;
	           } 
	           else 
	           {
	               nextSpace = text.indexOf(' ', lineBreak+1);
	               if (nextSpace == -1)
	                  nextSpace = text.length() -1;
	               int linewidth = font.getAdvance(text,current, nextSpace-current);
	               // If too long, break out of the find loop
	               if (linewidth > width) 
	                  break;
	           }
	      }
	      String line = text.substring(current, lineBreak + 1);
	      result.addElement(line);
	      current = lineBreak + 1;
	 }
	 return result;
	}
}
