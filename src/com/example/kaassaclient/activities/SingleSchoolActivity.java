package com.example.kaassaclient.activities;


import com.example.kaassaclient.activities.Entities.JSONSchool;
import com.example.kaassaclient.PagerActivity;
import com.example.kaassaclient.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.widget.TextView;

public class SingleSchoolActivity extends Activity{
	JSONSchool jschool; //Representation of a json school
	
	public SingleSchoolActivity(){
		jschool = new JSONSchool(){
			@Override
			public void setURL(String url) {
				this.url = url;				
			}			
		};
	}
	
	@Override
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaassa_single_school);
        
        // getting intent data
        Intent intent = getIntent();
        
        // Get JSON values from previous intent
        String name = intent.getStringExtra(jschool.KAASSA_NAME);
        String slug = intent.getStringExtra(jschool.KAASSA_SLUG);
        String website = intent.getStringExtra(jschool.KAASSA_CONTACT_WEB_SITE);
        
        // Displaying all values on the screen
        TextView lblName = (TextView)findViewById(R.id.shcool_name_label);
        TextView lblSlug = (TextView) findViewById(R.id.school_slug);
        TextView lblWebSite = (TextView) findViewById(R.id.school_website);
        
        lblName.setText(name);
        lblSlug.setText(slug);
        lblWebSite.setText(website);
        
     // Set up action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            	finishFromChild(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
