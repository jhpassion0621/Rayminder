package com.anshul.rayminder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anshul.rayminder.App;
import com.anshul.rayminder.R;
import com.anshul.rayminder.model.MenuItem;

import java.util.List;

public class menuAdapter extends BaseAdapter {
    private List<MenuItem> menuItems;
    private Context context;
    public menuAdapter(Context context, List<MenuItem> list) {
        this.context = context;
        this.menuItems = list;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MenuItem item = menuItems.get(i);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView  = inflater.inflate(R.layout.menu_item, viewGroup, false);
        ImageView img = (ImageView) convertView.findViewById(R.id.menuImage);
        TextView txt = (TextView) convertView.findViewById(R.id.menuTxt);
        txt.setTypeface(App.tf);

        img.setImageResource(item.resourceId);
        txt.setText(item.menuName);
        return convertView;
    }
}
