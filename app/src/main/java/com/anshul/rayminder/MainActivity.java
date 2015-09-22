package com.anshul.rayminder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;

import com.anshul.rayminder.fragment.NavigationDrawerFragment;
import com.anshul.rayminder.fragment.ReminderFragment;


public class MainActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.anshul.rayminder.R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(com.anshul.rayminder.R.id.navigation_drawer);

        getActionBar().hide();

        mNavigationDrawerFragment.setUp(
                com.anshul.rayminder.R.id.navigation_drawer,
                (DrawerLayout) findViewById(com.anshul.rayminder.R.id.drawer_layout));

        FragmentManager fragmentManager = getSupportFragmentManager();
        if(savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .replace(com.anshul.rayminder.R.id.container, ReminderFragment.newInstance("",""))
                    .commit();
        }
    }

    public void replaceFragment(Fragment fr){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(com.anshul.rayminder.R.id.container, fr)
                .commit();
    }
    @Override
    public void onNavigationDrawerItemSelected(int position) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fr = null;
        switch (position){
            case 0://account setting
                break;
            case 1://reminder
                fr = ReminderFragment.newInstance("","");
                break;
            case 2://add reminder
                break;
            case 3://feedback
                break;
            case 4://logout
                break;
        }
        if(fr == null)
            return;

        fragmentManager.beginTransaction()
                .replace(com.anshul.rayminder.R.id.container, fr)
                .commit();
    }
}
