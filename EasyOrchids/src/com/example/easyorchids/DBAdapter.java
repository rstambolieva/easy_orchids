package com.example.easyorchids;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.easyorchids.MyOrchidsContract.MyOrchidsTable;

/**
 * 
 * DBAdapter class provides the CRUD operations on the DB. An extension to
 * SQLiteOpenHelper is created for creating, deleting and upgrading the DB
 *
 */
public class DBAdapter {
	private static final String TAG_DB = "DBAdapter";
	private static final String DATABASE_NAME = "OrchidsDB";
	private static final int DATABASE_VERSION = 1;
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ORCHIDS_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ MyOrchidsTable.TABLE_NAME
			+ " ("
			+ MyOrchidsTable.COLUMN_NAME_ORCHID_ID
			+ " INTEGER PRIMARY KEY,"
			+ MyOrchidsTable.COLUMN_NAME_ORCHID_NAME
			+ TEXT_TYPE
			+ COMMA_SEP
			+ MyOrchidsTable.COLUMN_NAME_ORCHID_TYPE
			+ TEXT_TYPE
			+ COMMA_SEP
			+ MyOrchidsTable.COLUMN_NAME_ORCHID_LAST_WATERING_DATE
			+ TEXT_TYPE
			+ COMMA_SEP
			+ MyOrchidsTable.COLUMN_NAME_ORCHID_LAST_FERTILIZING_DATE
			+ TEXT_TYPE
			+ COMMA_SEP
			+ MyOrchidsTable.COLUMN_NAME_ORCHID_OUTSIDE_STATE
			+ TEXT_TYPE
			+ COMMA_SEP
			+ MyOrchidsTable.COLUMN_NAME_DAY_TEMP
			+ TEXT_TYPE
			+ COMMA_SEP
			+ MyOrchidsTable.COLUMN_NAME_NIGHT_TEMP
			+ TEXT_TYPE
			+ ")";

	private static final String SQL_DELETE_ORCHIDS_TABLE = "DROP TABLE IF EXISTS "
			+ MyOrchidsTable.TABLE_NAME;
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	private String[] allColumns = { MyOrchidsTable.COLUMN_NAME_ORCHID_ID,
			MyOrchidsTable.COLUMN_NAME_ORCHID_NAME,
			MyOrchidsTable.COLUMN_NAME_ORCHID_TYPE,
			MyOrchidsTable.COLUMN_NAME_ORCHID_LAST_WATERING_DATE,
			MyOrchidsTable.COLUMN_NAME_ORCHID_LAST_FERTILIZING_DATE,
			MyOrchidsTable.COLUMN_NAME_ORCHID_OUTSIDE_STATE,
			MyOrchidsTable.COLUMN_NAME_DAY_TEMP,
			MyOrchidsTable.COLUMN_NAME_NIGHT_TEMP };

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	/**
	 * 
	 * Provides access to the DB and specifies creating and upgrading the DB
	 *
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d(Constants.TAG, "Creating DB");
			db.execSQL(SQL_CREATE_ORCHIDS_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG_DB, "Upgrading database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data");
			db.execSQL(SQL_DELETE_ORCHIDS_TABLE);
			onCreate(db);
		}
	}

	// Drop the DB
	public void dropOrchidsTable() {
		Log.d(Constants.TAG, "Dropping the orchids table");
		db.execSQL(SQL_DELETE_ORCHIDS_TABLE);
	}

	// Оpens the database
	public DBAdapter open() throws SQLException {
		Log.d(Constants.TAG, "Opening DB");
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// Closes the database
	public void close() {
		Log.d(Constants.TAG, "Closing DB");
		DBHelper.close();
	}

	// Inserts an orchid into the database
	public long insertOrchid(String orchidName, OrchidTypes orchidType,
			String wateredDate, String fertilizedDate, String outsideState,
			String dayTemp, String nightTemp) {
		Log.d(Constants.TAG, "Inserting an orchid");

		ContentValues initialValues = new ContentValues();
		initialValues.put(MyOrchidsTable.COLUMN_NAME_ORCHID_NAME, orchidName);
		initialValues.put(MyOrchidsTable.COLUMN_NAME_ORCHID_TYPE,
				orchidType.toString());
		initialValues.put(MyOrchidsTable.COLUMN_NAME_ORCHID_LAST_WATERING_DATE,
				wateredDate);
		initialValues.put(
				MyOrchidsTable.COLUMN_NAME_ORCHID_LAST_FERTILIZING_DATE,
				fertilizedDate);
		initialValues.put(MyOrchidsTable.COLUMN_NAME_ORCHID_OUTSIDE_STATE,
				outsideState);
		initialValues.put(MyOrchidsTable.COLUMN_NAME_DAY_TEMP, dayTemp);
		initialValues.put(MyOrchidsTable.COLUMN_NAME_NIGHT_TEMP, nightTemp);
		return db.insert(MyOrchidsTable.TABLE_NAME, null, initialValues);
	}

	// Deletes an orchid
	public boolean deleteOrchid(long orchidId) {
		Log.d(Constants.TAG, "Deleting an orchid");
		return db.delete(MyOrchidsTable.TABLE_NAME,
				MyOrchidsTable.COLUMN_NAME_ORCHID_ID + "=" + orchidId, null) > 0;
	}

	// Retrieves the names of all orchids and returns a pointer to the first
	// entry in the resultset
	public Cursor getAllOrchids() {
		Log.d(Constants.TAG, "Getting Orchid Names");
		return db.query(MyOrchidsTable.TABLE_NAME, allColumns, null, null,
				null, null, null);
	}

	// Retrieves a particular orchid
	public Cursor getOrchidById(long orchidId) throws SQLException {
		Log.d(Constants.TAG, "Getting an orchid by Id");
		Cursor mCursor = db.query(true, MyOrchidsTable.TABLE_NAME, allColumns,
				MyOrchidsTable.COLUMN_NAME_ORCHID_ID + "=" + orchidId, null,
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// Updates an orchid
	public boolean updateOrchid(long orchidId, String orchidName,
			String orchidType, String wateredDate, String fertilizedDate,
			String outsideState, String dayTemp, String nightTemp) {
		Log.d(Constants.TAG, "updating an orchid");
		ContentValues args = new ContentValues();
		args.put(MyOrchidsTable.COLUMN_NAME_ORCHID_NAME, orchidName);
		args.put(MyOrchidsTable.COLUMN_NAME_ORCHID_TYPE, orchidType.toString());
		args.put(MyOrchidsTable.COLUMN_NAME_ORCHID_LAST_WATERING_DATE,
				wateredDate);
		args.put(MyOrchidsTable.COLUMN_NAME_ORCHID_LAST_FERTILIZING_DATE,
				fertilizedDate);
		args.put(MyOrchidsTable.COLUMN_NAME_ORCHID_OUTSIDE_STATE,
				outsideState.toString());
		args.put(MyOrchidsTable.COLUMN_NAME_DAY_TEMP, dayTemp.toString());
		args.put(MyOrchidsTable.COLUMN_NAME_NIGHT_TEMP, nightTemp.toString());

		return db.update(MyOrchidsTable.TABLE_NAME, args,
				MyOrchidsTable.COLUMN_NAME_ORCHID_ID + "=" + orchidId, null) > 0;
	}
}
