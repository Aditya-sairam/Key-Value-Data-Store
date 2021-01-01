package unitTests;
import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.DataStore;

/***
 * This class contains unit test for the read operation
 * 
 * @author Aditya
 */
public class readTest {
	
	
	//test case for invalid key.
	@Test
	public void readTestCase1() throws ClassNotFoundException, IOException {
		
		DataStore ds = new DataStore();
		
		
		
		JSONObject jsonObject4 = new JSONObject();
		jsonObject4.put("firstName", "CDE");
		jsonObject4.put("lastName", "PQR");
		jsonObject4.put("address", "Ooty");
		
		
		String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefkjasdhe";
		String st = "Operation failed due to key length exceeded the limit(32chars)";
		
		Object result = ds.read(key);
		
		
		
		assertEquals(result, st);
		
	}
	
	//test case for successful read operation.
	@Test
	public void readTestCase2() {
		
		DataStore ds = new DataStore();
		
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("firstName", "Hello");
		jsonObject2.put("lastName", "Hai");
		jsonObject2.put("address", "Shimla");
		
		ds.create("4", jsonObject2);
		Object res = ds.read("4");
		
		String st = jsonObject2.toString();
		String result = res.toString();
		assertEquals(st,result);
	
	}
	
	//test case for no key available.
	@Test
	public void readTestCase3() {
		DataStore ds = new DataStore();
		
		Object res = ds.read("7");
		
		String compare = "Operation failed due to key not available";
		
		assertEquals(compare,res);
	}
}
