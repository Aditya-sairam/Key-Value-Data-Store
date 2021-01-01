package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.management.ManagementFactory;
//import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.HashMap;

import bean.Data;
import utilities.Consts;



/***
 * 
 * @author Aditya
 * 		This class handles the main utility methods required for create,read 
 * 		delete operations(CRD).
 *
 */

public class MainUtils {
	
	/**
	 * To get the current processName
	 * 
	 * @return the process name as a string
	 */
	
	public static String getProcessName() {
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		return processName;
	}

	/***
	 * TO validate the key of the element
	 * @param key 
	 * 			The key of the element
	 * 
	 * @return 
	 * 		if the key name provided is valid or not(True or false)
	 */
	
	public static boolean isKeyNameValid(String key) {
		if (key.length() > Consts.KEY_MAX_LENGTH) {
			return false;
		}
		return true;
	}
	
	/***
	 * To check if the given exists or not.
	 * 
	 * @param key
	 *           the key of the element.
	 * @param filepath
	 *  			  the datastore location in laptop.
	 * @return 
	 *        True or false(The given key exists or not).
	 * @throws IOException 
	 *                    handles the exception.
	 * @throws ClassNotFoundException 
	 *                              handles the exception
	 */
	
	public static boolean iskeyExsists(String key,String filepath) throws IOException, ClassNotFoundException {
		boolean iskey = false;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;

		HashMap<String,Data> hmap = new HashMap<String,Data>();
		
		try {
		File file = new File(filepath);
		
		//checks if the file exists in datastore location.
		if(file.exists()) {
			fileInputStream = new FileInputStream(file);
			objectInputStream = new ObjectInputStream(fileInputStream);
			
			//writes the data into a hashmap.
			hmap = (HashMap<String, Data>) objectInputStream.readObject();
			
			// check if key exists
			if (hmap.containsKey(key)) {
				iskey = true;
			}
			
			fileInputStream.close();
			objectInputStream.close();
			
		// validate against time to live and destroy the object if time
		// expired	
			if(iskey) {
				Data data = hmap.get(key);
				long currentDateTimeMillis = new Date().getTime();
			
			if (data.getTimeToLive() > 0
					&& (currentDateTimeMillis - data
							.getCreationDateTimeMillis()) >= (data
							.getTimeToLive() * Consts.MILLISECONDS)) {
						
				hmap.remove(key);
				fileOutputStream = new FileOutputStream(file);
				objectOutputStream = new ObjectOutputStream(
						fileOutputStream);
				objectOutputStream.writeObject(hmap);
				fileOutputStream.close();
				objectOutputStream.close();

				// Since object is removed the key is available for storage
				iskey = false;
			}
		}
		}
		}
		
		 catch (Exception exception) {
				exception.printStackTrace();
			} 
		 finally {
				if (fileInputStream != null) {
					try {
						fileInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (objectInputStream != null) {
					try {
						objectInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		 }
			return iskey;
		}
	
	/***
	 * To write the data-store file handling.
	 * 
	 * @param data
	 * 			  The element to be written in the dataStore.
	 * @param filepath
	 * 		          The data-store location in the laptop.
	 * @return 
	 *        True of False(Status of the write operation).
	 */
	public static boolean writeData(Data data,String filepath) {
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		HashMap<String, Data> hmap = null;
		
		try {
		File file = new File(filepath);
		
		//If the file exists.
		if(file.exists()) {
		//Read the exisiting file data.
		fileInputStream = new FileInputStream(file);
		objectInputStream = new ObjectInputStream(fileInputStream);
		
		//Add the element.
		hmap = (HashMap<String, Data>) objectInputStream.readObject();
		
		//Write the data to the file.
		fileInputStream.close();
		objectInputStream.close();
		hmap.put(data.getKey(),data);
		fileOutputStream = new FileOutputStream(file);
		objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(hmap);
		fileOutputStream.close();
		objectOutputStream.close();

		return true;
		}
		else {
			hmap = new HashMap<String, Data>();
			hmap.put(data.getKey(), data);
			
			//Write the data to the file.
			fileOutputStream = new FileOutputStream(file);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(hmap);
			fileOutputStream.close();
			objectOutputStream.close();

			return true;
		}
		
		
	}
		catch (Exception exception) {
			return false;
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/***
	 * Utility method to support Read Operation.
	 * 
	 * @param key
	 * 			Key of the element to be read.
	 * @param filepath
	 * 				  The data-store location in the laptop.
	 * 
	 * @return
	 *        The data associated with the key.
	 */  
	
	public static Data readData(String key,String filepath) {
		
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		HashMap<String,Data> hmap = null;
		
		try {
			File file = new File(filepath);
			
			//If file exists,fetch the corresponding data.
			if(file.exists()) {
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				hmap = (HashMap<String,Data>)objectInputStream.readObject();
				
				fileInputStream.close();
				objectInputStream.close();
				
				return hmap.get(key);
			}
			else {
				return null;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			
		}
		 finally {
				if (fileInputStream != null) {
					try {
						fileInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (objectInputStream != null) {
					try {
						objectInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
	}
		return hmap.get(key);
  }
	
	/***
	 * Utility method used for delete operation.
	 * 
	 * @param key
	 * 			The key for data to be deleted.
	 * 
	 * @param filepath
	 * 				 Data-Store location in laptop.
	 * 
	 * @return
	 *        True or False(Status of delete operation).
	 */
	
	public static boolean deleteData(String key,String filepath) {
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		HashMap<String, Data> hmap = null;

		try {
			File file = new File(filepath);
			if(file.exists()) {
				//Read the existing file data.
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				
				hmap = (HashMap<String,Data>) objectInputStream.readObject();
				
				//Remove the element corresponding to the given key.
				hmap.remove(key);
				
				//write the data to the file.
				fileOutputStream = new FileOutputStream(file);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(hmap);
				fileOutputStream.close();
				objectOutputStream.close();
				
				return true;
			}
			return false;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
		
	}
	

