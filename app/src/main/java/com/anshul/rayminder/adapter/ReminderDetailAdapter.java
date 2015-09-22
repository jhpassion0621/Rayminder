package com.anshul.rayminder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anshul.rayminder.App;
import com.anshul.rayminder.R;
import com.anshul.rayminder.model.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReminderDetailAdapter extends BaseAdapter {
    private List<User> menuItems;
    private Context context;

    public ReminderDetailAdapter(Context context, List<User> list) {
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
        User item = (User) getItem(i);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.reminder_detail_item, viewGroup, false);
        CircleImageView img = (CircleImageView) convertView.findViewById(R.id.ivAvatar);
        TextView lblFName = (TextView) convertView.findViewById(R.id.lblFName);
        TextView lblLName = (TextView) convertView.findViewById(R.id.lblLName);

        ImageView ivDelete = (ImageView) convertView.findViewById(R.id.ivDelete);

        lblFName.setTypeface(App.tf);
        lblLName.setTypeface(App.tf);

        if (item.fName == null && item.lName == null) {
            lblFName.setText("Add");
            lblLName.setText("Name");
            img.setImageResource(R.drawable.ic_add_reminder);
            img.setScaleX(0.6f);
            img.setScaleY(0.6f);
            ivDelete.setVisibility(View.GONE);

        } else {
            lblFName.setText(item.fName);
            lblLName.setText(item.lName);
            img.setImageResource(R.drawable.avatar_2);
        }


        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "will delete this", Toast.LENGTH_SHORT).show();
            }
        });

        if (i % 2 == 0)
            convertView.setBackgroundColor(context.getResources().getColor(R.color.user_list_bg1));
        else
            convertView.setBackgroundColor(context.getResources().getColor(R.color.user_list_bg2));


        return convertView;
    }
}
