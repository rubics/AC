package rubyx.image_selector.camera;

import net.rim.device.api.io.file.FileSystemJournal;
import net.rim.device.api.io.file.FileSystemJournalEntry;
import net.rim.device.api.io.file.FileSystemJournalListener;
import rubyx.image_selector.UploadImagePopup;

public abstract class CameraListener implements FileSystemJournalListener{

	private static long last_usn;
	private UploadImagePopup uploadImagePopup;
	
	public CameraListener(UploadImagePopup uploadImagePopup){
		last_usn = FileSystemJournal.getNextUSN();
		this.uploadImagePopup = uploadImagePopup;
	}
	
	public void fileJournalChanged() {
	
		long nextUSN = FileSystemJournal.getNextUSN();
		for (long lookUSN = nextUSN-1; lookUSN >= last_usn; lookUSN--) {
			FileSystemJournalEntry entry = FileSystemJournal.getEntry(lookUSN);
		    if (entry == null) {
		    	// We didn't find an entry.
		    	break;
		    }
		      
		    if (entry.getEvent() == FileSystemJournalEntry.FILE_ADDED) {
		    	String path = entry.getPath();
		    	if (path != null && path.indexOf("jpg") != -1) {
		    		System.out.println("Entry path @ CameraListener: >> " + path);
		    		uploadImagePopup.selected_image_path = "file://" + path; 
//		    		onImageReturn("file://" + path);
		    		break;
		    	}
		    }
		}
		last_usn = nextUSN;
	}
	
	public abstract void onImageReturn(String last_image_path);
}
