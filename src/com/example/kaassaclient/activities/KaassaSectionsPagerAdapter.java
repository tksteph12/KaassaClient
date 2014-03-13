package com.example.kaassaclient.activities;

import com.example.kaassaclient.activities.fragments.HotelSearch;
import com.example.kaassaclient.activities.fragments.SchoolSearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class 
KaassaSectionsPagerAdapter extends FragmentPagerAdapter {

	public KaassaSectionsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                // The first section of the app is the most interesting -- it offers
                // a launchpad into the other demonstrations in this example application.
                return new SchoolSearch();

            default:
                // The other sections of the app are dummy placeholders.
                Fragment fragment = new HotelSearch();
                Bundle args = new Bundle();
                args.putInt(HotelSearch.ARG_SECTION_NUMBER, i + 1);
                fragment.setArguments(args);
                return fragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Section " + (position + 1);
    }

}
