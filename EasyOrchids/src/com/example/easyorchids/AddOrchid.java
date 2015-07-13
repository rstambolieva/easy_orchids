package com.example.easyorchids;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

// AddOrchid class handles the retrieval of data for adding a new orchid and persisting it to the DB
public class AddOrchid extends AppCompatActivity {

	// AppBar
	private Toolbar mToolbar;

	// boolean to distinguish watering from fertilizing
	private boolean isWateringDate = true;
	private EditText orchidName;
	private Spinner orchidTypeChoice;
	private Button lastWateredBtn;
	private Button lastFertilizedBtn;
	private CheckBox isOutsideChk;
	private Spinner seasonSpinner;
	private SQLiteDatabase db;
	private int lastWateredYear;
	private int lastWateredMonth;
	private int lastWateredDay;
	private int lastFertilizedYear;
	private int lastFertilizedMonth;
	private int lastFertilizedDay;
	private String outsideState = YesNoEnum.NO.toString();
	private String wateredDate;
	private String fertilizedDate;
	private static final String APPBAR_TITLE = "Add Orchid";
	private DatabaseHelper dbHelper;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.add_orchid);

		// // // Add the appbar
		// mToolbar = (Toolbar) findViewById(R.id.tool_bar);
		// // setSupportActionBar(mToolbar);
		// mToolbar.setTitle(APPBAR_TITLE);

		orchidName = (EditText) findViewById(R.id.add_orchid_name);
		orchidTypeChoice = (Spinner) findViewById(R.id.spinner_orchid_types);
		lastWateredBtn = (Button) findViewById(R.id.last_watering_date_btn);
		lastFertilizedBtn = (Button) findViewById(R.id.last_fertilizing_date_btn);
		isOutsideChk = (CheckBox) findViewById(R.id.isoutside_chk);
		seasonSpinner = (Spinner) findViewById(R.id.season_spinner);

		// Pass the application context as we need it across the whole app
		dbHelper = new DatabaseHelper(getApplicationContext());

		// Open the database for writing
		try {
			db = dbHelper.openDB();

		} catch (SQLException sqle) {
			throw sqle;
		}

		// Implement last fertilizing button on click listener
		lastWateredBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				// Initialize the Date picker with current date when invoked
				displayDatePicker();
			}
		});

		// Implement last watering button on click listener
		lastFertilizedBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				// Initialize the Date picker with current date when invoked
				displayDatePicker();
			}
		});

		// If the the orchid is outside, change state
		if (isOutsideChk.isChecked()) {
			outsideState = YesNoEnum.YES.toString();
		}
	}

	// Create Options Menu with Cancel and Save actions
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_orchids_actions, menu);
		menu.add("Save");
		menu.add("Delete");
		// return super.onCreateOptionsMenu(menu);
		return true;
		// to open the menu popup
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Insert the user input for newly added orchid in the DB
		// dbHelper.insertOrchid(db, orchidName.toString(),
		// (String) orchidTypeChoice.getSelectedItem(), wateredDate,
		// fertilizedDate, outsideState, "fakepath");
		//
		// switch (item.getItemId()) {
		//
		// case 1:
		// // write your code here
		//
		// Toast msg = Toast.makeText(MainHomeScreen.this, "Menu 1",
		// Toast.LENGTH_LONG);
		// msg.show();
		// return true;
		//
		// case 2:
		// // code here
		// return true;
		//
		// case 3:
		// // code here
		// return true;
		//
		// case 4:
		// // code here
		// return true;
		//
		// case 5:
		// // code here
		//
		// return true;
		//
		// case 6:
		// // code here
		// return true;
		//
		// default:
		// break;
		//
		// }
		return super.onOptionsItemSelected(item);
	}

	// Class to implement the behaior when date is set
	class mDateSetListener implements DatePickerDialog.OnDateSetListener {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			// getCalender();
			// Set the last watered date
			if (isWateringDate) {
				lastWateredYear = year;
				lastWateredMonth = monthOfYear;
				lastWateredDay = dayOfMonth;
				// Month starts from 0, so + 1
				wateredDate = new StringBuilder().append(lastWateredDay)
						.append("/").append(lastWateredMonth + 1).append("/")
						.append(lastWateredYear).toString();
			} else {

			}
			lastFertilizedYear = year;
			lastFertilizedMonth = monthOfYear;
			lastFertilizedDay = dayOfMonth;
			// Month starts from 0, so + 1
			fertilizedDate = new StringBuilder().append(lastFertilizedDay)
					.append("/").append(lastFertilizedMonth + 1).append("/")
					.append(lastFertilizedYear).toString();
			// view.setText(new StringBuilder()
			// // Month is 0 based so add 1
			// .append(mMonth + 1).append("/").append(mDay).append("/")
			// .append(mYear).append(" "));
			// System.out.println(view.getText().toString());

		}
	}

	// Displays a date picker and sets selected the current date
	private void displayDatePicker() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		DatePickerDialog dialog = new DatePickerDialog(getApplicationContext(),
				new mDateSetListener(), year, month, day);
		// Display the datepicker
		dialog.show();
	}

}
