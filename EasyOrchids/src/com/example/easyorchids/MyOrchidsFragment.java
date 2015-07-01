package com.example.easyorchids;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/*
 * This class represents the my orchids list view with all my orchids.
 * It populates the orchids from the DB
 */
public class MyOrchidsFragment extends ListFragment {
	public static final String ARG_MENU_ITEM_NUMBER = "menu_item_number";
	private ListView myOrchidsList;
	private String[] orchidsList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);

		View rootView = inflater.inflate(R.layout.myorchids_list_view,
				container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// orchidsList = getResources().getStringArray(R.array.my_orchids);
		//
		// ArrayAdapter<String> adapter = new
		// ArrayAdapter<String>(getActivity(),
		// R.layout.myorchid_list_item, orchidsList);

		// Getting the Activity as the Activity is a Context to provide to the
		// dbHelper for context
		DBAdapter dbAdapter = new DBAdapter(getActivity());

		// Open the DB for writing
		// dbAdapter.open();
		// dbAdapter.dropOrchidsTable();
		// getActivity().deleteDatabase(
		// MyOrchidsContract.MyOrchidsTable.TABLE_NAME);

		// // Remove elements insertion from
		// here//////////////////////////////////
		// long id;
		//
		// Log.d(Constants.TAG, "Inserting orchids");
		// id = dbAdapter.insertOrchid("Pah 1", OrchidTypes.PHAELANOPSIS,
		// "1 Sep",
		// "1 Sep");
		// id = dbAdapter.insertOrchid("Pah 2", OrchidTypes.PHAELANOPSIS,
		// "1 Sep",
		// "1 Sep");

		// Remove elements insertion from here/////////////////////////////////

		// Display a list view of the orchids in the DB
		Cursor orchidsCursor = dbAdapter.getAllOrchids();
		List<Orchid> allOrchids = getOrchids(orchidsCursor);

		// use the SimpleCursorAdapter to show the
		// elements in a ListView, provide an id of the textview to use more
		// complex layout.Use getActivity() to get the current context
		// TODO: Apply the ViewHolder Pattern for better performance of the list

		ArrayAdapter<Orchid> adapter = new ArrayAdapter<Orchid>(getActivity(),
				R.layout.myorchid_list_item, R.id.orchid_name, allOrchids) {

			// Override the getView method to make the transition from Obj to
			// View item
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// Get the data item for this position
				Orchid orchid = getItem(position);
				// Check if an existing view is being reused, otherwise inflate
				// the view
				if (convertView == null) {
					convertView = LayoutInflater.from(getContext()).inflate(
							R.layout.myorchid_list_item, parent, false);
				}
				// Lookup view for data population
				TextView orchidName = (TextView) convertView
						.findViewById(R.id.orchid_name);
				ImageView imageView = (ImageView) convertView
						.findViewById(R.id.orchid_img);
				// Populate the data into the template view using the data
				// object
				orchidName.setText(orchid.getOrchidName());

				// Return the completed view to render on screen
				return convertView;
			}
		};

		setListAdapter(adapter);
		dbAdapter.close();
		// setListAdapter();
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO implement some logic
	}

	// public MyOrchids() {
	// // Empty constructor required for fragment subclasses
	// }

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View rootView = inflater.inflate(R.layout.fragment_orchid, container,
	// false);
	// int i = getArguments().getInt(ARG_MENU_ITEM_NUMBER);
	// String orchid = getResources().getStringArray(R.array.main_menu)[i];

	// int imageId = getResources().getIdentifier(
	// orchid.toLowerCase(Locale.getDefault()), "drawable",
	// getActivity().getPackageName());
	// ((ImageView) rootView.findViewById(R.id.image))
	// .setImageResource(imageId);
	// getActivity().setTitle(orchid);
	// return rootView;
	// }

	private void displayOrchid(Cursor cursor) {

	}

	// Convert from cursor object to Orchid Object and populate orchid fields
	// Return the resulting orchid object.
	private Orchid cursorToOrchid(Cursor c) {
		Orchid orchid = new Orchid();
		orchid.setId(c.getLong(0));
		orchid.setOrchidName(c.getString(1));
		orchid.setLastWatering(c.getString(2));
		orchid.setLastFertilizing(c.getString(3));
		orchid.setIsOutside(c.getString(4));
		orchid.setDayTemp(c.getString(5));
		orchid.setNightTemp(c.getString(6));
		// release cursor resources, not sure if this should go here
		// c.close();
		return orchid;
	}

	// Returns all orchids from the query as an orchid list
	private List<Orchid> getOrchids(Cursor c) {
		List<Orchid> orchids = new ArrayList<Orchid>();
		c.moveToFirst();
		while (!c.isAfterLast()) {
			Orchid orchid = cursorToOrchid(c);
			orchids.add(orchid);
			c.moveToNext();
		}
		// release cursor resources, not sure if this should go here
		// c.close();
		return orchids;
	}

}
