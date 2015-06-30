package com.example.easyorchids;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;

/**
 * 
 * Creates the AppBar and Navigation drawer and its callbacks
 *
 */
public class MainActivity extends AppCompatActivity implements
		NavigationDrawerCallbacks {
	// AppBar
	private Toolbar mToolbar;
	// Navigation drawer fragment
	private NavigationDrawerFragment mNavigationDrawerFragment;

	// Drawer layout
	private DrawerLayout mDrawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mToolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		// Drawer fragment id in activity_main.xml
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.left_drawer);
		// Drawer Layout id in activity_main.xml
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
		
		mNavigationDrawerFragment.setup(R.id.left_drawer, mDrawerLayout,
				mToolbar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// Swap the fragments in the main content view
		selectItem(position);
	}

	@Override
	public void onBackPressed() {
		if (mNavigationDrawerFragment.isDrawerOpen())
			mNavigationDrawerFragment.closeDrawer();
		else
			super.onBackPressed();
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		// Create a new fragment and specify the frame to be displayed
		Fragment fragment = null;
		FragmentManager fragmentManager = getSupportFragmentManager();

		switch (position) {
		case 0:
			fragment = new ScheduleFragment();
			break;
		case 1:
			fragment = new MyOrchidsFragment();
			break;
		case 2:
			fragment = new OrchidCareFragment();
			break;
		case 3:
			fragment = new SettingsFragment();
			break;
		case 4:
			fragment = new HelpAndFeedbackFragment();
			break;
		}
		
		// Swap the fragment
		if (fragment != null) {
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();
		}
	}

}