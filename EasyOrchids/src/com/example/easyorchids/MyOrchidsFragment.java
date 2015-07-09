package com.example.easyorchids;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/*
 * This class represents the my orchids list view with all my orchids.
 * It populates the orchids from the DB and displays and orchid name and picture. Added a floating add button to add orchids.
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

		// get the view returned in onCreate view and get the floating action
		// button.

		DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

		getActivity().deleteDatabase(
				MyOrchidsContract.MyOrchidsTable.TABLE_NAME);

		// Create the DB
		try {
			dbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		// Open the DB for reading
		try {
			dbHelper.openDB();

		} catch (SQLException sqle) {
			throw sqle;
		}

		// Display a list view of the orchids in the DB
		Cursor orchidsCursor = dbHelper.getAllOrchids();
		List<Orchid> allOrchids = getOrchids(orchidsCursor);

		// remove this when you add functionality to select pictures
		for (Orchid orch : allOrchids) {
			orch.setPicture_icon(R.drawable.orch_1);
			;
		}

		final MyOrchidsAdapter adapter = new MyOrchidsAdapter(getActivity(),
				R.layout.myorchid_list_item, allOrchids);

		setListAdapter(adapter);
		dbHelper.close();
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO implement some logic
	}

	private void displayOrchid(Cursor cursor) {

	}

	// Convert from cursor object to Orchid Object and populate orchid fields
	// Return the resulting orchid object.
	// Test is here sql injection is possible. What checks should I do.
	private Orchid cursorToOrchid(Cursor c) {
		Orchid orchid = new Orchid();
		orchid.setId(c.getLong(0));
		orchid.setOrchidName(c.getString(1));
		orchid.setLastWatering(c.getString(2));
		orchid.setLastFertilizing(c.getString(3));
		// if (checkInputValidity(c)) {
		// orchid.setIsOutside(c.getString(4));
		// } else
		// throw new Error("Wrong input in orchid outside state");

		orchid.setIsOutside(c.getString(4));
		orchid.setPicturePath(c.getString(5));
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

	/**
	 * 
	 * @return true if input is valid, false otherwise for the outside state.
	 */
	private boolean checkInputValidity(Cursor c) {
		boolean result = false;
		if (c.getString(4) != null) {
			if (c.getString(4).toLowerCase().equals(YesNoEnum.YES.toString())
					|| c.getString(4).toLowerCase()
							.equals(YesNoEnum.NO.toString())) {
				return true;
			}
		}

		return result;
	}

}
