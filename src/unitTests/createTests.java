package unitTests;
import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.junit.Test;

import main.DataStore;

/***
 * This class contains the unit test cases 
 * for the Create method.
 *
 * @author Aditya 
 *
 *
 */

public class createTests {

	
	//Unit test for successful creation of key value store-Numbers as key.
	@Test
	public void testCase1() throws ClassNotFoundException, IOException {
		DataStore ds = new DataStore();
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("firstName", "Hello");
		jsonObject2.put("lastName", "Hai");
		jsonObject2.put("address", "Shimla");
		
	
		
		String st = "Create operation successful";
		String res = ds.create("4", jsonObject2,10);
		
		
		//Checks for equality of the actual and expected values.
		assertEquals(st,res);
		
		
	}
	
	
	//Unit test case for successful additon of a key value pair in data store-alphanumeric as key.
	@Test
	public void testCase3() throws ClassNotFoundException, IOException {
		DataStore ds = new DataStore();
		
		JSONObject jsonObject3 = new JSONObject();
		jsonObject3.put("firstName", "Sucess");
		jsonObject3.put("lastName", "victory");
		jsonObject3.put("address", "Delhi");
		
	
		
		String st = "Create operation successful";
		String res = ds.create("5abc", jsonObject3,10);
		
		
		
		assertEquals(st,res);
		
		
	}
	
	//Unit test case for successful additon of a key value pair in data store-Alphabets as key
	@Test
	public void testCase2() throws ClassNotFoundException, IOException {
		DataStore ds = new DataStore();
		
		JSONObject jsonObject4 = new JSONObject();
		jsonObject4.put("firstName", "Sucess");
		jsonObject4.put("lastName", "victory");
		jsonObject4.put("address", "Delhi");
		
	
		
		String st = "Create operation successful";
		String res = ds.create("abcdef", jsonObject4,10);
		
		
		
		assertEquals(st,res);
		
		
	}
	
	//Unit test for key already available in key-value data store.
	@Test
	public void testCase4() throws ClassNotFoundException, IOException {
		DataStore ds = new DataStore();
		
		
		
		JSONObject jsonObject3 = new JSONObject();
		jsonObject3.put("firstName", "ABC");
		jsonObject3.put("lastName", "XYZ");
		jsonObject3.put("address", "Delhi");
		
		String st = "Operation failed due to key already available";
		//String res = ds.create("4", jsonObject2,10);
		String result = ds.create("4",jsonObject3,10);
		
		
		
		assertEquals(result, st);
		
	}
	
	//Unit test for invalid key name.
	@Test
	public void testCase5() throws ClassNotFoundException, IOException {
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

}
