package com.anshul.rayminder.model;

import com.anshul.rayminder.App;
import com.parse.ParseGeoPoint;

import java.text.ParsePosition;
import java.util.Date;
import java.util.List;

/**
 * Created by jhpassion0621 on 9/11/15.
 */
public class Reminder {
    public String id;
    public Date date;
    public String name;
    public boolean status;
    public App.ReminderType reminderType;
    public List<User> invitedUsers;
    public App.AlertType alertType;
    public String note;
    public ParseGeoPoint geoPosition;
}
