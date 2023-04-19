<h1><b>DataStore</b>
</h1>
<u/>
A file-based key-value data store that supports the basic CRD (create, read, and delete) operations. This data store is meant to be used as a local storage for one single process on one laptop. The data store can be exposed as a library to clients that can instantiate a class and work with the data store. 

<h1><u/>
How to make use of the DataStore?
</h1>
First create a project and add the DataStore dependency, then you will be able instantiate and use the DataStore for your project usecase. For now the DataStore is available as a jar dependency only.download the datastore.jar and execute the following command in CMD:<b>java.exe -jar KeyValueDataStore.java</b> 

<h1>Example</h1><u/>
Download the project.In the eclipse,go to consumer package,and select DataConsumer.This is the driver class.Create the JSON Objects to be uploaded in file. 

Create a new object of the datastore as,
<b>DataStore ds = new DataStore();</b></h2><br/>
 if location has to be specified then,
<b>DataStore ds = new DataStore(String dataStoreLoc,String dataStoreName);</b></h2><br/>
Now the following operations can be performed.</h2><u/>

<h1><b>Create Operation</b></h1><u/>
  ds.create(String key,JSON Object value,int timeToLive);
 
<h1><b>Read Operation</b></h1><u />
   System.out.println(ds.read(String value));

<h1><b>Read Operation</b></h1><u />
   System.out.println(ds.delete(String value));
<u /><br><br>
  
<b>Appropriate error messages will be returned for "key already availabe" and other exceptions.</b>
<br><br>
<b> Thank You!</b>
