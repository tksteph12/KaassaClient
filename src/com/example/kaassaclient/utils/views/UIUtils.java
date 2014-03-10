package com.example.kaassaclient.utils.views;

import android.app.Dialog;
import android.app.ProgressDialog;

import com.example.kaassaclient.activities.SchoolListActivity;

/**
 * @author Stephane TATCHUM
 *	Delegate class for all the ui utilities such as dialog boxes, progress bars, standard menus ...
 * 
 */
public final class UIUtils {
	
	public static void wait(ProgressDialog dialog){
		dialog.setMessage("Application Loading...");
		dialog.setCancelable(false);
		dialog.show();
	}
	
	public static void dismiss(ProgressDialog dialog){
		if (dialog.isShowing())
			dialog.dismiss();
	}
	

}



