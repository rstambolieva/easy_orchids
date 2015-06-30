package com.example.easyorchids;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
	// String counter for the MyOrchids list
	private String itemsCounter;
	// Array of the drawer items to get info from
	private List<NavigationDrawerItem> navDrawerItems;
	// On item selected click listener
	private NavigationDrawerCallbacks mNavigationDrawerCallbacks;

	private int mTouchedPosition = -1;
	private int mSelectedPosition;

	// Creating a ViewHolder which extends the RecyclerView View Holder
	// ViewHolder are used to to store the inflated views in order to recycle
	// them
	public static class ViewHolder extends RecyclerView.ViewHolder {
		// 1 - item; 0- header
		int holderId;

		TextView drawerItemTitle;
		ImageView drawerImage;
		TextView drawerHeaderText;
		TextView myOrchidsCounterText;

		// Creating ViewHolder Constructor with View and viewType As a parameter
		public ViewHolder(View itemView, int viewType) {
			super(itemView);

			if (viewType == TYPE_ITEM) {
				// Populate the layouts for the drawer item fields
				drawerItemTitle = (TextView) itemView.findViewById(R.id.title);
				drawerImage = (ImageView) itemView.findViewById(R.id.icon);
				myOrchidsCounterText = (TextView) itemView
						.findViewById(R.id.counter);
				holderId = 1;
			} else {
				holderId = 0;
			}
		}
	}

	// Create custom recycler adapter for the navigation drawer constructor
	NavigationDrawerListAdapter(List<NavigationDrawerItem> navDrawerItems) {
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

		Context context = parent.getContext();

		if (viewType == TYPE_ITEM) {
			// Inflating the layout
			View v = LayoutInflater.from(context).inflate(
					R.layout.drawer_list_item, parent, false);
			// Creating ViewHolder and passing the object of type view
			final ViewHolder vhItem = new ViewHolder(v, viewType);

			// Set touch listener to the navigation drawer item
			vhItem.itemView.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {

					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						touchPosition(vhItem.getAdapterPosition());
						return false;
					case MotionEvent.ACTION_CANCEL:
						touchPosition(-1);
						return false;
					case MotionEvent.ACTION_MOVE:
						return false;
					case MotionEvent.ACTION_UP:
						touchPosition(-1);
						return false;
					}
					return true;
				}
			});

			// Set on click listener to the navigation drawer item
			vhItem.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mNavigationDrawerCallbacks != null)
						mNavigationDrawerCallbacks
								.onNavigationDrawerItemSelected(vhItem
										.getAdapterPosition());
				}
			});

			// Returning the created object
			return vhItem;
			// inflate your layout and pass it to view holder
		} else if (viewType == TYPE_HEADER) {
			// Inflating the layout
			View v = LayoutInflater.from(context).inflate(R.layout.header,
					parent, false);
			ViewHolder vhHeader = new ViewHolder(v, viewType);
			return vhHeader; // returning the object created

		}
		return null;

	}

	// Populates the item fields. Called when the item in a row is displayed,
	// here the int position
	// Tells us item at which position is being constructed to be displayed and
	// the holder id of the holder object tell us
	// which view type is being created 1 for item row
	@Override
	public void onBindViewHolder(NavigationDrawerListAdapter.ViewHolder holder,
			int position) {

		if (holder.holderId == 1) {
			holder.drawerItemTitle.setText(navDrawerItems.get(position - 1)
					.getTitle());
			holder.drawerImage.setImageResource(navDrawerItems
					.get(position - 1).getIcon());

			// When there is counter display it, otherwise not
			if (navDrawerItems.get(position - 1).getCounterVisibility()) {
				holder.myOrchidsCounterText.setText(navDrawerItems
						.get(position - 1).getCount());
			} else {
				// hide the counter view
				holder.myOrchidsCounterText.setVisibility(View.GONE);
			}
		} else {
			// nothing to set for the header
		}

	}

	// This method returns the number of items present in the list
	@Override
	public int getItemCount() {
		return navDrawerItems.size() + 1;
	}

	// With the following method we check what type of view is being passed
	@Override
	public int getItemViewType(int position) {
		if (isPositionHeader(position))
			return TYPE_HEADER;

		return TYPE_ITEM;
	}

	private boolean isPositionHeader(int position) {
		return position == 0;
	}

	private void touchPosition(int position) {
		int lastPosition = mTouchedPosition;
		mTouchedPosition = position;
		if (lastPosition >= 0)
			notifyItemChanged(lastPosition);
		if (position >= 0)
			notifyItemChanged(position);
	}

	public void selectPosition(int position) {
		if (position != 0) {
			int lastPosition = mSelectedPosition;
			mSelectedPosition = position;
			notifyItemChanged(lastPosition);
			notifyItemChanged(position);
		}
	}

	public NavigationDrawerCallbacks getNavigationDrawerCallbacks() {
		return mNavigationDrawerCallbacks;
	}

	public void setNavigationDrawerCallbacks(
			NavigationDrawerCallbacks navigationDrawerCallbacks) {
		mNavigationDrawerCallbacks = navigationDrawerCallbacks;
	}

}
