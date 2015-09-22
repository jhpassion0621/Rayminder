package com.anshul.rayminder.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anshul.rayminder.MainActivity;
import com.anshul.rayminder.R;
import com.anshul.rayminder.adapter.AddCategoryAdapter;
import com.anshul.rayminder.model.Category;
import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;
import com.codetroopers.betterpickers.timepicker.TimePickerBuilder;
import com.codetroopers.betterpickers.timepicker.TimePickerDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateReminderFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View rootView;
    private List<Category> categories;

    private CircleImageView ivColorGreen;
    private CircleImageView ivColorSatGreen;
    private CircleImageView ivColorTrans;
    private CircleImageView ivColorBlue;
    private CircleImageView ivColorPurple;
    private CircleImageView ivColorPink;

    //Dummy reminder name;
    private String reminderName = "Go for movie Jurassic World";

    private String[] categoryName = {"Birthday Dates","Marriage Anniversary Date","Death Anniversary Date","Children School Activity","Husband/ Suppose Event","Meeting Date","Add New"};

    public static CreateReminderFragment newInstance(String param1, String param2) {
        CreateReminderFragment fragment = new CreateReminderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CreateReminderFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private AddCategoryAdapter adapter;
    private LinearLayout lytRepeatOption;
    private TextView lblDate, lblTime;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_reminder, container, false);
        GridView grid = (GridView) rootView.findViewById(R.id.gridCategory);
        categories = getCategory();
        adapter = new AddCategoryAdapter(getActivity(), categories, 1);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemIndex(position);
                adapter.notifyDataSetChanged();
            }
        });

        rootView.findViewById(R.id.imgNav).setOnClickListener(this);
        rootView.findViewById(R.id.lblSave).setOnClickListener(this);
        rootView.findViewById(R.id.lblCancle).setOnClickListener(this);
        rootView.findViewById(R.id.ivRepeatOption).setOnClickListener(this);

        lytRepeatOption = (LinearLayout)rootView.findViewById(R.id.lytRepeatOption);

        ivColorGreen = (CircleImageView)rootView.findViewById(R.id.ivColorGreen);
        ivColorTrans = (CircleImageView)rootView.findViewById(R.id.ivColorTrans);
        ivColorSatGreen = (CircleImageView)rootView.findViewById(R.id.ivSatGreen);
        ivColorBlue = (CircleImageView)rootView.findViewById(R.id.ivColorBlue);
        ivColorPurple = (CircleImageView)rootView.findViewById(R.id.ivColorPurple);
        ivColorPink = (CircleImageView)rootView.findViewById(R.id.ivColorPink);


        ivColorGreen.setOnClickListener(this);
        ivColorTrans.setOnClickListener(this);
        ivColorSatGreen.setOnClickListener(this);
        ivColorBlue.setOnClickListener(this);
        ivColorPurple.setOnClickListener(this);
        ivColorPink.setOnClickListener(this);

        lblDate = (TextView)rootView.findViewById(R.id.lblSelectDate);
        lblTime = (TextView)rootView.findViewById(R.id.lblSelectTime);
        lblDate.setOnTouchListener(edtTouchHandler);
        lblTime.setOnTouchListener(timeTouchHandler);

        return rootView;
    }
    private View.OnTouchListener edtTouchHandler = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (MotionEvent.ACTION_UP == event.getAction()) {
                DatePickerBuilder dpb = new DatePickerBuilder()
                        .setFragmentManager(getActivity().getSupportFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment);
                Calendar cl = Calendar.getInstance();

                dpb.setYear(cl.get(Calendar.YEAR));
                dpb.setMonthOfYear(cl.get(Calendar.MONTH));

                dpb.show();
                dpb.addDatePickerDialogHandler(new DatePickerDialogFragment.DatePickerDialogHandler() {

                    @Override
                    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
                        lblDate.setText(String.format("%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth));
                    }
                });
            }
            return true;
        }
    };
    private View.OnTouchListener timeTouchHandler = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (MotionEvent.ACTION_UP == event.getAction()) {
                TimePickerBuilder tpb = new TimePickerBuilder()
                        .setFragmentManager(getActivity().getSupportFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment);

                tpb.show();
                tpb.addTimePickerDialogHandler(new TimePickerDialogFragment.TimePickerDialogHandler(){
                    @Override
                    public void onDialogTimeSet(int i, int hourOfDay, int minute) {
                        if(hourOfDay > 12)
                            lblTime.setText(String.format("%d:%02d PM",hourOfDay -12, minute));
                        else
                            lblTime.setText(String.format("%d:%02d AM",hourOfDay, minute));
                    }
                });
            }
            return true;
        }
    };
    private List<Category> getCategory() {

        List<Category> categories = new ArrayList<>();
        for(int i = 0 ; i < categoryName.length ; i++){
            Category item = new Category();
            item.name = categoryName[i];
            categories.add(item);
        }
        return categories;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgNav:
                ((MainActivity)getActivity()).replaceFragment(ReminderFragment.newInstance("",""));
                break;
            case R.id.lblSave:
                shopPopupNew(getActivity(), reminderName);
                break;
            case R.id.ivColorGreen:

                break;
            case R.id.ivColorTrans:
                break;
            case R.id.ivSatGreen:
                break;
            case R.id.ivColorBlue:
                break;
            case R.id.ivColorPurple:
                break;
            case R.id.ivColorPink:
                break;
            case R.id.ivRepeatOption:
                if(lytRepeatOption.getVisibility() == View.VISIBLE)
                    lytRepeatOption.setVisibility(View.GONE);
                else if(lytRepeatOption.getVisibility() == View.GONE)
                    lytRepeatOption.setVisibility(View.VISIBLE);
                break;
        }
    }

    public static void shopPopupNew(Context context, String reminderName) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_dlg);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Button btnShare = (Button) dialog.findViewById(R.id.btnShare);
        Button btnSkip = (Button) dialog.findViewById(R.id.btnSkip);

        final ImageView ivGoogle = (ImageView) dialog.findViewById(R.id.ivShareGoogle);
        final ImageView ivFB = (ImageView) dialog.findViewById(R.id.ivShareFB);
        final ImageView ivIn = (ImageView) dialog.findViewById(R.id.ivShareIn);
        final ImageView ivTwitter = (ImageView) dialog.findViewById(R.id.ivShareTwitter);
        final ImageView ivWhat = (ImageView) dialog.findViewById(R.id.ivShareWhat);
        ivGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivGoogle.setImageResource(R.drawable.icon_share_pop_google_plus_on);
                ivFB.setImageResource(R.drawable.icon_share_pop_facebook_off);
                ivIn.setImageResource(R.drawable.icon_share_pop_linkedin_off);
                ivTwitter.setImageResource(R.drawable.icon_share_pop_twitter_off);
                ivWhat.setImageResource(R.drawable.icon_share_pop_whatsapp_off);
            }
        });
        ivFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivGoogle.setImageResource(R.drawable.icon_share_pop_google_plus_off);
                ivFB.setImageResource(R.drawable.icon_share_pop_facebook_on);
                ivIn.setImageResource(R.drawable.icon_share_pop_linkedin_off);
                ivTwitter.setImageResource(R.drawable.icon_share_pop_twitter_off);
                ivWhat.setImageResource(R.drawable.icon_share_pop_whatsapp_off);
            }
        });
        ivIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ivGoogle.setImageResource(R.drawable.icon_share_pop_google_plus_off);
                ivFB.setImageResource(R.drawable.icon_share_pop_facebook_off);
                ivIn.setImageResource(R.drawable.icon_share_pop_linkedin_on);
                ivTwitter.setImageResource(R.drawable.icon_share_pop_twitter_off);
                ivWhat.setImageResource(R.drawable.icon_share_pop_whatsapp_off);
            }
        });
        ivTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivGoogle.setImageResource(R.drawable.icon_share_pop_google_plus_off);
                ivFB.setImageResource(R.drawable.icon_share_pop_facebook_off);
                ivIn.setImageResource(R.drawable.icon_share_pop_linkedin_off);
                ivTwitter.setImageResource(R.drawable.icon_share_pop_twitter_on);
                ivWhat.setImageResource(R.drawable.icon_share_pop_whatsapp_off);
            }
        });
        ivWhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivGoogle.setImageResource(R.drawable.icon_share_pop_google_plus_off);
                ivFB.setImageResource(R.drawable.icon_share_pop_facebook_off);
                ivIn.setImageResource(R.drawable.icon_share_pop_linkedin_off);
                ivTwitter.setImageResource(R.drawable.icon_share_pop_twitter_off);
                ivWhat.setImageResource(R.drawable.icon_share_pop_whatsapp_on);
            }
        });


        TextView caption = (TextView) dialog.findViewById(R.id.lblReminderName);
        caption.setText(reminderName);

        btnShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }
}
