package com.example.easyorchids;

import java.util.ArrayList;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * 
 * Creates the AppBar and Navigation drawer and its callbacks
 *
 */
public class MainActivity extends AppCompatActivity {
	private Toolbar toolbar;

	// Recycler view and drawer toggle
	RecyclerView mRecyclerView;
	RecyclerView.Adapter mAdapter;
	RecyclerView.LayoutManager mLayoutManager;
	DrawerLayout Drawer;

	ActionBarDrawerToggle mDrawerToggle;

	// Navigation drawer
	// private ListView mDrawerList;

	// Navigation drawer title
	private CharSequence mDrawerTitle;

	// Used to store app title
	private CharSequence mTitle;
	
	// Header Title
	private String  headerText;

	// Navigation Drawer Slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavigationDrawerItem> navDrawerItems;
	private NavigationDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set the Navigation Drawer title
		mTitle = mDrawerTitle = getTitle();

		// Attaching the layout to the toolbar
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		
		// Setting toolbar as the ActionBar with
		// setSupportActionBar() call object
		setSupportActionBar(toolbar); 

		mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

		mRecyclerView.setHasFixedSize(true); // Letting the system know that the
												// list objects are of fixed
												// size

		// Navigation Drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		// Create an array for the navigation menu items
		navDrawerItems = new ArrayList<NavigationDrawerItem>();

		// Load the Navigation Menu items from the string array
		navMenuTitles = getResources().getStringArray(
				R.array.navigation_drawer_menu);
	
		// Load the 

		// Populate the Navigation Menu items. TODO: get the count of orchid
		// dynamically from the DB
		for (int i = 0; i < 5; i++) {
			if (i == 1) {
				navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[i],
						navMenuIcons.getResourceId(i, -1), true, "2", headerText));
			} else {
				navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[i],
						navMenuIcons.getResourceId(i, -1), headerText));
			}

		}

		// Recycle the icons
		navMenuIcons.recycle();

		// setting the nav drawer list adapter, need to add header
		mAdapter = new NavigationDrawerListAdapter(
				navDrawerItems);

		mRecyclerView.setAdapter(mAdapter);
		mLayoutManager = new LinearLayoutManager(this); 

		mRecyclerView.setLayoutManager(mLayoutManager);

		Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout); 
		mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar,
				R.string.drawer_open, R.string.drawer_close) {

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				// code here will execute once the drawer is opened( As I dont
				// want anything happened whe drawer is
				// open I am not going to put anything here)
//				getActionBar().setTitle(mDrawerTitle);
//				// calling onPrepareOptionsMenu() to hide action bar icons
//				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				// Code here will execute once drawer is closed
//				getActionBar().setTitle(mTitle);
//				// calling onPrepareOptionsMenu() to show action bar icons
//				invalidateOptionsMenu();
			}

		}; // Drawer Toggle Object Made
		Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the
													// Drawer toggle
		mDrawerToggle.syncState();

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

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}