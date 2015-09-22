package com.anshul.rayminder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.anshul.rayminder.fragment.LoginFragment;
import com.anshul.rayminder.fragment.ReminderFragment;

public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.anshul.rayminder.R.layout.activity_login);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if(savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .replace(com.anshul.rayminder.R.id.container, new LoginFragment())
                    .commit();
        }
    }
    public void replaceFragment(Fragment fr){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(com.anshul.rayminder.R.id.container, fr)
                .commit();
    }
}
