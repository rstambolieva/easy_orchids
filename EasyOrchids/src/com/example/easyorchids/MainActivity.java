package com.example.easyorchids;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * 
 * Creates the Navigation drawer and its callbacks
 *
 */
public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	// Navigation Drawer Actionbar toogle button
	@SuppressWarnings("deprecation")
	private ActionBarDrawerToggle mDrawerToggle;

	// Navigation drawer title
	private CharSequence mDrawerTitle;

	// Used to store app title
	private CharSequence mTitle;

	// Navigation Drawer Slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavigationDrawerItem> navDrawerItems;
	private NavigationDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// = getResources().getStringArray(R.array.navigation_drawer_menu);

		// Navigation drawer layout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the Navigation Drawer title
		mTitle = mDrawerTitle = getTitle();

		// Navigation Drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		// Create an array for the navigation menu items
		navDrawerItems = new ArrayList<NavigationDrawerItem>();

		// Load the Navigation Menu items from the string array
		navMenuTitles = getResources().getStringArray(
				R.array.navigation_drawer_menu);

		// Populate the Navigation Menu items. TODO: get the count of orchid
		// dynamically from the DB
		for (int i = 0; i < 5; i++) {
			if (i == 1) {
				navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[0],
						navMenuIcons.getResourceId(i, -1), true, "2"));
			} else {
				navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[0],
						navMenuIcons.getResourceId(i, -1)));
			}

		}

		// Recycle the icons
		navMenuIcons.recycle();

		// setting the nav drawer list adapter
		adapter = new NavigationDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// Enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			selectItem(0);
		}

		// // Set the adapter for the list view
		// mDrawerList.setAdapter(new ArrayAdapter<String>(this,
		// R.layout.drawer_list_item, navigationDrawerList));
		// // Set the list's click listener
		// mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/***
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * 
	 * Handle navigation drawer behavior when drawer list items are clicked.
	 * Content view is changed on each click with the respective view.
	 *
	 */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			selectItem(position);
		}

	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		// Create a new fragment and specify the frame to be displayed
		Fragment fragment;
		FragmentManager fragmentManager = getFragmentManager();

		switch (position) {
		case 0:
			// Show the selected menu drawer item
			mDrawerList.setItemChecked(position, true);

			// Create a fragment with the selected menu item and set it in the
			// main fragment
			fragment = new Schedule();
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
			fragment = new OrchidCare();
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
			fragment = new Settings();
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
			fragment = new HelpAndFeedback();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();

			// Close the drawer
			mDrawerLayout.closeDrawer(mDrawerList);
			break;
		}

	}

}
