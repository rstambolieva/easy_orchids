package com.example.easyorchids;

import android.provider.BaseColumns;

/**
 * 
 * Container for constants that define names for URIs, tables, and columns for
 * the sqlite db for the easy orchids app. All constants are global, exh table
 * should enumerate its constants
 *
 */
public final class MyOrchidsContract {
	
	// To prevent someone from accidentally instantiating the contract class,
	// give it an empty constructor.
	public MyOrchidsContract() {
	}

	// Class that defines MyOrchids table
	public static abstract class MyOrchidsTable implements BaseColumns {
		public static final String TABLE_NAME = "MyOrchids";
//		public static final String COLUMN_NAME_ORCHID_ID = "orchidid";
		public static final String COLUMN_NAME_ORCHID_NAME = "name";
		// public static final String COLUMN_NAME_ORCHID_PICTURE = "picture";
		// TODO: add support for pictures
		public static final String COLUMN_NAME_ORCHID_TYPE = "type";
		public static final String COLUMN_NAME_ORCHID_LAST_WATERING_DATE = "last_watering";
		public static final String COLUMN_NAME_ORCHID_LAST_FERTILIZING_DATE = "last_fertilizing";
		public static final String COLUMN_NAME_ORCHID_OUTSIDE_STATE = "is_outside";
	}
}
