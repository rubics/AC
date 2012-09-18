package app.models;

import java.util.Hashtable;

import net.rim.device.api.util.Persistable;

public class PersistentHash implements Persistable{
	
	private static final String USER_NAME = "USR_NAME";
	private static final String USER_PASSWORD = "USR_PASSWORD";
	private static final String USER_ID = "USR_ID";
	private static final String SESSION_ID = "SESSION_ID";
	
	private Hashtable hashTable;
	
	public PersistentHash(){
		hashTable = new Hashtable(4);
		setUserId("");
		setUserName("");
		setUserPassword("");
		setSessionId("");
	}
	
	public void setUserName(Object value){
		hashTable.put(USER_NAME, value);
	}
	
	public String getUserName(){
		return (String)hashTable.get(USER_NAME);
	}
	
	public void setUserPassword(Object value){
		hashTable.put(USER_PASSWORD, value);
	}
	
	public String getUserPassword(){
		return (String)hashTable.get(USER_PASSWORD);
	}
	
	public void setUserId(Object value){
		hashTable.put(USER_ID, value);
	}
	
	public String getUserId(){
		return (String)hashTable.get(USER_ID);
	}
	
	public void setSessionId(Object value){
		hashTable.put(SESSION_ID, value);
	}
	
	public String getSessionId(){
		return (String)hashTable.get(SESSION_ID);
	}
}
