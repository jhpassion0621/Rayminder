package com.anshul.rayminder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.anshul.rayminder.App;
import com.anshul.rayminder.MainActivity;
import com.anshul.rayminder.R;
import com.anshul.rayminder.adapter.ReminderAdapter;
import com.anshul.rayminder.model.Reminder;
import com.anshul.rayminder.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReminderFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View rootView;
    private ListView lstReminder;

    private String mParam1;
    private String mParam2;

    public static ReminderFragment newInstance(String param1, String param2) {
        ReminderFragment fragment = new ReminderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ReminderFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_reminder, container, false);
        lstReminder = (ListView) rootView.findViewById(R.id.lstReminder);
        List<Reminder> list = getReminder();
        lstReminder.setAdapter(new ReminderAdapter(getActivity(), list));
        ImageView nav = (ImageView) rootView.findViewById(R.id.imgNav);
        nav.setOnClickListener(this);
        ImageView imgFilter = (ImageView) rootView.findViewById(R.id.imgOption);
        imgFilter.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgNav:
                NavigationDrawerFragment.mDrawerLayout.openDrawer(NavigationDrawerFragment.mFragmentContainerView);
                break;
            case R.id.imgOption:

                break;
        }
    }

    private List<Reminder> getReminder(){
        List<Reminder> reminders = new ArrayList<>();
        Reminder reminder = new Reminder();
        reminder.date = new Date(System.currentTimeMillis());
        reminder.name = "Go for movie Jurassic world";
        reminder.alertType = App.AlertType.Alarm;
        reminder.reminderType = App.ReminderType.FAMILY;
        reminder.invitedUsers = new ArrayList<>();

        User user = new User();
        user.fName = "Anshul";
        user.lName = "Obama";
        reminder.invitedUsers.add(user);
        user = new User();
        user.fName = "JH";
        user.lName = "Lee";
        reminder.invitedUsers.add(user);
        reminders.add(reminder);

        reminder = new Reminder();
        reminder.date = new Date(System.currentTimeMillis());
        reminder.name = "Go for Check body";
        reminder.alertType = App.AlertType.Email;
        reminder.reminderType = App.ReminderType.HEALTH;
        reminder.invitedUsers = new ArrayList<>();

        user = new User();
        user.fName = "Nicki";
        user.lName = "Denver";
        reminder.invitedUsers.add(user);
        user = new User();
        user.fName = "Roy";
        user.lName = "Dong";
        reminder.invitedUsers.add(user);

        reminders.add(reminder);
        return reminders;
    }
}
