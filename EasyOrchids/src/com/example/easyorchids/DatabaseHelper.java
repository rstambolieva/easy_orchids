package com.example.easyorchids;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.easyorchids.MyOrchidsContract.MyOrchidsTable;

/**
 * 
 * This class provides the CRUD operations on the DB as well as creating,
 * upgrading and deleting the database.
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	// Path to the place where the DB will be copied on the android device
	private static String DATABASE_PATH = "/data/data/com.example.easyorchids/databases/";
	private static final String TAG_DB = "DBAdapter";
	private static final String DATABASE_NAME = "easy_orchids.db";
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
			+ COMMA_SEP + MyOrchidsTable.PICTURE_PATH + TEXT_TYPE + ")";

	private static final String SQL_DELETE_ORCHIDS_TABLE = "DROP TABLE IF EXISTS "
			+ MyOrchidsTable.TABLE_NAME;
	// static to be accessed by the inner class
	private final Context context;
	// static to be accessed by the inner class
	private SQLiteDatabase db;
	private String[] allColumns = { MyOrchidsTable.COLUMN_NAME_ORCHID_ID,
			MyOrchidsTable.COLUMN_NAME_ORCHID_NAME,
			MyOrchidsTable.COLUMN_NAME_ORCHID_TYPE,
			MyOrchidsTable.COLUMN_NAME_ORCHID_LAST_WATERING_DATE,
			MyOrchidsTable.COLUMN_NAME_ORCHID_LAST_FERTILIZING_DATE,
			MyOrchidsTable.COLUMN_NAME_ORCHID_OUTSIDE_STATE,
			MyOrchidsTable.PICTURE_PATH };

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			// By calling this method and empty database will be created
			// into the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	// Оpens the database
	public SQLiteDatabase openDB() throws SQLException {
		// Log.d(Constants.TAG, "Opening DB");
		//
		String myPath = DATABASE_PATH + DATABASE_NAME;
		db = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
		return db;
	}

	/**
	 * Check if the database exists to avoid re-copying the file each time you
	 * open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;
		try {
			String myPath = DATABASE_PATH + DATABASE_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {
			// database does't exist yet.
			Log.d(Constants.TAG,
					"Myorchids DB doesn't exist. We must create it.");
		}

		if (checkDB != null) {
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transferring bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = context.getAssets().open(DATABASE_NAME);

		// Path to the just created empty db
		String outFileName = DATABASE_PATH + DATABASE_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	@Override
	public synchronized void close() {

		if (db != null)
			db.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Log.d(Constants.TAG, "Creating DB");
		// db.execSQL(SQL_CREATE_ORCHIDS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Log.w(TAG_DB, "Upgrading database from version " + oldVersion +
		// " to "
		// + newVersion + ", which will destroy all old data");
		// db.execSQL(SQL_DELETE_ORCHIDS_TABLE);
		// onCreate(db);
	}

	// Drop the DB
	public void dropOrchidsTable(SQLiteDatabase db) {
		Log.d(Constants.TAG, "Dropping the orchids table");
		db.execSQL(SQL_DELETE_ORCHIDS_TABLE);
	}

	// Inserts an orchid into the database
	public long insertOrchid(SQLiteDatabase db, String orchidName,
			String orchidType, String wateredDate, String fertilizedDate,
			String outsideState, String picturePath) {
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
		initialValues.put(MyOrchidsTable.PICTURE_PATH, picturePath);
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
			String outsideState, String dayTemp, String nightTemp,
			String picturePath) {
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

		args.put(MyOrchidsTable.PICTURE_PATH, picturePath.toString());

		return db.update(MyOrchidsTable.TABLE_NAME, args,
				MyOrchidsTable.COLUMN_NAME_ORCHID_ID + "=" + orchidId, null) > 0;
	}

}
