package app.controllers;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import app.models.PersistentHash;
import app.models.User;

public class PersistenceController {
	
	private static final long KEY = 0x37a4696e15dfdf4bL;
	
	private final PersistentObject persistentObject;
	private PersistentHash persistentHash;
	
	public PersistenceController(){
		persistentObject = PersistentStore.getPersistentObject(KEY);
		Object object = persistentObject.getContents();
		
		if(object!=null & PersistentHash.class.isInstance(object)){
			persistentHash = (PersistentHash)object;
		} else {
			persistentHash = new PersistentHash();
		}
	}
	
	public void persistUser(User user){
		persistentHash.setUserId(user.getUserId());
		persistentHash.setUserName(user.getUserName());
		persistentHash.setUserPassword(user.getPassword());
		persistentHash.setSessionId(user.getSessionId());
		setContents();
	}
	
	public void removeUser(){
		persistentHash = null;
		setContents();
	}
	
	public User retrieveUser(){
		
		if(persistentHash.getUserName().length() > 0)
			return (new User(persistentHash.getUserName(),
				persistentHash.getUserId(),
				persistentHash.getSessionId(),
				persistentHash.getUserPassword())
			);
		else return null;
	}
	
	private void setContents(){	
		persistentObject.setContents(persistentHash);
		persistentObject.commit();
	}
}