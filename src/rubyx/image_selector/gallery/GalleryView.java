package rubyx.image_selector.gallery;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.util.StringMatch;
import rubyx.layout_managers.TableLayoutManager;

public abstract class GalleryView extends MainScreen{
	
	final StringMatch[] extension = {new StringMatch(".jpg"),new StringMatch(".jpeg"),new StringMatch(".png"),new StringMatch(".gif"), new StringMatch(".JPG")};
	
	private Bitmap[] images;
	private byte[] previewedImageBytes;
	private String[] filePaths;
	
	private TextField textField = new TextField();
	private Vector vector = new Vector(5,5);
	private GalleryView this_screen;
	private GridViewController gridViewController;
	private FileConnection file_con;
	private int selectedImageIndex;
	
	private static final String store_gallery = "file:///store/home/user/camera/";
	
	public GalleryView(){
		
		super(Manager.USE_ALL_HEIGHT);	
		this_screen = this;
		getMainManager().setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		setTitle("Image Browser");
		
		try {
			// read image from picture gallery at device memory
			FileConnection file_a = (FileConnection) Connector.open(store_gallery, Connector.READ);
			Enumeration enum_a = file_a.list();
									
			while(enum_a.hasMoreElements()){
				String temp = enum_a.nextElement().toString();
				if(extension[0].indexOf(temp)!=-1 || extension[1].indexOf(temp)!=-1 || extension[2].indexOf(temp)!=-1
						|| extension[3].indexOf(temp)!=-1 || extension[4].indexOf(temp)!=-1 )
					vector.addElement(new String(store_gallery + temp));				
			}
			file_a.close();
			
//			// read image from picture gallery at media card (SD Card)
//			
//			FileConnection file_b = (FileConnection) Connector.open(store_gallery, Connector.READ);
//			
//			Enumeration enum_b = file_b.list();			
//									
//			while(enum_b.hasMoreElements()){
//				
//				String temp = enum_b.nextElement().toString();
//				System.out.println(temp);
//								
//				if(extension[0].indexOf(temp)!=-1 | extension[1].indexOf(temp)!=-1 |extension[2].indexOf(temp)!=-1
//						|extension[3].indexOf(temp)!=-1 |extension[4].indexOf(temp)!=-1 )
//					vector.addElement(new String(store_gallery + temp));				
//			}
//			
//			file_b.close();		
			
			/*********************************************************************************************/
			
			/**************************		read files into bitmap[]		******************************/ 
			
			images  = new Bitmap[vector.size()];
			
			for(int i=0; i < vector.size(); i++){
				FileConnection file_con = (FileConnection)Connector.open((String)vector.elementAt(i));
				if(file_con.exists()){

					InputStream ips = ( file_con.openInputStream());
					ByteArrayOutputStream ops = new ByteArrayOutputStream();
					
					byte[] array = new byte[4000];
					
					int length = ips.read(array);
					
					while(length > 0){					
						ops.write(array);
						length = ips.read(array);					
					}
					byte[] dest_array = ops.toByteArray();
					images[i] = Bitmap.createBitmapFromBytes(dest_array, 0, dest_array.length, 4);
					ops.close();
					ips.close();
				}
				file_con.close();
			}
			
			/*************************		Display GridView image selecor		**************************/
			
			int noOfColumn = Display.getWidth()/GridImageField.t_width;
			
			int[] columnStyles = new int[noOfColumn];
			int[] columnWidths = new int[noOfColumn];
			
			int temp = Display.getWidth()/noOfColumn;
			
			for(int i = 0; i < noOfColumn; i++){
				columnStyles[i] = TableLayoutManager.FIXED_WIDTH;
				columnWidths[i] = temp;
			}
			
			gridViewController = new GridViewController(columnStyles,columnWidths,0, Manager.FIELD_HCENTER|Manager.USE_ALL_WIDTH);
			gridViewController.setFocusListener(new FocusChangeListener() {
				
				public void focusChanged(Field field, int eventType) {					 
					if(eventType == FOCUS_GAINED)
						this_screen.addMenuItem(uploadMenuItem);
					
					else if(eventType == FOCUS_LOST)
						this_screen.removeMenuItem(uploadMenuItem);
				}
			});
			//GridFieldManager tablelayout = new GridFieldManager(noOfColumn, 0);
				
			for(int i = 0; i < images.length; i++){
				
				GridImageField gridImageField = new GridImageField(images[i]);
				gridImageField.setChangeListener(gridImageFieldListener);
				gridViewController.add(gridImageField);
			}
			
			add(gridViewController);			
		
			////////////////////////////////	  END OF CONSTRUCTOR  	 //////////////////////////////////////////
					
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("%%%%%%%%  Illegal Argument %%%%%%%%%%%%%");
		}catch (ConnectionNotFoundException e){
			e.printStackTrace();
			System.out.println("%%%%%%%%  Connection Not Found  %%%%%%%%%%%%%");
		}catch (IOException e){
			e.printStackTrace();
			System.out.println("%%%%%%%%  IOException  %%%%%%%%%%%%%");
		}
		
	}
	
	public abstract void onImageReturn(String last_image_path);
	
	FieldChangeListener gridImageFieldListener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			selectedImageIndex = field.getIndex();
			onImageReturn((String)vector.elementAt(selectedImageIndex));
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					UiApplication.getUiApplication().popScreen(this_screen);
				}
			});
		}
	};
	
	public boolean isDirty(){
		return false;
	}
	
	MenuItem uploadMenuItem = new MenuItem("Upload Card", 10, 10){
		
		public void run(){
			
			selectedImageIndex = gridViewController.getFieldWithFocus().getIndex();
			
			try{
				file_con = (FileConnection)Connector.open((String)vector.elementAt(selectedImageIndex));
				
				if(file_con.exists()){

					InputStream ips = ( file_con.openInputStream());
					ByteArrayOutputStream ops = new ByteArrayOutputStream();
					
					byte[] array = new byte[4000];
					
					int length = ips.read(array);
					
					while(length > 0){					
						ops.write(array);
						length = ips.read(array);					
					}
					
					previewedImageBytes = ops.toByteArray();						
					
					UiApplication.getUiApplication().popScreen(this_screen);
//					newCards.onAction(previewedImageBytes, saveImage(previewedImageBytes));
					
					ops.close();
					ips.close();					
				}				
				file_con.close();
			} catch (Exception exception){
				System.out.println("-----------	Exception @ GalleryView.gridImageFieldListener	------------");
				System.out.println(exception.getClass().getName());
				exception.printStackTrace();
			}
			
			
			
			
		}
	};
}

