package main;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONObject;

import bean.Data;
import utilities.MainUtils;
import utilities.Consts;
import consumer.DataConsumer;

/***
 *
 * @author Aditya 
 *  This class handles the datastore instantiation and provides operations
 *  for create,read and delete(CRD).
 *
 */


public class DataStore {

	//default storage location.
	private  String dataStoreLoc = "C:\\Users\\Govindan\\Documents";
	private  String dataStoreName = "Datastore";
	
	/**
	 * Constructor initialize the DataStore with default storage location
	 */
	public DataStore() {
		
			dataStoreLoc = Consts.defaultDataStoreLoc;
			dataStoreName = "datastore-" + MainUtils.getProcessName();
			}
	public DataStore(String datastoreLoc,String datastoreName) {
		try {
			dataStoreLoc = datastoreLoc;
			dataStoreName =datastoreName;
		} 
		catch (Exception exception) {

		}
		

	}
//*************************************Operations******************************************** 
	
	/**
	 * 
	 * Method to create an element in the DataStore
	 * 
	 * @param key
	 *            The key of the element(String)
	 * @param value
	 *            The value of the element(JSON Object)
	 *            
	 * @return status of the operation(String)
	 */
	
	public synchronized String create(String key, JSONObject value) {
		try {
			return create(key, value, -1);
		} 
		catch (Exception exception) {
			return Consts.FAILURE_CREATE;
		}
	}
	
	/**
	 * 
	 * Method to create an element in the DataStore
	 * 
	 * @param key
	 *            The key of the element(String)
	 * @param value
	 *            The value of the element(JSON Object)
	 * @param timeToLive
	 *            the time for which the key-value pair should exist.
	 *            
	 * @return status of the operation(String)
	 */
	public synchronized  String create(String key,JSONObject value,int timeToLive) throws ClassNotFoundException, IOException {
		
		try {
		String filepath = dataStoreLoc+ "/"+ dataStoreName;
		
		//Checks for key name validity.
		if(!MainUtils.isKeyNameValid(key)) {
			return Consts.FAILURE_KEY_LENGTH_EXCEEDED;
		}
		
		//Checks if the given key already exists.
		if(MainUtils.iskeyExsists(key, filepath)) {
			return Consts.FAILURE_KEY_ALREADY_AVAILABLE;
		}
		
		Data data = new Data();
		data.setKey(key);
		
		if(timeToLive > 0) {
			data.setTimeToLive(timeToLive);
			
		}
		else {
			data.setTimeToLive(-1);
		}
		data.setValue(value);
		data.setCreationDateTimeMillis(new Date().getTime());
		
		//returns the status of the operation.
		if (MainUtils.writeData(data, filepath)) {
			return Consts.SUCCESS_CREATE;
		} else {
			return Consts.FAILURE_CREATE;
		}
	} 
	catch (Exception exception) {
		return Consts.FAILURE_CREATE;
	}
	}
	
	/**
	 * Method to read an element from the DataStore
	 * 
	 * @param key
	 *            The key of the element to read the element(String)
	 *            
	 * @return The value as type of JSONObject
	 */
	
	public synchronized Object read(String key) {
		try {
			String filepath = dataStoreLoc+"/"+dataStoreName;
			
			//checks for key name validity
			if(!MainUtils.isKeyNameValid(key)) {
				return Consts.FAILURE_KEY_LENGTH_EXCEEDED;
			}
			
			//checks if the given key exists or not.
			if(!MainUtils.iskeyExsists(key, filepath)) {
				return Consts.FAILURE_KEY_NOT_AVAILABLE;
			}
			
			//returns the data for the corresponding key.
			Data data = MainUtils.readData(key, filepath);
			if(data != null) {
				return data.getValue();
			}
			else {
				return Consts.FAILURE_READ;
			}
		}
		catch(Exception ex){
			 ex.printStackTrace();
			 return Consts.FAILURE_READ;
		}
	}
	
	/**
	 * Method to delete an element from the DataStore
	 * 
	 * @param key
	 *            The key of the element to read the element(String)
	 *            
	 * @return The status of the delete operation(String)
	 */
	
	public synchronized Object delete(String key) {
		try {
			String filepath = dataStoreLoc+"/"+dataStoreName;
			
			//checks for the key name validity
			if(!MainUtils.isKeyNameValid(key)) {
				return Consts.FAILURE_KEY_LENGTH_EXCEEDED;
			}
			
			//checks if the given key exists.
			if(!MainUtils.iskeyExsists(key, filepath)) {
				return Consts.FAILURE_KEY_NOT_AVAILABLE;
			}
			//returns the status of the operation
			File file = new File(filepath);
			if(MainUtils.deleteData(key, filepath)) {
				return Consts.SUCCESS_DELETE;
			}
			return Consts.FAILURE_DELETE;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return Consts.FAILURE_DELETE;
		}
	}
	

}
