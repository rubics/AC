package rubyx.custom_fields.updatable_imagefield;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.system.Bitmap;
import app.models.Images;

public abstract class UpdatableImageModel {
	public static final Bitmap default_image = Images.aircrew_small;
	private Vector updatableFields;
	
	public UpdatableImageModel(){
		updatableFields = new Vector(2,2);
	}
	
	public void addUpdatableFields(UpdatableImageField _field){
		updatableFields.addElement(_field);
	}
	
	public void invokeUpdate(Bitmap bitmap){;
		Enumeration enm = updatableFields.elements();
		((UpdatableImageField)(enm.nextElement())).updateImage(bitmap);
//		System.out.println(">> HasNext ??" + enm.hasMoreElements());
//		for(Object element = enm.nextElement(); enm.hasMoreElements();){
//			System.out.println("-------------------------------");
//			((UpdatableImageField)(element)).updateImage(bitmap);
//		}
	}
}
