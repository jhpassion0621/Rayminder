package com.anshul.rayminder;

import android.app.Application;
import android.graphics.Typeface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jhpassion0621 on 9/10/15.
 */
public class App extends Application {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd kk:mm:ss";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/mm/yy");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm");


    public static Typeface tf;
    public enum ReminderType{FAMILY, SHOPPING, HEALTH, REGILIOUS, WORK, NEW};
    public enum AlertType{Alarm, Email, SMS};
    public enum CategoryType{Personal, Company};
    @Override
    public void onCreate() {
        super.onCreate();
        tf = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Regular.ttf");
    }

    public static String[] getFormatedDate(Date date){

        try{
            String[] DATE = {"SUN","MON","TUE","WEN","THU","FRI","SAT"};
            String DMY = App.DATE_FORMAT.format(date);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            String sDate = DATE[c.get(Calendar.DAY_OF_WEEK)-1];
            String sTime = App.TIME_FORMAT.format(date);

            return new String[]{DMY, sDate, sTime};
        }catch (Exception e){
            return null;
        }
    }
}
