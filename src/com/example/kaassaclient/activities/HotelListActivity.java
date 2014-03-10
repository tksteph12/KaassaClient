package com.example.kaassaclient.activities;

import android.app.ListActivity;
import android.content.Context;

/**
 * @author Stephane TATCHUM
 *
 * 
 */
public class HotelListActivity extends ListActivity {
	
	private static HotelListActivity instance;
	
	public HotelListActivity(){
		instance = this;
	}
	
	
    // Is this the proper way to do?
    public static Context getContext(){
        return instance.getApplicationContext();
    }
    

    public static HotelListActivity getInstance() {
        return instance;
    }
	

}



