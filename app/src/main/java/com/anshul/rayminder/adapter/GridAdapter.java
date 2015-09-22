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

public class GridAdapter extends BaseAdapter {
	Context context;

	List<Category> categories;

	public GridAdapter(Context context, List<Category> deals) {
		super();
		this.context = context;
		this.categories = deals;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		View grid = convertView;

		if (grid == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			grid = inflater.inflate(R.layout.category_item, parent, false);

			holder = new ViewHolder();

			holder.ivImageView = (ImageView) grid.findViewById(R.id.ivCategory);

			holder.tvdealName = (TextView) grid.findViewById(R.id.lblName);

			grid.setTag(holder);
		} else {
			holder = (ViewHolder) grid.getTag();
		}

		holder.tvdealName.setText(getItem(position).name);
		holder.tvdealName.setTypeface(App.tf);
		holder.ivImageView.setImageResource(getItem(position).imageResource);
		grid.setBackgroundColor(context.getResources().getColor(R.color.user_list_bg2));
		return grid;
	}

	private static class ViewHolder {
		ImageView ivImageView;
		TextView tvdealName;

	}
}