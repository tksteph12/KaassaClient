package com.example.kaassaclient.datas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

import com.example.kaassaclient.activities.SchoolListActivity;

public class BackUpDatas {
	
	
	public static String fetch(String file){
		Object obj = null;
		try {
			Context context = SchoolListActivity.getContext();
			InputStream is = context.getResources().getAssets().open(file);
		        int size = is.available();
		        byte[] buffer = new byte[size];
		        is.read(buffer);
		        is.close();
		        obj = new String(buffer, "UTF-8");		 
			
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return obj.toString();
	}
	
}
