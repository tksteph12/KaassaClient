package com.example.kaassaclient.activities;

import android.app.ListActivity;
import android.content.Context;

/**
 * @author Stephane TATCHUM
 * 
 */
public class CompanyListActivity extends ListActivity{
	
	private static CompanyListActivity instance;
	
	public CompanyListActivity(){
		instance = this;
	}
	
	
    // Is this the proper way to do?
    public static Context getContext(){
        return instance.getApplicationContext();
    }
    

    public static CompanyListActivity getInstance() {
        return instance;
    }
	
}



