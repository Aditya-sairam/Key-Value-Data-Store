package consumer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.json.simple.JSONObject;

import java.util.*;

import bean.Data;
import main.DataStore;

/***
 * The consumer class to prototype operations.
 * 
 * @author Aditya
 *
 */

public class DataConsumer {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		String dataStoreLoc = "D:\\FW";
		String dataStoreName = "Datastore";
		//String filepath = dataStoreLoc+ "/"+ dataStoreName;
		DataStore ds = new DataStore(dataStoreLoc,dataStoreName);
		
		
		//JSON Objects to be used as value in key-value data-store.
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("firstName", "Hello");
		jsonObject.put("lastName", "Hai");
		jsonObject.put("address", "Chennai");
		
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("firstName", "ABC");
		jsonObject2.put("lastName", "XYZ");
		jsonObject2.put("address", "coimbatore");
		
		//Create Operation.
		System.out.println(ds.create("4",jsonObject,10));
		System.out.println(ds.create("2",jsonObject2,10));
		
		//Read operation
		System.out.println(ds.read("4"));
		System.out.println(ds.read("5"));
		
		//Delete operation.
		System.out.println(ds.delete("2"));
		
		
	
	}
}
