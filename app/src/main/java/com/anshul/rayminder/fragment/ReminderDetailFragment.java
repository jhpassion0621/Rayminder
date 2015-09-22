package com.anshul.rayminder.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.anshul.rayminder.App;
import com.anshul.rayminder.MainActivity;
import com.anshul.rayminder.R;
import com.anshul.rayminder.adapter.ReminderDetailAdapter;
import com.anshul.rayminder.helper.ViewHelper;
import com.anshul.rayminder.model.Reminder;
import com.anshul.rayminder.model.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.leaking.slideswitch.SlideSwitch;
import com.parse.ParseGeoPoint;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReminderDetailFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private GoogleMap googleMap;
    private Reminder mReminder;
    private View rootView;

    private CircleImageView ivAlarm, ivSms, ivEmail;

    private String mParam1;
    private String mParam2;
    private String reminderName;

    public static ReminderDetailFragment newInstance(String param1, String param2) {
        ReminderDetailFragment fragment = new ReminderDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    public ReminderDetailFragment() {

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
        rootView = inflater.inflate(R.layout.fragment_reminder_detail, container, false);
        this.mReminder = getReminder();
        this.reminderName = mReminder.name;
        if(this.mReminder.invitedUsers.size() > 0)
            mReminder.invitedUsers.add(new User());
        initControl();
        createMapView();
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initControl(){
        if(rootView == null)
            return;

        TextView lblName = (TextView)rootView.findViewById(R.id.lblName);
        TextView lblCategory = (TextView)rootView.findViewById(R.id.lblCategory);
        TextView lblSubCategory = (TextView)rootView.findViewById(R.id.lblSubCategory);
        TextView lblDMY = (TextView) rootView.findViewById(R.id.lblDMY);
        TextView lblTime = (TextView) rootView.findViewById(R.id.lblTime);

        ImageView ivNav = (ImageView) rootView.findViewById(R.id.imgNav);
        ImageView ivNew = (ImageView) rootView.findViewById(R.id.imgOption);

        ImageView ivShare = (ImageView) rootView.findViewById(R.id.ivShare);
        ImageView img = (ImageView) rootView.findViewById(R.id.ivCategory);

        SlideSwitch ss = (SlideSwitch) rootView.findViewById(R.id.sOnOff);
        ListView lst = (ListView) rootView.findViewById(R.id.lstInvitedUser);
        lst.setAdapter(new ReminderDetailAdapter(getActivity(), mReminder.invitedUsers));
        ViewHelper.getListViewSize(lst, getActivity());

        ivAlarm = (CircleImageView)rootView.findViewById(R.id.ivAlarm);
        ivSms = (CircleImageView)rootView.findViewById(R.id.ivSms);
        ivEmail = (CircleImageView)rootView.findViewById(R.id.ivEmail);

        TextView edtNote = (TextView) rootView.findViewById(R.id.edtNote);
        TextView lblLocation = (TextView) rootView.findViewById(R.id.lblLocation);

        ivShare.setOnClickListener(this);
        ivNav.setOnClickListener(this);
        ivNew.setOnClickListener(this);
        switch (mReminder.reminderType){
            case FAMILY:
                img.setImageResource(R.drawable.ic_cate_family);
                break;
            case WORK:
                img.setImageResource(R.drawable.ic_cate_working);
                break;
            case NEW:
                img.setImageResource(R.drawable.ic_cate_add);
                break;
            case HEALTH:
                img.setImageResource(R.drawable.ic_cate_health);
                break;
            case SHOPPING:
                img.setImageResource(R.drawable.ic_cate_shopping);
                break;
            case REGILIOUS:
                img.setImageResource(R.drawable.ic_cate_regiliou);
                break;
        }
        switch (mReminder.alertType){
            case Alarm:
                ivAlarm.setBorderColor(Color.WHITE);
                ivAlarm.setBorderWidth(2);
                ivAlarm.setImageResource(R.drawable.ic_alarm);
                break;
            case SMS:
                ivSms.setBorderColor(Color.WHITE);
                ivSms.setBorderWidth(2);
                ivSms.setImageResource(R.drawable.ic_sms);
                break;
            case Email:
                ivEmail.setBorderColor(Color.WHITE);
                ivEmail.setBorderWidth(2);
                ivEmail.setImageResource(R.drawable.ic_email);
                break;
        }
    }

    private void createMapView() {

        try {
            if (null == googleMap) {
                FragmentManager myFM = getChildFragmentManager();
                if (myFM == null) return;

                SupportMapFragment myMAPF = (SupportMapFragment) myFM.findFragmentById(R.id.map);
                if (myMAPF == null) return;

                googleMap = myMAPF.getMap();

                if (null == googleMap) {
                    Toast.makeText(getActivity(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                } else {
                    googleMap.setMyLocationEnabled(false);
                    googleMap.getUiSettings().setZoomControlsEnabled(false);
                    googleMap.getUiSettings().setZoomGesturesEnabled(true);
                    googleMap.getUiSettings().setMyLocationButtonEnabled(false);
                    googleMap.getUiSettings().setRotateGesturesEnabled(true);

                    if (mReminder.geoPosition.getLatitude() == 0.0 && mReminder.geoPosition.getLongitude() == 0.0)
                        Toast.makeText(getActivity(), "can't find your location", Toast.LENGTH_SHORT).show();
                    else {
                        addMaker(mReminder.geoPosition.getLatitude(), mReminder.geoPosition.getLongitude());
                    }

                }

            }
        } catch (NullPointerException exception) {

        }
    }

    private void addMaker(double latitude, double longitude) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).tilt(45).zoom(17).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here.");

        googleMap.addMarker(marker);
    }

    private Reminder getReminder() {

        Reminder reminder = new Reminder();
        reminder.date = new Date(System.currentTimeMillis());
        reminder.name = "Go for movie Jurassic world";
        reminder.alertType = App.AlertType.Alarm;
        reminder.reminderType = App.ReminderType.FAMILY;
        reminder.invitedUsers = new ArrayList<>();
        reminder.geoPosition = new ParseGeoPoint(60, 10);

        User user = new User();
        user.fName = "Anshul";
        user.lName = "Obama";
        reminder.invitedUsers.add(user);
        user = new User();
        user.fName = "JH";
        user.lName = "Lee";
        reminder.invitedUsers.add(user);

        return reminder;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.imgNav:
                ((MainActivity)getActivity()).replaceFragment(ReminderFragment.newInstance("",""));
                break;
            case R.id.imgOption:
                ((MainActivity)getActivity()).replaceFragment(CategoryFragment.newInstance("",""));
                break;
            case R.id.ivShare:
                CreateReminderFragment.shopPopupNew(getActivity(), reminderName);
                break;
            case R.id.ivAlarm:
                break;
            case R.id.ivSms:
                break;
            case R.id.ivEmail:
                break;

        }
    }
}
