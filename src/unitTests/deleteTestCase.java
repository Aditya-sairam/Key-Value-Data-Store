package unitTests;
import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.junit.Test;

import main.DataStore;

/***
 * This class contains the unit tests for Delete Operation.
 * 
 * @author Aditya
 * 
 */

public class deleteTestCase {
	
	//Test case for invalid key.
	@Test
	public void deleteTestCase1() throws ClassNotFoundException, IOException {
		DataStore ds = new DataStore();
		
		
		
		JSONObject jsonObject4 = new JSONObject();
		jsonObject4.put("firstName", "CDE");
		jsonObject4.put("lastName", "PQR");
		jsonObject4.put("address", "Ooty");
		
		
		String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefkjasdhe";
		String st = "Operation failed due to key length exceeded the limit(32chars)";
		//String res = ds.create("4", jsonObject2,10);
		String result = ds.create(key,jsonObject4,10);
		
		
		
		assertEquals(result, st);
		
	}
	
	//test case for key not available.
	@Test
	public void deleteTestCase2() {
		DataStore ds = new DataStore();
		
		Object res = ds.read("7");
		
		String compare = "Operation failed due to key not available";
		
		assertEquals(compare,res);
	}
	
	//Test case for successful deletion.
	@Test
	public void deleteTestCase3() throws ClassNotFoundException, IOException {
		
		DataStore ds = new DataStore();
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("firstName", "Hello");
		jsonObject2.put("lastName", "Hai");
		jsonObject2.put("address", "Shimla");
		
		String res = ds.create("4", jsonObject2,10);
		
		Object result = ds.delete("4");
		
		String compare = "Record deletion successful";
		
		assertEquals(result,compare);
		
	}
	
	
	

}
