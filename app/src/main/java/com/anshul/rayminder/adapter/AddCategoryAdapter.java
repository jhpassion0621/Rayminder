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
import com.anshul.rayminder.model.Category;

import java.util.List;

public class AddCategoryAdapter extends BaseAdapter {
	Context context;

	List<Category> categories;
	int where;
	private int index = 0;

	public AddCategoryAdapter(Context context, List<Category> deals, int where) {
		super();
		this.context = context;
		this.categories = deals;
		this.where = where;
	}
	@Override
	public int getCount() {

		return categories.size();
	}

	@Override
	public Category getItem(int position) {
		return categories.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	public void setItemIndex(int index){
		this.index = index;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		View grid = convertView;

		if (grid == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			grid = inflater.inflate(R.layout.add_category_item, parent, false);

			holder = new ViewHolder();

			holder.ivImageView = (ImageView) grid.findViewById(R.id.ivCategory);

			holder.tvdealName = (TextView) grid.findViewById(R.id.lblName);

			grid.setTag(holder);
		} else {
			holder = (ViewHolder) grid.getTag();
		}

		holder.tvdealName.setText(getItem(position).name);
		holder.tvdealName.setTypeface(App.tf);
		if(where != 0){
			if(position == index)
				holder.ivImageView.setImageResource(where == 0 ? R.drawable.ic_green_circle : R.drawable.ic_alert_fill);
			else
				holder.ivImageView.setImageResource(R.drawable.circle_with_strok);

		}
		grid.setBackgroundColor(where  == 0 ? context.getResources().getColor(R.color.login_signup_button_bg)
				: context.getResources().getColor(R.color.create_reminder_bg));
		return grid;
	}

	private static class ViewHolder {
		ImageView ivImageView;
		TextView tvdealName;

	}
}