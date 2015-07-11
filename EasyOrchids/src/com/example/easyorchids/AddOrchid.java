package com.example.easyorchids;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

// AddOrchid class handles the retrieval of data for adding a new orchid and persisting it to the DB
public class AddOrchid extends Activity {

	// boolean to distinguish watering from fertilizing
	private boolean isWateringDate = true;
	private TextView orchidName;
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

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.add_orchid);

		orchidName = (TextView) findViewById(R.id.add_orchid_name);
		orchidTypeChoice = (Spinner) findViewById(R.id.spinner_orchid_types);
		lastWateredBtn = (Button) findViewById(R.id.last_watering_date_btn);
		lastFertilizedBtn = (Button) findViewById(R.id.last_fertilizing_date_btn);
		isOutsideChk = (CheckBox) findViewById(R.id.isoutside_chk);
		seasonSpinner = (Spinner) findViewById(R.id.season_spinner);

		// Pass the application context as we need it across the whole app
		DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

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
			}
		});

		// If the the orchid is outside, change state
		if (isOutsideChk.isChecked()) {
			outsideState = YesNoEnum.YES.toString();
		}
		// Insert the user input for newly added orchid in the DB
		dbHelper.insertOrchid(db, orchidName.toString(),
				(String) orchidTypeChoice.getSelectedItem(), wateredDate,
				fertilizedDate, outsideState, "fakepath");
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
