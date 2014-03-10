package com.example.kaassaclient.serviceshandler;

import java.util.List;

import org.apache.http.NameValuePair;

public abstract class ServiceHandler {
	
	//********Variable Declarations***********************
	
	static String response = null;
	 public final static int GET = 1;
	 public final static int POST = 2;
	
	public ServiceHandler(){
		
	}
	
	/**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * */
	public abstract String requestService(String url, int method);
	
	/**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     * */
	//abstract String requestService(String url, int method,List<?>params);
	public abstract String requestService(String url, int method, List<NameValuePair> params);
		
}
