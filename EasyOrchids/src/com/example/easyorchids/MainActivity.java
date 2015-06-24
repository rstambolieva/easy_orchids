package com.example.easyorchids;

import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 
 * Creates the Navigation drawer and its callbacks
 *
 */
public class MainActivity extends Activity {

	private String[] navigationDrawerList;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Navigation drawer list items
		navigationDrawerList = getResources().getStringArray(R.array.main_menu);

		// Navigation drawer layout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, navigationDrawerList));
		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 
	 * Handle navigation drawer behavior when drawer list items are clicked.
	 * Content view is changed on each click with the respective view.
	 *
	 */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		// Create a new fragment and specify the frame to be displayed
		ListFragment fragment;
		FragmentManager fragmentManager = getFragmentManager();

		switch (position) {
		case 0:
			// Show the selected menu drawer item
			mDrawerList.setItemChecked(position, true);

			// Create a fragment with the selected menu item and set it in the
			// main fragment
			fragment = new MyOrchids();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();

			// Close the drawer
			mDrawerLayout.closeDrawer(mDrawerList);
			break;
		case 1:
			// Show the selected menu drawer item
			mDrawerList.setItemChecked(position, true);

			// Create a fragment with the selected menu item and set it in the
			// main fragment
			fragment = new MyOrchids();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();

			// Close the drawer
			mDrawerLayout.closeDrawer(mDrawerList);
			break;
		case 2:
			// Show the selected menu drawer item
			mDrawerList.setItemChecked(position, true);

			// Create a fragment with the selected menu item and set it in the
			// main fragment
			fragment = new MyOrchids();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();

			// Close the drawer
			mDrawerLayout.closeDrawer(mDrawerList);
			break;
		case 3:
			// Show the selected menu drawer item
			mDrawerList.setItemChecked(position, true);

			// Create a fragment with the selected menu item and set it in the
			// main fragment
			fragment = new MyOrchids();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();

			// Close the drawer
			mDrawerLayout.closeDrawer(mDrawerList);
			break;
		case 4:
			// Show the selected menu drawer item
			mDrawerList.setItemChecked(position, true);

			// Create a fragment with the selected menu item and set it in the
			// main fragment
			fragment = new MyOrchids();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();

			// Close the drawer
			mDrawerLayout.closeDrawer(mDrawerList);
			break;
		}

		// Bundle args = new Bundle();
		// args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
		// fragment.setArguments(args);
		//
		// // Insert the fragment by replacing any existing fragment

		//
		// // Highlight the selected item, update the title, and close the
		// drawer
		// mDrawerList.setItemChecked(position, true);
		// setTitle(mPlanetTitles[position]);
		// mDrawerLayout.closeDrawer(mDrawerList);
	}

	// @Override
	// public void setTitle(CharSequence title) {
	// // mTitle = title;
	// // getActionBar().setTitle(mTitle);
	// }

	/**
	 * Fragment that appears in the "content_frame", shows a planet
	 */
	// public static class OrchidFragment extends Fragment {
	// public static final String ARG_PLANET_NUMBER = "planet_number";
	//
	// public OrchidFragment() {
	// // Empty constructor required for fragment subclasses
	// }
	//
	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View rootView = inflater.inflate(R.layout.fragment_orchid, container,
	// false);
	// int i = getArguments().getInt(ARG_PLANET_NUMBER);
	// String orchid = getResources().getStringArray(R.array.main_menu)[i];
	//
	// int imageId =
	// getResources().getIdentifier(orchid.toLowerCase(Locale.getDefault()),
	// "drawable", getActivity().getPackageName());
	// ((ImageView)
	// rootView.findViewById(R.id.image)).setImageResource(imageId);
	// getActivity().setTitle(orchid);
	// return rootView;
	// }
	// }

}
