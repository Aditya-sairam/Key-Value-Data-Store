package bean;


import org.json.simple.JSONObject;

import java.io.Serializable;

/***
 * Contains the data to be used in CRD Operations.
 * 
 * @author Aditya
 *
 */


public class Data implements Serializable {
	
	String key;
	int timeToLive;
	JSONObject  value;
	
	long creationDateTimeMillis;

	public long getCreationDateTimeMillis() {
		return creationDateTimeMillis;
	}
	public void setCreationDateTimeMillis(long creationDateTimeMillis) {
		this.creationDateTimeMillis = creationDateTimeMillis;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}
	public JSONObject getValue() {
		return value;
	}
	public void setValue(JSONObject value) {
		this.value = value;
	}
	
	
	
}
