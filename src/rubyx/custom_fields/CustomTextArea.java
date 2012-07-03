package rubyx.custom_fields;

import java.util.Vector;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;

public class CustomTextArea extends Field{
	
	private String string;
	private int width;
	private int height;
	private Vector lines;
	Font font;
	public int color = Color.BLACK;
	
	public CustomTextArea(String _string, int _width, Font _font){
		string = _string;
		width = _width;
		lines = wrap(string, width);
		font = _font;
		setFont(font);
		height = lines.size()*font.getHeight() + 10;
	}
	
	protected void layout(int _width, int _height){
		setExtent(width, height);
	}
	
	protected void paint(Graphics graphics){
		graphics.setColor(color);
		for (int i = 0; i < lines.size(); i++) {
			graphics.drawText((String)lines.elementAt(i),0, i*font.getHeight() + 5,DrawStyle.HCENTER |DrawStyle.ELLIPSIS);
		}
	}
	
	public int getPreferredHeight(){
		return height;
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
	               int linewidth = this.getFont().getAdvance(text,current, nextSpace-current);
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
