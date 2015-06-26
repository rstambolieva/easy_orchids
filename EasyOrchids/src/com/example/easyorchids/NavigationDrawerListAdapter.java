package com.example.easyorchids;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * Custom adapter to populate the navigation drawer with image, text and counter
 * fields per row
 *
 */
public class NavigationDrawerListAdapter extends
		RecyclerView.Adapter<NavigationDrawerListAdapter.ViewHolder> {

	// TYPE_HEADER and TYPE_ITEM define if we work with drawer header or with
	// drawer items
	private static final int TYPE_HEADER = 0;
	private static final int TYPE_ITEM = 1;

	// String Array to store the passed titles values from MainActivity.java
	private String mNavTitles[];
	// Int Array to store the passed icons resource value
	// from MainActivity.java
	private int mIcons[];
	// String Resource for header View Name
	private String headerName;
	// String counter for the MyOrchids list
	private String itemsCounter;
	// Array of the drawer items to get info from
	private ArrayList<NavigationDrawerItem> navDrawerItems;

	// Creating a ViewHolder which extends the RecyclerView View Holder
	// ViewHolder are used to to store the inflated views in order to recycle
	// them
	public static class ViewHolder extends RecyclerView.ViewHolder {
		int Holderid;

		TextView drawerItemTitle;
		ImageView drawerImage;
		TextView drawerHeaderText;
		TextView myOrchidsCounterText;

		// Creating ViewHolder Constructor with View and viewType As a parameter
		public ViewHolder(View itemView, int ViewType) {
			super(itemView);

			// Here we set the appropriate view in accordance with the the view
			// type as passed when the holder object is created
			if (ViewType == TYPE_ITEM) {
				// Populate the layouts for the drawer item fields
				drawerItemTitle = (TextView) itemView.findViewById(R.id.title);
				drawerImage = (ImageView) itemView.findViewById(R.id.icon);
				myOrchidsCounterText = (TextView) itemView
						.findViewById(R.id.counter);
				// setting holder id as 1 as the object being populated are of
				// type drawer item row
				Holderid = 1;
			} else {
				drawerHeaderText = (TextView) itemView
						.findViewById(R.id.drawerHeaderTxt); // Creating Text
																// View object
																// from
																// header.xml
																// for name
				Holderid = 0; // Setting holder id = 0 as the object being
								// populated are of type header view
			}
		}
	}

	// Create custom recycler adapter for the navigation drawer constructor
	NavigationDrawerListAdapter(ArrayList<NavigationDrawerItem> navDrawerItems) {
		this.navDrawerItems = navDrawerItems;
	}

	// Below first we override the method onCreateViewHolder which is called
	// when the ViewHolder is
	// Created, In this method we inflate the drawer_list_item.xml layout if the
	// viewType is Type_ITEM or else we inflate header.xml
	// if the viewType is TYPE_HEADER
	// and pass it to the view holder

	@Override
	public NavigationDrawerListAdapter.ViewHolder onCreateViewHolder(
			ViewGroup parent, int viewType) {

		if (viewType == TYPE_ITEM) {
			// Inflating the layout
			View v = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.drawer_list_item, parent, false);
			// Creating ViewHolder and passing the object of type view
			ViewHolder vhItem = new ViewHolder(v, viewType);

			// Returning the created object
			return vhItem;
			// inflate your layout and pass it to view holder
		} else if (viewType == TYPE_HEADER) {

			View v = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.header, parent, false); // Inflating the layout

			ViewHolder vhHeader = new ViewHolder(v, viewType);
			return vhHeader; // returning the object created

		}
		return null;

	}

	// Called when the item in a row is displayed, here the int position
	// Tells us item at which position is being constructed to be displayed and
	// the holder id of the holder object tell us
	// which view type is being created 1 for item row
	@Override
	public void onBindViewHolder(NavigationDrawerListAdapter.ViewHolder holder,
			int position) {
		if (holder.Holderid == 1) { // as the list view is going to be called
									// after the header view so we decrement the
									// position by 1 and pass it to the holder
									// while setting the text and image
			holder.drawerItemTitle.setText(navDrawerItems.get(position)
					.getTitle());
			holder.drawerImage.setImageResource(navDrawerItems
					.get(position).getIcon());

			// When there is counter display it, otherwise not
			if (navDrawerItems.get(position).getCounterVisibility()) {
				holder.myOrchidsCounterText.setText(navDrawerItems.get(
						position).getCount());
			} else {
				// hide the counter view
				holder.myOrchidsCounterText.setVisibility(View.GONE);
			}

		} else {

			holder.drawerHeaderText.setText(navDrawerItems.get(position)
					.getHeaderText());
		}
	}

	// This method returns the number of items present in the list
	@Override
	public int getItemCount() {
		return navDrawerItems.size(); // the number of items in the list
											// will be
											// +1 the titles including the
											// header
											// view.
	}

	// Witht the following method we check what type of view is being passed
	@Override
	public int getItemViewType(int position) {
		if (isPositionHeader(position))
			return TYPE_HEADER;

		return TYPE_ITEM;
	}

	private boolean isPositionHeader(int position) {
		return position == 0;
	}

	// @Override
	// public long getItemId(int position) {
	// return position;
	// }

}
