package com.anshul.rayminder.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anshul.rayminder.App;
import com.anshul.rayminder.MainActivity;
import com.anshul.rayminder.R;
import com.anshul.rayminder.fragment.CreateReminderFragment;
import com.anshul.rayminder.fragment.ReminderDetailFragment;
import com.anshul.rayminder.model.Reminder;
import com.anshul.rayminder.model.User;
import com.leaking.slideswitch.SlideSwitch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReminderAdapter extends BaseAdapter {
    private List<Reminder> menuItems;
    private Context context;
    private SimpleDateFormat sf = new SimpleDateFormat("dd/mm/yy");
    private SimpleDateFormat sfTime = new SimpleDateFormat("hh:mm");

    public ReminderAdapter(Context context, List<Reminder> list) {
        this.context = context;
        this.menuItems = list;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int i) {
        return menuItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Reminder item = (Reminder)getItem(i);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView  = inflater.inflate(R.layout.reminder_item, viewGroup, false);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgType);//category image
        TextView txtDMY = (TextView) convertView.findViewById(R.id.lblDMY);
        TextView txtDate = (TextView) convertView.findViewById(R.id.lblDate);
        TextView txtTime = (TextView) convertView.findViewById(R.id.lblTime);
        TextView txtName = (TextView) convertView.findViewById(R.id.lblName);
        SlideSwitch ss = (SlideSwitch)convertView.findViewById(R.id.sOnOff);
        LinearLayout lytContainer = (LinearLayout) convertView.findViewById(R.id.lytContainer);
        LinearLayout lytInvited = (LinearLayout) convertView.findViewById(R.id.lytInvited);
        TextView txtNoInvited = (TextView)convertView.findViewById(R.id.lblNoInvite);
        ImageView ivShare = (ImageView) convertView.findViewById(R.id.ivShare);

        txtDMY.setTypeface(App.tf);
        txtDate.setTypeface(App.tf);
        txtTime.setTypeface(App.tf);
        txtName.setTypeface(App.tf);
        txtNoInvited.setTypeface(App.tf);

        switch (item.reminderType){
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

        String[] formatedStr = getFormatedDate(item.date);

        if(formatedStr == null)
            return convertView;

        txtDMY.setText(formatedStr[0]);
        txtDate.setText(formatedStr[1]);
        txtTime.setText(formatedStr[2]);
        txtName.setText(item.name);

        if(item.invitedUsers == null || item.invitedUsers.size() == 0){
            lytContainer.setVisibility(View.GONE);
            txtNoInvited.setVisibility(View.VISIBLE);
        }else{
            AddObject(lytInvited, item.invitedUsers);
        }

        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateReminderFragment.shopPopupNew(context, item.name);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).replaceFragment(ReminderDetailFragment.newInstance("",""));
            }
        });
        return convertView;
    }

    private void AddObject(View v, List<User> users) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
        params.setMargins(3,3,3,3);
        params.gravity = Gravity.CENTER_HORIZONTAL;

        for (User user : users) {
            LinearLayout lyt = new LinearLayout(context);
            lyt.setOrientation(LinearLayout.VERTICAL);

            CircleImageView objImage = new CircleImageView(context);
            objImage.setImageResource(R.drawable.avatar_1);

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            param.setMargins(20,0,20,0);
            lyt.setLayoutParams(param);

            TextView txtfName = new TextView(context);
            txtfName.setText(user.fName);
            txtfName.setTypeface(App.tf);
            txtfName.setTextColor(Color.WHITE);
            txtfName.setTextSize(10);
            txtfName.setLayoutParams(param);

            TextView txtlName = new TextView(context);
            txtlName.setText(user.lName);
            txtlName.setTypeface(App.tf);
            txtlName.setTextColor(Color.WHITE);
            txtlName.setTextSize(10);
            txtlName.setLayoutParams(param);

            objImage.setMaxWidth(20);
            objImage.setMaxHeight(20);
            objImage.setLayoutParams(params);
            lyt.addView(objImage);
            lyt.addView(txtfName);
            lyt.addView(txtlName);
            ((ViewGroup) v).addView(lyt);
        }
    }


    private String[] getFormatedDate(Date date){

        try{
            String[] DATE = {"SUN","MON","TUE","WEN","THU","FRI","SAT"};
            String DMY = sf.format(date);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            String sDate = DATE[c.get(Calendar.DAY_OF_WEEK)-1];
            String sTime = sfTime.format(date);

            return new String[]{DMY, sDate, sTime};
        }catch (Exception e){
            return null;
        }
    }
}
