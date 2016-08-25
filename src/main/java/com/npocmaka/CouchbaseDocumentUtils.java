package com.npocmaka;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;


public class CouchbaseDocumentUtils {
	
	public static boolean createDocument(String host,String user,String password,String bucket,String id,String content){
		
		Client client = ClientBuilder.newClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.universalBuilder()
	      .credentialsForBasic(user, password)
	      .build();
		
		client.register(feature);
		
		if(!host.startsWith("http://")){
			host="http://"+host;
		}
		
		WebTarget target = client.target(host).path("/pools/default/buckets/"+bucket+"/docs/"+id);
				
		Response r=target.request(MediaType.APPLICATION_JSON_TYPE)
		.post(Entity.entity(content,MediaType.APPLICATION_JSON));
		
		if(r.getStatus()!=200){
			return false;
		}
		System.out.println(r.toString());
		return true;
	} 
	
	public static boolean deleteDocument(String host,String user,String password,String bucket,String id){
		
		Client client = ClientBuilder.newClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.universalBuilder()
	      .credentialsForBasic(user, password)
	      .build();
		
		client.register(feature);
		
		if(!host.startsWith("http://")){
			host="http://"+host;
		}
		
		WebTarget target = client.target(host).path("/pools/default/buckets/"+bucket+"/docs/"+id);
				
		Response r=target.request(MediaType.APPLICATION_JSON_TYPE)
		.delete();
		
		if(r.getStatus()!=200){
			return false;
		}
		System.out.println(r.toString());
		return true;
	} 
	
	public static String getDocument(String host,String user,String password,String bucket,String id){
		
		Client client = ClientBuilder.newClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.universalBuilder()
	      .credentialsForBasic(user, password)
	      .build();
		
		client.register(feature);
		
		if(!host.startsWith("http://")){
			host="http://"+host;
		}
		
		WebTarget target = client.target(host).path("/pools/default/buckets/"+bucket+"/docs/"+id);
				
		Response r=target.request(MediaType.APPLICATION_JSON_TYPE)
		.get();

		System.out.println(r.toString());
		//System.out.println(r.readEntity(String.class));
		return r.readEntity(String.class);
	}
	
	public static boolean updateDocument(String host,String user,String password,String bucket,String id,String content){
		return createDocument(host, user, password, bucket, id, content);
	}
	
	public static boolean createDocument(Map<String, String> props){
		String[] keys={"host","user","password","bucket","id","content"};
		checkProps(props, keys);
		String host=props.get("host");
		String user=props.get("user");
		String password=props.get("password");
		String bucket=props.get("bucket");
		String id=props.get("id");
		String content=props.get("content");
		
		return createDocument(host, user, password, bucket, id, content);
	}
	
	public static boolean updateDocument(Map<String, String> props){
		return createDocument(props);
	}
	
	public static boolean deleteDocument(Map<String, String> props){
		String[] keys={"host","user","password","bucket","id"};
		checkProps(props, keys);
		String host=props.get("host");
		String user=props.get("user");
		String password=props.get("password");
		String bucket=props.get("bucket");
		String id=props.get("id");
		
		return deleteDocument(host, user, password, bucket, id);
	}
	
	public static String getDocument(Map<String, String> props){
		String[] keys={"host","user","password","bucket","id"};
		checkProps(props, keys);
		String host=props.get("host");
		String user=props.get("user");
		String password=props.get("password");
		String bucket=props.get("bucket");
		String id=props.get("id");
		
		return getDocument(host, user, password, bucket, id);
	}
	
	private  static void  checkProps(Map<String,String> props,String[] keys){
		Object o;
		for (String k : keys) {
			o=props.get(k);
			if(o.equals(null)){
				throw new IllegalArgumentException(k + " has no value");
			}
		}
	}
}
