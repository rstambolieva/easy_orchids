package com.example.easyorchids;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyOrchidsAdapter extends ArrayAdapter<Orchid> {
	// The activity context
	Context context;
	// Id of the list item layout
	int layoutResourceId;
	List<Orchid> orchidsList = null;

	public MyOrchidsAdapter(Context context, int layoutResourceId,
			List<Orchid> orchidsList) {
		super(context, layoutResourceId, orchidsList);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.orchidsList = orchidsList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		// Holder pattern to cache orchid image and description properties for
		// performance
		OrchidHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new OrchidHolder();
			holder.imgIcon = (ImageView) row.findViewById(R.id.orchid_img);
			holder.txtTitle = (TextView) row.findViewById(R.id.orchid_name);

			row.setTag(holder);
		} else {
			holder = (OrchidHolder) row.getTag();
		}

		Orchid orchid = orchidsList.get(position);
		holder.txtTitle.setText(orchid.getOrchidName());
		// Implement loading the image from gallery
		holder.imgIcon.setImageResource(orchid.getPicture_icon());

		return row;
	}

	// Refresh the adapter list for add/delete/edit operations on MyOrchids list
	public void refresh(List<Orchid> orchidsList) {
		this.orchidsList = orchidsList;
		notifyDataSetChanged();
	}

	private static class OrchidHolder {
		ImageView imgIcon;
		TextView txtTitle;
	}
}
