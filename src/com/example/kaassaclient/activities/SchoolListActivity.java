package com.example.kaassaclient.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.kaassaclient.R;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.View;

import com.example.kaassaclient.activities.Entities.JSONSchool;
import com.example.kaassaclient.serviceshandler.HTTPServiceHandler;
import com.example.kaassaclient.serviceshandler.ServiceHandler;

public class SchoolListActivity extends ListActivity{
	
	//**********************Variables********************************
	JSONSchool jschool; //Representation of a json school
	JSONArray allschools = null;
    String url = null;	
	
	
	
	public SchoolListActivity(){
		instance = this;
		jschool = new JSONSchool(){
			@Override
			public void setURL(String url) {
				this.schoolUrl = url;				
			}			
		};
	}
	
	//***********Hashmap for ListView**********************
    List<HashMap<String,String>> schoolsList;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        schoolsList = new ArrayList<HashMap<String,String>>();
 
        ListView listview = getListView();
        
        // Listview on item click listener
        listview.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
            	try{
                String name = ((TextView)view.findViewById(R.id.school_name)).getText().toString();
                String slug = ((TextView)view.findViewById(R.id.slug)).getText().toString();
                String website = ((TextView)view.findViewById(R.id.website)).getText().toString();
 
                Log.d("KAASSA_SLUG",slug);
                // Starting single contact activity
                Intent intent = new Intent(getApplicationContext(),SingleSchoolActivity.class);
                intent.putExtra(jschool.KAASSA_NAME, name);
                intent.putExtra(jschool.KAASSA_CLASSE_SLUG, slug);
                intent.putExtra(jschool.KAASSA_CONTACT_WEB_SITE, website);
                startActivity(intent);
            	}
            	catch (Exception e){
            		Log.d("EXCEPTION",e.getMessage());
            		e.printStackTrace();
            		
            	}
 
            }
        });
        
        // Calling async task to get json
        new GetSchools().execute();
    }
 
   	
    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetSchools extends AsyncTask<Void, Void, Void> {

        private ProgressDialog pDialog;

    	
    	@Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(SchoolListActivity.this);
            pDialog.setMessage("Application Loading...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new HTTPServiceHandler();

			// Making a request to url and getting response
            //jschool.setURL("http://localhost/kaassa/schools.json");
            String jsonStr = sh.requestService(jschool.schoolUrl, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    
                    // Getting JSON Array node
                    allschools = jsonObj.getJSONArray(jschool.KAASSA_SCHOOLS);

                    // looping through All schools : not all the nodes have been selected
                    for (int i = 0; i < allschools.length(); i++) {
                        JSONObject schools = allschools.getJSONObject(i);
                         
                        String name = schools.getString(jschool.KAASSA_NAME);

                        String slug = schools.getString(jschool.KAASSA_SLUG);

                        // school node is JSON Object
                        JSONObject contact = schools.getJSONObject(jschool.KAASSA_CONTACT);
                        
                        String website = contact.getString(jschool.KAASSA_CONTACT_WEB_SITE);
                        // tmp hashmap for single school
                        HashMap<String, String> school = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        school.put(jschool.KAASSA_NAME, name);
                        school.put(jschool.KAASSA_SLUG, slug);
                        school.put(jschool.KAASSA_CONTACT_WEB_SITE, website);
                        
                        // adding school to school list
                        schoolsList.add(school);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    SchoolListActivity.this, schoolsList,
                    R.layout.kaassa_school_list_item, new String[] {jschool.KAASSA_NAME, jschool.KAASSA_SLUG,
                    		jschool.KAASSA_CONTACT_WEB_SITE }, new int[] { R.id.school_name,R.id.slug,R.id.website });

            setListAdapter(adapter);
        }
        

    }
    
    // Is this the proper way to do?
    public static Context getContext(){
        return instance.getApplicationContext();
    }
    
    private static SchoolListActivity instance;

    public static SchoolListActivity getInstance() {
        return instance;
    }
	
}
